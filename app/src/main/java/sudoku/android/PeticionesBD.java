package sudoku.android;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class PeticionesBD {

    private PeticionesBD() {};


    public static void mostrarPuntuaciones(List<Puntuaciones> puntuaciones) {

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .addConverterFactory(GsonConverterFactory
            .create()).build();

        ApiMongo api = retrofit.create(ApiMongo.class);

        Call<List<Puntuaciones>> llamada = api.leerPuntuacionesSudoku();

        llamada.enqueue(new Callback<List<Puntuaciones>>() {
            @Override
            public void onResponse(Call<List<Puntuaciones>> call, Response<List<Puntuaciones>> response) {
                // TODO ATLAS NO SINCRONIZA BIEN Y LANZA NULL EXCEPTION AQUÍ
                List<Puntuaciones> data = response.body();

                // cargamos documentos obtenidos de la bd como elementos de la lista
                puntuaciones.addAll(data);
//                for (Puntuaciones p : data) {
//                    puntuaciones.add(p);
////                    elementos.add(new Elemento(p));
//                }

                // CARGAR DE ELEMENTOS EL RECYCLERVIEW
//                recyclerView.setAdapter(new ElementoAdapter(getApplicationContext(), elementos, MainActivity2.this));
            }

            @Override
            public void onFailure(Call<List<Puntuaciones>> call, Throwable t) {
                Log.d("ERROR mostrarPuntuaciones", t.toString());
            }
        });
    }

    public static void guardarPuntuacion(Puntuaciones puntuacion) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory
                        .create()).build();

        ApiMongo api = retrofit.create(ApiMongo.class);

        // Envío Comanda a base de datos
        Call<Puntuaciones> llamada = api.crearPuntuacionSudoku(
                puntuacion.getNombre(), puntuacion.getDificultad(), puntuacion.getPuntuacion()
        );

        llamada.enqueue(new Callback<Puntuaciones>() {
            @Override
            public void onResponse(Call<Puntuaciones> call, Response<Puntuaciones> response) {
                if (response.isSuccessful()) {
                    System.out.println("Puntuación guardada con éxito");
                }
            }

            @Override
            public void onFailure(Call<Puntuaciones> call, Throwable t) {
                Log.d("ERROR guardarPuntuacion", t.toString());
            }
        });
    }

}
