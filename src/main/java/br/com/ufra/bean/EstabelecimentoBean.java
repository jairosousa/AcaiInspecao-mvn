/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.bean;

import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.geocoding.services.GoogleGeocodingService;
import br.com.ufra.rn.EstabelecimentoRN;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Jairo
 */
@ManagedBean
@SessionScoped
public class EstabelecimentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private MapModel draggableModel;

    private Marker marker;

    private LatLng center;

    private Estabelecimento estabelecimento;
    private EstabelecimentoRN rn = new EstabelecimentoRN();
    private List<Estabelecimento> estabelecimentos;
    private List<Estabelecimento> estabelecimentosPendentesVistoria;

    @PostConstruct
    public void init() {
        estabelecimento = new Estabelecimento();
        draggableModel = new DefaultMapModel();
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

    public List<Estabelecimento> getEstabelecimentosPendentesVistoria() {
        return estabelecimentosPendentesVistoria = rn.obterTodosPendenteVistoria();
    }

    public MapModel getDraggableModel() {
        return draggableModel;
    }

    public void onMarkerDrag(MarkerDragEvent event) {
        marker = event.getMarker();
        this.estabelecimento.setLatitude(String.valueOf(marker.getLatlng().getLat()));
        this.estabelecimento.setLongitude(String.valueOf(marker.getLatlng().getLng()));
    }

    public void obterCoordenadas() {

        GoogleGeocodingService geocodingService = new GoogleGeocodingService();

        String endereco = this.estabelecimento.getLogradouro() + ", " + this.estabelecimento.getNumero() + " "
                + this.estabelecimento.getCidade() + " - " + this.estabelecimento.getUf();

        System.out.println(endereco);

        geocodingService.obterCoordenada(endereco);

        this.estabelecimento.setLatitude(geocodingService.getLatitude());
        this.estabelecimento.setLongitude(geocodingService.getLongitude());

        draggableModel = new DefaultMapModel();
        center = new LatLng(Double.parseDouble(geocodingService.getLatitude()), Double.parseDouble(geocodingService.getLongitude()));
        draggableModel.addOverlay(new Marker(center, endereco));

        for (Marker premarket : draggableModel.getMarkers()) {
            premarket.setDraggable(true);
        }
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
