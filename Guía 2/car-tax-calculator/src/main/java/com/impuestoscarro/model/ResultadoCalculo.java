package com.impuestoscarro.model;

/** DTO con el detalle del cálculo. */
public class ResultadoCalculo {
    public final double baseGravable;
    public final double tasa;
    public final double factorUso;
    public final double impuestoBase;
    public final double impuestoFinal;
    public final Tarifa tarifaAplicada;

    public ResultadoCalculo(double baseGravable,
                            double tasa,
                            double factorUso,
                            Tarifa tarifaAplicada,
                            double impuestoBase,
                            double impuestoFinal) {
        this.baseGravable = baseGravable;
        this.tasa = tasa;
        this.factorUso = factorUso;
        this.tarifaAplicada = tarifaAplicada;
        this.impuestoBase = impuestoBase;
        this.impuestoFinal = impuestoFinal;
    }
}
