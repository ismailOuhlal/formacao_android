package com.iamageo.agenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iamageo.agenda.R;
import com.iamageo.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AdapterAluno extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public AdapterAluno(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Aluno alunoDevolvido = alunos.get(posicao);
        vincula(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vincula(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.adapter_aluno_nome);
        nome.setText(aluno.getNomeCompleto());

        TextView idade = view.findViewById(R.id.adapter_aluno_idade);
        idade.setText(aluno.getIdade());

        TextView telefone = view.findViewById(R.id.adapter_aluno_telefone);
        telefone.setText(aluno.getTelefone());
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.list_alunos, viewGroup, false);
    }

    public void atualiza(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
