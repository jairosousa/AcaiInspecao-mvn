/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.rn;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.dao.InspecaoDAOImpl;
import br.com.ufra.entidade.Inspecao;
import br.com.ufra.entidade.Vistoria;
import java.util.List;

/**
 *
 * @author Jairo
 */
public class InspecaoRN {
    
    private InspecaoDAOImpl dao = new InspecaoDAOImpl();
    
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
        if (inspecao.getDataInsp() == null) {
            return false;
        } else {
            if (inspecao.getId() == null || inspecao.getId() == 0) {
                return dao.criar(inspecao);
            } else {
                return dao.atualizar(inspecao);
            }
        }
    }
    
       public boolean salvarInspecaoApartirInspecoes(Vistoria vistoria, List<Inspecao> inspecoes){
           
          return dao.salvarInspecaoApartirInspecoes(vistoria, inspecoes);
    }
    
    public boolean excluir(Inspecao inspecao) {
        if (inspecao.getDataInsp() == null) {
            return false;
        } else {
            return dao.excluir(inspecao);
        }
    }
}
