package com.example.clase12;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "asistencia_table")
public class Asistencia {
    @PrimaryKey(autoGenerate = true)
    public int idAsistencia;
    public int alumnoId;
    public String fecha;

    public Asistencia(int alumnoId, String fecha) {
            this.alumnoId = alumnoId;
            this.fecha = fecha;
    }
}