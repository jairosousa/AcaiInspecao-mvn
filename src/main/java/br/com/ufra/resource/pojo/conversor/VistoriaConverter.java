/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.resource.pojo.conversor;

import br.com.ufra.entidade.Vistoria;
import br.com.ufra.resource.pojo.VistoriaPOJO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author geovane
 */
public class VistoriaConverter {
    private static final VistoriaConverter uniqueInstance = new VistoriaConverter();

    private VistoriaConverter() {

    }

    public static VistoriaConverter getInstance() {
        return uniqueInstance;
    }

    public static VistoriaPOJO toVistoriaPOJO(Vistoria vistoria) {
        if (vistoria != null) {

            VistoriaPOJO resposta = new VistoriaPOJO();

            resposta.setId(vistoria.getId());
            resposta.setApto(vistoria.getApto());
            resposta.setDataSolicitacao(vistoria.getDataSolicitacao().getTime());
            resposta.setDataVistoria(vistoria.getDataVistoria().getTime());
            resposta.setEstabelecimentoPOJO(EstabelecimentoConverter.toEstabelecimentoPOJO(vistoria.getEstabelecimento()));
            resposta.setObservacao(vistoria.getObservacao());
            resposta.setPrazo(vistoria.getPrazo());
            resposta.setTecnicoPOJO1(TecnicoConverter.toTecnicoPOJO(vistoria.getTecnico1()));
            resposta.setTecnicoPOJO2(TecnicoConverter.toTecnicoPOJO(vistoria.getTecnico2()));          
            return resposta;

        } else {
            return null;
        }
    }

    public static Vistoria fromVistoriaPOJO(VistoriaPOJO vistoriaPOJO) {
        if (vistoriaPOJO != null) {
            Vistoria resposta = new Vistoria();

            resposta.setId(vistoriaPOJO.getId());
            resposta.setApto(vistoriaPOJO.getApto());
            resposta.setDataSolicitacao(new Date(vistoriaPOJO.getDataSolicitacao()));
            resposta.setDataVistoria(new Date(vistoriaPOJO.getDataVistoria()));
            resposta.setEstabelecimento(EstabelecimentoConverter.fromEstabelecimentoPOJO(vistoriaPOJO.getEstabelecimentoPOJO()));
            resposta.setObservacao(vistoriaPOJO.getObservacao());
            resposta.setPrazo(vistoriaPOJO.getPrazo());
            resposta.setTecnico1(TecnicoConverter.fromTecnicoPOJO(vistoriaPOJO.getTecnicoPOJO1()));
            resposta.setTecnico2(TecnicoConverter.fromTecnicoPOJO(vistoriaPOJO.getTecnicoPOJO2()));          
            
            return resposta;

        } else {
            return null;
        }
    }

    public static List<VistoriaPOJO> toVistoriasPOJO(List<Vistoria> vistorias) {
        if (vistorias != null) {

            ArrayList<VistoriaPOJO> resposta = new ArrayList<>();

            vistorias.stream().forEach((vistoria) -> {
                resposta.add(toVistoriaPOJO(vistoria));
            });

            return resposta;
        } else {
            return null;
        }

    }

    public static List<Vistoria> fromVistoriasPOJO(List<VistoriaPOJO> vistoriasPOJO) {
        if (vistoriasPOJO != null) {

            ArrayList<Vistoria> resposta = new ArrayList<>();

            vistoriasPOJO.stream().forEach((vistoriaPOJO) -> {
                resposta.add(fromVistoriaPOJO(vistoriaPOJO));
            });

            return resposta;
        } else {
            return null;
        }
    }
}
