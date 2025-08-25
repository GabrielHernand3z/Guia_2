package com.impuestoscarro.model;

/** Servicio de dominio que calcula el impuesto. */
public class CalculadoraImpuestos {
    private final TablaTarifas tabla;

    public CalculadoraImpuestos(TablaTarifas tabla) {
        this.tabla = tabla;
    }

    /** Calcula el impuesto total según avalúo, tarifa y factor por tipo de uso. */
    public ResultadoCalculo calcular(Vehiculo v) {
        Tarifa tr = tabla.buscarPorAvaluo(v.getAvaluo());
        double tasa = tr.getTasa();
        double base = v.getAvaluo();
        double impuestoBase = base * tasa;
        double factorUso = v.getTipoUso().factorUso();
        double impuestoFinal = impuestoBase * factorUso;
        return new ResultadoCalculo(base, tasa, factorUso, tr, impuestoBase, redondear(impuestoFinal));
    }

    /** Redondeo al peso. */
    private double redondear(double valor) {
        return Math.round(valor);
    }
}
