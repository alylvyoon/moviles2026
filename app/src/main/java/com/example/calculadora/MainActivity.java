package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ViewModel modeloVista;
    private TextView textoOperacion, textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        modeloVista = new ViewModel();
        textoOperacion = findViewById(R.id.txvOperation);
        textoResultado = findViewById(R.id.txvResult);

        configurarBotones();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void configurarBotones() {
        configurarBotonNumero(R.id.btnCero, "0");
        configurarBotonNumero(R.id.btnOne, "1");
        configurarBotonNumero(R.id.btnSecond, "2");
        configurarBotonNumero(R.id.btnThird, "3");
        configurarBotonNumero(R.id.btnFour, "4");
        configurarBotonNumero(R.id.btnFive, "5");
        configurarBotonNumero(R.id.btnSix, "6");
        configurarBotonNumero(R.id.btnSeven, "7");
        configurarBotonNumero(R.id.btnEight, "8");
        configurarBotonNumero(R.id.btnNine, "9");

        findViewById(R.id.btnPoint).setOnClickListener(v -> {
            modeloVista.agregarDecimal();
            actualizarPantalla();
        });

        findViewById(R.id.btnPlus).setOnClickListener(v -> {
            modeloVista.establecerOperacion(OperationType.ADD);
            actualizarPantalla();
        });
        findViewById(R.id.btnMinus).setOnClickListener(v -> {
            modeloVista.establecerOperacion(OperationType.MINUS);
            actualizarPantalla();
        });
        findViewById(R.id.btnMultiple).setOnClickListener(v -> {
            modeloVista.establecerOperacion(OperationType.MULTIPLY);
            actualizarPantalla();
        });
        findViewById(R.id.btnDivide).setOnClickListener(v -> {
            modeloVista.establecerOperacion(OperationType.DIV);
            actualizarPantalla();
        });

        findViewById(R.id.btnEqual).setOnClickListener(v -> {
            modeloVista.calcularResultado();
            actualizarPantalla();
        });

        findViewById(R.id.btnAc).setOnClickListener(v -> {
            modeloVista.limpiarTodo();
            actualizarPantalla();
        });
    }

    private void configurarBotonNumero(int idBoton, String digito) {
        findViewById(idBoton).setOnClickListener(v -> {
            modeloVista.agregarDigito(digito);
            actualizarPantalla();
        });
    }

    private void actualizarPantalla() {
        textoResultado.setText(modeloVista.obtenerTextoResultado());
        textoOperacion.setText(modeloVista.obtenerTextoHistorial());
    }
}