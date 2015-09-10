/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.resource.pojo.conversor;

import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.resource.pojo.EstabelecimentoPOJO;
import java.util.ArrayList;
import java.util.Date;
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
            resposta.setNomeFantasia(estabelecimento.getNomeFantasia());
            resposta.setNomeContato(estabelecimento.getNomeContato());
            resposta.setTelefone(estabelecimento.getTelefone());
            resposta.setCep(estabelecimento.getCep());
            resposta.setLogradouro(estabelecimento.getLogradouro());
            resposta.setNumero(estabelecimento.getNumero());
            resposta.setComplemeto(estabelecimento.getComplemento());
            resposta.setBairro(estabelecimento.getBairro());
            if (estabelecimento.getDataCadastro() == null) {
                resposta.setDataCadastro(0);
            } else {
                resposta.setDataCadastro(estabelecimento.getDataCadastro().getTime());
            }
            if (estabelecimento.getDataLicenca() == null) {
                resposta.setDataLicenca(0);
            } else {

                resposta.setDataLicenca(estabelecimento.getDataLicenca().getTime());
            }
            if (estabelecimento.getDataVencimento() == null) {
                resposta.setDataVencimento(0);
            } else {
                resposta.setDataVencimento(estabelecimento.getDataVencimento().getTime());

            }
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
            resposta.setNomeFantasia(estabelecimentoPOJO.getNomeFantasia());
            resposta.setNomeContato(estabelecimentoPOJO.getNomeContato());
            resposta.setTelefone(estabelecimentoPOJO.getTelefone());
            resposta.setCep(estabelecimentoPOJO.getCep());
            resposta.setLogradouro(estabelecimentoPOJO.getLogradouro());
            resposta.setNumero(estabelecimentoPOJO.getNumero());
            resposta.setComplemento(estabelecimentoPOJO.getComplemeto());
            resposta.setBairro(estabelecimentoPOJO.getBairro());

            resposta.setDataCadastro(new Date(estabelecimentoPOJO.getDataCadastro()));
            if (estabelecimentoPOJO.getDataLicenca() == 0) {
                resposta.setDataLicenca(null);
            } else {
                resposta.setDataLicenca(new Date(estabelecimentoPOJO.getDataLicenca()));
            }
            if (estabelecimentoPOJO.getDataVencimento() == 0) {
                resposta.setDataVencimento(null);
            } else {
                resposta.setDataVencimento(new Date(estabelecimentoPOJO.getDataVencimento()));

            }

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
