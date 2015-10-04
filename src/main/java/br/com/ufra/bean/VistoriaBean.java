/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.bean;

import br.com.ufra.entidade.Equipamento;
import br.com.ufra.entidade.Inspecao;
import br.com.ufra.entidade.Vistoria;
import br.com.ufra.rn.EquipamentoRN;
import br.com.ufra.rn.InspecaoRN;
import br.com.ufra.rn.VistoriaRN;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Jairo
 */
@ManagedBean
@SessionScoped
public class VistoriaBean {

    private static final long serialVersionUID = 1L;
    private Vistoria vistoria = new Vistoria();
    private Inspecao inspecao;
    private Equipamento equipamento = new Equipamento();
    private VistoriaRN rnVistoria = new VistoriaRN();
    private InspecaoRN rnInspecao = new InspecaoRN();
    private EquipamentoRN rnEquipamento = new EquipamentoRN();
    private List<Vistoria> vistorias;
    private List<Inspecao> inspecoes = new ArrayList<>();
    private List<Equipamento> equipamentos = new ArrayList<>();
    private List<Equipamento> equipamentosObrigatorios = new ArrayList<>();
    private boolean skip;
    private boolean inspApto;
    private String obs;
    private Date dataInspecao;

    public Date getDataInspecao() {
        return dataInspecao;
    }

    public void setDataInspecao(Date dataInspecao) {
        this.dataInspecao = dataInspecao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public boolean isInspApto() {
        return inspApto;
    }

    public void setInspApto(boolean inspApto) {
        this.inspApto = inspApto;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Inspecao getInspecao() {
        return inspecao;
    }

    public void setInspecao(Inspecao inspecao) {
        this.inspecao = inspecao;
    }

    public List<Inspecao> getInspecoes() {
        return inspecoes;
    }

    public void setInspecoes(List<Inspecao> inspecoes) {
        this.inspecoes = inspecoes;
    }

    public List<Equipamento> getEquipamentos() {
        return rnEquipamento.obterTodosNaoObrigatorios();
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public List<Equipamento> getEquipamentosObrigatorios() {
        return equipamentosObrigatorios = rnEquipamento.obterTodosObrigatorios();
    }

    public Vistoria getVistoria() {
        return vistoria;
    }

    public void setVistoria(Vistoria vistoria) {
        this.vistoria = vistoria;
    }

    public List<Vistoria> getVistorias() {
        return vistorias = rnVistoria.obterTodos();
    }

    public List<Inspecao> getInspecaos() {
        for (Equipamento equipamento : getEquipamentosObrigatorios()) {
            inspecao = new Inspecao();
            inspecao.setEquipamento(equipamento);
            inspecoes.add(inspecao);
        }
        return inspecoes;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public void adicionarinspecao() {
        inspecao = new Inspecao();
        inspecao.setVistoria(vistoria);
        inspecao.setEquipamento(equipamento);
        inspecao.setApto(inspApto);
        inspecao.setObservacao(obs);
        inspecao.setDataInsp(dataInspecao);
        inspecoes.add(inspecao);

    }

    private boolean definirStatusEstabelecimentoEVistoriaApt(List<Inspecao> inspecoesRealizadas) {
        Inspecao inspecaoRealizada;
        Iterator<Inspecao> iterator = inspecoesRealizadas.iterator();
        int contInspecaoApt = 0;
        while (iterator.hasNext() && iterator.next().getApto() == true) {
            System.out.println("aa");
            contInspecaoApt++;
        }
        System.out.println("context inspecao apt: " + contInspecaoApt);
        if (contInspecaoApt == inspecoesRealizadas.size()) {
            vistoria.getEstabelecimento().setStatus("regular");
            return true;
        } else {
            vistoria.getEstabelecimento().setStatus("pendente");
            return false;
        }
    }

    public String salvar() {
        definirStatusEstabelecimentoEVistoriaApt(inspecoes);
        if (rnInspecao.salvarInspecaoApartirInspecoes(vistoria, inspecoes)) {
            FacesMessage fm = null;
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro feito com Sucesso");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            vistoria = new Vistoria();
            return "lista.xhtml";
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "Erro no Cadastro!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";

        }
    }

    public String excluir() {
        if (rnVistoria.excluir(vistoria)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro foi excluído com exito");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "O cadastro não foi excluído!!!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";
        }
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public String editar() {
        return "formulario.xhtml";
    }

    public String incluir() {
        return "formulario.xhtml";
    }

    public String cancelar() {
        return "lista.xhtml";
    }
}
