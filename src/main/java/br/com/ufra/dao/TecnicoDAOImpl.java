/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.dao;

import br.com.ufra.dao.service.GenericDAO;
import br.com.ufra.dao.service.TecnicoDAO;
import br.com.ufra.entidade.Tecnico;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jairo
 */
public class TecnicoDAOImpl extends GenericDAOImpl<Tecnico> implements TecnicoDAO {
    
    private EntityManager em;
    private final GenericDAO<Tecnico> dao = FabricaDAO.criarGenericDAO();
    
    public TecnicoDAOImpl(EntityManager em) {
        this.em = em;
    }

    public TecnicoDAOImpl() {
    }

    @Override
    public Tecnico obterEmail(String email) {
        String query = "SELECT t FROM Tecnico t WHERE t.email = :email";
        Query q = super.getEntityManager().createQuery(query);
        q.setParameter("email", email);
        try {
            return (Tecnico) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean existe(String email) {
        String query = "SELECT t FROM Tecnico t WHERE t.email = :email";
        Query q = super.getEntityManager().createQuery(query);
        q.setParameter("email", email);
        try {
            return ((Tecnico) q.getSingleResult()).getEmail().equalsIgnoreCase(email);
        } catch (Exception e) {
            return false;
        }
    }

}
