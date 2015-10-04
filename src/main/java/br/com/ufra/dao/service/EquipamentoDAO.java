
package br.com.ufra.dao.service;

import br.com.ufra.entidade.Equipamento;
import java.util.List;

/**
 *
 * @author Jairo
 */
public interface EquipamentoDAO extends GenericDAO<Equipamento> {
    
    public List<Equipamento> obterTodosObrigatorios();
    public List<Equipamento> obterTodosNaoObrigatorios();
    
}
