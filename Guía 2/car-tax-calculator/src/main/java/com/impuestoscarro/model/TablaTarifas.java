package com.impuestoscarro.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Tabla de tarifas por avalúo. */
public class TablaTarifas {
    private final List<Tarifa> tarifas = new ArrayList<>();

    public TablaTarifas(List<Tarifa> tarifas) {
        this.tarifas.addAll(tarifas);
        this.tarifas.sort(Comparator.comparingDouble(Tarifa::getMin));
    }

    /** Tarifa por defecto (ejemplo académico). */
    public static TablaTarifas porDefecto() {
        return new TablaTarifas(List.of(
                new Tarifa(0, 50_000_000, 0.015),
                new Tarifa(50_000_000, 120_000_000, 0.025),
                new Tarifa(120_000_000, Double.POSITIVE_INFINITY, 0.035)
        ));
    }

    /**
     * Carga desde un CSV en el classpath con columnas: min,max,tasa
     * Use 'INF' para infinito en la columna max.
     */
    public static TablaTarifas cargarDesdeCSV(String resourcePath) {
        try (InputStream is = TablaTarifas.class.getResourceAsStream(resourcePath)) {
            if (is == null) throw new IllegalStateException("Recurso no encontrado: " + resourcePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            List<Tarifa> list = new ArrayList<>();
            boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) { header = false; continue; }
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split(",");
                double min = Double.parseDouble(parts[0].trim());
                double max = parts[1].trim().equalsIgnoreCase("INF")
                        ? Double.POSITIVE_INFINITY
                        : Double.parseDouble(parts[1].trim());
                double tasa = Double.parseDouble(parts[2].trim());
                list.add(new Tarifa(min, max, tasa));
            }
            return new TablaTarifas(list);
        } catch (Exception e) {
            System.err.println("No se pudo leer " + resourcePath + " (" + e.getMessage() + "). Usando tarifas por defecto.");
            return porDefecto();
        }
    }

    /** Busca la tarifa aplicable para el avalúo dado. */
    public Tarifa buscarPorAvaluo(double avaluo) {
        return tarifas.stream()
                .filter(t -> t.pertenece(avaluo))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No hay tarifa para el avalúo: " + avaluo));
    }
}
