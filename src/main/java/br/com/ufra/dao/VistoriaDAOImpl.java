/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.dao;

import br.com.ufra.dao.service.VistoriaDAO;
import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.entidade.Vistoria;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Jairo Sousa
 * @since 09/2015
 */
public class VistoriaDAOImpl extends GenericDAOImpl<Vistoria> implements VistoriaDAO {

    @Override
    public List<Vistoria> obterVistoriasComEstabelecimento() {
//       //Session do hiberbate é utilizado para se comunicar com o bd. Ela quem permite armazenar a consulta em cache.
//        Session session = getEntityManager().unwrap(Session.class);
//     //Utilizado para diminuir o número de consultas do hibernate. O fetch diz para retornar os estabelecimentos naquela consulta
//     //setCacheable diz que aquela consulta deve ficar no cache.   
//        return session.createQuery("from Vistoria v inner join fetch v.estabelecimento")
//                .setCacheable(true)
//                .list();
        return getEntityManager().createQuery("from Vistoria v inner join fetch v.estabelecimento ", Vistoria.class).getResultList();
    }

    @Override
    public List<Vistoria> obterVistoriasPorEstabelecimento(Estabelecimento estabelecimento) {
        try {
            String query = "SELECT v FROM Vistoria v WHERE v.estabelecimento =:e";
            Query q = super.getEntityManager().createQuery(query);
            q.setParameter("e", estabelecimento);
            return (List<Vistoria>) q.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao obterVistoriasPorEstabelecimento: " + e.getMessage());
            return null;
        }

    }

    @Override
    public List<Vistoria> obterVistoriasPendentes() {
        try {
            String regular = "false";
            String query = "SELECT e FROM Vistoria e WHERE e.status =:false";
            Query q = super.getEntityManager().createQuery(query);
            q.setParameter("false", regular);

            return (List<Vistoria>) q.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao estabelecimento pendentes: " + e.toString());
            return null;
        }

    }

}
