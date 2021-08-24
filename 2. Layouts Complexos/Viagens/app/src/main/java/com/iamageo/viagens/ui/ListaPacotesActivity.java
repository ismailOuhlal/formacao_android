package com.iamageo.viagens.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.iamageo.viagens.R;
import com.iamageo.viagens.adapter.ListaPacotesAdapter;
import com.iamageo.viagens.dao.PacoteDAO;
import com.iamageo.viagens.model.Pacote;

import java.util.List;

public class ListaPacotesActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Pacotes";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacotes);
        setTitle(TITULO_APP_BAR);

        configuraLista();

    }

    private void configuraLista() {
        ListView listview = findViewById(R.id.main_listview);
        List<Pacote> pacotes = new PacoteDAO().lista();
        listview.setAdapter(new ListaPacotesAdapter(pacotes, this));
    }

}
