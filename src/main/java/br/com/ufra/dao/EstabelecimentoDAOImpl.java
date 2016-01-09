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
import javax.persistence.Query;
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
    public List<Estabelecimento> estabelecimentosAguardandoVistoriaEPendente() {

        try {
            String aguardando = "Aguardando vistoria", pendente = "Pendente";
            String query = "SELECT e FROM Estabelecimento e WHERE e.status =:aguardando OR e.status =:pendente ";
            Query q = super.getEntityManager().createQuery(query);
            q.setParameter("aguardando", aguardando).setParameter("pendente", pendente);

            return (List<Estabelecimento>) q.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao obterAguardandoVistoriaEPendente: " + e.toString());
            return null;
        }

    }

    @Override
    public List<Estabelecimento> obterEstabelecimentosRegulares() {
        try {
            String regular = "Regular";
            String query = "SELECT e FROM Estabelecimento e WHERE e.status =:regular";
            Query q = super.getEntityManager().createQuery(query);
            q.setParameter("regular", regular);

            return (List<Estabelecimento>) q.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao obter estabelecimentos regulares: " + e.toString());
            return null;
        }

    }
    
    @Override
    public List<Estabelecimento> obterEstabelecimentosPendentes() {
        try {
            String regular = "Pendente";
            String query = "SELECT e FROM Estabelecimento e WHERE e.status =:pendente";
            Query q = super.getEntityManager().createQuery(query);
            q.setParameter("pendente", regular);

            return (List<Estabelecimento>) q.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao estabelecimento pendentes: " + e.toString());
            return null;
        }

    }
    
    @Override
    public List<Estabelecimento> obterEstabelecimentoVencido() {
        Date data = new Date();
        try {
            String regular ="";
            String query = "SELECT e FROM Estabelecimento e WHERE e.dataVencimento <:data";
            Query q = super.getEntityManager().createQuery(query);
            q.setParameter("data", data);

            return (List<Estabelecimento>) q.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao estabelecimento pendentes: " + e.toString());
            return null;
        }

    }

}
