package com.iamageo.agenda.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.iamageo.agenda.R;
import com.iamageo.agenda.model.Aluno;
import com.iamageo.agenda.model.AlunoDAO;

public class ActivityCadastro extends AppCompatActivity {


    private EditText nome;
    private EditText idade;
    private Button cadastro_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Cadastrar novo aluno");

        final AlunoDAO dao = new AlunoDAO();

        nome = findViewById(R.id.cadastro_nome);
        idade = findViewById(R.id.cadastro_idade);
        cadastro_btn = findViewById(R.id.cadastro_btn);

        cadastro_btn.setOnClickListener(v -> {
            String nome_text;
            String idade_text;

            nome_text = nome.getText().toString();
            idade_text = idade.getText().toString();

            Aluno aluno = new Aluno(nome_text, idade_text);
            dao.save(aluno);

            startActivity(new Intent(this, MainActivity.class));
            finish();


        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}