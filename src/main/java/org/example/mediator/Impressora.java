package org.example.mediator;

public class Impressora extends Colega {

    private final String nome;
    private String trabalhoAtual;
    private String ultimoTrabalhoConcluido;

    public Impressora(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public boolean isOcupada() {
        return trabalhoAtual != null;
    }

    public String getTrabalhoAtual() {
        return trabalhoAtual;
    }

    public String getUltimoTrabalhoConcluido() {
        return ultimoTrabalhoConcluido;
    }

    public void iniciar(String trabalho) {
        this.trabalhoAtual = trabalho;
    }

    public void concluir() {
        if (!isOcupada()) {
            throw new IllegalStateException("Impressora livre, nao ha trabalho para concluir");
        }
        this.ultimoTrabalhoConcluido = trabalhoAtual;
        this.trabalhoAtual = null;
        mediador.notificar(this, Evento.TRABALHO_CONCLUIDO);
    }
}
