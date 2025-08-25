package com.impuestoscarro.controller;

import com.impuestoscarro.model.*;
import com.impuestoscarro.view.ImpuestosView;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/** Controlador que coordina la vista y el modelo (MVC). */
public class ImpuestosController {
    private final ImpuestosView view;
    private final CalculadoraImpuestos calc;
    private final NumberFormat currency;
    private final NumberFormat number;

    public ImpuestosController(ImpuestosView view, CalculadoraImpuestos calc) {
        this.view = view;
        this.calc = calc;

        Locale co = new Locale("es", "CO");
        this.currency = NumberFormat.getCurrencyInstance(co);
        this.currency.setCurrency(Currency.getInstance("COP"));
        this.number = NumberFormat.getNumberInstance(co);
        this.number.setMaximumFractionDigits(0);

        wire();
    }

    private void wire() {
        view.onCalcular(() -> {
            try {
                Vehiculo v = view.leerVehiculo();
                ResultadoCalculo r = calc.calcular(v);

                String resumen = String.format(
                        "Base gravable: %s%n" +
                        "Rango: %s con tasa %.2f%%%n" +
                        "Factor por uso (%s): %.2f%n" +
                        "Impuesto base: %s%n" +
                        "Impuesto final: %s%n",
                        money(r.baseGravable),
                        rango(r.tarifaAplicada),
                        r.tasa * 100,
                        v.getTipoUso().name(),
                        r.factorUso,
                        money(r.impuestoBase),
                        money(r.impuestoFinal)
                );

                view.mostrarResultado(money(r.impuestoFinal), resumen);
            } catch (Exception ex) {
                view.mostrarError("No se pudo calcular: " + ex.getMessage());
            }
        });

        view.onLimpiar(view::limpiarFormulario);
    }

    private String money(double v) { return currency.format(v); }

    private String rango(Tarifa t) {
        String min = number.format(t.getMin());
        String max = Double.isInfinite(t.getMax())
                ? "∞"
                : number.format(t.getMax() - 1);
        return "[" + min + " — " + max + "]";
    }
}

