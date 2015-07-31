/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.resource.pojo.conversor;

import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.resource.pojo.EstabelecimentoPOJO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author geovane
 */
public class EstabelecimentoConverter {

    private static final EstabelecimentoConverter uniqueInstance = new EstabelecimentoConverter();

    private EstabelecimentoConverter() {

    }

    public static EstabelecimentoConverter getInstance() {
        return uniqueInstance;
    }

    public static EstabelecimentoPOJO toEstabelecimentoPOJO(Estabelecimento estabelecimento) {
        if (estabelecimento != null) {

            EstabelecimentoPOJO resposta = new EstabelecimentoPOJO();

            resposta.setId(estabelecimento.getId());
            resposta.setRazaoSocial(estabelecimento.getRazaoSocial());
            resposta.setNomeFantasia(estabelecimento.getNomeFantasia());
            resposta.setCnpj(estabelecimento.getCnpj());
            resposta.setNomeContato(estabelecimento.getNomeContato());
            resposta.setRg(estabelecimento.getRg());
            resposta.setCpf(estabelecimento.getCpf());
            resposta.setEmail(estabelecimento.getEmail());
            resposta.setTelefone(estabelecimento.getTelefone());
            resposta.setCep(estabelecimento.getCep());
            resposta.setLogradouro(estabelecimento.getLogradouro());
            resposta.setNumero(estabelecimento.getNumero());
            resposta.setComplemeto(estabelecimento.getComplemento());
            resposta.setBairro(estabelecimento.getBairro());
            resposta.setDataCadastro(estabelecimento.getDataCadastro());
            resposta.setDataLicenca(estabelecimento.getDataLicenca());
            resposta.setDataVencimento(estabelecimento.getDataVencimento());
            resposta.setStatus(estabelecimento.getStatus());
            resposta.setLatitude(null);
            resposta.setLongitude(null);

            return resposta;

        } else {
            return null;
        }
    }

    public static Estabelecimento fromEstabelecimentoPOJO(EstabelecimentoPOJO estabelecimentoPOJO) {
        if (estabelecimentoPOJO != null) {
            Estabelecimento resposta = new Estabelecimento();

            resposta.setId(estabelecimentoPOJO.getId());
            resposta.setRazaoSocial(estabelecimentoPOJO.getRazaoSocial());
            resposta.setNomeFantasia(estabelecimentoPOJO.getNomeFantasia());
            resposta.setCnpj(estabelecimentoPOJO.getCnpj());
            resposta.setNomeContato(estabelecimentoPOJO.getNomeContato());
            resposta.setRg(estabelecimentoPOJO.getRg());
            resposta.setCpf(estabelecimentoPOJO.getCpf());
            resposta.setEmail(estabelecimentoPOJO.getEmail());
            resposta.setTelefone(estabelecimentoPOJO.getTelefone());
            resposta.setCep(estabelecimentoPOJO.getCep());
            resposta.setLogradouro(estabelecimentoPOJO.getLogradouro());
            resposta.setNumero(estabelecimentoPOJO.getNumero());
            resposta.setComplemento(estabelecimentoPOJO.getComplemeto());
            resposta.setBairro(estabelecimentoPOJO.getBairro());
            resposta.setDataCadastro(estabelecimentoPOJO.getDataCadastro());
            resposta.setDataLicenca(estabelecimentoPOJO.getDataLicenca());
            resposta.setDataVencimento(estabelecimentoPOJO.getDataVencimento());
            resposta.setStatus(estabelecimentoPOJO.getStatus());
            resposta.setLatitude(null);
            resposta.setLongitude(null);

            return resposta;

        } else {
            return null;
        }
    }

    public static List<EstabelecimentoPOJO> toEstabelecimentosPOJO(List<Estabelecimento> estabelecimentos) {
        if (estabelecimentos != null) {

            ArrayList<EstabelecimentoPOJO> resposta = new ArrayList<>();

            estabelecimentos.stream().forEach((estabelecimento) -> {
                resposta.add(toEstabelecimentoPOJO(estabelecimento));
            });

            return resposta;
        } else {
            return null;
        }

    }

    public static List<Estabelecimento> fromEstabelecimentosPOJO(List<EstabelecimentoPOJO> estabelecimentosPOJO) {
        if (estabelecimentosPOJO != null) {

            ArrayList<Estabelecimento> resposta = new ArrayList<>();

            estabelecimentosPOJO.stream().forEach((estabelecimentoPOJO) -> {
                resposta.add(fromEstabelecimentoPOJO(estabelecimentoPOJO));
            });

            return resposta;
        } else {
            return null;
        }
    }
}
