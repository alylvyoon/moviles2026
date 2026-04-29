package com.example.clase12;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CursosDao {
    @Insert
    void insertCursos(Cursos cursos);

    @Query("SELECT * FROM alumnos_curso")
    List<Cursos> getAllCursos();
}