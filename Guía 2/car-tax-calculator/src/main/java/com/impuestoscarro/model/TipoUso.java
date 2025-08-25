package com.impuestoscarro.model;

public enum TipoUso {
    PARTICULAR, PUBLICO;   // ← las constantes van primero y terminan con ;

    // Público con 10% de descuento (ejemplo académico)
    public double factorUso() {
        return this == PUBLICO ? 0.90 : 1.00;
    }
}
