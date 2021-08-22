package com.iamageo.agenda;

import com.iamageo.agenda.model.Aluno;
import com.iamageo.agenda.model.AlunoDAO;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Leticia", "18"));
        dao.salva(new Aluno("Geovani", "23"));

    }
}
