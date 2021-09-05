package com.iamageo.ceep.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.iamageo.ceep.R;
import com.iamageo.ceep.adapter.ListaNotasAdapter;
import com.iamageo.ceep.dao.NotasDAO;
import com.iamageo.ceep.model.Notas;

import java.util.List;

import static com.iamageo.ceep.ui.Constants.CODIGO_INSERE_NOTA;
import static com.iamageo.ceep.ui.Constants.CODIGO_NOTA_RECEBIDA;
import static com.iamageo.ceep.ui.Constants.CODIGO_RECEBE_NOTA;

public class CeepMain extends AppCompatActivity {

    private ListaNotasAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Notas> todasNotas = pegaTodasNotas();
        configuraRecyclerView(todasNotas);
        congifuraBotaoInsereNotas();

    }

    private void congifuraBotaoInsereNotas() {
        configuraTextview();
    }

    private void configuraTextview() {
        TextView botaoInsereNota = findViewById(R.id.lista_notas_insere_nota);
        botaoInsereNota.setOnClickListener(view -> {
            VaiParaFormularioNotaActivity();
        });
    }

    private void VaiParaFormularioNotaActivity() {
        Intent iniciaFormularioNota = new Intent(CeepMain.this, InserirNota.class);
        startActivityForResult(iniciaFormularioNota, CODIGO_INSERE_NOTA);
    }

    private List<Notas> pegaTodasNotas() {
        NotasDAO dao = new NotasDAO();
        return dao.todos();
    }

    private void configuraRecyclerView(List<Notas> todasNotas) {
        RecyclerView listaNotas = findViewById(R.id.recyclerview);
        configuraAdapter(todasNotas, listaNotas);
    }

    private void configuraAdapter(List<Notas> todasNotas, RecyclerView listaNotas) {
        adapter = new ListaNotasAdapter(getApplicationContext(), todasNotas);
        listaNotas.setAdapter(adapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (eResultadoComNota(requestCode, resultCode, data)) {
            Notas nota = (Notas) data.getSerializableExtra(CODIGO_NOTA_RECEBIDA);
            adicionaNota(nota);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void adicionaNota(Notas nota) {
        new NotasDAO().insere(nota);
        adapter.adiciona(nota);
    }

    private boolean eResultadoComNota(int requestCode, int resultCode, @Nullable Intent data) {
        return eCodigoInsereNota(requestCode) && eCodigoRecebeNota(resultCode) && data.hasExtra(CODIGO_NOTA_RECEBIDA);
    }

    private boolean eCodigoRecebeNota(int resultCode) {
        return resultCode == CODIGO_RECEBE_NOTA;
    }

    private boolean eCodigoInsereNota(int requestCode) {
        return requestCode == CODIGO_INSERE_NOTA;
    }

}
