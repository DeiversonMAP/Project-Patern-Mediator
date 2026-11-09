package org.example.mediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CentralImpressaoTest {

    private FilaTrabalhos fila;
    private PainelOperador painel;
    private Impressora impressora1;
    private Impressora impressora2;

    @BeforeEach
    void montarCentral() {
        fila = new FilaTrabalhos();
        painel = new PainelOperador();
        CentralImpressao central = new CentralImpressao(fila, painel);

        impressora1 = new Impressora("Impressora 1");
        impressora2 = new Impressora("Impressora 2");
        central.adicionarImpressora(impressora1);
        central.adicionarImpressora(impressora2);
    }

    @Test
    void deveEntregarNovoTrabalhoParaPrimeiraImpressoraLivre() {
        fila.adicionar("Busto");

        assertTrue(impressora1.isOcupada());
        assertEquals("Busto", impressora1.getTrabalhoAtual());
        assertFalse(impressora2.isOcupada());
        assertEquals(Arrays.asList("Impressora 1 iniciou Busto"), painel.getMensagens());
    }

    @Test
    void deveUsarSegundaImpressoraQuandoPrimeiraEstaOcupada() {
        fila.adicionar("Busto");
        fila.adicionar("Chaveiro");

        assertEquals("Busto", impressora1.getTrabalhoAtual());
        assertEquals("Chaveiro", impressora2.getTrabalhoAtual());
    }

    @Test
    void deveManterTrabalhoNaFilaQuandoTodasEstaoOcupadas() {
        fila.adicionar("Busto");
        fila.adicionar("Chaveiro");
        fila.adicionar("Imagem");

        assertEquals(1, fila.tamanho());
        assertEquals(
                "Todas as impressoras ocupadas, trabalho aguardando na fila (1)",
                painel.getMensagens().get(painel.getMensagens().size() - 1)
        );
    }

    @Test
    void deveIniciarProximoDaFilaQuandoImpressoraConclui() {
        fila.adicionar("Busto");
        fila.adicionar("Chaveiro");
        fila.adicionar("Imagem");

        impressora1.concluir();

        assertEquals("Imagem", impressora1.getTrabalhoAtual());
        assertTrue(fila.isVazia());
        assertTrue(painel.getMensagens().contains("Impressora 1 concluiu Busto"));
        assertTrue(painel.getMensagens().contains("Impressora 1 iniciou Imagem"));
    }

    @Test
    void deveAvisarQueFicouLivreQuandoNaoHaFila() {
        fila.adicionar("Busto");

        impressora1.concluir();

        assertFalse(impressora1.isOcupada());
        assertEquals(
                Arrays.asList(
                        "Impressora 1 iniciou Busto",
                        "Impressora 1 concluiu Busto",
                        "Impressora 1 ficou livre"
                ),
                painel.getMensagens()
        );
    }
}
