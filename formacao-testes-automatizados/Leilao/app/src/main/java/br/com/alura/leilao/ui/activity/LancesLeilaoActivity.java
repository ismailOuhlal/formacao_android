package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

import br.com.alura.leilao.R;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;

public class LancesLeilaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lances_leilao);
        Intent dadosRecebidos = getIntent();
        if(dadosRecebidos.hasExtra("leilao")){
            Leilao leilao = (Leilao) dadosRecebidos.getSerializableExtra("leilao");

            inicializaLeilaoDescricao(leilao);

            nicializaLeilaoMaiorLance(leilao);

            inicializaLeilaoMenorLance(leilao);

            inicializaLeilaoMaioresLances(leilao);
        }
    }

    private void inicializaLeilaoMaioresLances(Leilao leilao) {
        TextView maioresLances = findViewById(R.id.lances_leilao_maiores_lances);
        String maioresLancesTexto = converteLeiloesEmTexto(leilao);
        maioresLances.setText(maioresLancesTexto);
    }

    @NonNull
    private String converteLeiloesEmTexto(Leilao leilao) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Lance lance: leilao.tresMaioresLances()) {
            stringBuilder.append(lance.getValor() + "\n");
        }
        return stringBuilder.toString();
    }

    private void inicializaLeilaoMenorLance(Leilao leilao) {
        TextView menorLance = findViewById(R.id.lances_leilao_menor_lance);
        menorLance.setText(String.valueOf(leilao.getMenorLance()));
    }

    private void nicializaLeilaoMaiorLance(Leilao leilao) {
        TextView maiorLance = findViewById(R.id.lances_leilao_maior_lance);
        maiorLance.setText(String.valueOf(leilao.getMaiorLance()));
    }

    private void inicializaLeilaoDescricao(Leilao leilao) {
        TextView descricao = findViewById(R.id.lances_leilao_descricao);
        descricao.setText(leilao.getDescricao());
    }

}
