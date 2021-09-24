package com.iamageo.ceep.dao;

import com.iamageo.ceep.model.Notas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NotasDAO {

    private final static ArrayList<Notas> notas = new ArrayList<>();

    public List<Notas> todos() {
        return (List<Notas>) notas.clone();
    }

    public void insere(Notas... notas) {
        NotasDAO.notas.addAll(Arrays.asList(notas));
    }

    public void altera(int posicao, Notas nota) {
        notas.set(posicao, nota);
    }

    public void remove(int posicao) {
        notas.remove(posicao);
    }

    public void troca(int posicaoInicio, int posicaoFim) {
        Collections.swap(notas, posicaoInicio, posicaoFim);
    }

    public void removeTodos() {
        notas.clear();
    }
}