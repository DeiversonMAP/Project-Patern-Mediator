package org.example.mediator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PainelOperadorTest {

    @Test
    void deveGuardarMensagensNaOrdemRecebida() {
        PainelOperador painel = new PainelOperador();
        painel.receber("primeira");
        painel.receber("segunda");

        assertEquals(Arrays.asList("primeira", "segunda"), painel.getMensagens());
    }
}
