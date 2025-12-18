package sudoku.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TableroSudoku extends View {
    private final int colorTablero, colorRellenarCelda, colorResaltarCeldas, colorLetra, colorLetraResuelta;

    private final Paint pintarColorTablero = new Paint();
    private final Paint pintarColorRellenarCelda = new Paint();
    private final Paint pintarColorResaltarCeldas = new Paint();
    private final Paint pintarLetra = new Paint();
    private final Paint pintarLetraResuelta = new Paint();

    private final Rect pintarLetraBordes = new Rect();

    private int celdaSize;

    private final ResolverSudoku resolverSudoku = new ResolverSudoku();


    public TableroSudoku(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TableroSudoku, 0, 0);

        try {
            colorTablero = a.getInteger(R.styleable.TableroSudoku_colorTablero, 0);
            colorRellenarCelda = a.getInteger(R.styleable.TableroSudoku_colorRellenarCelda, 0);
            colorResaltarCeldas = a.getInteger(R.styleable.TableroSudoku_colorResaltarCeldas, 0);
            colorLetra = a.getInteger(R.styleable.TableroSudoku_colorLetra, 0);
            colorLetraResuelta = a.getInteger(R.styleable.TableroSudoku_colorLetraResuelta, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int dimension = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());
        celdaSize = dimension / 9;

        setMeasuredDimension(dimension, dimension); // 9x9
    }

    @Override
    protected void onDraw(Canvas canvas) {
        pintarColorTablero.setStyle(Paint.Style.STROKE);
        pintarColorTablero.setStrokeWidth(16);
        pintarColorTablero.setColor(colorTablero);
        pintarColorTablero.setAntiAlias(true);

        pintarColorRellenarCelda.setStyle(Paint.Style.FILL);
        pintarColorRellenarCelda.setColor(colorRellenarCelda);
        pintarColorRellenarCelda.setAntiAlias(true);

        pintarColorResaltarCeldas.setStyle(Paint.Style.FILL);
        pintarColorResaltarCeldas.setColor(colorResaltarCeldas);
        pintarColorResaltarCeldas.setAntiAlias(true);

        pintarLetra.setStyle(Paint.Style.FILL);
        pintarLetra.setColor(colorLetra);
        pintarLetra.setAntiAlias(true);

        pintarLetraResuelta.setStyle(Paint.Style.FILL);
        pintarLetraResuelta.setColor(colorLetraResuelta);
        pintarLetraResuelta.setAntiAlias(true);

        colorCelda(canvas, resolverSudoku.getFilaSeleccionada(), resolverSudoku.getColumnaSeleccionada());
        canvas.drawRect(0, 0, getWidth(), getHeight(), pintarColorTablero);
        drawBoard(canvas);
        drawNumbers(canvas);

//        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean esValido;

        float x = event.getX();
        float y = event.getY();

        int accion = event.getAction();

        if (accion == MotionEvent.ACTION_DOWN) {
            resolverSudoku.setFilaSeleccionada((int) Math.ceil(y / celdaSize));
            resolverSudoku.setColumnaSeleccionada((int) Math.ceil(x / celdaSize));
            esValido = true;

        } else {
            esValido = false;
        }

        return esValido;
    }

    private void drawNumbers(Canvas canvas) {
        pintarLetra.setTextSize(celdaSize);

        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                if (resolverSudoku.getTablero()[f][c] != 0) {
                    String texto = Integer.toString(resolverSudoku.getTablero()[f][c]);
                    float ancho, alto;

                    pintarLetra.getTextBounds(texto, 0, texto.length(), pintarLetraBordes);
                    ancho = pintarLetra.measureText(texto);
                    alto = pintarLetraBordes.height();

                    canvas.drawText(texto, (c * celdaSize) + ((celdaSize - ancho) / 2), (f * celdaSize + celdaSize) - ((celdaSize - alto) / 2), pintarLetra);
                }
            }
        }
        // TODO REVISAR ESTA PINTADA
        pintarLetra.setColor(colorLetraResuelta);

        for (ArrayList<Object> letra : resolverSudoku.getIndiceCajaVacia()) {
            int f = (int) letra.get(0);
            int c = (int) letra.get(1);

            String texto = Integer.toString(resolverSudoku.getTablero()[f][c]);
            float ancho, alto;

            pintarLetra.getTextBounds(texto, 0, texto.length(), pintarLetraBordes);
            ancho = pintarLetra.measureText(texto);
            alto = pintarLetraBordes.height();

            canvas.drawText(texto, (c * celdaSize) + ((celdaSize - ancho) / 2), (f * celdaSize + celdaSize) - ((celdaSize - alto) / 2), pintarLetra);

        }
    }

    private void colorCelda(Canvas canvas, int f, int c) {
        if ((resolverSudoku.getColumnaSeleccionada() != -1) && (resolverSudoku.getFilaSeleccionada() != -1)) {
            // Resalta fila de celda pulsada, EMBELLECEDOR OPCIONAL
//            canvas.drawRect(0, (f - 1) * celdaSize, celdaSize * 9, f * celdaSize, pintarColorResaltarCeldas);
            // Resalta columna de celda pulsada, EMBELLECEDOR OPCIONAL
//            canvas.drawRect((c - 1) * celdaSize, 0, c * celdaSize, celdaSize * 9, pintarColorResaltarCeldas);
            // Resalta celda pulsada
            canvas.drawRect((c - 1) * celdaSize, (f - 1) * celdaSize, c * celdaSize, f * celdaSize, pintarColorRellenarCelda);
        }

        invalidate(); // refresca tablero
    }

    private void drawThickLine() {
        pintarColorTablero.setStyle(Paint.Style.STROKE);
        pintarColorTablero.setStrokeWidth(10);
        pintarColorTablero.setColor(colorTablero);
    }

    private void drawThinLine() {
        pintarColorTablero.setStyle(Paint.Style.STROKE);
        pintarColorTablero.setStrokeWidth(4);
        pintarColorTablero.setColor(colorTablero);
    }

    private void drawBoard(Canvas canvas) {
        for (int c = 0; c < 10; c++) {
            if (c % 3 == 0) { // cada 3 líneas horizontales
                drawThickLine();
            } else {
                drawThinLine();
            }

            canvas.drawLine(celdaSize * c, 0, celdaSize * c, getWidth(), pintarColorTablero);
        }

        for (int r = 0; r < 10; r++) {
            if (r % 3 == 0) { // cada 3 líneas verticales
                drawThickLine();
            } else {
                drawThinLine();
            }

            canvas.drawLine(0, celdaSize * r, getWidth(), celdaSize * r, pintarColorTablero);

        }
    }

    public ResolverSudoku getResolverSudoku() {
        return resolverSudoku;
    }

    public void fijarDificultad(String dificultad) {
        this.resolverSudoku.setNivelDificultad(dificultad);
    }

    public void nuevoSudoku() {
        resolverSudoku.generarNuevo();
    }

}
