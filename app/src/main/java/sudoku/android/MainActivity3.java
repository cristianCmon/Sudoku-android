package sudoku.android;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity implements TextWatcher {

    String nombreJugador, dificultadPartida;
    EditText etNombre;
    RadioGroup rgDificultades;
    RadioButton rbFacil, rbIntermedio, rbDificil;
    Button btnJugar;

    boolean nombreIntroducido, dificultadSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        activarComponentesActivity();
        System.out.println("Jugador: " + nombreJugador + " - Dificultad: " + dificultadPartida);
    }

    public void activarComponentesActivity() {
//        TODO VISTA PARA SOLICITAR NOMBRE USUARIO Y NIVEL DIFICULTAD
//        TODO TÍTULO, TEXTAREA PARA NOMBRE Y GROUPBUTTON PARA SELECCIONAR DIFICULTAD, BOTÓN EMPEZAR
        // https://www.youtube.com/watch?v=ZuhkYS5AEYM
        System.out.println("HOLA VISTA INTRODUCE NOMBRE Y SELECCIONA DIFICULTAD");

        // CAMPO NOMBRE
        etNombre = findViewById(R.id.etNombre);
        etNombre.addTextChangedListener(this);

        // BOTÓN JUGAR
        btnJugar = findViewById(R.id.btnJugar);
        btnJugar.setEnabled(false);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                intent.putExtra("DATOS_PARTIDA", new String[]{nombreJugador, dificultadPartida});
                startActivity(intent);
            }
        });

        // RADIO GROUP SELECTOR DIFICULTAD
        rgDificultades = findViewById(R.id.rgDificultades);
        rbFacil = findViewById(R.id.rbFacil);
        rbIntermedio = findViewById(R.id.rbIntermedio);
        rbDificil = findViewById(R.id.rbDificil);

        rgDificultades.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                RadioButton botonPulsado = findViewById(checkedId);

                if (botonPulsado != null) {
                    dificultadSeleccionada = true;
                    dificultadPartida = botonPulsado.getText().toString();
                    System.out.println(botonPulsado.getText());
                    // Por ejemplo, mostrar un Toast
                    // Toast.makeText(MainActivity.this, "Seleccionado: " + checkedRadioButton.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    dificultadSeleccionada = false;
                }

                btnJugar.setEnabled(nombreIntroducido && dificultadSeleccionada);
            }
        });

    }

    @Override
    public void afterTextChanged(Editable s) {
//        nombreIntroducido = !s.isEmpty();
        if (s.isEmpty()) {
            nombreIntroducido = false;
        } else {
            nombreIntroducido = true;
            nombreJugador = s.toString();
        }

        btnJugar.setEnabled(nombreIntroducido && dificultadSeleccionada);

        System.out.println(nombreJugador);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}
}