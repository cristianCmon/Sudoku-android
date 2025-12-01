package sudoku.android;

public class Puntuaciones {

    private String _id;
    private String nombre;
    private String dificultad;
    private String puntuacion;


    public Puntuaciones(String puntuacion, String dificultad, String nombre) {
        this.puntuacion = puntuacion;
        this.dificultad = dificultad;
        this.nombre = nombre;
    }

    public Puntuaciones(String _id, String nombre, String dificultad, String puntuacion) {
        this._id = _id;
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.puntuacion = puntuacion;
    }


    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return this._id + " - " + this.nombre + " | " + this.dificultad + " | " + this.puntuacion;
    }

}
