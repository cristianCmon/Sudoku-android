package sudoku.android;

public class ResolverSudoku {

    private static int filaSeleccionada;
    private static int columnaSeleccionada;


    public ResolverSudoku() {
        filaSeleccionada = -1;
        columnaSeleccionada = -1;
    }


    public int getFilaSeleccionada() {
        return filaSeleccionada;
    }

    public void setFilaSeleccionada(int fila) {
        ResolverSudoku.filaSeleccionada = fila;
    }

    public int getColumnaSeleccionada() {
        return columnaSeleccionada;
    }

    public void setColumnaSeleccionada(int columna) {
        ResolverSudoku.columnaSeleccionada = columna;
    }
}
