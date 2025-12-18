package sudoku.android;

import java.util.ArrayList;

public class ResolverSudoku {

    int[][] tablero, tableroCompleto;
    boolean[][] esClicable;

    String nivelDificultad = "";
    ArrayList<ArrayList<Object>> indiceCajaVacia;

    private int filaSeleccionada;
    private int columnaSeleccionada;


    public ResolverSudoku() {
        filaSeleccionada = -1;
        columnaSeleccionada = -1;

        generarNuevo();
//        prueba();
//        tablero = new int[9][9];

        // Crea tablero vacío (lleno de 0s)
//        for (int f = 0; f < 9; f++) {
//            for (int c = 0; c < 9; c++) {
//                tablero[f][c] = 0;
//            }
//        }

        // Almacenará celdas vacías
        indiceCajaVacia = new ArrayList<>();
    }

//    public ResolverSudoku(int[][] tableroPropuesto) {
//        filaSeleccionada = -1;
//        columnaSeleccionada = -1;
//
//        tablero = tableroPropuesto;
//
//        // Almacenará celdas vacías
//        indiceCajaVacia = new ArrayList<>();
//    }

    private void prueba() {
        tablero[0][0] = 0;
        tablero[1][1] = 0;
        tablero[2][2] = 0;
        tablero[3][3] = 0;
        tablero[4][4] = 0;
        tablero[5][5] = 0;
        tablero[6][6] = 0;
        tablero[7][7] = 0;
        tablero[8][8] = 0;
    }

    private void getIndicesCajaVacia() {
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                if (this.tablero[f][c] == 0) {
                    this.indiceCajaVacia.add(new ArrayList<>());
                    this.indiceCajaVacia.get(this.indiceCajaVacia.size() - 1).add(f);
                    this.indiceCajaVacia.get(this.indiceCajaVacia.size() - 1).add(c);
                }
            }
        }
    }

    public void setPosicionNumero(int numero) {
        if ((this.filaSeleccionada != -1) && (this.columnaSeleccionada != -1) && esClicable[this.filaSeleccionada - 1][this.columnaSeleccionada - 1]) {
            // TODO Función de borrado temporal, borra número si se vuelve a pulsar
            if (this.tablero[this.filaSeleccionada - 1][this.columnaSeleccionada - 1] == numero) {
                this.tablero[this.filaSeleccionada - 1][this.columnaSeleccionada - 1] = 0;
            } else {
                this.tablero[this.filaSeleccionada - 1][this.columnaSeleccionada - 1] = numero;
                System.out.println(comprobarNumeroTablero(this.filaSeleccionada - 1, this.columnaSeleccionada - 1));
//                comprobarNumeroTablero(numero);
            }
        }
    }
    // TODO RESOLVER PINTAR ROJO
    private boolean comprobarNumeroTablero(int fila, int columna) {
        if (this.tablero[fila][columna] > 0) { // Si no está vacío...
            for (int i = 0; i < 9; i++) {
                // Comprobador Horizontal
                if (this.tablero[i][columna] == this.tablero[fila][columna] && (fila != i)) {
                    return false;
                }
                // Comprobador Vertical
                if (this.tablero[fila][i] == this.tablero[fila][columna] && (columna != i)) {
                    return false;
                }
            }

            // División de enteros, obtendremos 0-1-2
            int filaBloque = fila / 3;
            int columnaBloque = columna / 3;

            // Comprobador Bloque
            for (int f = filaBloque * 3; f < (filaBloque * 3) + 3; f++) {
                for (int c = columnaBloque * 3; c < (columnaBloque * 3) + 3; c++) {
                    if (this.tablero[f][c] == this.tablero[fila][columna] && (fila != f) && (columna != c)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public int[][] getTablero() {
        return this.tablero;
    }

    public ArrayList<ArrayList<Object>> getIndiceCajaVacia() {
        return this.indiceCajaVacia;
    }

    public int getFilaSeleccionada() {
        return this.filaSeleccionada;
    }

    public void setFilaSeleccionada(int fila) {
        this.filaSeleccionada = fila;
    }

    public int getColumnaSeleccionada() {
        return this.columnaSeleccionada;
    }

    public void setColumnaSeleccionada(int columna) {
        this.columnaSeleccionada = columna;
    }

    public String getNivelDificultad() {
        return this.nivelDificultad;
    }

    public void setNivelDificultad(String dificultadSeleccionada) {
        this.nivelDificultad = dificultadSeleccionada;
    }

    public void generarNuevo() {
        tableroCompleto = Sudoku.generar();
        tablero = Sudoku.vaciarSudoku(tableroCompleto);
//        tablero = Sudoku.vaciarSudoku(tableroCompleto, this.nivelDificultad);
        esClicable = Sudoku.posicionesClicablesSudoku(tablero); // las que contengan 0 de inicio serán clicables
    }

    public void mostrarSudokuConsola() {
        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {

                if ((j + 1) % 3 == 0) {
                    System.out.print(tablero[i][j] + "    ");
                } else {
                    System.out.print(tablero[i][j] + " ");
                }
            }

            if ((i + 1) % 3 == 0) {
                if ((i + 1) % 9 == 0) {
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("-----------------------\n");
                }
            } else {
                System.out.println();
            }
        }

        System.out.println("***********************\n");
    }

    public boolean estaCompletado() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
