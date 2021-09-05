package com.iamageo.ceep.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.iamageo.ceep.R;
import com.iamageo.ceep.dao.NotasDAO;
import com.iamageo.ceep.model.Notas;

import org.jetbrains.annotations.NotNull;

import static com.iamageo.ceep.ui.Constants.CODIGO_NOTA_RECEBIDA;
import static com.iamageo.ceep.ui.Constants.CODIGO_RECEBE_NOTA;

public class InserirNota extends AppCompatActivity {

    public static final String TITLE = "Inserir nota";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_nota);
        setTitle(TITLE);

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
        Intent intent = new Intent();
        setResult(CODIGO_RECEBE_NOTA, intent.putExtra(CODIGO_NOTA_RECEBIDA, notaCriada));
        finish();
    }

    @NotNull
    private Notas criaNota() {
        EditText titulo = findViewById(R.id.formulario_nota_titulo);
        EditText descricao = findViewById(R.id.formulario_nota_descricao);
        return new Notas(titulo.getText().toString(), descricao.getText().toString());
    }

    private boolean EmenuSalvaNota(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_nota_ic_salva;
    }
}