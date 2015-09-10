
package br.com.ufra.resource;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.entidade.Vistoria;
import br.com.ufra.resource.pojo.VistoriaPOJO;
import br.com.ufra.resource.pojo.conversor.VistoriaConverter;
import br.com.ufra.rn.InspecaoRN;
import br.com.ufra.rn.VistoriaRN;
import br.com.ufra.util.Mensagem;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Path("/vistoria")
public class VistoriaResource extends Application{
    GenericDAOImpl<Vistoria> dao = new GenericDAOImpl();
    VistoriaRN rnVistoria = new VistoriaRN();
    InspecaoRN rnInspecao = new InspecaoRN();
    Vistoria vistoria;
    VistoriaPOJO vistoriaPOJO; 
    Gson gson = new Gson();
    Mensagem mensagem = Mensagem.getInstance();
    String json;
    List<VistoriaPOJO> listPOJO = new ArrayList<>();
    List<Vistoria> vistorias = new ArrayList<>();
    
    public VistoriaResource(){
        
    }
    
    @Path("/salvar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public String salvar(VistoriaPOJO vistoriaPOJO) {
        try {
            System.out.println("pojo "+vistoriaPOJO.getDataVistoria());
            vistoria = new Vistoria();
            vistoria = VistoriaConverter.fromVistoriaPOJO(vistoriaPOJO);
            System.out.println("inspecoes: "+vistoria.getInspecaoList().size());
           
            if (rnInspecao.salvarInspecaoApartirInspecoes(vistoria , vistoria.getInspecaoList())){
            mensagem.setMensagemServToClient("Sucesso, a vistoria foi salva !");            
            System.out.println("json "+gson.toJson(mensagem));            
            return gson.toJson(mensagem);                
            } else {
                mensagem.setMensagemServToClient("Erro, não foi possível salvar a vistoria !");
               return gson.toJson(mensagem);
            }
        } catch (Exception e) {
            mensagem.setMensagemServToClient(e.getMessage());   
            return gson.toJson(mensagem);
        }
        
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
