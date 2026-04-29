package com.example.clase10fixed;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weather_table")
public class Weather {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String date;
    public String city;
    public double temp;
    public String condition;

    public Weather(String date, String city, double temp, String condition) {
        this.date = date;
        this.city = city;
        this.temp = temp;
        this.condition = condition;
    }
}