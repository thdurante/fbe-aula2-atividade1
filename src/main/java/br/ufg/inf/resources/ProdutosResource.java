package br.ufg.inf.resources;

import br.ufg.inf.model.Produto;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Path("produtos")
public class ProdutosResource {

    private List<Produto> produtos;
    private Gson gson;

    public ProdutosResource() {
        this.produtos = new ArrayList<>();
        this.gson = new Gson();

        this.produtos.add(new Produto(1, "Produto 1", "Descrição 1"));
        this.produtos.add(new Produto(2, "Produto 2", "Descrição 2"));
        this.produtos.add(new Produto(3, "Produto 3", "Descrição 3"));
        this.produtos.add(new Produto(4, "Produto 4", "Descrição 4"));
    }

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(this.produtos);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {

    }

    @GET
    @Path("{produtoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAluno(@PathParam("produtoId") String id){
        for (Produto p: this.produtos) {
            if(p.getId() == Integer.valueOf(id)) {
                return this.gson.toJson(p);
            }
        }
        return null;
    }
}
