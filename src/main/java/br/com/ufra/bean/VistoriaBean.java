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
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Jairo
 */
@ManagedBean
@ViewScoped
public class VistoriaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Vistoria vistoria;
    private Vistoria vistoriaInspecao = new Vistoria();
    private Inspecao inspecao = new Inspecao();
    private Estabelecimento estabelecimentoSelecionado = new Estabelecimento();
    private Estabelecimento estabelecimentoVistoria = new Estabelecimento();
    private Equipamento equipamentoSelecionado = new Equipamento();
    private Equipamento equipamentoEdicao = new Equipamento();

    private final VistoriaRN RN_VISTORIA = new VistoriaRN();
    private final InspecaoRN RN_INSPECAO = new InspecaoRN();
    private final TecnicoRN RN_tecnico = new TecnicoRN();
    private final EstabelecimentoRN RN_ESTABELECIMENTO = new EstabelecimentoRN();
    private final EquipamentoRN RN_EQUIPAMENTO = new EquipamentoRN();

    private List<Estabelecimento> estabelecimentos = new ArrayList<>();
    private List<Vistoria> vistorias;
    private List<Vistoria> vistoriasEstabelecimento = new ArrayList<>();
    private List<Inspecao> inspecoes;
    private List<Inspecao> inspecoesEquipamentos;
    private List<Equipamento> equipamentosNaoObrigatorios;
    private List<Tecnico> tecnicos = new ArrayList<>();

    @PostConstruct
    public void init() {

        this.vistoria = new Vistoria();
        vistoria.setTecnico1(UsuarioUtil.obterUsuarioLogado());

        //Inicializando as inspeções dos equipamentos
        List<Equipamento> equipamentosObrigatorios = RN_EQUIPAMENTO.obterTodosObrigatorios();
        this.inspecoes = RN_INSPECAO.criarInspecoes(equipamentosObrigatorios);
        //adicionar a vistoria as inspeções
        for (Inspecao i : this.inspecoes) {
            i.setVistoria(vistoria);
        }

        try {
            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("id")) {
                Integer id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id").toString());
                estabelecimentoSelecionado = RN_ESTABELECIMENTO.obter(id);
                System.out.println("Estabelecimento: " + estabelecimentoSelecionado.getNomeFantasia());
                vistoria.setEstabelecimento(estabelecimentoSelecionado);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("id");
            }
        } catch (Exception e) {
        }

        vistoria.setDataSolicitacao(estabelecimentoSelecionado.getDataCadastro());
        System.out.println("Tecnico: " + vistoria.getTecnico1().getNome());
    }

    public String UsuarioLogado() {
        String usuario = UsuarioUtil.obterUsuarioLogado().getNome();
        System.out.println("Usuario: " + usuario);
        return usuario;
    }

    public Equipamento getEquipamentoSelecionado() {
        return equipamentoSelecionado;
    }

    public List<Equipamento> getEquipamentosNaoObrigatorios() {
        if (equipamentosNaoObrigatorios == null || equipamentosNaoObrigatorios.isEmpty()) {
            equipamentosNaoObrigatorios = RN_EQUIPAMENTO.obterTodosNaoObrigatorios();
        }
        return equipamentosNaoObrigatorios;
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
        return inspecoes;
    }

    public void setInspecoes(List<Inspecao> inspecoes) {
        this.inspecoes = inspecoes;
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

    public List<Vistoria> getVistoriasEstabelecimento() {
        return vistoriasEstabelecimento = RN_VISTORIA.obterVistoriasPorEstabelecimento(estabelecimentoVistoria);
    }

    public Estabelecimento getEstabelecimentoVistoria() {
        return estabelecimentoVistoria;
    }

    public void setEstabelecimentoVistoria(Estabelecimento estabelecimentoVistoria) {
        this.estabelecimentoVistoria = estabelecimentoVistoria;
    }

    public List<Inspecao> getInspecoesEquipamentos() {
        return inspecoesEquipamentos = RN_INSPECAO.obterInspecoesPorVistoria(vistoriaInspecao);
    }

    public List<Vistoria> getVistoriasPorEstabelecimento(AjaxBehaviorEvent event) {
        System.out.println("est get" + estabelecimentoSelecionado.getNomeContato());
        vistorias = RN_VISTORIA.obterVistoriasPorEstabelecimento(estabelecimentoSelecionado);
        System.out.println("get vis" + vistorias.size());
        return vistorias;
    }

    public void obterTodasVistorias() {
        vistorias = RN_VISTORIA.obterTodos();
    }

    public Vistoria getVistoriaInspecao() {
        return vistoriaInspecao;
    }

    public void setVistoriaInspecao(Vistoria vistoriaInspecao) {
        this.vistoriaInspecao = vistoriaInspecao;
    }

    public void obterVistoriaDoEstabelecimento() {
        vistorias = estabelecimentoSelecionado.getVistoriaList();
    }

    public List<Vistoria> getVistorias() {
        return vistorias = RN_VISTORIA.obterTodos();
    }

    public Estabelecimento getEstabelecimentoSelecionado() {
        return estabelecimentoSelecionado;
    }

    public void setEstabelecimentoSelecionado(Estabelecimento estabelecimento) {
        this.estabelecimentoSelecionado = estabelecimento;
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos = RN_tecnico.obterTodos();
    }

    public List<Estabelecimento> getEstabelecimentosAguardandoVistoria() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            return RN_ESTABELECIMENTO.obterEstabelecimentosAguardandoVistoria();
        } else {
            return null;
        }
    }

    public void consultarEstabelecimentosAguardandoVistoria() {
        estabelecimentos = getEstabelecimentosAguardandoVistoria();
    }

    public List<Estabelecimento> getEstabelecimentosVencidos() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            return RN_ESTABELECIMENTO.obterEstabelecimentoVencido();
        } else {
            return null;
        }
    }

    public void consultarEstabelecimentosVencidos() {
        estabelecimentos = getEstabelecimentosVencidos();
    }

    public List<Estabelecimento> getEstabelecimentosPendenteVistoria() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            return RN_ESTABELECIMENTO.obterEstabelecimentosPendentes();
        } else {
            return null;
        }
    }

    public void consultarEstabelecimentosPendenteVistoria() {
        estabelecimentos = getEstabelecimentosPendenteVistoria();
    }

    public void adicionarNovaInspecao() {
        Inspecao i = RN_INSPECAO.criarInspecao(equipamentoSelecionado);
        this.inspecoes.add(i);
    }

    public void atualizarInspecao() {
        FacesMessage fm = null;
        fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Inspeção Atualizada");
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    private boolean definirStatusEstabelecimentoEVistoriaApt(List<Inspecao> inspecoesRealizadas) {
        Iterator<Inspecao> iterator = inspecoesRealizadas.iterator();
        int contInspecaoApt = 0;
        while (iterator.hasNext() && iterator.next().getApto() == true) {
            System.out.println("aa");
            contInspecaoApt++;
        }
        System.out.println("context inspecao apt: " + contInspecaoApt);
        if (contInspecaoApt == inspecoesRealizadas.size()) {
            Estabelecimento estabelecimentoStatus = RN_ESTABELECIMENTO.obter(this.vistoria.getEstabelecimento().getId());
            estabelecimentoStatus.setStatus("Regular");
            RN_ESTABELECIMENTO.salvar(estabelecimentoStatus);
            this.vistoria.setEstabelecimento(estabelecimentoStatus);
            this.vistoria.setApto(true);
            return true;
        } else {
            Estabelecimento estabelecimentoStatus = RN_ESTABELECIMENTO.obter(this.vistoria.getEstabelecimento().getId());
            estabelecimentoStatus.setStatus("Pendente");
            RN_ESTABELECIMENTO.salvar(estabelecimentoStatus);

            return false;
        }
    }

    public String salvar() {
        definirStatusEstabelecimentoEVistoriaApt(this.inspecoes);
        if (RN_INSPECAO.salvarInspecaoApartirInspecoes(this.vistoria, this.inspecoes)) {
            FacesMessage fm = null;
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO,  "Cadastro feito com Sucesso","");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            vistoria = new Vistoria();
            return "/index.xhtml";
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "Erro no Cadastro!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "/index.xhtml";

        }
    }

    public String excluir() {
        if (RN_VISTORIA.excluir(vistoria)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro foi excluído com exito");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "O cadastro não foi excluído!!!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";
        }
    }

    public String abrirVistoria() {

        if (this.estabelecimentoSelecionado == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione um estabelecimento para vistoriar!",null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        } else {
            Integer id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", id);
            return "vistoriar?faces-redirect=true.xhtml";
        }

    }

}
