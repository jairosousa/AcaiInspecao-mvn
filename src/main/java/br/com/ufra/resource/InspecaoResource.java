package br.com.ufra.resource;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.entidade.Inspecao;
import br.com.ufra.resource.pojo.InspecaoPOJO;
import br.com.ufra.resource.pojo.conversor.InspecaoConverter;
import br.com.ufra.util.Mensagem;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Path("/inspecao")
public class InspecaoResource extends Application {

    GenericDAOImpl<Inspecao> dao = new GenericDAOImpl();
    Inspecao inspecao;
    InspecaoPOJO inspecaoPOJO; 
    Gson gson = new Gson();
    Mensagem mensagem = Mensagem.getInstance();
    String json;
    List<InspecaoPOJO> listPOJO = new ArrayList<>();
    List<Inspecao> inspecoes = new ArrayList<>();
    //String listPOJO ;

    public InspecaoResource() {

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String obterId(@PathParam("id") Long id) {
            System.out.println("id"+id);
            inspecao = new Inspecao();
            inspecao = dao.obter(Inspecao.class, id);
            System.out.println("ob"+inspecao.getId());
        try {
            
            if (inspecao != null) {
                inspecaoPOJO = InspecaoConverter.toInspecaoPOJO(inspecao);
                json = gson.toJson(inspecaoPOJO);
                System.out.println("inspecao json" + json);
                return json;
            } else {
                mensagem.setMensagemServToClient("Elemento não encontrado");
                json = gson.toJson(mensagem);
                System.out.println(json);
                return json;
            }
        } catch (Exception e) {
            mensagem.setMensagemServToClient("Erro ao obter por id: " + e.getMessage());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String obterTodos() {

//        System.out.println(listPOJO.size());
//        System.out.println("json" + gson.toJson(listPOJO));
        try {
            inspecoes = dao.obterTodos(Inspecao.class);
            if (!inspecoes.isEmpty()) {
                return gson.toJson(InspecaoConverter.toInspecoesPOJO(inspecoes));
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(Inspecao entity) {
        try {
            dao.criar(entity);
            mensagem.setMensagemServToClient(Mensagem.getMensagemOperacao());
            json = gson.toJson(mensagem);

            System.out.println("json " + json);

            return json;
        } catch (Exception e) {
            mensagem.setMensagemServToClient("Erro ao salvar: " + e.getMessage());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;

        }

    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String delete(@PathParam("id") Long id) {
        try {
            inspecao = dao.obter(Inspecao.class, id);
            dao.excluir(inspecao);
            mensagem.setMensagemServToClient(Mensagem.getMensagemOperacao());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;
        } catch (Exception e) {
            mensagem.setMensagemServToClient("Erro ao excluir: " + e.getMessage());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String atualizar(@PathParam("id") Long id) {
        try {
            inspecao = dao.obter(Inspecao.class, id);
            dao.atualizar(inspecao);
            mensagem.setMensagemServToClient(Mensagem.getMensagemOperacao());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;
        } catch (Exception e) {
            mensagem.setMensagemServToClient("Erro ao atualizar: " + e.getMessage());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;
        }
    }

}
