
package br.com.ufra.resource;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.entidade.Vistoria;
import br.com.ufra.resource.pojo.VistoriaPOJO;
import br.com.ufra.resource.pojo.conversor.VistoriaConverter;
import br.com.ufra.util.Mensagem;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Path("/vistoria")
public class VistoriaResource extends Application{
    GenericDAOImpl<Vistoria> dao = new GenericDAOImpl();
    Vistoria vistoria;
    VistoriaPOJO vistoriaPOJO; 
    Gson gson = new Gson();
    Mensagem mensagem = Mensagem.getInstance();
    String json;
    List<VistoriaPOJO> listPOJO = new ArrayList<>();
    List<Vistoria> vistorias = new ArrayList<>();
    
    public VistoriaResource(){
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String obterTodos() {
        try {
            vistorias = dao.obterTodos(Vistoria.class);
            if (!vistorias.isEmpty()) {
                return gson.toJson(VistoriaConverter.toVistoriasPOJO(vistorias));
            } else {
                mensagem.setMensagemServClient("A lista está vazia!");
                json = gson.toJson(mensagem);
                System.out.println(json);
                return json;
            }
        } catch (Exception e) {
            mensagem.setMensagemServClient("Erro ao obter todos: " + e.getMessage());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;
        }

    }
}
