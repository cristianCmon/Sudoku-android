package sudoku.android;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class PuntuacionesViewHolder extends RecyclerView.ViewHolder {

    TextView nombre, dificultad, puntuacion;
    ConstraintLayout parentLayout;


    public PuntuacionesViewHolder(@NonNull View itemView) {
        super(itemView);
        // TODO HACER LAYOUT
        nombre = itemView.findViewById(R.id.txtNombre);
        dificultad = itemView.findViewById(R.id.txtDificultad);
        puntuacion = itemView.findViewById(R.id.txtPuntuacion);

        parentLayout = itemView.findViewById(R.id.puntuacionesLayout);
    }
}
