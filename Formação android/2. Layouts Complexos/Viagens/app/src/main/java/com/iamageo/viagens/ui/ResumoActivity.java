package com.iamageo.viagens.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.iamageo.viagens.R;
import com.iamageo.viagens.model.Pacote;
import com.iamageo.viagens.utils.DataUtils;
import com.iamageo.viagens.utils.DiasUtil;
import com.iamageo.viagens.utils.MoedaUtil;
import com.iamageo.viagens.utils.ResourcesUtil;

import static com.iamageo.viagens.ui.Constants.CHAVE_PACOTE;

public class ResumoActivity extends AppCompatActivity {

    public static final String RESUMO_DO_PEDIDO = "Resumo do pacote";
    private Button btn_pagamento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        setTitle(RESUMO_DO_PEDIDO);

        carregaPacote();

    }

    private void carregaPacote() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)) {
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);

            inicializaCampos(pacote);
            inicializaBotao(pacote);

        }
    }

    private void inicializaBotao(Pacote pacote) {
        btn_pagamento = findViewById(R.id.botao_pagamento);

        btn_pagamento.setOnClickListener(v -> {

            vaiParaPagamento(pacote);

        });
    }

    private void vaiParaPagamento(Pacote pacote) {
        Intent i = new Intent(getApplicationContext(), PagamentoActivity.class);
        i.putExtra(CHAVE_PACOTE, pacote);
        startActivity(i);
    }

    private void inicializaCampos(Pacote pacote) {
        mostraEstado(pacote);
        mostraImagem(pacote);
        mostraDias(pacote);
        mostraPreco(pacote);
        periodoEmTextoFormatado(pacote);
    }

    private void periodoEmTextoFormatado(Pacote pacote) {
        TextView data = findViewById(R.id.resumo_data);
        String periodoEmTexto = DataUtils.periodoEmTexto(pacote.getDias());
        data.setText(periodoEmTexto);
    }

    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.resumo_preco);
        String moedaFormatada = MoedaUtil.getMoedaFormatada(pacote.getPreco());
        preco.setText(moedaFormatada);
    }

    private void mostraDias(Pacote pacote) {
        TextView dias = findViewById(R.id.resumo_dias);
        String diasEmTexto = DiasUtil.getDiasEmTexto(pacote.getDias());
        dias.setText(diasEmTexto);
    }

    private void mostraImagem(Pacote pacote) {
        ImageView imagem = findViewById(R.id.resumo_imv);
        Drawable drawable = ResourcesUtil.getDrawable(this, pacote.getImagem());
        imagem.setImageDrawable(drawable);
    }

    private void mostraEstado(Pacote pacote) {
        TextView estado = findViewById(R.id.resumo_estado);
        estado.setText(pacote.getLocal());
    }
}
