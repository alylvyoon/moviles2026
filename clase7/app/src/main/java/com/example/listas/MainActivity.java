package com.example.listas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MiAdaptador adaptador;
    ArrayList<String> listaNombres;
    EditText edtInput;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listaNombres = new ArrayList<>(Arrays.asList("Mario", "Luigi", "Peach", "Browser"));

        adaptador = new MiAdaptador(listaNombres);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptador);

        edtInput = findViewById(R.id.edtInput);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> agregarNombre());
    }
    private void agregarNombre() {
        String nombre = edtInput.getText().toString().trim();
        if (nombre.isEmpty()) {
            return;
        }
        adaptador.agregarNombre(nombre);
        edtInput.setText("");
    }
}