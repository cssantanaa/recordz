package com.cssantana.recordz.avaliacoes;

import lombok.Getter;

@Getter
public enum Nota {

    ZERO(0.0),
    MEIO(0.5),
    UM(1.0),
    UM_MEIO(1.5),
    DOIS(2.0),
    DOIS_MEIO(2.5),
    TRES(3.0),
    TRES_MEIO(3.5),
    QUATRO(4.0),
    QUATRO_MEIO(4.5),
    CINCO(5.0);

    private final double valor;

    Nota(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}