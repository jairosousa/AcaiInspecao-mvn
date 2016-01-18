/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.rn;

import br.com.ufra.dao.InspecaoDAOImpl;
import br.com.ufra.dao.VistoriaDAOImpl;
import br.com.ufra.entidade.Equipamento;
import br.com.ufra.entidade.Inspecao;
import br.com.ufra.entidade.Vistoria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jairo
 */
public class InspecaoRN {

    private InspecaoDAOImpl daoInspecao = new InspecaoDAOImpl();
    private VistoriaDAOImpl daoVistoria;

    public List<Inspecao> criarInspecoes(List<Equipamento> equipamentos) {
        if (equipamentos == null || equipamentos.isEmpty()) {
            return null;
        } else {
            List<Inspecao> resposta = new ArrayList<>();
            Inspecao i = null;
            Date hoje = new Date();
            for (Equipamento e : equipamentos) {
                i = new Inspecao();
                i.setEquipamento(e);
                i.setDataInsp(hoje);
                i.setApto(false);
                resposta.add(i);
            }
            return resposta;
        }
    }

    public Inspecao criarInspecao(Equipamento equipamento) {
        if (equipamento == null) {
            return null;
        } else {
            Inspecao resposta = new Inspecao();
            Date hoje = new Date();
            resposta.setEquipamento(equipamento);
            resposta.setDataInsp(hoje);
            resposta.setApto(false);
            return resposta;
        }
    }

    public Inspecao obter(Integer id) {
        if (id == null) {
            return null;

        } else {
            return daoInspecao.obter(Inspecao.class, id);
        }
    }

    public List<Inspecao> obterTodos() {
        return daoInspecao.obterTodos(Inspecao.class
        );
    }

    public boolean salvar(Inspecao inspecao) {
        if (inspecao.getDataInsp() == null) {
            return false;
        } else {
            if (inspecao.getId() == null || inspecao.getId() == 0) {
                return daoInspecao.criar(inspecao);
            } else {
                return daoInspecao.atualizar(inspecao);
            }
        }
    }

    public boolean salvarInspecaoApartirInspecoes(Vistoria vistoria, List<Inspecao> inspecoes) {

        return daoInspecao.salvarInspecaoApartirInspecoes(vistoria, inspecoes);
    }

    public boolean excluir(Inspecao inspecao) {
        if (inspecao.getDataInsp() == null) {
            return false;
        } else {
            return daoInspecao.excluir(inspecao);
        }
    }

    public List<Inspecao> obterInspecoesPorVistoria(Integer idv) {
        daoVistoria = new VistoriaDAOImpl();
        Vistoria vistoria = daoVistoria.obter(Vistoria.class, idv);
        return daoInspecao.obterInspecoesPorVistoria(vistoria);
    }

    public List<Inspecao> obterInspecoesPorVistoria(Vistoria v) {
        return daoInspecao.obterInspecoesPorVistoria(v);
    }

}
