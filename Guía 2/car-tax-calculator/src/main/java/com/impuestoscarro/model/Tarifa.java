package com.impuestoscarro.model;

/** Rango de tarifa por avalúo. */
public class Tarifa {
    private double minInclusivo;
    private double maxExclusivo;
    private double tasa; // p. ej. 0.015 = 1.5%

    public Tarifa(double minInclusivo, double maxExclusivo, double tasa) {
        this.minInclusivo = minInclusivo;
        this.maxExclusivo = maxExclusivo;
        this.tasa = tasa;
    }

    /** Retorna true si el avalúo cae en [min, max). */
    public boolean pertenece(double avaluo) {
        return avaluo >= minInclusivo && avaluo < maxExclusivo;
    }

    public double getTasa() { return tasa; }
    public double getMin()  { return minInclusivo; }
    public double getMax()  { return maxExclusivo; }
}
