package com.example.clase10fixed;

public class WeatherResponse {
    public Current current;
    public Location location;

    public class Current {
        public double temp_c;
        public Condition condition;
    }

    public class Condition {
        public String text;
    }

    public class Location {
        public String localtime;
    }
}
