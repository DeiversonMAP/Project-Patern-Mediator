package org.example.mediator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImpressoraTest {

    /** Mediador falso que apenas registra o ultimo evento recebido. */
    private static class MediadorEspiao implements IMediador {
        Colega origem;
        Evento evento;

        @Override
        public void notificar(Colega origem, Evento evento) {
            this.origem = origem;
            this.evento = evento;
        }
    }

    @Test
    void deveComecarLivre() {
        Impressora impressora = new Impressora("Impressora 1");
        assertFalse(impressora.isOcupada());
        assertNull(impressora.getTrabalhoAtual());
    }

    @Test
    void deveFicarOcupadaAoIniciarTrabalho() {
        Impressora impressora = new Impressora("Impressora 1");
        impressora.iniciar("Busto");

        assertTrue(impressora.isOcupada());
        assertEquals("Busto", impressora.getTrabalhoAtual());
    }

    @Test
    void deveNotificarMediadorAoConcluir() {
        MediadorEspiao mediador = new MediadorEspiao();
        Impressora impressora = new Impressora("Impressora 1");
        impressora.setMediador(mediador);
        impressora.iniciar("Busto");

        impressora.concluir();

        assertSame(impressora, mediador.origem);
        assertEquals(Evento.TRABALHO_CONCLUIDO, mediador.evento);
        assertFalse(impressora.isOcupada());
        assertEquals("Busto", impressora.getUltimoTrabalhoConcluido());
    }

    @Test
    void deveLancarExcecaoAoConcluirSemTrabalho() {
        Impressora impressora = new Impressora("Impressora 1");
        impressora.setMediador(new MediadorEspiao());
        assertThrows(IllegalStateException.class, impressora::concluir);
    }
}
