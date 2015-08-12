
package br.com.ufra.resource;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.entidade.Equipamento;
import br.com.ufra.resource.pojo.EquipamentoPOJO;
import br.com.ufra.resource.pojo.conversor.EquipamentoConverter;
import br.com.ufra.util.Mensagem;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Path("/equipamento")
public class EquipamentoResource extends Application {
    GenericDAOImpl<Equipamento> dao = new GenericDAOImpl();
    Equipamento equipamento;
    EquipamentoPOJO equipamentoPOJO; 
    Gson gson = new Gson();
    Mensagem mensagem = Mensagem.getInstance();
    String json;
    List<EquipamentoPOJO> listPOJO = new ArrayList<>();
    List<Equipamento> equipamentos = new ArrayList<>();
    
    public EquipamentoResource(){
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String obterTodos() {
        try {
            equipamentos = dao.obterTodos(Equipamento.class);
            if (!equipamentos.isEmpty()) {
                return gson.toJson(EquipamentoConverter.toEquipamentosPOJO(equipamentos));
            } else {
                mensagem.setMensagemServToClient("A lista está vazia!");
                json = gson.toJson(mensagem);
                System.out.println(json);
                return json;
            }
        } catch (Exception e) {
            mensagem.setMensagemServToClient("Erro ao obter todos: " + e.getMessage());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;
        }

    }
    
    
    
}
