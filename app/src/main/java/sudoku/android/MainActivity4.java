package sudoku.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class MainActivity4 extends AppCompatActivity {

    private TextView tvDificultad, tvTemporizador;
    private TableroSudoku tableroJuego;
    private ResolverSudoku resolverSudoku;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPista, btnRendirse, btnResolver;
    public static List<Button> botonera = new ArrayList<>();
    String[] datosJugador; // 0 - Nombre, 1 - Dificultad
    private int casillasObjetivo;

    private int segundosPartida = 0;
    private boolean ejecutarTemporizadorPartida = true;

    Puntuaciones puntuacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tableroSudoku), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent datosRecibidos = getIntent();

        if (datosRecibidos != null) {
            datosJugador = datosRecibidos.getStringArrayExtra("DATOS_PARTIDA");
            System.out.println(datosJugador[0] + " - " + datosJugador[1]);
        }

        activarComponentesActivity();
        activarBotoneraNumerica(true);
        configurarBotoneraAcciones();
        System.out.println(resolverSudoku.getNivelDificultad());
        Sudoku.mostrarSudokuConsola(resolverSudoku.tableroCompleto);
    }

    public void activarComponentesActivity() {
        // Textos Estado
        tvDificultad = findViewById(R.id.tvDificultad);
        tvDificultad.setText("Dificultad " + datosJugador[1]);
        tvTemporizador = findViewById(R.id.tvTemporizador);
        ejecutarTemporizador();

        // Setup tablero
        tableroJuego = findViewById(R.id.tableroSudoku);
        resolverSudoku = tableroJuego.getResolverSudoku();
        casillasObjetivo = resolverSudoku.getCasillasObjetivo();
        resolverSudoku.setNivelDificultad(datosJugador[1]);

        // Botón 1
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(1);
                estadoBotonera();
                tableroJuego.invalidate();
            }
        });

        // Botón 2
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(2);
                estadoBotonera();
                tableroJuego.invalidate();
            }
        });

        // Botón 3
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(3);
                estadoBotonera();
                tableroJuego.invalidate();
            }
        });

        // Botón 4
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(4);
                estadoBotonera();
                tableroJuego.invalidate();
            }
        });

        // Botón 5
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(5);
                estadoBotonera();
                tableroJuego.invalidate();
            }
        });

        // Botón 6
        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(6);
                estadoBotonera();
                tableroJuego.invalidate();
            }
        });

        // Botón 7
        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(7);
                estadoBotonera();
                tableroJuego.invalidate();
            }
        });

        // Botón 8
        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(8);
                estadoBotonera();
                tableroJuego.invalidate();
            }
        });

        // Botón 9
        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(9);
                estadoBotonera();
                tableroJuego.invalidate();
            }
        });

        // Botón Pista
        btnPista = findViewById(R.id.btnPista);
        btnPista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.validarPista();
                estadoBotonera();
//                resolverSudoku.mostrarSudokuConsola();
//                Sudoku.mostrarSudokuConsola(resolverSudoku.tableroCompleto);
            }
        });

        // Botón Rendirse
        btnRendirse = findViewById(R.id.btnRendirse);
        btnRendirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertaSalir = new AlertDialog.Builder(v.getContext());
                alertaSalir.setTitle("Rendición");
                alertaSalir.setMessage("\n¿Seguro que quieres abandonar " + datosJugador[0] + "?\n");
                alertaSalir.setCancelable(false);

                alertaSalir.setPositiveButton("Abandonar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });

                alertaSalir.setNegativeButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertaSalir.show();
            }
        });

        // Botón Resolver
        btnResolver = findViewById(R.id.btnResolver);
        btnResolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fácil - Normal si se resuelve con trampa
                if (btnResolver.getText().toString().equals("Volver menú principal")) {
                    Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                    startActivity(intent);

                } else {

                    if (resolverSudoku.getNivelDificultad().equals("Difícil")) {
                        if (Arrays.deepEquals(resolverSudoku.tablero, resolverSudoku.tableroCompleto)) {
                            ejecutarTemporizadorPartida = false;
                            establecerPuntuacion();

                            AlertDialog.Builder alertaSalir = new AlertDialog.Builder(v.getContext());
                            alertaSalir.setTitle("ENHORABUENA");
                            alertaSalir.setMessage("\nLo lograste " + datosJugador[0] + ".\n\nHas conseguido " + puntuacion.getPuntuacion() + " puntos.\n\n¡Bien hecho!.\n");
                            alertaSalir.setCancelable(false);

                            alertaSalir.setPositiveButton("Guardar y Volver", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    PeticionesBD.guardarPuntuacion(getApplicationContext(), puntuacion);

                                    Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                                    startActivity(intent);

                                    dialog.dismiss();
                                }
                            });

                            alertaSalir.show();

                        } else {
                            AlertDialog.Builder alertaSalir = new AlertDialog.Builder(v.getContext());
                            alertaSalir.setTitle("Fallaste");
                            alertaSalir.setMessage("\nSigue intentádolo, ¡ánimo!.\n");
                            alertaSalir.setCancelable(false);

                            alertaSalir.setNegativeButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            alertaSalir.show();
                        }

                    } else {
                        // Fácil-Normal si el jugador ha completado el tablero
                        if (resolverSudoku.estaCompletado()) {
                            if (Arrays.deepEquals(resolverSudoku.tablero, resolverSudoku.tableroCompleto)) {
                                ejecutarTemporizadorPartida = false;
                                establecerPuntuacion();

                                AlertDialog.Builder alertaSalir = new AlertDialog.Builder(v.getContext());
                                alertaSalir.setTitle("ENHORABUENA");
                                alertaSalir.setMessage("\nLo lograste " + datosJugador[0] + ".\n\nHas conseguido " + puntuacion.getPuntuacion() + " puntos.\n\n¡Bien hecho!.\n");
                                alertaSalir.setCancelable(false);

                                alertaSalir.setPositiveButton("Guardar y Volver", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        PeticionesBD.guardarPuntuacion(getApplicationContext() ,puntuacion);

                                        Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                                        startActivity(intent);

                                        dialog.dismiss();
                                    }
                                });

                                alertaSalir.show();

                            } else {
                                AlertDialog.Builder alertaSalir = new AlertDialog.Builder(v.getContext());
                                alertaSalir.setTitle("Fallaste");
                                alertaSalir.setMessage("\nSigue intentádolo, ¡ánimo!.\n");
                                alertaSalir.setCancelable(false);

                                alertaSalir.setNegativeButton("Continuar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                                alertaSalir.show();
                            }

                        } else { // Si no está completado dará opción de trampear (no se guardará puntuación)
                            AlertDialog.Builder alertaSalir = new AlertDialog.Builder(v.getContext());
                            alertaSalir.setTitle("NO HAS TERMINADO");
                            alertaSalir.setMessage("\nSi haces trampa resolverás el sudoku pero no se guardará la puntuación.\n");
                            alertaSalir.setCancelable(false);

                            alertaSalir.setPositiveButton("Hacer Trampa", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ejecutarTemporizadorPartida = false;
                                    resolverSudoku.setTablero(resolverSudoku.getTableroCompleto());
                                    tableroJuego.invalidate();

                                    btnPista.setVisibility(View.GONE);
                                    btnRendirse.setVisibility(View.GONE);
                                    btnResolver.setText("Volver menú principal");

                                    dialog.dismiss();
                                }
                            });

                            alertaSalir.setNegativeButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            alertaSalir.show();
                        }
                    }
                }
            }
        });

        // Grupo botonera
        botonera.add(btn1);
        botonera.add(btn2);
        botonera.add(btn3);
        botonera.add(btn4);
        botonera.add(btn5);
        botonera.add(btn6);
        botonera.add(btn7);
        botonera.add(btn8);
        botonera.add(btn9);
    }

    private void ejecutarTemporizador() {
        final Handler manejador = new Handler(Looper.getMainLooper());

        manejador.post(new Runnable() {
            @Override
            public void run() {
                int minutos = (segundosPartida % 3600) / 60;
                int segundos = segundosPartida % 60;

                String tiempoFormateado = String.format(Locale.getDefault(),
                        "%02d:%02d", minutos, segundos);

                // Mostrará el tiempo de partida
                tvTemporizador.setText(tiempoFormateado);

                if (ejecutarTemporizadorPartida) {
                    // necesitaremos los segundos para calibrar la puntuación final
                    segundosPartida++;
                }

                // Se actualiza cada segundo
                manejador.postDelayed(this, 1000);
            }
        });
    }

    public static void activarBotoneraNumerica(boolean activar) {
        for (Button b : botonera) {
            b.setEnabled(activar);
        }
    }

    public void estadoBotonera() {
        // Muestra el botón Resolver si se ha cubierto al completo en Difícil
        if (datosJugador[1].equals("Difícil")) {
            btnResolver.setEnabled(resolverSudoku.estaCompletado());
        }
        // Deshabilita el botón Pista si se ha cubierto al completo en Fácil
        if (datosJugador[1].equals("Fácil")) {
            btnPista.setEnabled(!resolverSudoku.estaCompletado());
        }
    }

    public void configurarBotoneraAcciones() {
        if (!resolverSudoku.getNivelDificultad().equals("Fácil")) {
            btnPista.setVisibility(View.GONE);
        }

        if (resolverSudoku.getNivelDificultad().equals("Difícil")) {
            btnResolver.setEnabled(false);
        }
    }

    /*
    * El sistema de puntuación tiene en cuenta las casillas resueltas, la cantidad
    * de pistas utilizadas, la dificultad y el tiempo empleado en resolver el tablero.
    *
    * La puntuación nunca podrá ser menor o igual a 0, en esos supuestos la puntuación final
    * equivaldrá al multiplicador/100 según dificultad (Fácil 100, Normal 400, Difícil 2000)
     */
    public void establecerPuntuacion() {
        int puntuacionFinal;
        int multiplicador = 0;

        switch(datosJugador[1]) {
            case "Fácil":
                multiplicador = 100;
                break;
            case "Normal":
                multiplicador = 400;
                break;
            case "Difícil":
                multiplicador = 2000;
                break;
        }

        puntuacionFinal = (casillasObjetivo - resolverSudoku.getPistasUsadas()) * multiplicador - segundosPartida;

        if (puntuacionFinal <= 0) {
            puntuacionFinal = multiplicador / 100;
        }

        puntuacion = new Puntuaciones(datosJugador[0], datosJugador[1], Integer.toString(puntuacionFinal));
        System.out.println(puntuacion);
    }

}
