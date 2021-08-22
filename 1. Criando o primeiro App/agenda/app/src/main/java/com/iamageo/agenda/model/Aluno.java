package com.iamageo.agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {

    private int id = 0;
    private String nome;
    private String idade;

    public Aluno(String nome, String idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public Aluno() {

    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }


    public String getIdade() {
        return idade;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}
