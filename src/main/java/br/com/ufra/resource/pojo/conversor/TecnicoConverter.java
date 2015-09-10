/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.resource.pojo.conversor;

import br.com.ufra.entidade.Tecnico;
import br.com.ufra.resource.pojo.TecnicoPOJO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author geovane
 */
public class TecnicoConverter {
    private static final TecnicoConverter uniqueInstance = new TecnicoConverter();

    private TecnicoConverter() {

    }

    public static TecnicoConverter getInstance() {
        return uniqueInstance;
    }

    public static TecnicoPOJO toTecnicoPOJO(Tecnico tecnico) {
        if (tecnico != null) {

            TecnicoPOJO resposta = new TecnicoPOJO();

            resposta.setId(tecnico.getId());
            resposta.setMatricula(tecnico.getMatricula());
            
            return resposta;

        } else {
            return null;
        }
    }

    public static Tecnico fromTecnicoPOJO(TecnicoPOJO tecnicoPOJO) {
        if (tecnicoPOJO != null) {
            Tecnico resposta = new Tecnico();

            resposta.setId(tecnicoPOJO.getId());
            resposta.setMatricula(tecnicoPOJO.getMatricula());
            
            return resposta;

        } else {
            return null;
        }
    }

    public static List<TecnicoPOJO> toTecnicosPOJO(List<Tecnico> tecnicos) {
        if (tecnicos != null) {

            ArrayList<TecnicoPOJO> resposta = new ArrayList<>();

            tecnicos.stream().forEach((tecnico) -> {
                resposta.add(toTecnicoPOJO(tecnico));
            });

            return resposta;
        } else {
            return null;
        }

    }

    public static List<Tecnico> fromTecnicosPOJO(List<TecnicoPOJO> tecnicosPOJO) {
        if (tecnicosPOJO != null) {

            ArrayList<Tecnico> resposta = new ArrayList<>();

            tecnicosPOJO.stream().forEach((tecnicoPOJO) -> {
                resposta.add(fromTecnicoPOJO(tecnicoPOJO));
            });

            return resposta;
        } else {
            return null;
        }
    }
}
