/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ufra.dao.service;

import java.util.List;

/**
 *
 * @author Jairo
 */
public interface GenericDAO<T> {
    
    public boolean criar(T o);
    public boolean atualizar(T o);
    public boolean excluir(T o);
    public T obter(Class<T> classe, Object id);
    public List<T> obterTodos(Class<T> classe);
}
