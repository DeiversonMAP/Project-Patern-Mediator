package org.example.mediator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilaTrabalhosTest {

    @Test
    void deveNotificarMediadorAoAdicionarTrabalho() {
        final Evento[] recebido = new Evento[1];
        FilaTrabalhos fila = new FilaTrabalhos();
        fila.setMediador((origem, evento) -> recebido[0] = evento);

        fila.adicionar("Busto");

        assertEquals(Evento.NOVO_TRABALHO, recebido[0]);
    }

    @Test
    void deveRetirarTrabalhosNaOrdemDeChegada() {
        FilaTrabalhos fila = new FilaTrabalhos();
        fila.setMediador((origem, evento) -> { });
        fila.adicionar("Busto");
        fila.adicionar("Chaveiro");

        assertEquals(2, fila.tamanho());
        assertEquals("Busto", fila.retirarProximo());
        assertEquals("Chaveiro", fila.retirarProximo());
        assertTrue(fila.isVazia());
    }
}
