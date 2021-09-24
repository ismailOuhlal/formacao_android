package com.iamageo.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.iamageo.agenda.R;
import com.iamageo.agenda.asynctask.EditaAlunoTask;
import com.iamageo.agenda.asynctask.SalvaAlunoTask;
import com.iamageo.agenda.database.AgendaDatabase;
import com.iamageo.agenda.database.RoomAlunoDAO;
import com.iamageo.agenda.model.Aluno;

import static com.iamageo.agenda.ui.Constants.CHAVE_ALUNO;

public class ActivityCadastro extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
    private EditText campoNome;
    private EditText campoSobrenome;
    private EditText campoIdade;
    private EditText campoTelefone;
    private RoomAlunoDAO dao;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        AgendaDatabase database = AgendaDatabase.getInstance(this);
        dao = database.getRoomAlunoDAO();

        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.salvar_aluno, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_aluno_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoSobrenome.setText(aluno.getSobrenome());
        campoIdade.setText(aluno.getIdade());
        campoTelefone.setText(aluno.getTelefone());
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            new EditaAlunoTask(dao, aluno).execute();
        } else {
            new SalvaAlunoTask(dao, aluno).execute();
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.cadastro_nome);
        campoSobrenome = findViewById(R.id.cadastro_sobrenome);
        campoIdade = findViewById(R.id.cadastro_idade);
        campoTelefone = findViewById(R.id.cadastro_telefone_fixo);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String idade = campoIdade.getText().toString();
        String sobrenome = campoSobrenome.getText().toString();
        String telefone = campoTelefone.getText().toString();

        aluno.setNome(nome);
        aluno.setSobrenome(sobrenome);
        aluno.setIdade(idade);
        aluno.setTelefone(telefone);
    }


}