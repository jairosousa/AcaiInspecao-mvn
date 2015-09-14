
package br.com.ufra.dao;

import br.com.ufra.dao.service.InspecaoDAO;
import br.com.ufra.entidade.Inspecao;
import br.com.ufra.entidade.Vistoria;
import java.util.List;

public class InspecaoDAOImpl extends GenericDAOImpl<Inspecao> implements InspecaoDAO{
        
    
    @Override
    public boolean salvarInspecaoApartirInspecoes(Vistoria vistoria, List<Inspecao> inspecoes) {
        try {
            this.iniciarTransacao();
            
            getEntityManager().persist(vistoria);            
            inspecoes.stream().forEach((inspecao) ->{
                inspecao.setId(null);
                inspecao.setVistoria(vistoria);
                getEntityManager().persist(inspecao);
            });
            
                this.confirmarTransacao();
            return true;
            
        } catch (Exception e) {
            this.desfazerTransacao();
            System.out.println("Erro: "+e.toString());
            return false;
        }
    }
    
    
    
}
