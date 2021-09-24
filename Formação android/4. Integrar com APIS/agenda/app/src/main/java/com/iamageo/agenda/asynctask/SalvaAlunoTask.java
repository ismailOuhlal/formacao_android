package com.iamageo.agenda.asynctask;

import android.os.AsyncTask;

import com.iamageo.agenda.database.RoomAlunoDAO;
import com.iamageo.agenda.model.Aluno;

public class SalvaAlunoTask extends AsyncTask {
    private final RoomAlunoDAO dao;
    private final Aluno aluno;

    public SalvaAlunoTask(RoomAlunoDAO dao, Aluno aluno) {
        this.dao = dao;
        this.aluno = aluno;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        dao.salva(aluno); 
        return null;
    }
}
