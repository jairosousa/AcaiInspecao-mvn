/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.dao.service;

import br.com.ufra.entidade.Inspecao;
import br.com.ufra.entidade.Vistoria;
import java.util.List;

/**
 *
 * @author geovane
 */
public interface InspecaoDAO extends GenericDAO<Inspecao>{
    public boolean salvarInspecaoApartirInspecoes (Vistoria vistoria, List<Inspecao> inspecoes);
}
