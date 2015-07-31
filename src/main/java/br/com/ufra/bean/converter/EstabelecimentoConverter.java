/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.bean.converter;

import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.rn.EstabelecimentoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Jairo
 */
@FacesConverter(value = "estabelecimentoConverter")
public class EstabelecimentoConverter implements Converter {
    private final EstabelecimentoRN rn = new EstabelecimentoRN();

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
            Estabelecimento estabelecimento = (Estabelecimento) o;
            if (estabelecimento.getId() == null) {
                return null;
            }
            return estabelecimento.getId().toString();
        }
    }

}
