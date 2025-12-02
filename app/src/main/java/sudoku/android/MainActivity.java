package sudoku.android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnNuevaPartida, btnPuntuaciones, btnSalir;
    private int[][] rejilla;


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

        activarComponentesActivity();
    }


    public void activarComponentesActivity() {
        // BOTÓN NUEVA PARTIDA
        btnNuevaPartida = findViewById(R.id.clicNuevaPartida);
        btnNuevaPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejilla = Sudoku.generar();
                Sudoku.mostrarSudokuConsola(rejilla);
//                TODO OJEAR ESTE ENLACE https://www.youtube.com/watch?v=_fnRrruE2do
            }
        });

        // BOTÓN PUNTUACIONES
        btnPuntuaciones = findViewById(R.id.clicPuntuaciones);
        btnPuntuaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        // BOTÓN SALIR APLICACIÓN
        btnSalir = findViewById(R.id.clicSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });
    }

}
