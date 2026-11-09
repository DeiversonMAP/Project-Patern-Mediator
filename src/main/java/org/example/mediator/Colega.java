package org.example.mediator;

public abstract class Colega {

    protected IMediador mediador;

    public void setMediador(IMediador mediador) {
        this.mediador = mediador;
    }
}
