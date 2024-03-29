package br.com.alura.estoque.repository;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.List;

import br.com.alura.estoque.asynctask.BaseAsyncTask;
import br.com.alura.estoque.database.dao.ProdutoDAO;
import br.com.alura.estoque.model.Produto;
import br.com.alura.estoque.retrofit.EstoqueRetrofit;
import br.com.alura.estoque.retrofit.callback.BaseCallback;
import br.com.alura.estoque.retrofit.callback.BaseCallbackNoReturn;
import br.com.alura.estoque.retrofit.service.ProdutoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ProdutoRepository {

    private final ProdutoDAO dao;
    private final ProdutoService produtoService;

    public ProdutoRepository(ProdutoDAO dao) {
        this.dao = dao;
         produtoService = new EstoqueRetrofit().getProdutoService();
    }

    //buscar produto
    public void buscaProdutos(DadosCarregadosCallback<List<Produto>> callback) {
        buscaProdutosInternos(callback);
    }

    public void buscaProdutosInternos(DadosCarregadosCallback<List<Produto>> callback) {
        new BaseAsyncTask<>(dao::buscaTodos,
                resultado -> {
                    //notifica que o dado está pronto
                    callback.quandoSucesso(resultado);
                    buscaProdutosApi(callback);
                }).execute();
    }

    private void buscaProdutosApi(DadosCarregadosCallback<List<Produto>> callback) {

        Call<List<Produto>> call = produtoService.buscaTodos();

        call.enqueue(new BaseCallback<>(new BaseCallback.RespostCallback<List<Produto>>() {
            @Override
            public void quandoSucesso(List<Produto> resultado) {
                atualizaInterno(resultado, callback);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));

    }

    //atualizar salva
    private void atualizaInterno(List<Produto> produtos, DadosCarregadosCallback<List<Produto>> callback) {
        new BaseAsyncTask<>(()->{
            dao.salva(produtos);
             return dao.buscaTodos();
        }, callback::quandoSucesso).execute();
    }

    public void salva(Produto produto, DadosCarregadosCallback<Produto> callback) {
        salvaNaApi(produto, callback);
    }

    private void salvaNaApi(Produto produto, DadosCarregadosCallback<Produto> callback) {
        Call<Produto> call = produtoService.salva(produto);

        call.enqueue(new BaseCallback<>(new BaseCallback.RespostCallback<Produto>() {
            @Override
            public void quandoSucesso(Produto resultado) {
                salvarInternamente(resultado, callback);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    private void salvarInternamente(Produto produto, DadosCarregadosCallback<Produto> callback) {

        new BaseAsyncTask<>(() -> {
            long id = dao.salva(produto);
            return dao.buscaProduto(id);
        }, callback::quandoSucesso);
    }

    //edita
    public void edita(Produto produto, DadosCarregadosCallback<Produto> callback) {
        editaNaApi(produto, callback);
    }

    private void editaNaApi(Produto produto, DadosCarregadosCallback<Produto> callback) {
        Call<Produto> call = produtoService.edita(produto.getId(), produto);

        call.enqueue(new BaseCallback<>(new BaseCallback.RespostCallback<Produto>() {
            @Override
            public void quandoSucesso(Produto resultado) {
                editaInternamente(produto, callback);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    private void editaInternamente(Produto produto, DadosCarregadosCallback<Produto> callback) {
        new BaseAsyncTask<>(() -> {
            dao.atualiza(produto);
            return produto;
        }, callback::quandoSucesso)
                .execute();
    }


    public void remove(Produto produto, DadosCarregadosCallback<Void> callback) {
        removeNaApi(produto, callback);

    }

    private void removeNaApi(Produto produto, DadosCarregadosCallback<Void> callback) {
        Call call = produtoService.remmove(produto.getId());
        call.enqueue(new BaseCallbackNoReturn(new BaseCallbackNoReturn.RespostCallback() {
            @Override
            public void quandoSucesso() {
                removeInterno(produto, callback);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    private void removeInterno(Produto produto, DadosCarregadosCallback<Void> callback) {
        new BaseAsyncTask<>(() -> {
            dao.remove(produto);
            return null;
        }, callback::quandoSucesso)
                .execute();
    }


    public interface DadosCarregadosCallback<T> {
        void quandoSucesso(T resultado);
        void quandoFalha(String erro);
    }


}
