package com.iamageo.ceep.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iamageo.ceep.R;
import com.iamageo.ceep.adapter.listener.OnItemClickListener;
import com.iamageo.ceep.model.Notas;

import java.util.Collections;
import java.util.List;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.NotaViewHolder> {


    private final Context context;
    private final List<Notas> notas;
    private OnItemClickListener onItemClickListener;

    public ListaNotasAdapter(Context context, List notas) {
        this.context = context;
        this.notas = notas;
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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

    public void altera(int posicao, Notas nota) {
        notas.set(posicao, nota);
        notifyDataSetChanged();
    }

    public void remove(int posicao) {
        notas.remove(posicao);
        notifyItemRemoved(posicao);
    }

    public void troca(int inicio, int fim) {
        Collections.swap(notas, inicio, fim);
        notifyItemMoved(inicio, fim);
    }

    class NotaViewHolder extends RecyclerView.ViewHolder {

        private final TextView titulo;
        private final TextView descricao;
        private Notas nota;

        public NotaViewHolder(@NonNull View itemView) {

            super(itemView);

            titulo = itemView.findViewById(R.id.item_nota_titulo);
            descricao  = itemView.findViewById(R.id.item_nota_descricao);

            itemView.setOnClickListener(
                    v -> onItemClickListener.onItemClick(nota, getAdapterPosition())
            );

        }

        public void vincula(Notas notas) {
            this.nota = notas;
            preencheCampo(notas);
        }

        private void preencheCampo(Notas notas) {
            titulo.setText(notas.getTitulo());
            descricao.setText(notas.getDescricao());
        }

    }

    public void adiciona(Notas nota) {
        notas.add(nota);
        notifyDataSetChanged();
    }

}