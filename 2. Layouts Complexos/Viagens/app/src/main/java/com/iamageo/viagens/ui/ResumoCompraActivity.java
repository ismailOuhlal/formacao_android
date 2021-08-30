package com.iamageo.viagens.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.iamageo.viagens.R;
import com.iamageo.viagens.model.Pacote;
import com.iamageo.viagens.utils.DataUtils;
import com.iamageo.viagens.utils.MoedaUtil;
import com.iamageo.viagens.utils.ResourcesUtil;

import static com.iamageo.viagens.ui.Constants.CHAVE_PACOTE;

public class ResumoCompraActivity extends AppCompatActivity {

    public static final String RESUMO_DA_COMPRA = "Resumo da compra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);
        setTitle(RESUMO_DA_COMPRA);

        carregaPacote();

    }

    private void carregaPacote() {

        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)) {
            Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);

            inicializaCampos(pacote);

        }

    }

    private void inicializaCampos(Pacote pacote) {
        mostraEstado(pacote);
        mostraImagem(pacote);
        mostraPreco(pacote);
        periodoEmTextoFormatado(pacote);
    }

    private void periodoEmTextoFormatado(Pacote pacote) {
        TextView data = findViewById(R.id.resumo_data_final);
        String periodoEmTexto = DataUtils.periodoEmTexto(pacote.getDias());
        data.setText(periodoEmTexto);
    }

    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.resumo_preco_final);
        String moedaFormatada = MoedaUtil.getMoedaFormatada(pacote.getPreco());
        preco.setText(moedaFormatada);
    }


    private void mostraImagem(Pacote pacote) {
        ImageView imagem = findViewById(R.id.resumo_imagem_final);
        Drawable drawable = ResourcesUtil.getDrawable(this, pacote.getImagem());
        imagem.setImageDrawable(drawable);
    }

    private void mostraEstado(Pacote pacote) {
        TextView estado = findViewById(R.id.resumo_estado_final);
        estado.setText(pacote.getLocal());
    }
}