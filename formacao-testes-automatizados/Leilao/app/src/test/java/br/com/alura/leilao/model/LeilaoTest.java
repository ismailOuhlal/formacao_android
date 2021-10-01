package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private Leilao console = new Leilao("console");
    private Usuario geovani = new Usuario("Geovani");
    private Usuario leticia = new Usuario("Leticia");

    @Test
    public void getDescricao() {
        String descricaoDevolvida = console.getDescricao();
        assertEquals("console", descricaoDevolvida);
    }


    @Test
    public void getMaiorLance() {
        console.propoe(new Lance(geovani, 200.0));

        double maiorLanceDevolvidoDoConsole = console.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvidoDoConsole, DELTA);
    }

    @Test
    public void getMaiorLanceOrdemCrescente() {
        console.propoe(new Lance(geovani, 100.0));
        console.propoe(new Lance(leticia, 200.0));

        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void getMaiorLanceOrdemDecrescente() {
        console.propoe(new Lance(geovani, 10000.0));
        console.propoe(new Lance(leticia, 9000.0));

        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void getMenorLance() {
        console.propoe(new Lance(geovani, 200.0));

        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(200.0, menorLanceDevolvido, DELTA);
    }

    /*
    @Test
    public void getMenorLanceOrdemCrescente() {
        console.propoe(new Lance(geovani, 100.0));
        console.propoe(new Lance(leticia, 200.0));

        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void getMenorLanceOrdemDecrescente() {
        console.propoe(new Lance(geovani, 10000.0));
        console.propoe(new Lance(leticia, 9000.0));

        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(9000.0, menorLanceDevolvido, DELTA);
    }
    */

    @Test
    public void getTresMaioresLances() {
        console.propoe(new Lance(geovani, 200));
        console.propoe(new Lance(new Usuario("Xing"), 300));
        console.propoe(new Lance(new Usuario("Xing 2"), 400));

        List<Lance> tresMaioresLances = console.tresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(400.0, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void getNenhumLance() {
        List<Lance> tresMaioreLances = console.tresMaioresLances();
        assertEquals(0, tresMaioreLances.size());
    }


    @Test
    public void getTresMaioresLancesUmLance() {

        console.propoe(new Lance(geovani, 200));

        List<Lance> tresMaioreLances = console.tresMaioresLances();

        assertEquals(1, tresMaioreLances.size());
        assertEquals(200.0, tresMaioreLances.get(0).getValor(), DELTA);
    }

    @Test
    public void getTresMaioresLancesDoisLances() {

        console.propoe(new Lance(geovani, 200.0));
        console.propoe(new Lance(leticia, 300.0));

        List<Lance> tresMaioreLances = console.tresMaioresLances();

        assertEquals(2, tresMaioreLances.size());
        assertEquals(300.0, tresMaioreLances.get(0).getValor(), DELTA);
        assertEquals(200.0, tresMaioreLances.get(1).getValor(), DELTA);
    }


    @Test
    public void getQuatroMaioresLancesDoisLances() {
        console.propoe(new Lance(geovani, 200.0));
        console.propoe(new Lance(leticia, 300.0));
        console.propoe(new Lance(new Usuario("Marcio"), 400.0));
        console.propoe(new Lance(new Usuario("João"), 450.0));

        List<Lance> tresMaioreLances = console.tresMaioresLances();

        assertEquals(3, tresMaioreLances.size());
        assertEquals(450.0, tresMaioreLances.get(0).getValor(), DELTA);
        assertEquals(400.0, tresMaioreLances.get(1).getValor(), DELTA);
        assertEquals(300.0, tresMaioreLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_devolverZeroParaMaiorLance_QuandoNaoTiverLances() {
        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_devolverZeroParaMenorLance_QuandoNaoTiverLances() {
        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void naoDeve_adicionarLance_QuandoForMenorQueOMaiorLance() {
        console.propoe(new Lance(geovani, 500));
        console.propoe(new Lance(leticia, 400));

        int quantidadeLancesDevolvidos = console.quantidadeLances();

        assertEquals(1, quantidadeLancesDevolvidos);
    }




}