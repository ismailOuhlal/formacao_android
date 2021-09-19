package br.com.alura.estoque.retrofit.callback;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;
import retrofit2.internal.EverythingIsNonNull;

public class BaseCallbackNoReturn implements Callback<Void>  {

    private final RespostCallback callback;

    public BaseCallbackNoReturn(RespostCallback callback) {
        this.callback = callback;
    }

    @Override
    @EverythingIsNonNull
    public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.isSuccessful()) {
            //notifica resposta
            callback.quandoSucesso();

        } else {
            //notifica falha
            callback.quandoFalha("Ocorreu um erro tente novamente");
        }
    }

    @Override
    @EverythingIsNonNull
    public void onFailure(Call<Void> call, Throwable t) {
        callback.quandoFalha("Ocorreu um erro tente novamente");
    }

    public interface RespostCallback {

        void quandoSucesso();
        void quandoFalha(String erro);

    }


}
