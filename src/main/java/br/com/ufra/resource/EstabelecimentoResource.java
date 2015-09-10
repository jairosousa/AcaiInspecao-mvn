/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.resource;

import br.com.ufra.dao.GenericDAOImpl;
import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.resource.pojo.EstabelecimentoPOJO;
import br.com.ufra.resource.pojo.conversor.EstabelecimentoConverter;
import br.com.ufra.util.Mensagem;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Path("/estabelecimento")
public class EstabelecimentoResource extends Application {
    
    GenericDAOImpl<Estabelecimento> dao = new GenericDAOImpl();
    Estabelecimento estabelecimento;
    EstabelecimentoPOJO estabelecimentoPOJO; 
    Gson gson = new Gson();
    Mensagem mensagem = Mensagem.getInstance();
    String json;
    List<EstabelecimentoPOJO> listPOJO = new ArrayList<>();
    List<Estabelecimento> estabelecimentos = new ArrayList<>();

    public EstabelecimentoResource() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String obterTodos() {
       try {
            estabelecimentos = dao.obterTodos(Estabelecimento.class);
            if (!estabelecimentos.isEmpty()) {

               json = gson.toJson(EstabelecimentoConverter.toEstabelecimentosPOJO(estabelecimentos));
                System.out.println("consultando a lista"+json);
                return gson.toJson(EstabelecimentoConverter.toEstabelecimentosPOJO(estabelecimentos));
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
