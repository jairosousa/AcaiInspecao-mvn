/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.bean;

import br.com.ufra.entidade.Inspecao;
import br.com.ufra.rn.InspecaoRN;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jairo
 */
@ManagedBean
@RequestScoped
public class InspecaoBean {

    private static final long serialVersionUID = 1L;
    private Inspecao inspecao = new Inspecao();
    private InspecaoRN rn = new InspecaoRN();
    private List<Inspecao> inspecaos;

    public Inspecao getInspecao() {
        return inspecao;
    }

    public void setInspecao(Inspecao inspecao) {
        this.inspecao = inspecao;
    }

    public List<Inspecao> getInspecaos() {
        return inspecaos = rn.obterTodos();
    }

    public String salvar() {
        if (rn.salvar(inspecao)) {
            FacesMessage fm = null;
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro feito com Sucesso");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "Erro no Cadastro!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "lista.xhtml";

        }
    }

    public String excluir() {
        if (rn.excluir(inspecao)) {
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
