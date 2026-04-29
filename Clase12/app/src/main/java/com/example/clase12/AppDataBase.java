package com.example.clase12;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cursos.class, Alumnos.class, Asistencia.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;

    public abstract CursosDao cursosDao();
    public abstract AlumnosDao alumnosDao();
    public abstract AsistenciaDao asistenciaDao();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "gestion_cursos_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

