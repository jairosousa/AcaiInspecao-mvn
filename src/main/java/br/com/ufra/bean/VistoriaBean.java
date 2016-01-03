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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Jairo
 */
@ManagedBean
@ViewScoped
public class VistoriaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Vistoria vistoria;
    private Inspecao inspecao;
    private Inspecao inspecaoEdicao = new Inspecao();
    private Estabelecimento estabelecimentoSelecionado = new Estabelecimento();
    private EstabelecimentoRN rnestabelecimento = new EstabelecimentoRN();
    private Equipamento equipamentoSelecionado;
    private Equipamento equipamentoEdicao = new Equipamento();
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
    private List<Equipamento> equipamentosSelecionados = new ArrayList<>();
    private List<Tecnico> tecnicos = new ArrayList<>();
    private boolean skip;
    private boolean inspApto;
    private String obs;
    private Date dataInspecao;
    Calendar cal;

    @PostConstruct
    public void init() {

        this.vistoria = new Vistoria();
        vistoria.setTecnico1(UsuarioUtil.obterUsuarioLogado());
        cal = Calendar.getInstance();

        try {
            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("id")) {
                Integer id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id").toString());
                estabelecimentoSelecionado = rnEstabelecimento.obter(id);
                System.out.println("Estabelecimento: " + estabelecimentoSelecionado.getNomeFantasia());
                vistoria.setEstabelecimento(estabelecimentoSelecionado);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("id");
            }
        } catch (Exception e) {
        }

        vistoria.setDataSolicitacao(estabelecimentoSelecionado.getDataCadastro());
//        vistoria.setInspecaoList(inspecoes);
//        for (Equipamento equipamento : getEquipamentosObrigatorios()) {
//            equipamentos.add(equipamento);
//        }
        gerarListaEquipamentosObrigatorios();
        carregarEquipamentosNaoObrigatorios();
        System.out.println("Tecnico: " + vistoria.getTecnico1().getNome());
    }

    public void gerarInspeoesObrigatorias() {

        equipamentosObrigatorios = getEquipamentosNaoObrigatorios();
        System.out.println("Lista de equipamentos1");

        for (Equipamento equipamento : equipamentosObrigatorios) {
            inspecao = new Inspecao();
            inspecao.setEquipamento(equipamento);
            inspecao.setVistoria(vistoria);
            inspecao.setDataInsp(cal.getTime());
            inspecao.setApto(true);
            this.inspecoes.add(inspecao);
        }

        System.out.println("Lista de equipamentos2");
    }

    public void adicionarEquipamento() {
        System.out.println("Nº equipamentos: " + equipamentos.size());
        for (Equipamento equipamento : equipamentosSelecionados) {
            System.out.println("Equipamento: " + equipamentoSelecionado.getNome());
            equipamentos.add(equipamento);
        }

        RequestContext.getCurrentInstance().update(
                Arrays.asList("frm-vistoriar:inspEquip-table"));
    }

    public void gerarListaEquipamentosObrigatorios() {
        for (Equipamento equipamento : getEquipamentosObrigatorios()) {
            equipamentos.add(equipamento);
        }
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

    public Equipamento getEquipamentoSelecionado() {
        return equipamentoSelecionado;
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

    public List<Equipamento> getEquipamentosSelecionados() {
        return equipamentosSelecionados;
    }

    public void setEquipamentosSelecionados(List<Equipamento> equipamentosSelecionados) {
        this.equipamentosSelecionados = equipamentosSelecionados;
    }

    public void setEquipamentoSelecionado(Equipamento equipamento) {
        this.equipamentoSelecionado = equipamento;
    }

    public Equipamento getEquipamentoEdicao() {
        return equipamentoEdicao;
    }

    public void setEquipamentoEdicao(Equipamento equipamentoEdicao) {
        this.equipamentoEdicao = equipamentoEdicao;
    }

    public Inspecao getInspecao() {
        return inspecao;
    }

    public void setInspecao(Inspecao inspecao) {
        this.inspecao = inspecao;
    }

    public List<Inspecao> getInspecoes() {

        equipamentosObrigatorios = getEquipamentosObrigatorios();

        System.out.println("Numero equipamentos origratorios " + equipamentosObrigatorios.size());

        for (Equipamento equipamento : equipamentosObrigatorios) {
            inspecao.setEquipamento(equipamento);
            inspecao.setVistoria(vistoria);
            inspecao.setDataInsp(cal.getTime());
            inspecao.setApto(true);
            this.inspecoes.add(inspecao);
            inspecao = new Inspecao();
            System.out.println("adicionado");
        }

        return inspecoes;
    }

    public void setInspecoes(List<Inspecao> inspecoes) {
        this.inspecoes = inspecoes;
    }

    public List<Equipamento> getEquipamentos() {
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

    public List<Estabelecimento> getEstabelecimentos() {
        return estabelecimentos;
    }

    public List<Vistoria> getVistoriasPorEstabelecimento(AjaxBehaviorEvent event) {
        System.out.println("est get" + estabelecimentoSelecionado.getNomeContato());
        vistorias = rnVistoria.obterVistoriasPorEstabelecimento(estabelecimentoSelecionado);
        System.out.println("get vis" + vistorias.size());
        return vistorias;
    }

    public List<Vistoria> getVistorias() {
        return vistorias = rnVistoria.obterTodos();
    }

    public Estabelecimento getEstabelecimentoSelecionado() {
        return estabelecimentoSelecionado;
    }

    public void setEstabelecimentoSelecionado(Estabelecimento estabelecimento) {
        this.estabelecimentoSelecionado = estabelecimento;
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos = rnTecnico.obterTodos();
    }

    public Inspecao getInspecaoEdicao() {
        return inspecaoEdicao;
    }

    public void setInspecaoEdicao(Inspecao inspecaoEdicao) {
        this.inspecaoEdicao = inspecaoEdicao;
    }

    public boolean isSkip() {
        return skip;
    }

    public void inicializar() {
        //Se for a primeira requisição para esse bean, eu entro nesse método, se não eu retorno os equipamentos salvos em cache.

        equipamentoSelecionado = null;

        if (!FacesContext.getCurrentInstance().isPostback()) {
            System.out.println("aqui");
            carregarEquipamentosObrigatorios();
            carregarEquipamentosNaoObrigatorios();
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
            return rnEstabelecimento.estabelecimentosAguardandoVistoriaEPendente();
        } else {
            return null;
        }
    }

    public void consultarEstabelecimentosVistoriar() {
        estabelecimentos = getEstabelecimentosPendenteEAguardando();
    }

    public void adicionarinspecao() {
        inspecao = new Inspecao();
        inspecao.setVistoria(vistoria);
        inspecao.setEquipamento(equipamentoSelecionado);
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
            estabelecimentoSelecionado = rnestabelecimento.obter(vistoria.getEstabelecimento().getId());
            estabelecimentoSelecionado.setStatus("Regular");
            rnestabelecimento.salvar(estabelecimentoSelecionado);
            vistoria.setEstabelecimento(estabelecimentoSelecionado);
            vistoria.setApto(true);
            return true;
        } else {
            estabelecimentoSelecionado = rnestabelecimento.obter(vistoria.getEstabelecimento().getId());
            estabelecimentoSelecionado.setStatus("Pendente");
            rnestabelecimento.salvar(estabelecimentoSelecionado);

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

    public String abrirVistoria() {
        Integer id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", id);
        return "vistoriar.xhtml";
    }

    public String cancelar() {
        return "lista.xhtml";
    }

}
