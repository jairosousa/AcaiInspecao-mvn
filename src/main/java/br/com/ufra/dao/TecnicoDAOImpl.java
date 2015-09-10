/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.dao;

import br.com.ufra.dao.service.GenericDAO;
import br.com.ufra.dao.service.TecnicoDAO;
import br.com.ufra.entidade.Tecnico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jairo
 */
public class TecnicoDAOImpl extends GenericDAOImpl<Tecnico> implements TecnicoDAO {
    
    private EntityManager em;
    private final GenericDAO<Tecnico> dao = FabricaDAO.criarGenericDAO();
    private List<Tecnico> tecnicos;
     
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

    @Override
    public List<Tecnico> obterTecnicosPorMatricula(String mat1, String mat2) {
        try {
              tecnicos = new ArrayList<>();
        tecnicos.add(getTecnico1(mat1));
        tecnicos.add(getTecnico1(mat2));
         if (tecnicos.size() ==2 ){
             return tecnicos;
         } else {             
             return null;
         }
        } catch (Exception e) {
            System.out.println("erro: "+e.getCause());
            return null;
        }
    }


    private Tecnico getTecnico1(String mat) {
     String query = "SELECT t FROM Tecnico t WHERE t.matricula = :mat";
        Query q = super.getEntityManager().createQuery(query);
        q.setParameter("mat", mat);
        try {
            return (Tecnico) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Tecnico nulo");
            return null;
        }    
    }

  
    private Tecnico getTecnico2(String mat) {
     String query = "SELECT t FROM Tecnico t WHERE t.matricula = :mat";
        Query q = super.getEntityManager().createQuery(query);
        q.setParameter("mat", mat);
        try {
            return (Tecnico) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Tecnico nulo");
            return null;
        }    
    }

}
