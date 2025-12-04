package sudoku.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TableroSudoku extends View {
    private final int colorTablero;
    private final Paint pintarColorTablero = new Paint();
    private int celdaSize;


    public TableroSudoku(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TableroSudoku, 0, 0);

        try {
            colorTablero = a.getInteger(R.styleable.TableroSudoku_colorTablero, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int dimension = Math.min(this.getWidth(), this.getHeight());
        celdaSize = dimension / 9;

        setMeasuredDimension(dimension, dimension); // 9x9
    }

    @Override
    protected void onDraw(Canvas canvas) {
        pintarColorTablero.setStyle(Paint.Style.STROKE);
        pintarColorTablero.setStrokeWidth(16);
        pintarColorTablero.setColor(colorTablero);
        pintarColorTablero.setAntiAlias(true);

        canvas.drawRect(0, 0, getWidth(), getHeight(), pintarColorTablero);
        drawBoard(canvas);
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
            if (c % 3 == 0) {
                drawThickLine();
            } else {
                drawThinLine();
            }

            canvas.drawLine(celdaSize * c, 0, celdaSize * c, getWidth(), pintarColorTablero);
        }

        for (int r = 0; r < 10; r++) {
            if (r % 3 == 0) {
                drawThickLine();
            } else {
                drawThinLine();
            }

            canvas.drawLine(0, celdaSize * r, getWidth(), celdaSize * r, pintarColorTablero);

        }
    }
}
