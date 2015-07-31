/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.resource;

import br.com.ufra.dao.GenericDAOImpl;

import br.com.ufra.util.Mensagem;
import com.google.gson.Gson;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

public class GenericResource<T> extends Application {

    GenericDAOImpl<T> dao = new GenericDAOImpl();
    Class<T> classe;
    Object objeto;
    Gson gson = new Gson();
    Mensagem mensagem = Mensagem.getInstance();
    String json;
//    List<?> listPOJO = new ArrayList<>();
    String listPOJO ;
    
    public GenericResource() {

    }

    public GenericResource(Class<T> classe, String listPOJO) {
        this.classe = classe;
        this.listPOJO = listPOJO;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String obterId(@PathParam("id") Long id) {
        try {
            objeto = new Object();
            objeto = dao.obter(classe, id);
            if (objeto != null) {
                json = gson.toJson(objeto);
                System.out.println("objeto json" + json);
                return json;
            } else {
                mensagem.setMensagemServClient(Mensagem.getMensagemOperacao());
                json = gson.toJson(mensagem);
                System.out.println(json);
                return json;
            }
        } catch (Exception e) {
            mensagem.setMensagemServClient("Erro: " + e.getMessage());
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

            if (!dao.obterTodos(classe).isEmpty()) {

                //
//                GenericDAOImpl<Inspecao> dao2 = new GenericDAOImpl<>();
//                List<Inspecao> inspecoes = new ArrayList<>();
//                inspecoes = dao2.obterTodos(Inspecao.class);
//                inspecoesPOJO = InspecaoConverter.toInspecoesPOJO(list);
                return gson.toJson(listPOJO);
            } else {
                mensagem.setMensagemServClient("A lista está vazia!");
                json = gson.toJson(mensagem);
                System.out.println(json);
                return json;
            }
        } catch (Exception e) {
            mensagem.setMensagemServClient("Erro: " + e.getMessage());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(T entity) {
        try {
            dao.criar(entity);
            mensagem.setMensagemServClient(Mensagem.getMensagemOperacao());
            json = gson.toJson(mensagem);

            System.out.println("json " + json);

            return json;
        } catch (Exception e) {
            mensagem.setMensagemServClient("Erro: " + e.getMessage());
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
            T o = dao.obter(classe, id);
            dao.excluir(o);
            mensagem.setMensagemServClient(Mensagem.getMensagemOperacao());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;
        } catch (Exception e) {
            mensagem.setMensagemServClient("Erro: " + e.getMessage());
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
            T o = dao.obter(classe, id);
            dao.atualizar(o);
            mensagem.setMensagemServClient(Mensagem.getMensagemOperacao());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;
        } catch (Exception e) {
            mensagem.setMensagemServClient("Erro: " + e.getMessage());
            json = gson.toJson(mensagem);

            System.out.println(json);

            return json;
        }
    }

    public void escolhaPOJO() {

    }

}
