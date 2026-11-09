package org.example.mediator;

import java.util.ArrayList;
import java.util.List;

public class PainelOperador extends Colega {

    private final List<String> mensagens = new ArrayList<>();

    public void receber(String mensagem) {
        mensagens.add(mensagem);
    }

    public List<String> getMensagens() {
        return mensagens;
    }
}
