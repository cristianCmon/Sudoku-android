package sudoku.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity4 extends AppCompatActivity {

    TextView tvDificultad, tvTemporizador;
    private TableroSudoku tableroJuego;
    private ResolverSudoku resolverSudoku;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPista, btnRendirse, btnResolver;
    public static List<Button> botonera = new ArrayList<>();
    String[] datosJugador; // 0 - Nombre, 1 - Dificultad
    boolean estaCompletado = false;


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
    }

    public void activarComponentesActivity() {
        // Textos Estado
        tvDificultad = findViewById(R.id.tvDificultad);
        tvDificultad.setText("Dificultad " + datosJugador[1]);
        tvTemporizador = findViewById(R.id.tvTemporizador);


        tableroJuego = findViewById(R.id.tableroSudoku);
//        resolverSudoku = new ResolverSudoku();
        resolverSudoku = tableroJuego.getResolverSudoku();
        resolverSudoku.setNivelDificultad(datosJugador[1]);


        // Botón 1
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(1);
                configurarBotonResolver(resolverSudoku.estaCompletado());
                tableroJuego.invalidate();
//                resolverSudoku.mostrarSudokuConsola();
            }
        });

        // Botón 2
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(2);
                configurarBotonResolver(resolverSudoku.estaCompletado());
                tableroJuego.invalidate();
//                resolverSudoku.mostrarSudokuConsola();
            }
        });

        // Botón 3
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(3);
                configurarBotonResolver(resolverSudoku.estaCompletado());
                tableroJuego.invalidate();
//                resolverSudoku.mostrarSudokuConsola();
            }
        });

        // Botón 4
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(4);
                configurarBotonResolver(resolverSudoku.estaCompletado());
                tableroJuego.invalidate();
//                resolverSudoku.mostrarSudokuConsola();
            }
        });

        // Botón 5
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(5);
                configurarBotonResolver(resolverSudoku.estaCompletado());
                tableroJuego.invalidate();
//                resolverSudoku.mostrarSudokuConsola();
            }
        });

        // Botón 6
        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(6);
                configurarBotonResolver(resolverSudoku.estaCompletado());
                tableroJuego.invalidate();
//                resolverSudoku.mostrarSudokuConsola();
            }
        });

        // Botón 7
        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(7);
                configurarBotonResolver(resolverSudoku.estaCompletado());
                tableroJuego.invalidate();
//                resolverSudoku.mostrarSudokuConsola();
            }
        });

        // Botón 8
        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(8);
                configurarBotonResolver(resolverSudoku.estaCompletado());
                tableroJuego.invalidate();
//                resolverSudoku.mostrarSudokuConsola();
            }
        });

        // Botón 9
        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolverSudoku.setPosicionNumero(9);
                configurarBotonResolver(resolverSudoku.estaCompletado());
                tableroJuego.invalidate();
//                resolverSudoku.mostrarSudokuConsola();
            }
        });
        // TODO RECOLOCAR BOTÓN PISTA, OCULTAR SI NO SE JUEGA EN FÁCIL
        // Botón Pista
        btnPista = findViewById(R.id.btnPista);
        btnPista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("PISTA");
                resolverSudoku.mostrarSudokuConsola();
                Sudoku.mostrarSudokuConsola(resolverSudoku.tableroCompleto);
            }
        });

        // Botón Rendirse
        btnRendirse = findViewById(R.id.btnRendirse);
        btnRendirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // TODO POSIBLE REFACTORIZACIÓN
                AlertDialog.Builder alertaSalir = new AlertDialog.Builder(v.getContext());
                alertaSalir.setTitle("Rendición");
                alertaSalir.setMessage("\n¿Seguro que quieres abandonar?\n");
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
                // TODO HACER BIEN
                // TODO COMPROBAR SI DIFICULTAD ES DIFICIL $$ SI TABLERO LLENO, SINO...
//                if (btnResolver.getText().toString().equals("Volver menú principal")) {
//                    Intent intent = new Intent(MainActivity4.this, MainActivity.class);
//                    startActivity(intent);
//                }
                if (resolverSudoku.getNivelDificultad().equals("Difícil")) {
                    System.out.println("RESUELTO?...");
                    if (Arrays.deepEquals(resolverSudoku.tablero, resolverSudoku.tableroCompleto)) {
                        System.out.println("COMPLETADO CON EXITO");
                        // TODO POSIBLE REFACTORIZACIÓN
                        AlertDialog.Builder alertaSalir = new AlertDialog.Builder(v.getContext());
                        alertaSalir.setTitle("ENHORABUENA");
                        alertaSalir.setMessage("\nLo has logrado, bien hecho\n");
                        alertaSalir.setCancelable(false);

                        alertaSalir.setPositiveButton("Guardar y Volver", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                                startActivity(intent);

                                dialog.dismiss();
                            }
                        });

//                    alertaSalir.setNegativeButton("Continuar", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });

                        alertaSalir.show();


                    } else {
                        System.out.println("NO COINCIDE :(");
                        // TODO POSIBLE REFACTORIZACIÓN
                        AlertDialog.Builder alertaSalir = new AlertDialog.Builder(v.getContext());
                        alertaSalir.setTitle("Fallaste");
                        alertaSalir.setMessage("\nSigue intentádolo, ¡ánimo!\n");
                        alertaSalir.setCancelable(false);

//                    alertaSalir.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            Intent intent = new Intent(MainActivity4.this, MainActivity.class);
//                            startActivity(intent);
//
//                            dialog.dismiss();
//                        }
//                    });

                        alertaSalir.setNegativeButton("Continuar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        alertaSalir.show();
                    }
                }
//                System.out.println("RESUELTO?...");
//                if (Arrays.deepEquals(resolverSudoku.tablero, resolverSudoku.tableroCompleto)) {
//                    System.out.println("COMPLETADO CON EXITO");
//                    // TODO POSIBLE REFACTORIZACIÓN
//                    AlertDialog.Builder alertaSalir = new AlertDialog.Builder(v.getContext());
//                    alertaSalir.setTitle("ENHORABUENA");
//                    alertaSalir.setMessage("\nLo has logrado, bien hecho\n");
//                    alertaSalir.setCancelable(false);
//
//                    alertaSalir.setPositiveButton("Guardar y Volver", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            Intent intent = new Intent(MainActivity4.this, MainActivity.class);
//                            startActivity(intent);
//
//                            dialog.dismiss();
//                        }
//                    });
//
////                    alertaSalir.setNegativeButton("Continuar", new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////                            dialog.cancel();
////                        }
////                    });
//
//                    alertaSalir.show();
//
//
//                } else {
//                    System.out.println("NO COINCIDE :(");
//                    // TODO POSIBLE REFACTORIZACIÓN
//                    AlertDialog.Builder alertaSalir = new AlertDialog.Builder(v.getContext());
//                    alertaSalir.setTitle("Fallaste");
//                    alertaSalir.setMessage("\nSigue intentádolo, ¡ánimo!\n");
//                    alertaSalir.setCancelable(false);
//
////                    alertaSalir.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
////                        public void onClick(DialogInterface dialog, int id) {
////                            Intent intent = new Intent(MainActivity4.this, MainActivity.class);
////                            startActivity(intent);
////
////                            dialog.dismiss();
////                        }
////                    });
//
//                    alertaSalir.setNegativeButton("Continuar", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//
//                    alertaSalir.show();
//                }

                // TODO ADAPTAR PARA MOSTRAR SOLUCIÓN Y SALIR
                // TODO METER DENTRO DE ALERTS - OJO CON NIVEL DIFÍCIL
                // TODO LO SIGUIENTE FUNCIONA EN FACIL - NORMAL
                if (btnResolver.getText().toString().equals("Resolver")) {
                    btnPista.setVisibility(View.GONE);
                    btnRendirse.setVisibility(View.GONE);
                    btnResolver.setText("Volver menú principal");
                } else {
                    btnResolver.setText("Resolver");
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

    public static void activarBotoneraNumerica(boolean activar) {
        for (Button b : botonera) {
            b.setEnabled(activar);
        }
    }

    public void configurarBotonResolver(boolean seActiva) {
        btnResolver.setEnabled(seActiva);
    }

    public void configurarBotoneraAcciones() {
        if (!resolverSudoku.getNivelDificultad().equals("Fácil")) {
            btnPista.setVisibility(View.GONE);
        }

        if (resolverSudoku.getNivelDificultad().equals("Difícil")) {
            btnResolver.setEnabled(false);
        }
    }

}