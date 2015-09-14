/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.bean;

import br.com.ufra.cep.service.CepWebService;
import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.geocoding.services.GoogleGeocodingService;
import br.com.ufra.rn.EstabelecimentoRN;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Jairo
 */
@ManagedBean
@RequestScoped
public class EstabelecimentoBean implements Serializable {

    private MapModel geoModel;

    private String centerGeoMap = "-1.2965754,-48.4652863";

    private LatLng center;

    private Estabelecimento estabelecimento;
    private EstabelecimentoRN rn = new EstabelecimentoRN();
    private List<Estabelecimento> estabelecimentos;

    @PostConstruct
    public void init() {
        geoModel = new DefaultMapModel();
        estabelecimento = new Estabelecimento();
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<Estabelecimento> getEstabelecimentos() {
        return estabelecimentos = rn.obterTodos();
    }

    public MapModel getGeoModel() {
        return geoModel;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();

        if (results != null && !results.isEmpty()) {
            this.center = results.get(0).getLatLng();
            centerGeoMap = this.center.getLat() + "," + this.center.getLng();

            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }

        }
        this.estabelecimento.setLatitude(String.valueOf(center.getLat()));
        this.estabelecimento.setLongitude(String.valueOf(center.getLng()));

        System.out.println("Latitude: " + String.valueOf(center.getLat()));
        System.out.println("Longitude: " + String.valueOf(center.getLng()));
    }

    public void obterCoordenadas() {

        GoogleGeocodingService geocodingService = new GoogleGeocodingService();

        String endereco = this.estabelecimento.getLogradouro() + ", " + this.estabelecimento.getNumero() + " "
                + this.estabelecimento.getCidade() + " - " + this.estabelecimento.getUf();

        System.out.println(endereco);

        geocodingService.obterCoordenada(endereco);

        this.estabelecimento.setLatitude(geocodingService.getLatitude());
        this.estabelecimento.setLongitude(geocodingService.getLongitude());

        System.out.println("Latitude" + estabelecimento.getLatitude());
        System.out.println("Longitude" + estabelecimento.getLongitude());
    }

    public void consultarCEP() {
        rn.obterCep(estabelecimento);
    }

    public String salvar() {
       
        if (rn.salvar(this.estabelecimento)) {
            FacesMessage fm = null;
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro feito com Sucesso");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "Erro no Cadastro!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";

        }
    }

    public String excluir() {
        if (rn.excluir(estabelecimento)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro foi excluído com exito");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "O cadastro não foi excluído!!!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";
        }
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selecionada", format.format(event.getObject())));
    }

    public void atualizaDataVencimento() {

        estabelecimento.setDataVencimento(rn.atualizaDataVencimento(estabelecimento));

    }

    public String editar() {
        return "formulario.xhtml";
    }

    public String incluir() {
        return "formulario.xhtml";
    }

    public String cancelar() {
        return "lista.xhtml";
    }

}
