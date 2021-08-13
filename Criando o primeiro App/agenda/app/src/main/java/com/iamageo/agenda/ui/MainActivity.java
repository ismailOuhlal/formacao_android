package com.iamageo.agenda.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.iamageo.agenda.R;
import com.iamageo.agenda.adapter.AdapterAluno;
import com.iamageo.agenda.model.Aluno;
import com.iamageo.agenda.model.AlunoDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView myListview;

    AdapterAluno _adapter;

    FloatingActionButton floatingActionButton;

    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lista de alunos");

        myListview = findViewById(R.id.main_listview_alunos);
        floatingActionButton = findViewById(R.id.main_fab);

        final AlunoDAO alunoDAO = new AlunoDAO();

        _adapter = new AdapterAluno(this, R.layout.list_alunos, alunoDAO.getAll());

        myListview.setAdapter(_adapter);


        floatingActionButton.setOnClickListener(v -> {
            Intent i = new Intent(this, ActivityCadastro.class);
            startActivity(i);
        });

    }

}
