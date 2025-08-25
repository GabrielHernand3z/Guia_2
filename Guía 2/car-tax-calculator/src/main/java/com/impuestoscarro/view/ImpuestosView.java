package com.impuestoscarro.view;

import com.impuestoscarro.model.TipoUso;
import com.impuestoscarro.model.Vehiculo;

import javax.swing.*;
import java.awt.*;

/** Vista Swing para captura de datos y presentación del resultado. */
public class ImpuestosView extends JFrame {
    private final JTextField txtMarca = new JTextField();
    private final JTextField txtModelo = new JTextField();
    private final JSpinner spAnio = new JSpinner(new SpinnerNumberModel(2015, 1950, java.time.Year.now().getValue(), 1));
    private final JSpinner spCilindraje = new JSpinner(new SpinnerNumberModel(1600, 50, 10000, 50));
    private final JTextField txtAvaluo = new JTextField();
    private final JComboBox<TipoUso> cbUso = new JComboBox<>(TipoUso.values());
    private final JButton btnCalcular = new JButton("Calcular");
    private final JButton btnLimpiar = new JButton("Limpiar");
    private final JTextArea txtDetalle = new JTextArea(7, 40);
    private final JLabel lblResultado = new JLabel("—");

    public ImpuestosView() {
        super("Cálculo de impuestos de un carro");
        construirUI();
    }

    private void construirUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        addRow(form, c, row++, "Marca:", txtMarca);
        addRow(form, c, row++, "Modelo:", txtModelo);
        addRow(form, c, row++, "Año:", spAnio);
        addRow(form, c, row++, "Cilindraje (cc):", spCilindraje);
        addRow(form, c, row++, "Avalúo (COP):", txtAvaluo);
        addRow(form, c, row++, "Tipo de uso:", cbUso);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botones.add(btnLimpiar);
        botones.add(btnCalcular);

        JPanel result = new JPanel(new BorderLayout(6, 6));
        lblResultado.setFont(lblResultado.getFont().deriveFont(Font.BOLD, 18f));
        txtDetalle.setEditable(false);
        txtDetalle.setLineWrap(true);
        txtDetalle.setWrapStyleWord(true);
        result.add(lblResultado, BorderLayout.NORTH);
        result.add(new JScrollPane(txtDetalle), BorderLayout.CENTER);

        add(form, BorderLayout.NORTH);
        add(botones, BorderLayout.CENTER);
        add(result, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void addRow(JPanel p, GridBagConstraints c, int y, String label, JComponent field) {
        c.gridx = 0; c.gridy = y; c.weightx = 0; p.add(new JLabel(label), c);
        c.gridx = 1; c.gridy = y; c.weightx = 1; p.add(field, c);
    }

    // Callbacks que el Controller engancha
    public void onCalcular(Runnable r) { btnCalcular.addActionListener(e -> r.run()); }
    public void onLimpiar(Runnable r) { btnLimpiar.addActionListener(e -> r.run()); }

    // Entrada/Salida con el modelo
    public Vehiculo leerVehiculo() {
        String marca = txtMarca.getText().trim();
        String modelo = txtModelo.getText().trim();
        int anio = (int) spAnio.getValue();
        int cilindraje = (int) spCilindraje.getValue();
        double avaluo;
        try {
            avaluo = Double.parseDouble(txtAvaluo.getText().replace(".", "").replace(",", "").trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Avalúo inválido. Use solo números, sin puntos ni comas.");
        }
        TipoUso uso = (TipoUso) cbUso.getSelectedItem();
        return new Vehiculo(marca, modelo, anio, cilindraje, avaluo, uso);
    }

    public void mostrarResultado(String total, String detalle) {
        lblResultado.setText("Impuesto a pagar: " + total);
        txtDetalle.setText(detalle);
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void limpiarFormulario() {
        txtMarca.setText("");
        txtModelo.setText("");
        spAnio.setValue(2015);
        spCilindraje.setValue(1600);
        txtAvaluo.setText("");
        cbUso.setSelectedIndex(0);
        lblResultado.setText("—");
        txtDetalle.setText("");
    }
}
