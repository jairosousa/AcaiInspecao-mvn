
package br.com.ufra.resource;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.entidade.Tecnico;
import br.com.ufra.resource.pojo.TecnicoPOJO;
import br.com.ufra.resource.pojo.conversor.TecnicoConverter;
import br.com.ufra.util.Mensagem;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Path("/tecnico")
public class TecnicoResource extends Application{
    
    
    GenericDAOImpl<Tecnico> dao = new GenericDAOImpl();
    Tecnico tecnico;
    TecnicoPOJO tecnicoPOJO; 
    Gson gson = new Gson();
    Mensagem mensagem = Mensagem.getInstance();
    String json;
    List<TecnicoPOJO> listPOJO = new ArrayList<>();
    List<Tecnico> tecnicos = new ArrayList<>();
    
    public TecnicoResource(){
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String obterTodos() {
        try {
            tecnicos = dao.obterTodos(Tecnico.class);
            if (!tecnicos.isEmpty()) {
                return gson.toJson(TecnicoConverter.toTecnicosPOJO(tecnicos));
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
