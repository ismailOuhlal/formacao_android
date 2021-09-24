package br.com.alura.estoque.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import br.com.alura.estoque.R;
import br.com.alura.estoque.asynctask.BaseAsyncTask;
import br.com.alura.estoque.database.EstoqueDatabase;
import br.com.alura.estoque.database.dao.ProdutoDAO;
import br.com.alura.estoque.model.Produto;
import br.com.alura.estoque.repository.ProdutoRepository;
import br.com.alura.estoque.retrofit.EstoqueRetrofit;
import br.com.alura.estoque.retrofit.service.ProdutoService;
import br.com.alura.estoque.ui.dialog.EditaProdutoDialog;
import br.com.alura.estoque.ui.dialog.SalvaProdutoDialog;
import br.com.alura.estoque.ui.recyclerview.adapter.ListaProdutosAdapter;
import retrofit2.Call;
import retrofit2.Response;

public class ListaProdutosActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de produtos";
    private ListaProdutosAdapter adapter;
    private ProdutoDAO dao;
    private ProdutoRepository produtoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        setTitle(TITULO_APPBAR);

        configuraListaProdutos();
        configuraFabSalvaProduto();

        EstoqueDatabase db = EstoqueDatabase.getInstance(this);
        dao = db.getProdutoDAO();

        produtoRepository = new ProdutoRepository(dao);
        produtoRepository.buscaProdutos(new ProdutoRepository.DadosCarregadosCallback<List<Produto>>() {
            @Override
            public void quandoSucesso(List<Produto> resultado) {
                adapter.atualiza(resultado);
            }

            @Override
            public void quandoFalha(String erro) {
                Toast.makeText(ListaProdutosActivity.this, "Não foi possível carregar os produtos novos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configuraListaProdutos() {
        RecyclerView listaProdutos = findViewById(R.id.activity_lista_produtos_lista);
        adapter = new ListaProdutosAdapter(this, this::abreFormularioEditaProduto);
        listaProdutos.setAdapter(adapter);
        adapter.setOnItemClickRemoveContextMenuListener(((posicao, produtoRemovido) -> produtoRepository.remove(produtoRemovido, new ProdutoRepository.DadosCarregadosCallback<Void>() {
            @Override
            public void quandoSucesso(Void resultado) {
                adapter.remove(posicao);
            }

            @Override
            public void quandoFalha(String erro) {
                Toast.makeText(ListaProdutosActivity.this, "Não foi possível remover o produto", Toast.LENGTH_SHORT).show();
            }
        })));
    }



    private void configuraFabSalvaProduto() {
        FloatingActionButton fabAdicionaProduto = findViewById(R.id.activity_lista_produtos_fab_adiciona_produto);
        fabAdicionaProduto.setOnClickListener(v -> abreFormularioSalvaProduto());
    }

    private void abreFormularioSalvaProduto() {
        new SalvaProdutoDialog(this, produto -> {
            produtoRepository.salva(produto, new ProdutoRepository.DadosCarregadosCallback<Produto>() {
                @Override
                public void quandoSucesso(Produto resultado) {
                    adapter.adiciona(resultado);
                }

                @Override
                public void quandoFalha(String erro) {
                    Toast.makeText(ListaProdutosActivity.this, "Não foi possível salvar o produto", Toast.LENGTH_SHORT).show();
                }
            });
        }).mostra();
    }



    private void abreFormularioEditaProduto(int posicao, Produto produto) {
        new EditaProdutoDialog(this, produto,
                produtoCriado -> produtoRepository.edita(produtoCriado, new ProdutoRepository.DadosCarregadosCallback<Produto>() {
                    @Override
                    public void quandoSucesso(Produto produtoEditado) {
                        adapter.edita(posicao, produtoEditado);
                    }

                    @Override
                    public void quandoFalha(String erro) {
                        Toast.makeText(ListaProdutosActivity.this, "Não foi possível editar o produto", Toast.LENGTH_SHORT).show();
                    }
                }))
                .mostra();
    }



}
