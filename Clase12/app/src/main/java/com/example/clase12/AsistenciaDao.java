package com.example.clase12;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AsistenciaDao {
    @Insert
    void insertAsistencia(Asistencia asistencia);


    @Query("SELECT * FROM asistencia_table WHERE alumnoId = :alumnoId")
    List<Asistencia> getAsistenciaPorAlumno(int alumnoId);
}