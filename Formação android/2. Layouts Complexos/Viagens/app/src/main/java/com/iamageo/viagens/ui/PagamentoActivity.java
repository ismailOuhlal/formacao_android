package com.iamageo.viagens.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.iamageo.viagens.R;
import com.iamageo.viagens.model.Pacote;
import com.iamageo.viagens.utils.MoedaUtil;


import static com.iamageo.viagens.ui.Constants.CHAVE_PACOTE;

public class PagamentoActivity extends AppCompatActivity {

    public static final String APP_BAR_TITLE = "Pagamento";
    private Button btn_finalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setTitle(APP_BAR_TITLE);

        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)) {
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);

            mostraPreco(pacote);

            configuraBotao(pacote);

        }


    }

    private void configuraBotao(Pacote pacote) {

        btn_finalizar = findViewById(R.id.botao_pagamento);

        btn_finalizar.setOnClickListener(v -> {

            vaiParaResumoCompra(pacote);

        });
    }

    private void vaiParaResumoCompra(Pacote pacote) {
        Intent i = new Intent(getApplicationContext(), ResumoCompraActivity.class);
        i.putExtra(CHAVE_PACOTE, pacote);
        startActivity(i);
    }

    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.compra_valor);
        String moedaFormatada = MoedaUtil.getMoedaFormatada(pacote.getPreco());
        preco.setText(moedaFormatada);
    }

}