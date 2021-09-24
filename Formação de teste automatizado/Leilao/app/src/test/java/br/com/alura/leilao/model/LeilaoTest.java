package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

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
        assertEquals(200.0, maiorLanceDevolvidoDoConsole, 0.0001);
    }

    @Test
    public void getMaiorLanceOrdemCrescente() {
        console.propoe(new Lance(geovani, 100.0));
        console.propoe(new Lance(leticia, 200.0));

        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void getMaiorLanceOrdemDecrescente() {
        console.propoe(new Lance(geovani, 10000.0));
        console.propoe(new Lance(leticia, 9000.0));

        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void getMenorLance() {
        console.propoe(new Lance(geovani, 200.0));

        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(200.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void getMenorLanceOrdemCrescente() {
        console.propoe(new Lance(geovani, 100.0));
        console.propoe(new Lance(leticia, 200.0));

        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void getMenorLanceOrdemDecrescente() {
        console.propoe(new Lance(geovani, 10000.0));
        console.propoe(new Lance(leticia, 9000.0));

        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(9000.0, menorLanceDevolvido, 0.0001);
    }



}