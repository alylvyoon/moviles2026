package com.example.intentos;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);

        // Obtener el número ingresado
        String posicionStr = getIntent().getStringExtra(Valores.miLlave);

        try {
            int posicion = Integer.parseInt(posicionStr);
            int fibonacci = calcularFibonacci(posicion);
            textView.setText("Fib(" + posicion + ") = " + fibonacci);
        } catch (NumberFormatException e) {
            textView.setText("Error: Ingresa un número válido");
        }
    }

    // Método correcto para calcular Fibonacci
    private int calcularFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return calcularFibonacci(n - 1) + calcularFibonacci(n - 2);
    }
}