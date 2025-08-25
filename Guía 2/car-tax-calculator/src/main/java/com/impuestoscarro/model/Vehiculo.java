package com.impuestoscarro.model;

import java.time.Year;

/** Entidad de dominio que representa un vehículo. */
public class Vehiculo {
    private String marca;
    private String modelo;
    private int anio;
    private int cilindraje;
    private double avaluo;
    private TipoUso tipoUso;

    public Vehiculo(String marca, String modelo, int anio, int cilindraje, double avaluo, TipoUso tipoUso) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.cilindraje = cilindraje;
        this.avaluo = avaluo;
        this.tipoUso = tipoUso;
        validar(); // <- ya no lanza UnsupportedOperationException
    }

    /** Validaciones básicas de integridad. */
    public void validar() {
        int current = Year.now().getValue();
        if (marca == null || marca.isBlank()) throw new IllegalArgumentException("La marca es obligatoria.");
        if (modelo == null || modelo.isBlank()) throw new IllegalArgumentException("El modelo es obligatorio.");
        if (anio < 1950 || anio > current) throw new IllegalArgumentException("Año inválido.");
        if (cilindraje <= 0) throw new IllegalArgumentException("Cilindraje debe ser > 0.");
        if (avaluo <= 0) throw new IllegalArgumentException("Avalúo debe ser > 0.");
        if (tipoUso == null) throw new IllegalArgumentException("Tipo de uso es obligatorio.");
    }

    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnio() { return anio; }
    public int getCilindraje() { return cilindraje; }
    public double getAvaluo() { return avaluo; }
    public TipoUso getTipoUso() { return tipoUso; }
}
