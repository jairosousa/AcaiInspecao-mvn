/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.bean;

import br.com.ufra.entidade.Equipamento;
import br.com.ufra.rn.EquipamentoRN;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jairo
 */
@ManagedBean
@ViewScoped
public class EquipamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Equipamento equipamentoEdicao = new Equipamento();
    private Equipamento equipamentoSelecionado;
    private EquipamentoRN rn = new EquipamentoRN();
    private List<Equipamento> equipamentos;

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void prepararNovoCadastro() {
        equipamentoEdicao = new Equipamento();
    }

    public void consultar() {
        equipamentos = rn.obterTodos();
    }

    public void salvar() {
        if (rn.salvar(equipamentoEdicao)) {
            consultar();
            FacesMessage fm = null;
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro feito com Sucesso");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            RequestContext.getCurrentInstance().update(Arrays.asList("frm-equipamento:msgs", "frm-equipamento:equipamento-table"));
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "Erro no Cadastro!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }

    }

    public void excluir() {
        if (rn.excluir(equipamentoSelecionado)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro foi excluído com exito");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            equipamentoSelecionado = null;
            consultar();
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "O cadastro não foi excluído!!!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }

    public Equipamento getEquipamentoEdicao() {
        return equipamentoEdicao;
    }

    public void setEquipamentoEdicao(Equipamento equipamentoEdicao) {
        this.equipamentoEdicao = equipamentoEdicao;
    }

    public Equipamento getEquipamentoSelecionado() {
        return equipamentoSelecionado;
    }

    public void setEquipamentoSelecionado(Equipamento equipamentoSelecionado) {
        this.equipamentoSelecionado = equipamentoSelecionado;
    }

}
