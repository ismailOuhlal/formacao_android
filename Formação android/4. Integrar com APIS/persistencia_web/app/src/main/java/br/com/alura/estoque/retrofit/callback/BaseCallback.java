package br.com.alura.estoque.retrofit.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class BaseCallback <T> implements Callback<T> {

    private final RespostCallback<T> callback;

    public BaseCallback(RespostCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    @EverythingIsNonNull
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()) {
            T resultado = response.body();
            if(resultado != null) {
                //notifica resposta
                callback.quandoSucesso(resultado);
            }

        } else {
            //notifica falha
            callback.quandoFalha("Ocorreu um erro tente novamente");
        }
    }

    @Override
    @EverythingIsNonNull
    public void onFailure(Call<T> call, Throwable t) {
        callback.quandoFalha("Ocorreu um erro tente novamente");
    }

    public interface RespostCallback <T> {

        void quandoSucesso(T resultado);
        void quandoFalha(String erro);

    }


}
