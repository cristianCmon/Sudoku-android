package sudoku.android;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

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
    }

    public void activarComponentesActivity() {
//        TODO VISTA PARA SOLICITAR NOMBRE USUARIO Y NIVEL DIFICULTAD
//        TODO TÍTULO, TEXTAREA PARA NOMBRE Y GROUPBUTTON PARA SELECCIONAR DIFICULTAD, BOTÓN EMPEZAR
        // https://www.youtube.com/watch?v=ZuhkYS5AEYM
        System.out.println("HOLA VISTA INTRODUCE NOMBRE Y SELECCIONA DIFICULTAD");
    }
}