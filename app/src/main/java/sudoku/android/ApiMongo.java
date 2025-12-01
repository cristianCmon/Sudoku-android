package sudoku.android;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiMongo {

    @GET("puntuaciones")
    Call<List<Puntuaciones>> leerPuntuacionesSudoku();

    @FormUrlEncoded
    @POST("puntuaciones")
    Call<Puntuaciones> crearPuntuacionSudoku(
        @Field("nombre") String nombre,
        @Field("dificultad") String dificultad,
        @Field("puntuacion") String puntuacion
    );

}
