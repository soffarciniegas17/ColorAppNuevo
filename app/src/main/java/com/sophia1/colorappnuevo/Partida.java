package com.sophia1.colorappnuevo;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Partida extends AppCompatActivity {

    int numeros[];
    String colorT [] = {"AZUL", "ROJO", "AMARILLO", "VERDE"};
    int colores [] = {R.drawable.b_azul, R.drawable.b_rojo, R.drawable.b_amarillo, R.drawable.b_verde};
    TextView palabra, porcent, intent, totalidad;
    Button b1, b2, b3, b4, inicio, facebook, twitter, reinicio;
    Long tiempoPa, tiempoP;
    double porcentaje;
    Dialog finaljuego;
    int modo, intentos, total, bien, mal, pb, ontick;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        tiempoPartida();
    }

    public int random (){
        Random r = new Random();
        int n1 = r.nextInt(4);
        return n1;
    }

    public void desordenar (){
        int i=0;
        numeros= new int[4];
        do {
            int num = random();
            if (numeros[num]==0){
                i++;
                numeros[num] =i;
            }
        }while (i<5);

        b1.setBackgroundResource(colores[numeros[0]]);
        b2.setBackgroundResource(colores[numeros[1]]);
        b3.setBackgroundResource(colores[numeros[2]]);
        b4.setBackgroundResource(colores[numeros[3]]);


    }
    int p;
    public void asignarColor (){
        p = random();
        switch (p){
            case 0:
                palabra.setTextColor(Color.BLUE);
                break;
            case 1:
                palabra.setTextColor(Color.RED);
                break;
            case 2:
                palabra.setTextColor(Color.YELLOW);
                break;
            case 3:
                palabra.setTextColor(Color.GREEN);
                break;
        }
    }

    public void mostrar (){
        tiempoPalabra();
        total++;
        desordenar();
        asignarColor();
        palabra.setText(colorT[random()]);
        porcent.setText(""+ porcentaje());
        intent.setText(""+ intentos);
        totalidad.setText(""+total);
    }

    public void comparar (){
        if (pb==p){
            bien++
        } else {
            mal++;
            intentos--;
        }
        juego();
        tiempoPalabra();
    }
    public void juego (){
        mostrar();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                palabraT.cancel();
                pb=numeros[0];
                comparar();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                palabraT.cancel();
                pb=numeros[1];
                comparar();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                palabraT.cancel();
                pb=numeros[2];
                comparar();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                palabraT.cancel();
                pb=numeros[3];
                comparar();
            }
        });


    }

    CountDownTimer palabraT;
    public void tiempoPalabra(){
        palabraT = new CountDownTimer(tiempoPa, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ontick++;
            }

            @Override
            public void onFinish() {
                intentos--;
                mostrar();
            }
        }.start();
    }

    public void tiempoPartida(){
        juego();
        CountDownTimer partida= new CountDownTimer(tiempoP, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                finalJuego();
            }
        }.start();
    }
    Intent i;
    public void finalJuego(){
        finaljuego.show();

        inicio = finaljuego.findViewById(R.id.inicio);
        reinicio = finaljuego.findViewById(R.id.reiniciar);
        facebook = finaljuego.findViewById(R.id.facebook);
        twitter = finaljuego.findViewById(R.id.twitter);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finaljuego.dismiss();
                i = new Intent(Partida.this, Home.class);
                startActivity(i);
                finish();
            }
        });

        reinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finaljuego.dismiss();
                tiempoPartida();
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, "HE GANADO EL PUNTAJE "+ porcentaje+ " EN COLORITO APP");
                startActivity(i);
            }
        });


    }

}
