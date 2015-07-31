/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.rn;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.entidade.Vistoria;
import java.util.List;

/**
 *
 * @author Jairo
 */
public class VistoriaRN {
    
    private GenericDAOImpl<Vistoria> dao = new GenericDAOImpl<Vistoria>();
    
    public Vistoria obter(Integer id) {
        if (id == null) {
            return null;
        } else {
            return dao.obter(Vistoria.class, id);
        }
    }

    public List<Vistoria> obterTodos() {
        return dao.obterTodos(Vistoria.class);
    }
    
    public boolean salvar(Vistoria vistoria) {
        if (vistoria.getDataVistoria().equals(null)) {
            return false;
        } else {
            if (vistoria.getId() == null) {
                return dao.criar(vistoria);
            } else {
                return dao.atualizar(vistoria);
            }
        }
    }

    public boolean excluir(Vistoria vistoria) {
        if (vistoria.getDataVistoria().equals(null)) {
            return false;
        } else {
            return dao.excluir(vistoria);
        }
    }
    
}
