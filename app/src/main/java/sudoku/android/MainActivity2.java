package sudoku.android;

import android.content.Intent;
import android.os.Bundle;
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

    PuntuacionesAdapter adaptador;
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

        PeticionesBD.mostrarPuntuaciones(puntuaciones, adaptador);
    }

    public void activarComponentesActivity() {
        // ADAPTADOR Y RECYCLER VIEW
        recyclerView = findViewById(R.id.rvPuntuaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptador = new PuntuacionesAdapter(getApplicationContext(), puntuaciones, MainActivity2.this);
        recyclerView.setAdapter(adaptador);

        // BOTÓN SALIR
        btnVolverMenu = findViewById(R.id.btnVolverMenu);
        btnVolverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
