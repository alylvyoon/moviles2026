package com.example.calculadora;

public class ViewModel {
    ICalculadora calculadora = new Calculadora();

    ViewModel() {

    }

    Double sum(double x, double y) {
        return calculadora.sum(x, y);
    }

    Double minus(double x, double y) {
        return calculadora.minus(x, y);
    }

    Double multiply(double x, double y) {
        return calculadora.multiply(x, y);
    }

    Double divide(double x, double y) {
        return calculadora.divide(x, y);
    }
}