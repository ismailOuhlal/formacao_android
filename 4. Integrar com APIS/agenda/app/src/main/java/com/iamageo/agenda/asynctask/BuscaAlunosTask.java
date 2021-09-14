package com.iamageo.agenda.asynctask;

import android.os.AsyncTask;

import com.iamageo.agenda.adapter.AdapterAluno;
import com.iamageo.agenda.database.RoomAlunoDAO;
import com.iamageo.agenda.model.Aluno;

import java.util.List;

public class BuscaAlunosTask  extends AsyncTask<Void, Void, List<Aluno>> {

    private final RoomAlunoDAO dao;
    private final AdapterAluno adapter;

    public BuscaAlunosTask(RoomAlunoDAO dao, AdapterAluno adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return dao.todos();
    }

    @Override
    protected void onPostExecute(List<Aluno> todosAlunos) {
        super.onPostExecute(todosAlunos);
        adapter.atualiza((List<Aluno>) todosAlunos);

    }
}
