/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.resource.pojo.conversor;

import br.com.ufra.entidade.Equipamento;
import br.com.ufra.resource.pojo.EquipamentoPOJO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author geovane
 */
public class EquipamentoConverter {

    private static final EquipamentoConverter uniqueInstance = new EquipamentoConverter();

    private EquipamentoConverter() {

    }

    public static EquipamentoConverter getInstance() {
        return uniqueInstance;
    }

    public static EquipamentoPOJO toEquipamentoPOJO(Equipamento equipamento) {
        if (equipamento != null) {

            EquipamentoPOJO resposta = new EquipamentoPOJO();

            resposta.setId(equipamento.getId());
            resposta.setMaterial(equipamento.getMaterial());
            resposta.setDescricao(equipamento.getDescricao());
            resposta.setNome(equipamento.getNome());
            resposta.setStatus(equipamento.getStatus());

            return resposta;

        } else {
            return null;
        }
    }

    public static Equipamento fromEquipamentoPOJO(EquipamentoPOJO equipamentoPOJO) {
        if (equipamentoPOJO != null) {
            Equipamento resposta = new Equipamento();

            resposta.setId(equipamentoPOJO.getId());
            resposta.setMaterial(equipamentoPOJO.getMaterial());
            resposta.setDescricao(equipamentoPOJO.getDescricao());
            resposta.setNome(equipamentoPOJO.getNome());
            resposta.setStatus(equipamentoPOJO.getStatus());

            return resposta;

        } else {
            return null;
        }
    }

    public static List<EquipamentoPOJO> toEquipamentosPOJO(List<Equipamento> equipamentos) {
        if (equipamentos != null) {

            ArrayList<EquipamentoPOJO> resposta = new ArrayList<>();

            equipamentos.stream().forEach((equipamento) -> {
                resposta.add(toEquipamentoPOJO(equipamento));
            });

            return resposta;
        } else {
            return null;
        }

    }

    public static List<Equipamento> fromEquipamentosPOJO(List<EquipamentoPOJO> equipamentosPOJO) {
        if (equipamentosPOJO != null) {

            ArrayList<Equipamento> resposta = new ArrayList<>();

            equipamentosPOJO.stream().forEach((equipamentoPOJO) -> {
                resposta.add(fromEquipamentoPOJO(equipamentoPOJO));
            });

            return resposta;
        } else {
            return null;
        }
    }
}
