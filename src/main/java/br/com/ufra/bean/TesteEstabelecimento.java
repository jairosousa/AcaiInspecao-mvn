/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.bean;

import br.com.ufra.cep.service.CepService;
import br.com.ufra.dao.EstabelecimentoDAOImpl;
import br.com.ufra.entidade.Estabelecimento;

/**
 *
 * @author Jairo
 */
public class TesteEstabelecimento {

    public static void main(String[] args) {

        
        Estabelecimento estabecimento = new Estabelecimento();

//        estabecimento.setRazaoSocial("Ind. Alimenticia Bacana S/A");
//        estabecimento.setNomeFantasia("Açai Bacana");
//        estabecimento.setCnpj("12.123.113/0001-01");
//        estabecimento.setNomeContato("João Barcare");
//        estabecimento.setRg("6472364");
//        estabecimento.setCpf("345.546.211-45");
//        estabecimento.setEmail("joao@email.com");
//        estabecimento.setTelefone("(91)3902-9012");
//        estabecimento.setCep("66813-700");
//        estabecimento.setStatus("ok");
//        

        CepService cs = new CepService();
        
        estabecimento = cs.consultarCEP("66813-700");
        
        
        System.out.println(estabecimento.getLogradouro() + "\n"
                + estabecimento.getCep() + "\n" + estabecimento.getLatitude() + "\n" + estabecimento.getLongitude());
    }
}
