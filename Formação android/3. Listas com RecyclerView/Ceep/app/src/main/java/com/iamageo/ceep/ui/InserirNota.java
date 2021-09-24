package com.iamageo.ceep.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.iamageo.ceep.R;
import com.iamageo.ceep.dao.NotasDAO;
import com.iamageo.ceep.model.Notas;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import static com.iamageo.ceep.ui.Constants.CHAVE_POSICAO;
import static com.iamageo.ceep.ui.Constants.CODIGO_NOTA_RECEBIDA;
import static com.iamageo.ceep.ui.Constants.CODIGO_RECEBE_NOTA;
import static com.iamageo.ceep.ui.Constants.POSICAO_INVALIDA;

public class InserirNota extends AppCompatActivity {

    public static final String TITLE = "Insere nota";
    public static final String TITLE_NOTA = "Edita nota";
    private int posicao = POSICAO_INVALIDA;
    private TextView titulo;
    private TextView descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_nota);
        setTitle(TITLE);

        inicializaCampos();

        Intent dadosRecebidos = getIntent();
        if(dadosRecebidos.hasExtra(CODIGO_NOTA_RECEBIDA) && dadosRecebidos.hasExtra(CHAVE_POSICAO)){
            setTitle(TITLE_NOTA);
            Notas notaRecebida = (Notas) dadosRecebidos.getSerializableExtra(CODIGO_NOTA_RECEBIDA);
            posicao = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
            preencheCampos(notaRecebida);
        }

    }

    public void inicializaCampos() {
        titulo = findViewById(R.id.formulario_nota_titulo);
        descricao = findViewById(R.id.formulario_nota_descricao);
    }

    private void preencheCampos(Notas notaRecebida) {
        titulo.setText(notaRecebida.getTitulo());
        descricao.setText(notaRecebida.getDescricao());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (EmenuSalvaNota(item)) {
            Notas nota = criaNota();
            retornaNota(nota);

        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaNota(Notas notaCriada) {
        Intent resultadoInsercao = new Intent();
        resultadoInsercao.putExtra(CODIGO_NOTA_RECEBIDA, notaCriada);
        resultadoInsercao.putExtra(CHAVE_POSICAO, posicao);
        setResult(CODIGO_RECEBE_NOTA, resultadoInsercao);
        finish();
    }

    @NotNull
    private Notas criaNota() {
        return new Notas(titulo.getText().toString(), descricao.getText().toString());
    }

    private boolean EmenuSalvaNota(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_nota_ic_salva;
    }
}