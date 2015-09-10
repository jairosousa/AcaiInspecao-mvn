/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.rn;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.dao.TecnicoDAOImpl;
import br.com.ufra.entidade.Tecnico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jairo
 */
public class TecnicoRN {

    private TecnicoDAOImpl dao = new TecnicoDAOImpl();

    public Tecnico obter(Integer id) {
        if (id == null) {
            return null;
        } else {
            return dao.obter(Tecnico.class, id);
        }
    }

    public List<Tecnico> obterTodos() {
        return dao.obterTodos(Tecnico.class);
    }

    public List<Tecnico> obterTecnicos(String busca) {
        if (busca == null || busca.length() < 3) {
            return null;
        } else {
            List<Tecnico> resposta = new ArrayList<Tecnico>();
            for (Tecnico tecnico : obterTodos()) {
                if (tecnico.getNome().toUpperCase().indexOf(busca.toUpperCase()) >= 0) {
                    resposta.add(tecnico);
                }
            }
            return resposta;
        }

    }

    public boolean comparaeSenha(Tecnico tecnico) {
        if (tecnico.getSenha().equals(tecnico.getConfirmeSenha())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean salvar(Tecnico tecnico) {
        if (tecnico.getNome().equals("")) {
            return false;
        } else {
            if (tecnico.getId() == null) {
                return dao.criar(tecnico);
            } else {
                return dao.atualizar(tecnico);
            }
        }
    }

    public boolean excluir(Tecnico tecnico) {
        if (tecnico.getId() == null) {
            return false;
        } else {
            return dao.excluir(tecnico);
        }
    }

    public Tecnico obterEmail(String email) {
        return dao.obterEmail(email);
    }

    public boolean existe(String email) {
        return dao.existe(email);
    }
    
    public List<Tecnico> obterTecnicosPorMatricula(String mat1, String mat2){
        return dao.obterTecnicosPorMatricula(mat1, mat2);
    }
}
