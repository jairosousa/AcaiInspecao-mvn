
package br.com.ufra.resource.pojo.conversor;

import br.com.ufra.entidade.Inspecao;
import br.com.ufra.resource.pojo.InspecaoPOJO;
import java.util.ArrayList;
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
            resposta.setApto(inspecao.getApto());
            resposta.setDataInsp(inspecao.getDataInsp());
            resposta.setEquipamentoPOJO(EquipamentoConverter.toEquipamentoPOJO(inspecao.getEquipamento()));
            resposta.setObservacao(inspecao.getObservacao());

            return resposta;

        } else {
            return null;
        }
    }

    public static Inspecao fromInspecaoPOJO(InspecaoPOJO inspecaoPOJO) {
        if (inspecaoPOJO != null) {
            Inspecao resposta = new Inspecao();

            resposta.setId(inspecaoPOJO.getId());
            resposta.setApto(inspecaoPOJO.getApto());
            resposta.setDataInsp(inspecaoPOJO.getDataInsp());
            resposta.setEquipamento(EquipamentoConverter.fromEquipamentoPOJO(inspecaoPOJO.getEquipamentoPOJO()));
            resposta.setObservacao(inspecaoPOJO.getObservacao());

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
