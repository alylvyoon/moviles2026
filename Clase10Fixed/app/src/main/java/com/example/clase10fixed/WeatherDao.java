package com.example.clase10fixed;
import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface WeatherDao {
    @Insert
    void insertWeather(Weather weather);
}