package com.iamageo.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.iamageo.agenda.adapter.AdapterAluno;
import com.iamageo.agenda.asynctask.BuscaAlunosTask;
import com.iamageo.agenda.asynctask.RemoveAlunoTask;
import com.iamageo.agenda.database.AgendaDatabase;
import com.iamageo.agenda.database.RoomAlunoDAO;
import com.iamageo.agenda.model.Aluno;
import com.iamageo.agenda.model.AlunoDAO;

public class ListaAlunosView {

    private final AdapterAluno adapter;
    private final RoomAlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new AdapterAluno(this.context);
        AgendaDatabase database = AgendaDatabase.getInstance(context);
        dao = database.getRoomAlunoDAO();


    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    remove(alunoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {

        new BuscaAlunosTask(dao, adapter).execute();

    }

    private void remove(Aluno aluno) {
        new RemoveAlunoTask(dao, adapter, aluno).execute();

    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }
}