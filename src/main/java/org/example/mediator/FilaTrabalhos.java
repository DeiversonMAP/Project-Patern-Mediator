package org.example.mediator;

import java.util.LinkedList;
import java.util.Queue;

public class FilaTrabalhos extends Colega {

    private final Queue<String> trabalhos = new LinkedList<>();

    public void adicionar(String trabalho) {
        trabalhos.add(trabalho);
        mediador.notificar(this, Evento.NOVO_TRABALHO);
    }

    public String retirarProximo() {
        return trabalhos.poll();
    }

    public boolean isVazia() {
        return trabalhos.isEmpty();
    }

    public int tamanho() {
        return trabalhos.size();
    }
}
