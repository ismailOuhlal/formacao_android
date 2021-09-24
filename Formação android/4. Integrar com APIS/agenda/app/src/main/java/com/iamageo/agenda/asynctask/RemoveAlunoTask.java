package com.iamageo.agenda.asynctask;

import android.os.AsyncTask;

import com.iamageo.agenda.adapter.AdapterAluno;
import com.iamageo.agenda.database.RoomAlunoDAO;
import com.iamageo.agenda.model.Aluno;

import java.util.List;

public class RemoveAlunoTask extends AsyncTask<Void, Void, List<Aluno>> {


    private final RoomAlunoDAO dao;
    private final AdapterAluno adapter;
    private final Aluno aluno;

    public RemoveAlunoTask(RoomAlunoDAO dao, AdapterAluno adapter, Aluno aluno) {
        this.dao = dao;
        this.adapter = adapter;
        this.aluno = aluno;
    }

    @Override
    protected List<Aluno> doInBackground(Void... voids) {
        dao.remove(aluno);
        return null;
    }


    @Override
    protected void onPostExecute(List<Aluno> alunos) {
        super.onPostExecute(alunos);
        adapter.remove(aluno);
    }
}
