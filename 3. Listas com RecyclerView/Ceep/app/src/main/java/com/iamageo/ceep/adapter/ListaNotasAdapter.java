package com.iamageo.ceep.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iamageo.ceep.R;
import com.iamageo.ceep.model.Notas;

import java.util.List;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.NotaViewHolder> {


    private final Context context;
    private final List<Notas> notas;

    public ListaNotasAdapter(Context context, List notas) {
        this.context = context;
        this.notas = notas;
    }


    @NonNull
    @Override
    public ListaNotasAdapter.NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaNotasAdapter.NotaViewHolder holder, int position) {
        Notas nota = notas.get(position);
        holder.vincula(nota);
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    class NotaViewHolder extends RecyclerView.ViewHolder {

        private final TextView titulo;
        private final TextView descricao;

        public NotaViewHolder(@NonNull View itemView) {

            super(itemView);

            titulo = itemView.findViewById(R.id.item_nota_titulo);
            descricao  = itemView.findViewById(R.id.item_nota_descricao);
        }

        public void vincula(Notas notas) {
            titulo.setText(notas.getTitulo());
            descricao.setText(notas.getDescricao());
        }

    }

    public void adiciona(Notas nota) {
        notas.add(nota);
        notifyDataSetChanged();
    }

}