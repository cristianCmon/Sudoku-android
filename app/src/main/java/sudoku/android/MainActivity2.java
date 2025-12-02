package sudoku.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Puntuaciones> puntuaciones = new ArrayList<>();
    Button btnVolverMenu;


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

        activarComponentesActivity();

        // TODO SINCRONIZAR RESPUESTA PETICION BD CON RECYCLERVIEW
        PeticionesBD.mostrarPuntuaciones(puntuaciones);
//        recyclerView.setAdapter(new PuntuacionesAdapter(getApplicationContext(), puntuaciones, MainActivity2.this));
    }

    public void activarComponentesActivity() {
        // RECYCLER VIEW
        recyclerView = findViewById(R.id.rvPuntuaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // BOTÓN SALIR
        btnVolverMenu = findViewById(R.id.btnVovlerMenu);
        btnVolverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println(puntuaciones);
                System.out.println("VOLVER MENÚ");
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}