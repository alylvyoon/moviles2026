package com.example.clase12;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alumnos_table")
public class Alumnos {
    @PrimaryKey(autoGenerate = true)
    public int alumnoId;
    public String nombreAlumno;
    public String apellidoPaterno;
    public String apellidoMaterno;

    public int cursoId;

    public Alumnos(String nombreAlumno, int cursoId, String apellidoPaterno, String apellidoMaterno) {
        this.nombreAlumno = nombreAlumno;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.cursoId = cursoId;
    }
}