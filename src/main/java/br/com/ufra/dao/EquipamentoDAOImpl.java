/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.dao;

import br.com.ufra.dao.service.EquipamentoDAO;
import br.com.ufra.dao.service.GenericDAO;
import br.com.ufra.entidade.Equipamento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jairo Sousa
 * @since 10/2015
 */
public class EquipamentoDAOImpl extends GenericDAOImpl<Equipamento> implements EquipamentoDAO {

    private final GenericDAO<Equipamento> dao = FabricaDAO.criarGenericDAO();
    private List<Equipamento> todos = dao.obterTodos(Equipamento.class);

    @Override
    public List<Equipamento> obterTodosObrigatorios() {

        List<Equipamento> todosObrigatorios = new ArrayList<>();

        for (Equipamento equipamentoObrigatorio : todos) {
            if (equipamentoObrigatorio.getStatus().equals("Obrigatorio")) {
                todosObrigatorios.add(equipamentoObrigatorio);
            }

        }
        return todosObrigatorios;
    }

    @Override
    public List<Equipamento> obterTodosNaoObrigatorios() {
        List<Equipamento> todosNaoObrigatorios = new ArrayList<>();

        for (Equipamento equipamentoObrigatorio : todos) {
            if (equipamentoObrigatorio.getStatus().equals("Não obrigatorio")) {
                todosNaoObrigatorios.add(equipamentoObrigatorio);
            }

        }
        return todosNaoObrigatorios;
    }

}
