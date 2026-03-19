package com.example.calculadora;

public class Operacion {
    Double x;
    Double y;
    com.example.calculadora.OperationType type;

    public Operacion(Double x, Double y, com.example.calculadora.OperationType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public com.example.calculadora.OperationType getType() {
        return type;
    }

    public void setType(com.example.calculadora.OperationType type) {
        this.type = type;
    }
}