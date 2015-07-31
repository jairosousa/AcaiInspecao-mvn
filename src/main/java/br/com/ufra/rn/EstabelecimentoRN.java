/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.rn;

import br.com.ufra.dao.EstabelecimentoDAOImpl;
import br.com.ufra.entidade.Estabelecimento;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jairo
 */
public class EstabelecimentoRN {

    private EstabelecimentoDAOImpl dao = new EstabelecimentoDAOImpl();

    public Estabelecimento obter(Integer id) {
        if (id == null) {
            return null;
        } else {
            return dao.obter(Estabelecimento.class, id);
        }
    }

    public List<Estabelecimento> obterTodos() {
        return dao.obterTodos(Estabelecimento.class);
    }
    

    public List<Estabelecimento> obterEstabelecimento(String busca) {
        if (busca == null || busca.length() < 3) {
            return null;
        } else {
            List<Estabelecimento> resposta = new ArrayList<Estabelecimento>();
            for (Estabelecimento estabelecimento : obterTodos()) {
                if (estabelecimento.getNomeContato().toUpperCase().indexOf(busca.toUpperCase()) >= 0) {
                    resposta.add(estabelecimento);
                }
            }
            return resposta;
        }

    }
    
    public Estabelecimento obterCep(Estabelecimento estabelecimento){
        return dao.obterCep(estabelecimento);
    }

    public boolean salvar(Estabelecimento estabelecimento) {
        if (estabelecimento.getNomeContato().equals("")) {
            return false;
        } else {
            if (estabelecimento.getId() == null) {
                return dao.criar(estabelecimento);
            } else {
                return dao.atualizar(estabelecimento);
            }
        }
    }

    public boolean excluir(Estabelecimento estabelecimento) {
        if (estabelecimento.getId() == null) {
            return false;
        } else {
            return dao.excluir(estabelecimento);
        }
    }

    public Date atualizaDataVencimento(Estabelecimento estabelecimento) {
        if (estabelecimento.getDataLicenca() != null) {
            return dao.atualizaDataVencimento(estabelecimento);

        } else {
            return null;
        }
    }

}
