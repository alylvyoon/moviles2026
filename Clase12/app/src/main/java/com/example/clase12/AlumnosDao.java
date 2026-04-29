package com.example.clase12;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlumnosDao {
    @Insert
    void insertAlumnos(Alumnos alumnos);

    @Query("SELECT * FROM alumnos_table WHERE cursoId = :cursoId")
    List<Alumnos> getAlumnosPorCurso(int cursoId);
}