
package br.com.ufra.resource;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.entidade.Tecnico;
import br.com.ufra.resource.pojo.TecnicoPOJO;
import br.com.ufra.resource.pojo.conversor.TecnicoConverter;
import br.com.ufra.rn.TecnicoRN;
import br.com.ufra.util.Mensagem;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import org.primefaces.json.JSONException;

@Path("/tecnico")
public class TecnicoResource extends Application{
    
    
     
    TecnicoRN rnTecnico = new TecnicoRN();
    Tecnico tecnico;
    TecnicoPOJO tecnicoPOJO; 
    Gson gson = new Gson();
    Mensagem mensagem = Mensagem.getInstance();
    String json;
    List<TecnicoPOJO> listPOJO = new ArrayList<>();
    List<Tecnico> tecnicos;
    
    public TecnicoResource(){        

    }
    
    @Path("/logar")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String logar(@QueryParam("m") String matricula, @QueryParam("s") String senha) throws JSONException{
           // String json;
        try {
           

            tecnico = new Tecnico();
            tecnico = rnTecnico.obterPorMatriculaSenha(matricula, senha);
             
            if (tecnico != null){
               return gson.toJson(TecnicoConverter.toTecnicoPOJO(tecnico));
            } else {
              return null;
            }

        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String obterTodos() {
        try {
            tecnicos = new ArrayList<>();
            tecnicos = rnTecnico.obterTodos();
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
