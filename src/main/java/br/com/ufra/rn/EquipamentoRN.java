/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ufra.rn;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.entidade.Equipamento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jairo
 */
public class EquipamentoRN {
    
     private GenericDAOImpl<Equipamento> dao = new GenericDAOImpl<Equipamento>();
     
     public Equipamento obter(Integer id) {
        if (id == null) {
            return null;
        } else {
            return dao.obter(Equipamento.class, id);
        }
    }
     
     public List<Equipamento> obterTodos() {
        return dao.obterTodos(Equipamento.class);
    }

    public List<Equipamento> obterEquipamento(String busca) {
        if (busca == null || busca.length() < 3) {
            return null;
        } else {
            List<Equipamento> resposta = new ArrayList<Equipamento>();
            for (Equipamento equipamento : obterTodos()) {
                if (equipamento.getNome().toUpperCase().indexOf(busca.toUpperCase()) >= 0) {
                    resposta.add(equipamento);
                }
            }
            return resposta;
        }

    }
    
    public boolean salvar(Equipamento equipamento) {
        if (equipamento.getNome().equals("")) {
            return false;
        } else {
            if (equipamento.getId() == null) {
                return dao.criar(equipamento);
            } else {
                return dao.atualizar(equipamento);
            }
        }
    }

    public boolean excluir(Equipamento equipamento) {
        if (equipamento.getId() == null) {
            return false;
        } else {
            return dao.excluir(equipamento);
        }
    }
    
}
