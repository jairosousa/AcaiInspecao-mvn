/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.cep.service;

import br.com.ufra.entidade.Estabelecimento;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Jairo
 */
public class CepService {

    public Estabelecimento consultarCEP(String cep) {

        Estabelecimento estabelecimento = null;

        URL url;

        JAXBContext jc;

        Unmarshaller u;

        try {

            jc = JAXBContext.newInstance(Estabelecimento.class);

            u = jc.createUnmarshaller();

            url = new URL("http://www.devmedia.com.br/devware/cep/service/?cep=" + cep + "&chave=6PL0AP2LP4&formato=xml");

            estabelecimento = (Estabelecimento) u.unmarshal(url);
            estabelecimento.setCep(cep);
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return estabelecimento;
    }
}
