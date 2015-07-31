/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.util;

import br.com.ufra.entidade.Tecnico;
import br.com.ufra.rn.TecnicoRN;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jairo
 */
public class UsuarioUtil {

    public static Tecnico obterUsuarioLogado() {
        FacesContext f = FacesContext.getCurrentInstance();
        ExternalContext e = f.getExternalContext();
        TecnicoRN tecnicoRN = new TecnicoRN();
        return tecnicoRN.obterEmail(e.getRemoteUser());
    }
}
