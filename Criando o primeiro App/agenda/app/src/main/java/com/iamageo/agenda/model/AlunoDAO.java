package com.iamageo.agenda.model;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();

    public void save(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> getAll() {
        return new ArrayList<>(alunos);
    }

}
