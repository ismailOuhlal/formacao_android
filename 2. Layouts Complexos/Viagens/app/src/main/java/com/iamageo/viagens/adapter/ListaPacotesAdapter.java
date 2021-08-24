package com.iamageo.viagens.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.iamageo.viagens.R;
import com.iamageo.viagens.model.Pacote;
import com.iamageo.viagens.utils.DiasUtil;
import com.iamageo.viagens.utils.MoedaUtil;
import com.iamageo.viagens.utils.ResourcesUtil;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ListaPacotesAdapter extends BaseAdapter {

    private final List pacotes;
    private final Context context;

    public ListaPacotesAdapter(List<Pacote> pacotes, Context context) {
        this.pacotes = pacotes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pacotes.size();
    }

    @Override
    public Object getItem(int position) {
        return pacotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.pacotes_item, parent, false);

        Pacote pacote = (Pacote) pacotes.get(position);

        setLocal(view, pacote);

        setImagem(view, pacote);

        setDias(view, pacote);

        setPreco(view, pacote);

        return view;
    }

    private void setPreco(View view, Pacote pacote) {
        TextView preco = view.findViewById(R.id.item_preco);
        String moedaFormatada = MoedaUtil.getMoedaFormatada(pacote.getPreco());
        preco.setText(moedaFormatada);
    }

    private void setDias(View view, Pacote pacote) {
        TextView dias = view.findViewById(R.id.item_dias);
        String diasEmTexto = DiasUtil.getDiasEmTexto(pacote.getDias());
        dias.setText(diasEmTexto);
    }

    private void setImagem(View view, Pacote pacote) {
        ImageView imagem = view.findViewById(R.id.item_imv);
        Drawable drawable = ResourcesUtil.getDrawable(context,pacote.getImagem());
        imagem.setImageDrawable(drawable);
    }

    private void setLocal(View view, Pacote pacote) {
        TextView local = view.findViewById(R.id.item_local);
        local.setText(pacote.getLocal());
    }

}
