/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.bean;

import br.com.ufra.entidade.Inspecao;
import br.com.ufra.entidade.Vistoria;
import br.com.ufra.rn.InspecaoRN;
import br.com.ufra.rn.VistoriaRN;
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
public class VistoriaBean {

    private Vistoria vistoria = new Vistoria();
    private VistoriaRN rn = new VistoriaRN();
    private InspecaoRN rnInp = new InspecaoRN();
    private List<Vistoria> vistorias;

    public Vistoria getVistoria() {
        return vistoria;
    }

    public void setVistoria(Vistoria vistoria) {
        this.vistoria = vistoria;
    }

    public List<Vistoria> getVistorias() {
        return vistorias = rn.obterTodos();
    }
    public List<Inspecao> getInspecaos() {
        return  rnInp.obterTodos();
    }
    
    public void adicionarinspecao(){
        
    }

    public String salvar() {
        if (rn.salvar(vistoria)) {
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
        if (rn.excluir(vistoria)) {
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
        return "./inpeção/formulario.xhtml";
    }

    public String cancelar() {
        return "lista.xhtml";
    }
}
