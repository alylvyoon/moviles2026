package com.example.calculadora;

public class ViewModel {
    private ICalculadora calculadora = new Calculadora();

    private String entradaActual = "0";
    private String textoPantalla = "0";
    private String textoHistorial = "";
    private OperationType operacionPendiente = null;
    private double resultadoActual = 0;
    private boolean empezarNuevoNumero = true;

    public void agregarDigito(String digito) {
        if (empezarNuevoNumero) {
            entradaActual = digito;
            empezarNuevoNumero = false;
        } else {
            entradaActual += digito;
        }
        actualizarPantalla();
    }

    public void agregarDecimal() {
        if (empezarNuevoNumero) {
            entradaActual = "0.";
            empezarNuevoNumero = false;
        } else if (!entradaActual.contains(".")) {
            entradaActual += ".";
        }
        actualizarPantalla();
    }

    public void establecerOperacion(OperationType operacion) {
        if (operacionPendiente != null && !empezarNuevoNumero) {
            calcularResultado();
        }

        resultadoActual = Double.parseDouble(entradaActual);
        operacionPendiente = operacion;
        empezarNuevoNumero = true;

        actualizarHistorial(entradaActual + " " + simboloOperacion(operacion));
    }

    public void calcularResultado() {
        if (operacionPendiente == null) return;

        double valorEntrada = Double.parseDouble(entradaActual);
        double resultado = 0;

        switch (operacionPendiente) {
            case ADD:
                resultado = calculadora.sum(resultadoActual, valorEntrada);
                break;
            case MINUS:
                resultado = calculadora.minus(resultadoActual, valorEntrada);
                break;
            case MULTIPLY:
                resultado = calculadora.multiply(resultadoActual, valorEntrada);
                break;
            case DIV:
                resultado = calculadora.divide(resultadoActual, valorEntrada);
                break;
        }

        resultadoActual = resultado;
        entradaActual = String.valueOf(resultado);
        operacionPendiente = null;
        empezarNuevoNumero = true;
        actualizarPantalla();
    }

    public void limpiarTodo() {
        entradaActual = "0";
        textoPantalla = "0";
        textoHistorial = "";
        resultadoActual = 0;
        operacionPendiente = null;
        empezarNuevoNumero = true;
        actualizarPantalla();
    }

    private void actualizarPantalla() {
        textoPantalla = entradaActual.replace(".0", "");
        if (textoPantalla.equals("0")) textoPantalla = "0";
    }

    private void actualizarHistorial(String operacion) {
        if (!textoHistorial.isEmpty()) {
            textoHistorial += " " + simboloOperacion(operacionPendiente) + " " + entradaActual;
        } else {
            textoHistorial = operacion;
        }
    }

    private String simboloOperacion(OperationType tipo) {
        switch (tipo) {
            case ADD: return "+";
            case MINUS: return "−";
            case MULTIPLY: return "×";
            case DIV: return "÷";
            default: return "";
        }
    }

    public String obtenerTextoResultado() {
        return textoPantalla;
    }

    public String obtenerTextoHistorial() {
        return textoHistorial;
    }
}