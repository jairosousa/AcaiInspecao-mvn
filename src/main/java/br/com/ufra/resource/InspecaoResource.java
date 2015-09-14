package br.com.ufra.resource;

import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.entidade.Inspecao;
import br.com.ufra.entidade.Tecnico;
import br.com.ufra.entidade.Vistoria;
import br.com.ufra.resource.pojo.InspecaoPOJO;
import br.com.ufra.resource.pojo.conversor.InspecaoConverter;
import br.com.ufra.resource.pojo.conversor.VistoriaConverter;
import br.com.ufra.resource.pojo.lista.InspecaoListaPOJO;
import br.com.ufra.rn.EstabelecimentoRN;
import br.com.ufra.rn.InspecaoRN;
import br.com.ufra.rn.TecnicoRN;
import br.com.ufra.rn.VistoriaRN;
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

    InspecaoRN rnInspecao = new InspecaoRN();
    VistoriaRN rnVistoria = new VistoriaRN();
    TecnicoRN rnTecnico = new TecnicoRN();
    EstabelecimentoRN rnEstabelecimento = new EstabelecimentoRN();
    
    Estabelecimento estabelecimento;
    Tecnico tecnico1, tecnico2;
    Inspecao inspecao;
    InspecaoPOJO inspecaoPOJO;
    Gson gson = new Gson();
    Mensagem mensagem = Mensagem.getInstance();
    String json;
    List<InspecaoPOJO> listPOJO = new ArrayList<>();
    List<Inspecao> inspecoes = new ArrayList<>();
    Vistoria vistoria;
    //String listPOJO ;

    public InspecaoResource() {

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String obterId(@PathParam("id") Integer id) {
        System.out.println("id" + id);
        inspecao = new Inspecao();
        inspecao = rnInspecao.obter(id);
        System.out.println("ob" + inspecao.getId());
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
            inspecoes = rnInspecao.obterTodos();
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

    @Path("/salvar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String salvar(InspecaoListaPOJO inspecaoListaPOJO) {
        try {
            vistoria = VistoriaConverter.fromVistoriaPOJO(inspecaoListaPOJO.getInspecoesPOJO().get(0).getVistoriaPOJO());
            inspecoes = InspecaoConverter.fromInspecoesPOJO(inspecaoListaPOJO.getInspecoesPOJO());
            tecnico1 = rnTecnico.obter(vistoria.getTecnico1().getId());
            tecnico2 = rnTecnico.obter(vistoria.getTecnico2().getId());
            rnEstabelecimento.salvar(vistoria.getEstabelecimento());
            vistoria.setId(null);
            vistoria.setTecnico1(tecnico1);
            vistoria.setTecnico2(tecnico2);
            
          if (rnInspecao.salvarInspecaoApartirInspecoes(vistoria, inspecoes)){
        
            mensagem.setMensagemServToClient("Operação realizada com sucesso !");            
          System.out.println("json "+gson.toJson(mensagem));            
          return gson.toJson(mensagem);                
          } else {
              mensagem.setMensagemServToClient("Erro, não foi possível concluir a operação. Tente novamente!");
               return gson.toJson(mensagem);
            }

        } catch (Exception e) {
            System.out.println("Erro " + e.toString());
            mensagem.setMensagemServToClient(e.toString());
            return gson.toJson(mensagem);
        }

    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String delete(@PathParam("id") Integer id) {
        try {
            inspecao = rnInspecao.obter(id);
            rnInspecao.excluir(inspecao);
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
    public String atualizar(@PathParam("id") Integer id) {
        try {
            inspecao = rnInspecao.obter(id);
            rnInspecao.salvar(inspecao);
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
