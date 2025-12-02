package sudoku.android;

import android.util.Log;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class PeticionesBD {

    private static Retrofit retrofit;
    private static ApiMongo api;


    private PeticionesBD() {};


    public static void mostrarPuntuaciones(List<Puntuaciones> puntuaciones, PuntuacionesAdapter adaptador) {
        establecerConexion();

        Call<List<Puntuaciones>> llamada = api.leerPuntuacionesSudoku();

        llamada.enqueue(new Callback<List<Puntuaciones>>() {
            @Override
            public void onResponse(Call<List<Puntuaciones>> call, Response<List<Puntuaciones>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Puntuaciones> data = response.body();
                    puntuaciones.addAll(data);
                    adaptador.actualizarPuntuaciones(puntuaciones);
                }
            }

            @Override
            public void onFailure(Call<List<Puntuaciones>> call, Throwable t) {
                Log.d("ERROR mostrarPuntuaciones", t.toString());
            }
        });

        limpiarConexion();
    }

    public static void guardarPuntuacion(Puntuaciones puntuacion) {
        establecerConexion();

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

        limpiarConexion();
    }

    private static void establecerConexion() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory
                .create()).build();

        api = retrofit.create(ApiMongo.class);
    }

    private static void limpiarConexion() {
        retrofit = null;
        api = null;
    }
}
