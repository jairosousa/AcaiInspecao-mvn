/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.bean;

import br.com.ufra.entidade.Tecnico;
import br.com.ufra.rn.TecnicoRN;
import br.com.ufra.util.UsuarioUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jairo
 */
@ManagedBean
@RequestScoped
public class TecnicoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Tecnico tecnico = new Tecnico();
    private Tecnico tecnicoLogado = new Tecnico();
    private TecnicoRN rn = new TecnicoRN();
    private List<Tecnico> tecnicos;

    public Tecnico getTecnico() {
        System.out.println("tec"+tecnico.getNome());
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Tecnico getTecnicoLogado() {

        return tecnicoLogado = UsuarioUtil.obterUsuarioLogado();
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos = rn.obterTodos();
    }

    public String salvar() {
        FacesMessage fm = null;
        if (rn.comparaeSenha(tecnico)) {
            if (rn.salvar(tecnico)) {
                fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro atualizado com Sucesso");
                FacesContext.getCurrentInstance().addMessage(null, fm);
                return "lista.xhtml";
            } else {
                fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "Erro no Cadastro!");
                FacesContext.getCurrentInstance().addMessage(null, fm);
                return "lista.xhtml";
            }
        } else {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "Senhas não confere!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "formulario.xhtml";
        }

    }

    public String excluir() {
        if (rn.excluir(tecnico)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro foi excluído com exito");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "O cadastro não foi excluído!!!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";
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
