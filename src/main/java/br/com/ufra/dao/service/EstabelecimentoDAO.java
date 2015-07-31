/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.dao.service;

import br.com.ufra.entidade.Estabelecimento;
import java.util.Date;

/**
 *
 * @author Jairo
 */
public interface EstabelecimentoDAO extends GenericDAO<Estabelecimento>{
    
    public Date atualizaDataVencimento(Estabelecimento estabelecimento);
    
    public Estabelecimento obterCep(Estabelecimento estabelecimento);
    
}
