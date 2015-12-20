/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.bean;

import br.com.ufra.entidade.Equipamento;
import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.entidade.Inspecao;
import br.com.ufra.entidade.Tecnico;
import br.com.ufra.entidade.Vistoria;
import br.com.ufra.rn.EquipamentoRN;
import br.com.ufra.rn.EstabelecimentoRN;
import br.com.ufra.rn.InspecaoRN;
import br.com.ufra.rn.TecnicoRN;
import br.com.ufra.rn.VistoriaRN;
import br.com.ufra.util.UsuarioUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Jairo
 */
@ManagedBean
@ViewScoped
public class VistoriaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Vistoria vistoria = new Vistoria();
    private Inspecao inspecao;
    private Estabelecimento estabelecimento = new Estabelecimento();
    private EstabelecimentoRN rnestabelecimento = new EstabelecimentoRN();
    private Equipamento equipamento = new Equipamento();
    private final VistoriaRN rnVistoria = new VistoriaRN();
    private final InspecaoRN rnInspecao = new InspecaoRN();
    private final TecnicoRN rnTecnico = new TecnicoRN();
    private final EstabelecimentoRN rnEstabelecimento = new EstabelecimentoRN();
    private final EquipamentoRN rnEquipamento = new EquipamentoRN();
    private List<Estabelecimento> estabelecimentos = new ArrayList<>();
    private List<Vistoria> vistorias;
    private List<Inspecao> inspecoes = new ArrayList<>();
    private List<Equipamento> equipamentos = new ArrayList<>();
    private List<Equipamento> equipamentosObrigatorios = new ArrayList<>();
    private List<Equipamento> equipamentosNaoObrigatorios = new ArrayList<>();
    private List<Tecnico> tecnicos = new ArrayList<>();
    private boolean skip;
    private boolean inspApto;
    private String obs;
    private Date dataInspecao;

    public VistoriaBean() {
        vistoria.setTecnico1(UsuarioUtil.obterUsuarioLogado());
        Calendar cal = Calendar.getInstance();
        for (Equipamento equipamento : getEquipamentosObrigatorios()) {
            inspecao = new Inspecao();
            inspecao.setEquipamento(equipamento);
            inspecao.setVistoria(vistoria);
            inspecao.setDataInsp(cal.getTime());
            inspecao.setApto(true);
            inspecoes.add(inspecao);
        }
        System.out.println("Tecnico: " + vistoria.getTecnico1().getNome());
    }

    public String UsuarioLogado() {

        String usuario = UsuarioUtil.obterUsuarioLogado().getNome();
        System.out.println("Usuario: " + usuario);
        return usuario;
    }

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

    public List<Equipamento> getEquipamentosObrigatorios() {
        if (equipamentosObrigatorios.isEmpty()) {
            carregarEquipamentosObrigatorios();
        }
        return equipamentosObrigatorios;
    }

    public List<Equipamento> getEquipamentosNaoObrigatorios() {

        if (equipamentosNaoObrigatorios.isEmpty()) {
            carregarEquipamentosNaoObrigatorios();
        }
        return equipamentosNaoObrigatorios;
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
        //Se for a primeira requisição para esse bean, eu entro nesse método, se não eu retorno os equipamentos salvos em cache.
//        
//        if (!FacesContext.getCurrentInstance().isPostback()){
//            
//        }
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public Vistoria getVistoria() {
        return vistoria;
    }

    public void setVistoria(Vistoria vistoria) {
        this.vistoria = vistoria;
    }

    public List<Vistoria> getVistoriasPorEstabelecimento(AjaxBehaviorEvent event) {
//        if (!FacesContext.getCurrentInstance().isPostback()){
//            
//        }
        System.out.println("est get" + estabelecimento.getNomeContato());
        vistorias = rnVistoria.obterVistoriasPorEstabelecimento(estabelecimento);
        System.out.println("get vis" + vistorias.size());
        return vistorias;
    }

    public List<Vistoria> getVistorias() {
        return vistorias = rnVistoria.obterTodos();
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        System.out.println("est set" + estabelecimento.getId());
        this.estabelecimento = estabelecimento;
    }

    public List<Inspecao> getInspecaos() {
        return inspecoes;
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public Estabelecimento getEstabelecimento() {

        return estabelecimento;
    }

    public boolean isSkip() {
        return skip;
    }

    public void inicializar() {
        //Se for a primeira requisição para esse bean, eu entro nesse método, se não eu retorno os equipamentos salvos em cache.
        equipamento = null;

        System.out.println("fora");
        if (!FacesContext.getCurrentInstance().isPostback()) {
            System.out.println("aqui");
            carregarEquipamentosObrigatorios();
            carregarEquipamentosNaoObrigatorios();
        }

        if (!FacesContext.getCurrentInstance().isPostback()) {
            tecnicos = rnTecnico.obterTodos();
        }
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public void carregarEquipamentosObrigatorios() {
        equipamentosObrigatorios = rnEquipamento.obterTodosObrigatorios();
    }

    public void carregarEquipamentosNaoObrigatorios() {
        equipamentosNaoObrigatorios = rnEquipamento.obterTodosNaoObrigatorios();
    }

    public List<Estabelecimento> getEstabelecimentosPendenteEAguardando() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            estabelecimentos = rnEstabelecimento.estabelecimentosAguardandoVistoriaEPendente();
        }
        return estabelecimentos;
    }

    public void adicionarinspecao() {
        inspecao = new Inspecao();
        inspecao.setVistoria(vistoria);
        inspecao.setEquipamento(equipamento);
        inspecao.setApto(inspApto);
        inspecao.setObservacao(obs);
        inspecao.setDataInsp(dataInspecao);
        inspecoes.add(inspecao);
        inicializar();

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
            estabelecimento = rnestabelecimento.obter(vistoria.getEstabelecimento().getId());
            estabelecimento.setStatus("Regular");
            rnestabelecimento.salvar(estabelecimento);
            vistoria.setEstabelecimento(estabelecimento);
            vistoria.setApto(true);
            return true;
        } else {
            estabelecimento = rnestabelecimento.obter(vistoria.getEstabelecimento().getId());
            estabelecimento.setStatus("Pendente");
            rnestabelecimento.salvar(estabelecimento);

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

    public String vistoriasDoEstabelecimento() {
        return "vistoriasPorEstabelecimento.xhtml";
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
