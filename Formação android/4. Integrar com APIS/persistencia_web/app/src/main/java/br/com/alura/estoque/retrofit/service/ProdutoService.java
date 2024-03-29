package br.com.alura.estoque.retrofit.service;

import java.util.List;

import br.com.alura.estoque.model.Produto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProdutoService {

    @GET("produto")
    Call <List<Produto>> buscaTodos();

    @POST("produto")
    Call<Produto> salva(@Body Produto produto);

    @POST("produto/{id}")
    Call<Produto> edita(@Path("id") long id, @Body Produto produto);

    @DELETE("produto/{id}")
    Call<Produto> remmove(@Path("id") long id);


}
