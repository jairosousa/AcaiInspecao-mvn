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
import static com.itextpdf.text.Utilities.skip;
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
    private Inspecao inspecao = new Inspecao();
    private Estabelecimento estabelecimentoSelecionado = new Estabelecimento();
    private Equipamento equipamentoSelecionado = new Equipamento();
    private Equipamento equipamentoEdicao = new Equipamento();

    private final EstabelecimentoRN RNESTABELECIMENTO = new EstabelecimentoRN();
    private final VistoriaRN RNVISTORIA = new VistoriaRN();
    private final InspecaoRN RNINSPECAO = new InspecaoRN();
    private final TecnicoRN rnTecnico = new TecnicoRN();
    private final EstabelecimentoRN rnEstabelecimento = new EstabelecimentoRN();
    private final EquipamentoRN RN_EQUIPAMENTO = new EquipamentoRN();

    private List<Estabelecimento> estabelecimentos = new ArrayList<>();
    private List<Vistoria> vistorias;
    private List<Inspecao> inspecoes;
    private List<Equipamento> equipamentosNaoObrigatorios;
    private List<Tecnico> tecnicos = new ArrayList<>();

    @PostConstruct
    public void init() {

        this.vistoria = new Vistoria();
        vistoria.setTecnico1(UsuarioUtil.obterUsuarioLogado());

        //Inicializando as inspeções dos equipamentos
        List<Equipamento> equipamentosObrigatorios = RN_EQUIPAMENTO.obterTodosObrigatorios();
        this.inspecoes = RNINSPECAO.criarInspecoes(equipamentosObrigatorios);
        //adicionar a vistoria as inspeções
        for (Inspecao i : this.inspecoes) {
            i.setVistoria(vistoria);
        }

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

    public List<Vistoria> getVistoriasPorEstabelecimento(AjaxBehaviorEvent event) {
        System.out.println("est get" + estabelecimentoSelecionado.getNomeContato());
        vistorias = RNVISTORIA.obterVistoriasPorEstabelecimento(estabelecimentoSelecionado);
        System.out.println("get vis" + vistorias.size());
        return vistorias;
    }

    public List<Vistoria> getVistorias() {
        return vistorias = RNVISTORIA.obterTodos();
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

    public void adicionarNovaInspecao() {
        Inspecao i = RNINSPECAO.criarInspecao(equipamentoSelecionado);
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
            Estabelecimento estabelecimentoStatus = RNESTABELECIMENTO.obter(this.vistoria.getEstabelecimento().getId());
            estabelecimentoStatus.setStatus("Regular");
            RNESTABELECIMENTO.salvar(estabelecimentoStatus);
            this.vistoria.setEstabelecimento(estabelecimentoStatus);
            this.vistoria.setApto(true);
            return true;
        } else {
            Estabelecimento estabelecimentoStatus = RNESTABELECIMENTO.obter(this.vistoria.getEstabelecimento().getId());
            estabelecimentoStatus.setStatus("Pendente");
            RNESTABELECIMENTO.salvar(estabelecimentoStatus);

            return false;
        }
    }

    public String salvar() {
        definirStatusEstabelecimentoEVistoriaApt(this.inspecoes);
        if (RNINSPECAO.salvarInspecaoApartirInspecoes(this.vistoria, this.inspecoes)) {
            FacesMessage fm = null;
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro feito com Sucesso");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            vistoria = new Vistoria();
            return "realizarVistoria?faces-redirect=true";
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "Erro no Cadastro!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "realizarVistoria?faces-redirect=true";

        }
    }

    public String excluir() {
        if (RNVISTORIA.excluir(vistoria)) {
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
        Integer id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", id);
        return "vistoriar?faces-redirect=true";
    }

}
