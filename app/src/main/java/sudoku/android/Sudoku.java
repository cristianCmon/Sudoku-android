package sudoku.android;

import java.util.Random;

public class Sudoku {

    static int[][] rejilla;


    static int[][] generar() {
        rejilla = new int[9][9];
        crearSudoku(rejilla, 0, 0);
        return rejilla;
    }

    static boolean crearSudoku(int[][] rejilla, int fila, int columna) {
        Random random = new Random();
        int cifraGenerada = 0;

        // Comprueba si ha llegado al límite de la rejilla
        if (fila == 8 && columna == 9) {
            return true;
        }

        // Comprueba si la última columna de la fila pasa a la siguiente fila
        if (columna == 9) {
            fila++;
            columna = 0;
        }

        // Comprueba si la celda ya está ocupada, entonces avanza
        if (rejilla[fila][columna] != 0) {
            return crearSudoku(rejilla, fila, columna + 1);
        }

        for (int num = 1; num <= 9; num++) {
            cifraGenerada = random.nextInt(1, 10);

            // Comprueba si es seguro colocar la cifra en la posición actual
            if (esSeguro(rejilla, fila, columna, cifraGenerada)) {
                rejilla[fila][columna] = cifraGenerada;

                if (crearSudoku(rejilla, fila, columna + 1)) {
                    return true;
                }

                rejilla[fila][columna] = 0;
            }
        }

        return false;
    }

    static boolean esSeguro(int[][] rejilla, int fila, int columna, int cifraObjetivo) {
        // Comprueba si la cifra existe en la fila
        for (int x = 0; x < 9; x++) {
            if (rejilla[fila][x] == cifraObjetivo) {
                return false;
            }
        }

        // Comprueba si la cifra existe en la columna
        for (int x = 0; x < 9; x++) {
            if (rejilla[x][columna] == cifraObjetivo) {
                return false;
            }
        }

        // Comprueba si la cifra existe en bloque 3x3
        int inicioFila = fila - (fila % 3);
        int inicioColumna = columna - (columna % 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (rejilla[i + inicioFila][j + inicioColumna] == cifraObjetivo) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void mostrarSudokuConsola(int[][] sudoku) {
        for (int i = 0; i < sudoku.length; i++) {

            for (int j = 0; j < sudoku[i].length; j++) {

                if ((j + 1) % 3 == 0) {
                    System.out.print(sudoku[i][j] + "    ");
                } else {
                    System.out.print(sudoku[i][j] + " ");
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

}
