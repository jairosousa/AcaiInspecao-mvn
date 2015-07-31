/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.bean.converter;

import br.com.ufra.entidade.Vistoria;
import br.com.ufra.rn.VistoriaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Jairo
 */
@FacesConverter(value = "vistoriaConverter")
public class VistoriaConverter implements Converter{
    
    private final VistoriaRN rn = new VistoriaRN();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
       if (string.isEmpty()) {
            return null;
        } else {
            Integer id = new Integer(string);
            return rn.obter(id);
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        } else {
            Vistoria vistoria = (Vistoria) o;
            if (vistoria.getId() == null) {
                return null;
            }
            return vistoria.getId().toString();
        }
    }
    
}
