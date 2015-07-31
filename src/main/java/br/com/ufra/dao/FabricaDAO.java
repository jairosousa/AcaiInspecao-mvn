/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.dao;

import br.com.ufra.dao.service.EstabelecimentoDAO;
import br.com.ufra.dao.service.GenericDAO;
import br.com.ufra.dao.service.TecnicoDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jairo
 */
public class FabricaDAO {

    private static EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("acaiInspecaoPU");

    public FabricaDAO() {
    }

    public static EntityManagerFactory obterFabrica() {
        return fabrica;
    }

    public static EntityManagerFactory obterFabrica(String unidadePersistencia) {
        if (fabrica != null && fabrica.isOpen()) {
            fabrica.close();
        }
        fabrica = Persistence.createEntityManagerFactory(unidadePersistencia);
        return fabrica;
    }

    public static EntityManager criarEntityManager() {
        return fabrica.createEntityManager();
    }

    public static GenericDAO criarGenericDAO() {
        return new GenericDAOImpl(fabrica.createEntityManager());
    }

    public static EstabelecimentoDAO criarEstabelecimentoDAO() {
        return new EstabelecimentoDAOImpl(fabrica.createEntityManager());
    }
    public static TecnicoDAO criarTecnicoDAO() {
        return new TecnicoDAOImpl(fabrica.createEntityManager());
    }

}
