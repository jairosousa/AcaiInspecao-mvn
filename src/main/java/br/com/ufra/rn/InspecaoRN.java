/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.rn;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.entidade.Inspecao;
import java.util.List;

/**
 *
 * @author Jairo
 */
public class InspecaoRN {
    
    private GenericDAOImpl<Inspecao> dao = new GenericDAOImpl<Inspecao>();
    
    public Inspecao obter(Integer id) {
        if (id == null) {
            return null;
        } else {
            return dao.obter(Inspecao.class, id);
        }
    }

    public List<Inspecao> obterTodos() {
        return dao.obterTodos(Inspecao.class);
    }
    
    public boolean salvar(Inspecao inspecao) {
        if (inspecao.getDataInsp().equals(null)) {
            return false;
        } else {
            if (inspecao.getId() == null) {
                return dao.criar(inspecao);
            } else {
                return dao.atualizar(inspecao);
            }
        }
    }
    
    public boolean excluir(Inspecao inspecao) {
        if (inspecao.getDataInsp().equals(null)) {
            return false;
        } else {
            return dao.excluir(inspecao);
        }
    }
}
