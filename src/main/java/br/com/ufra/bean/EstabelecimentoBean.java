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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
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
@ViewScoped
public class EstabelecimentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private MapModel draggableModel;
    private Marker marker;
    private LatLng center;

    private Estabelecimento estabelecimento;
    private EstabelecimentoRN RN = new EstabelecimentoRN();

    private List<Estabelecimento> estabelecimentos;
    private List<Estabelecimento> estabelecimentosRegulares = new ArrayList<>();
    private List<Estabelecimento> estabelecimentosPendentes = new ArrayList<>();
    private List<Estabelecimento> estabelecimentosVencida = new ArrayList<>();
    private List<String> bairros = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.estabelecimento = new Estabelecimento();
        this.estabelecimento.setStatus("Aguardando vistoria");

        draggableModel = new DefaultMapModel();

        try {
            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("id")) {
                Integer id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id").toString());
                this.estabelecimento = RN.obter(id);
                System.out.println("estabelecimento id: " + estabelecimento.getId() + "\nestabelecimento nome: " + estabelecimento.getNomeFantasia());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("id");
            }
        } catch (Exception e) {
        }
    }

    public void onMarkerDrag(MarkerDragEvent event) {
        marker = event.getMarker();
        this.estabelecimento.setLatitude(String.valueOf(marker.getLatlng().getLat()));
        this.estabelecimento.setLongitude(String.valueOf(marker.getLatlng().getLng()));
    }

    public void consultar() {
        this.estabelecimentos = RN.obterTodos();
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

        if (RN.salvar(this.estabelecimento)) {
            consultar();
            FacesMessage fm = null;
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro feito com Sucesso");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista";

        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "Erro no Cadastro!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista";
        }
    }

    public void excluir() {
        if (RN.excluir(estabelecimento)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro foi excluído com exito");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            consultar();
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "O cadastro não foi excluído!!!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            consultar();
        }
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selecionada", format.format(event.getObject())));
    }

    public void atualizaDataVencimento() {
        estabelecimento.setDataVencimento(RN.atualizaDataVencimento(estabelecimento));
    }

    public String editar() {
        Integer id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", id);

        System.out.println("estabelecimento id: " + id);
        return "/pages/estabelecimento/formulario?faces-redirect=true";
    }

    public String editarStatus() {
        Integer id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", id);

        System.out.println("estabelecimento id: " + id);
        return "/pages/estabelecimento/formularioStatus?faces-redirect=true";
    }

    public String incluir() {
        return "formulario.xhtml";
    }

    public String cancelar() {
        return "/index?faces-redirect=true";
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<Estabelecimento> getEstabelecimentos() {
        return estabelecimentos = RN.obterTodos();
    }

    public List<Estabelecimento> getEstabelecimentosRegulares() {
        return estabelecimentosRegulares = RN.obterEstabelecimentosRegulares();
    }

    public List<Estabelecimento> getEstabelecimentosPendentes() {
        return estabelecimentosPendentes = RN.obterEstabelecimentosPendentes();
    }

    public List<Estabelecimento> getEstabelecimentosVencida() {
        return estabelecimentosVencida = RN.obterEstabelecimentoVencido();
    }

    public MapModel getDraggableModel() {
        return draggableModel;
    }

    public List<String> getBairros() {
        this.bairros.add("Águas Lindas");
        this.bairros.add("Aurá");
        this.bairros.add("Barreiro");
        this.bairros.add("Batista Campos");
        this.bairros.add("Cabanagem");
        this.bairros.add("Canudos");
        this.bairros.add("Castanheira");
        this.bairros.add("Ciadade Velha");
        this.bairros.add("Condor");
        this.bairros.add("Coqueiro");
        this.bairros.add("Cremação");
        this.bairros.add("Curió-Utinga");
        this.bairros.add("Fatima");
        this.bairros.add("Guamá");
        this.bairros.add("Guanabara");
        this.bairros.add("Icoaraci");
        this.bairros.add("Ilha de Cintra");
        this.bairros.add("Ilha de Cotijuba");
        this.bairros.add("Ilha do Combú");
        this.bairros.add("Ilha do Fortinho");
        this.bairros.add("Ilha do Jutuba");
        this.bairros.add("Ilha do Murucutu");
        this.bairros.add("Ilha dos Patos");
        this.bairros.add("Ilha Grande");
        this.bairros.add("Ilha Negra");
        this.bairros.add("Jurunas");
        this.bairros.add("Mangueirão");
        this.bairros.add("Marambaia");
        this.bairros.add("Marancagalha");
        this.bairros.add("Marco");
        this.bairros.add("Montese");
        this.bairros.add("Mosqueiro");
        this.bairros.add("Nazaré");
        this.bairros.add("Outeiro");
        this.bairros.add("Parque Guajará");
        this.bairros.add("Parque Verde");
        this.bairros.add("Pedreira");
        this.bairros.add("Pratinha");
        this.bairros.add("Reduto");
        this.bairros.add("Sacramenta");
        this.bairros.add("São Brás");
        this.bairros.add("Souza");
        this.bairros.add("Tapanã");
        this.bairros.add("Telegrafo");
        this.bairros.add("Tenoné");
        this.bairros.add("Umarizal");
        this.bairros.add("Una");
        this.bairros.add("Val-de-Cães");

        Collections.sort(bairros);

        return bairros;
    }

}
