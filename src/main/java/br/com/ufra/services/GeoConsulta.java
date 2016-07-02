/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.services;

import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.rn.EstabelecimentoRN;
import java.io.Serializable;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.*;

/**
 *
 * @author BPMLAB
 */
@ManagedBean
@ViewScoped
public class GeoConsulta implements Serializable {

    private static final long serialVersionUID = 1L;

    EstabelecimentoRN RN = new EstabelecimentoRN();

    private MapModel mapModel;

    private Marker marker;

    private List<Estabelecimento> estabelecimentos = new ArrayList<>();
    private List<LatLng> coordenadas = new ArrayList<>();

    @PostConstruct
    public void init() {
        mapModel = new DefaultMapModel();

        double lat = 0, lon = 0;

        estabelecimentos = RN.obterTodos();

        //Shared coordinates
        LatLng coord1 = new LatLng(lat, lon);

        for (int i = 0; i < estabelecimentos.size(); i++) {
            coordenadas.add(new LatLng(Double.parseDouble(estabelecimentos.get(i).getLatitude()), Double.parseDouble(estabelecimentos.get(i).getLongitude())));

        }
        
        for (int j = 0; j < estabelecimentos.size(); j++) {
            mapModel.addOverlay(new Marker(coordenadas.get(j), estabelecimentos.get(j).getNomeFantasia()));
        }

//        LatLng coord2 = new LatLng(Double.parseDouble(estabelecimentos.get(1).getLatitude()), Double.parseDouble(estabelecimentos.get(1).getLongitude()));
//        LatLng coord3 = new LatLng(Double.parseDouble(estabelecimentos.get(2).getLatitude()), Double.parseDouble(estabelecimentos.get(2).getLongitude()));
//        LatLng coord4 = new LatLng(Double.parseDouble(estabelecimentos.get(3).getLatitude()), Double.parseDouble(estabelecimentos.get(3).getLongitude()));
        //Draggable
//        mapModel.addOverlay(new Marker(coord1, "Konyaalti"));
//        mapModel.addOverlay(new Marker(coord2, "Ataturk Parki"));
//        mapModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
//        mapModel.addOverlay(new Marker(coord4, "Kaleici"));

//        for (Marker premarker : mapModel.getMarkers()) {
//            premarker.setDraggable(true);
//        }
    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public Marker getMarker() {
        return marker;
    }

    public List<LatLng> getCoordenadas() {
        return coordenadas;
    }

    public List<Estabelecimento> getEstabelecimentos() {
        return estabelecimentos;
    }

    public void onMarkerDrag(MarkerDragEvent event) {
        marker = event.getMarker();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
    }

}
