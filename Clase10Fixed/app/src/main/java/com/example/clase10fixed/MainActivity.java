package com.example.clase10fixed;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private ListView lvHistorial;
    private Button btnCargar;
    private DBHelper dbHelper;
    private ArrayList<String> datosLista;
    private ArrayAdapter<String> adaptador;

    private final String API_KEY = "9dedb80af75643c0b6130738262004";
    private final String CIUDAD = "Mexico City";
    private final String URL = "https://api.weatherapi.com/v1/forecast.json?key=" + API_KEY + "&q=" + CIUDAD + "&days=3&lang=es";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lvHistorial = findViewById(R.id.lvHistorial);
        btnCargar = findViewById(R.id.btnCargar);
        dbHelper = new DBHelper(this);
        datosLista = new ArrayList<>();


        mostrarHistorialLocal();

        btnCargar.setOnClickListener(v -> obtenerClimaAPI());
    }

    private void obtenerClimaAPI() {
        RequestQueue queue = Volley.newRequestQueue(this);


        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.GET, URL, null,
                response -> {
                    try {
                        JSONArray forecastDays = response.getJSONObject("forecast").getJSONArray("forecastday");


                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.delete(DBHelper.TABLE_NAME, null, null);

                        for (int i = 0; i < forecastDays.length(); i++) {
                            JSONObject dayData = forecastDays.getJSONObject(i);
                            String fecha = dayData.getString("date");
                            String temp = dayData.getJSONObject("day").getString("avgtemp_c");
                            String cond = dayData.getJSONObject("day").getJSONObject("condition").getString("text");


                            dbHelper.guardarClima(fecha, temp, cond);
                        }

                        Toast.makeText(this, "Datos guardados en SQLite", Toast.LENGTH_SHORT).show();
                        mostrarHistorialLocal();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(this, "Error de red: " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }

    private void mostrarHistorialLocal() {
        datosLista.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String fecha = cursor.getString(1);
                String temp = cursor.getString(2);
                String cond = cursor.getString(3);

                datosLista.add("Fecha: " + fecha + " | " + temp + "C - " + cond);

            } while (cursor.moveToNext());
        }
        cursor.close();

        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datosLista);
        lvHistorial.setAdapter(adaptador);
    }
}