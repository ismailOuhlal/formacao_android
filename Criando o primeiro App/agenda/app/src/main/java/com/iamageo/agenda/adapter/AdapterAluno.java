package com.iamageo.agenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iamageo.agenda.R;
import com.iamageo.agenda.model.Aluno;

import java.util.List;

public class AdapterAluno extends ArrayAdapter<Aluno> {

    private Context context_;

    public AdapterAluno(Context context, int layout_id, List<Aluno> alunosList) {
        super(context, layout_id, alunosList);
        context_ = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context_).inflate(R.layout.list_alunos, parent, false);
        }

        Aluno aluno_da_vez = (Aluno) getItem(position);

        TextView textViewNome = convertView.findViewById(R.id.adapter_aluno_nome);
        TextView textViewIdade = convertView.findViewById(R.id.adapter_aluno_idade);


        textViewNome.setText(aluno_da_vez.getNome());
        textViewIdade.setText(aluno_da_vez.getIdade());

        return convertView;
    }
}
