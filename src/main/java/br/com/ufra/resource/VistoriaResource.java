
package br.com.ufra.resource;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.entidade.Vistoria;
import br.com.ufra.resource.pojo.VistoriaPOJO;
import br.com.ufra.resource.pojo.conversor.VistoriaConverter;
import br.com.ufra.rn.EstabelecimentoRN;
import br.com.ufra.rn.InspecaoRN;
import br.com.ufra.rn.VistoriaRN;
import br.com.ufra.util.Mensagem;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Path("/vistoria")
public class VistoriaResource extends Application{
    GenericDAOImpl<Vistoria> dao = new GenericDAOImpl();
    VistoriaRN rnVistoria = new VistoriaRN();
    InspecaoRN rnInspecao = new InspecaoRN();
    EstabelecimentoRN rnEstabelecimento = new EstabelecimentoRN();
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
    @Path("{ide}")    
    public String obterVistoriasEstabelecimento(@PathParam("ide") Integer idEstabelecimento) {
        try {
            Estabelecimento estabelecimento = rnEstabelecimento.obter(idEstabelecimento);
            vistorias = rnVistoria.obterVistoriasPorEstabelecimento(estabelecimento);
            if (!vistorias.isEmpty()) {
                return gson.toJson(VistoriaConverter.toVistoriasPOJO(vistorias));
            } else {
                mensagem.setMensagemServToClient("A lista está vazia!");
                json = gson.toJson(mensagem);
                System.out.println(json);
                return json;
            }
        } catch (Exception e) {
            mensagem.setMensagemServToClient("Erro ao obter vistorias do estabelecimento: " + e.getMessage());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;
        }

    }
    
 
}
