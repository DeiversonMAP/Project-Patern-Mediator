package org.example.mediator;

import java.util.ArrayList;
import java.util.List;

public class CentralImpressao implements IMediador {

    private final List<Impressora> impressoras = new ArrayList<>();
    private final FilaTrabalhos fila;
    private final PainelOperador painel;

    public CentralImpressao(FilaTrabalhos fila, PainelOperador painel) {
        this.fila = fila;
        this.painel = painel;
        this.fila.setMediador(this);
        this.painel.setMediador(this);
    }

    public void adicionarImpressora(Impressora impressora) {
        impressora.setMediador(this);
        impressoras.add(impressora);
    }

    @Override
    public void notificar(Colega origem, Evento evento) {
        switch (evento) {
            case NOVO_TRABALHO:
                entregarTrabalhoParaImpressoraLivre();
                break;
            case TRABALHO_CONCLUIDO:
                tratarConclusao((Impressora) origem);
                break;
            default:
                break;
        }
    }

    private void entregarTrabalhoParaImpressoraLivre() {
        for (Impressora impressora : impressoras) {
            if (!impressora.isOcupada()) {
                iniciarProximoTrabalho(impressora);
                return;
            }
        }
        painel.receber("Todas as impressoras ocupadas, trabalho aguardando na fila (" + fila.tamanho() + ")");
    }

    private void tratarConclusao(Impressora impressora) {
        painel.receber(impressora.getNome() + " concluiu " + impressora.getUltimoTrabalhoConcluido());
        if (!fila.isVazia()) {
            iniciarProximoTrabalho(impressora);
        } else {
            painel.receber(impressora.getNome() + " ficou livre");
        }
    }

    private void iniciarProximoTrabalho(Impressora impressora) {
        String trabalho = fila.retirarProximo();
        impressora.iniciar(trabalho);
        painel.receber(impressora.getNome() + " iniciou " + trabalho);
    }
}
