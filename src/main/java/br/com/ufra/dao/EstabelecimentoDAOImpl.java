/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.dao;

import br.com.ufra.dao.service.EstabelecimentoDAO;
import br.com.ufra.dao.service.GenericDAO;
import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.rn.EstabelecimentoRN;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Jairo
 */
public class EstabelecimentoDAOImpl extends GenericDAOImpl<Estabelecimento> implements EstabelecimentoDAO {

    private EntityManager em;
    private final GenericDAO<Estabelecimento> dao = FabricaDAO.criarGenericDAO();

    public EstabelecimentoDAOImpl(EntityManager em) {
        this.em = em;
    }

    public EstabelecimentoDAOImpl() {
    }

    @Override
    public Date atualizaDataVencimento(Estabelecimento estabelecimento) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(estabelecimento.getDataLicenca());
        cal.add(Calendar.DAY_OF_MONTH, 365);
        return (cal.getTime());
    }

    @Override
    public Estabelecimento obterCep(Estabelecimento estabelecimento) {
        String cep = estabelecimento.getCep();

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

    @Override
    public List<Estabelecimento> obterTodosPendenteVistoria() {

        List<Estabelecimento> todos = dao.obterTodos(Estabelecimento.class);

        List<Estabelecimento> list = new ArrayList<>();

        for (Estabelecimento pendente : todos) {

            if (pendente.getStatus().equalsIgnoreCase("Aguardando vistoria") || pendente.getStatus().equalsIgnoreCase("Pendente")) {
                list.add(pendente);
            }

        }

        return list;
    }

    }
