# Cálculo de impuestos de un carro (Java + Swing, MVC)

Aplicación de escritorio que permite calcular el impuesto de un vehículo en Colombia, usando **Java** y el patrón **MVC**.  
Incluye interfaz gráfica con **Swing**, lectura de tarifas desde **CSV** y el **diagrama de clases**.

## 📦 Requisitos
- **JDK 11+**
- NetBeans (o cualquier IDE compatible con Maven)

## 🧱 Arquitectura
- **Model** (`com.impuestoscarro.model`): `Vehiculo`, `TipoUso` (enum), `Tarifa`, `TablaTarifas`, `CalculadoraImpuestos`, `ResultadoCalculo`.
- **View** (`com.impuestoscarro.view`): `ImpuestosView` (Swing).
- **Controller** (`com.impuestoscarro.controller`): `ImpuestosController`.
- **App** (`com.impuestoscarro.App`): punto de entrada.

## 📂 Estructura
src/
└─ main/
├─ java/
│ └─ com/impuestoscarro/
│ ├─ App.java
│ ├─ controller/ImpuestosController.java
│ ├─ model/
│ │ ├─ Vehiculo.java
│ │ ├─ TipoUso.java
│ │ ├─ Tarifa.java
│ │ ├─ TablaTarifas.java
│ │ ├─ CalculadoraImpuestos.java
│ │ └─ ResultadoCalculo.java
│ └─ view/ImpuestosView.java
└─ resources/
└─ tarifas-2025.csv
docs/
└─ car_tax_calculator.vpp

## ▶️ Ejecutar

### En NetBeans
1. Verifica **Main Class**: `com.impuestoscarro.App` (Project → Properties → Run).
2. **Run Project** (F6) o **Clean and Build** y luego Run.

### Por consola (Maven)
```bash
mvn -q -DskipTests package
mvn exec:exec -Dexec.mainClass=com.impuestoscarro.App
