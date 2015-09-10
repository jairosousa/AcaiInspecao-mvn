
package br.com.ufra.resource.pojo.conversor;

import br.com.ufra.entidade.Inspecao;
import br.com.ufra.resource.pojo.InspecaoPOJO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InspecaoConverter {
      private static final InspecaoConverter uniqueInstance = new InspecaoConverter();

    private InspecaoConverter() {

    }

    public static InspecaoConverter getInstance() {
        return uniqueInstance;
    }

    public static InspecaoPOJO toInspecaoPOJO(Inspecao inspecao) {
        if (inspecao != null) {

            InspecaoPOJO resposta = new InspecaoPOJO();

            resposta.setId(inspecao.getId());
            resposta.setVistoriaPOJO(VistoriaConverter.toVistoriaPOJO(inspecao.getVistoria()));
            resposta.setAptoPOJO(inspecao.getApto());
            resposta.setDataInspPOJO(inspecao.getDataInsp().getTime());
            resposta.setEquipamentoPOJO(EquipamentoConverter.toEquipamentoPOJO(inspecao.getEquipamento()));
            resposta.setObservacaoPOJO(inspecao.getObservacao());
                
            return resposta;

        } else {
            return null;
        }
    }

    public static Inspecao fromInspecaoPOJO(InspecaoPOJO inspecaoPOJO) {
        if (inspecaoPOJO != null) {
            Inspecao resposta = new Inspecao();

            resposta.setId(inspecaoPOJO.getId());
            resposta.setVistoria(VistoriaConverter.fromVistoriaPOJO(inspecaoPOJO.getVistoriaPOJO()));
            resposta.setApto(inspecaoPOJO.isAptoPOJO());
            resposta.setDataInsp(new Date(inspecaoPOJO.getDataInspPOJO()));
            resposta.setEquipamento(EquipamentoConverter.fromEquipamentoPOJO(inspecaoPOJO.getEquipamentoPOJO()));
            resposta.setObservacao(inspecaoPOJO.getObservacaoPOJO());

            return resposta;

        } else {
            return null;
        }
    }

    public static List<InspecaoPOJO> toInspecoesPOJO(List<Inspecao> inspecoes) {
        if (inspecoes != null) {

            ArrayList<InspecaoPOJO> resposta = new ArrayList<>();

            inspecoes.stream().forEach((inspecao) -> {
                resposta.add(toInspecaoPOJO(inspecao));
            });

            return resposta;
        } else {
            return null;
        }

    }

    public static List<Inspecao> fromInspecoesPOJO(List<InspecaoPOJO> inspecoesPOJO) {
        if (inspecoesPOJO != null) {

            ArrayList<Inspecao> resposta = new ArrayList<>();

            inspecoesPOJO.stream().forEach((inspecaoPOJO) -> {
                resposta.add(fromInspecaoPOJO(inspecaoPOJO));
            });

            return resposta;
        } else {
            return null;
        }
    }
}
