package com.example.clase10fixed;

public class Clima {
    String dia, temp, condicion;

    public Clima(String dia, String temp, String condicion) {
        this.dia = dia;
        this.temp = temp;
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        return dia + ": " + temp + "°C - " + condicion;
    }
}