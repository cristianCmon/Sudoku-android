package sudoku.android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

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

        cargarComponentes();
    }

    public void cargarComponentes() {
        // BOTÓN NUEVA PARTIDA
        Button btnNuevaPartida = findViewById(R.id.clicNuevaPartida);
        btnNuevaPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejilla = Sudoku.generar();
                Sudoku.mostrarSudoku(rejilla);
//                TODO OJEAR ESTE ENLACE https://www.youtube.com/watch?v=_fnRrruE2do
            }
        });

        // BOTÓN PUNTUACIONES
        Button btnPuntuaciones = findViewById(R.id.clicPuntuaciones);
        btnPuntuaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("PUNTUACIONES");
            }
        });

        // BOTÓN SALIR APLICACIÓN
        Button btnSalir = findViewById(R.id.clicSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });
    }


}
