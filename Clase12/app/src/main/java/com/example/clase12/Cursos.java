package com.example.clase12;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alumnos_curso")
public class Cursos {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;

    public Cursos(String nombre) {
        this.nombre = nombre;
    }
}