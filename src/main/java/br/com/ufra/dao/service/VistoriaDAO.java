/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.dao.service;

import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.entidade.Vistoria;
import java.util.List;

/**
 *
 * @author Jairo
 */
public interface VistoriaDAO extends GenericDAO<Vistoria> {
    
    public List<Vistoria> obterVistoriasComEstabelecimento();
    
    public List<Vistoria> obterVistoriasPorEstabelecimento(Estabelecimento e);
}
