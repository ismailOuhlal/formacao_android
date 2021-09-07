package com.iamageo.ceep.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.iamageo.ceep.R;
import com.iamageo.ceep.adapter.ListaNotasAdapter;
import com.iamageo.ceep.dao.NotasDAO;
import com.iamageo.ceep.helper.NotaItemTouchHelperCallback;
import com.iamageo.ceep.model.Notas;

import java.util.List;

import static com.iamageo.ceep.ui.Constants.CHAVE_POSICAO;
import static com.iamageo.ceep.ui.Constants.CODIGO_ALTERA_NOTA;
import static com.iamageo.ceep.ui.Constants.CODIGO_INSERE_NOTA;
import static com.iamageo.ceep.ui.Constants.CODIGO_NOTA_RECEBIDA;
import static com.iamageo.ceep.ui.Constants.CODIGO_RECEBE_NOTA;
import static com.iamageo.ceep.ui.Constants.POSICAO_INVALIDA;

public class CeepMain extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Notas";
    private ListaNotasAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(TITLE_APPBAR);

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
        dao.insere(new Notas("teste 1", "teste 1"));
        dao.insere(new Notas("teste 2", "teste 2"));
        dao.insere(new Notas("teste 3", "teste 3"));
        dao.insere(new Notas("teste 4", "teste 4"));
        return dao.todos();
    }

    private void configuraRecyclerView(List<Notas> todasNotas) {
        RecyclerView listaNotas = findViewById(R.id.recyclerview);
        configuraAdapter(todasNotas, listaNotas);
        configuraItemTouchHelper(listaNotas);
    }

    private void configuraItemTouchHelper(RecyclerView listaNotas) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NotaItemTouchHelperCallback(adapter));
        itemTouchHelper.attachToRecyclerView(listaNotas);
    }

    private void configuraAdapter(List<Notas> todasNotas, RecyclerView listaNotas) {
        adapter = new ListaNotasAdapter(getApplicationContext(), todasNotas);
        listaNotas.setAdapter(adapter);
        adapter.setItemClickListener(this::VaiParaFormularioNotaActivityComNota);
    }

    private void VaiParaFormularioNotaActivityComNota(Notas nota, int posicao) {
        Intent abrirFormularioComNota = new Intent(CeepMain.this, InserirNota.class);
        abrirFormularioComNota.putExtra(CODIGO_NOTA_RECEBIDA, nota);
        abrirFormularioComNota.putExtra(CHAVE_POSICAO, posicao);
        startActivityForResult(abrirFormularioComNota, CODIGO_RECEBE_NOTA);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (eResultadoComNota(requestCode, resultCode, data)) {
            Notas nota = (Notas) data.getSerializableExtra(CODIGO_NOTA_RECEBIDA);
            adicionaNota(nota);
        }
        if (eResultadoAlteraNota(requestCode, resultCode, data)) {
            Notas nota = (Notas) data.getSerializableExtra(CODIGO_NOTA_RECEBIDA);
            int posicaoRecebida = data.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);

            if(ePosicaoValida(posicaoRecebida)) {
                altera(nota, posicaoRecebida);
            } else {
                Toast.makeText(this, "Ocorreu um problema na alteração da nota", Toast.LENGTH_SHORT).show();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void altera(Notas nota, int posicaoRecebida) {
        new NotasDAO().altera(posicaoRecebida, nota);
        adapter.altera(posicaoRecebida, nota);
    }

    private boolean ePosicaoValida(int posicaoRecebida) {
        return posicaoRecebida > POSICAO_INVALIDA;
    }

    private boolean eResultadoAlteraNota(int requestCode, int resultCode, @Nullable Intent data) {
        return eCodigoAlteraNota(requestCode) && eCodigoAlteraNota(resultCode) && temNota(data);
    }

    private boolean eCodigoAlteraNota(int requestCode) {
        return requestCode == CODIGO_ALTERA_NOTA;
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

    private boolean temNota(Intent data) {
        return data.hasExtra(CODIGO_NOTA_RECEBIDA);
    }

}
