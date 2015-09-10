/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.dao.service;

import br.com.ufra.entidade.Tecnico;
import java.util.List;

/**
 *
 * @author Jairo
 */
public interface TecnicoDAO extends GenericDAO<Tecnico>{
    
    public Tecnico obterEmail(String email);
    public boolean existe(String email);
    public List<Tecnico> obterTecnicosPorMatricula(String mat1, String mat2);

    
}
