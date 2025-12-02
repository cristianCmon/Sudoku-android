package sudoku.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PuntuacionesAdapter extends RecyclerView.Adapter<PuntuacionesViewHolder> {

    Context applicationContext;
    Context activityContext;
    List<Puntuaciones> puntuaciones;

    public PuntuacionesAdapter(Context applicationContext, List<Puntuaciones> puntuaciones, Context activityContext) {
        this.applicationContext = applicationContext;
        this.puntuaciones = puntuaciones;
        this.activityContext = activityContext;
    }

    @NonNull
    @Override
    public PuntuacionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PuntuacionesViewHolder(LayoutInflater.from(applicationContext).inflate(R.layout.puntuaciones_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PuntuacionesViewHolder holder, int position) {
        Puntuaciones puntuacion = puntuaciones.get(position);
        holder.nombre.setText(puntuacion.getNombre());
        holder.dificultad.setText(puntuacion.getDificultad());
        holder.puntuacion.setText(puntuacion.getPuntuacion());
    }

    @Override
    public int getItemCount() {
        return puntuaciones.size();
    }

    public void actualizarPuntuaciones(List<Puntuaciones> puntuacionesActualizadas) {
        puntuaciones = puntuacionesActualizadas;
        notifyDataSetChanged();
    }
}
