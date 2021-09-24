package com.iamageo.ceep.model;

import java.io.Serializable;

public class Notas implements Serializable {

    private final String titulo;
    private final String descricao;

    public Notas(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

}