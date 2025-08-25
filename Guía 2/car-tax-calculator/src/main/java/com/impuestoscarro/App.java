package com.impuestoscarro;

import com.impuestoscarro.controller.ImpuestosController;
import com.impuestoscarro.model.CalculadoraImpuestos;
import com.impuestoscarro.model.TablaTarifas;
import com.impuestoscarro.view.ImpuestosView;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            // Carga tarifas desde el CSV del classpath
            TablaTarifas tabla = TablaTarifas.cargarDesdeCSV("/tarifas-2025.csv");
            CalculadoraImpuestos calc = new CalculadoraImpuestos(tabla);

            // Vista y Controller
            ImpuestosView view = new ImpuestosView();
            new ImpuestosController(view, calc);

            view.setVisible(true);
        });
    }
}
