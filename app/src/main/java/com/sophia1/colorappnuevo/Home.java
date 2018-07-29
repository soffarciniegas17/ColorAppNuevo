package com.sophia1.colorappnuevo;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private Animation deslizarD, deslizarI, salirD, salirI, desaparecer;
    private Button btonPlay, btonScores, btonSetting, btonXX;
    private ImageView logoApp;
    private Dialog scoresTop;
    private double[] top5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        findViews();
        findAnimaciones();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LinearLayout contBtones=findViewById(R.id.contenedor_botones_inicio);
                contBtones.setVisibility(View.VISIBLE);
                animarEntrada();
            }
        },500);

        scoresTop=new Dialog(this);
        scoresTop.setContentView(R.layout.dialog_scores);
        scoresTop.setCanceledOnTouchOutside(false);
        scoresTop.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        top5=new double[5];
        for (int i=0; i<top5.length;i++){
            top5[i]=0.00;
        }

    }

    public void findAnimaciones(){
        deslizarD= AnimationUtils.loadAnimation(this,R.anim.deslizar_desde_derecha);
        deslizarD.setFillAfter(true);

        deslizarI=AnimationUtils.loadAnimation(this,R.anim.deslizar_desde_izquierda);
        deslizarI.setFillAfter(true);

        salirD=AnimationUtils.loadAnimation(this,R.anim.salir_desde_derecha);
        salirD.setFillAfter(true);

        salirI=AnimationUtils.loadAnimation(this,R.anim.salir_desde_izquierda);
        salirI.setFillAfter(true);

        desaparecer=AnimationUtils.loadAnimation(this,R.anim.desaparecer);
        desaparecer.setFillAfter(true);

    }
    public void findViews(){
        btonPlay=findViewById(R.id.bton_play);
        btonScores=findViewById(R.id.bton_scores);
        btonSetting=findViewById(R.id.bton_setting);
        btonXX=findViewById(R.id.bton_XX);
        logoApp=findViewById(R.id.logo_app);

    }
    public void animarEntrada(){
        deslizarI.setDuration(100);
        btonPlay.startAnimation(deslizarI);

        deslizarI.setDuration(370);
        btonSetting.startAnimation(deslizarI);

        btonXX.startAnimation(deslizarD);

        deslizarD.setDuration(400);
        btonScores.startAnimation(deslizarD);

    }
    public void animarSalida(){
        salirI.setDuration(250);
        btonPlay.startAnimation(salirI);

        salirI.setDuration(350);
        btonSetting.startAnimation(salirI);

        btonXX.startAnimation(salirD);

        salirD.setDuration(250);
        btonScores.startAnimation(salirD);

        logoApp.startAnimation(desaparecer);


    }
    private Intent intent;

    public void botonesInicio(View view) {


        switch (view.getId()){
            case R.id.bton_play:
                break;
            case R.id.bton_scores:
                abrirScores();
                animarSalida();
                break;
            case R.id.bton_setting:
                animarSalida();

                salirI.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        intent =new Intent(getApplicationContext(), Setting.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                break;
        }
    }
    //codigo mostrar dialog de puntajes
    public void abrirScores(){
        LinearLayout layoutScores=scoresTop.findViewById(R.id.layout_scores);
        final Animation aparecer=AnimationUtils.loadAnimation(this, R.anim.aparecer);
        aparecer.setFillAfter(true);
        layoutScores.startAnimation(aparecer);


        TextView xSalir=scoresTop.findViewById(R.id.dialog_p_salir);

        xSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoresTop.dismiss();
                animarEntrada();
                logoApp.startAnimation(aparecer);
            }
        });
        organizaScores();
        scoresTop.show();


    }

    public void organizaScores(){
        TextView p1, p2, p3, p4, p5;
        p1=scoresTop.findViewById(R.id.p1);
        p2=scoresTop.findViewById(R.id.p2);
        p3=scoresTop.findViewById(R.id.p3);
        p4=scoresTop.findViewById(R.id.p4);
        p5=scoresTop.findViewById(R.id.p5);

        cargarPuntajes();

        p1.setText(String.format("%.2f",top5[0])+"%");
        p2.setText(String.format("%.2f",top5[1])+"%");
        p3.setText(String.format("%.2f",top5[2])+"%");
        p4.setText(String.format("%.2f",top5[3])+"%");
        p5.setText(String.format("%.2f",top5[4])+"%");

    }
    public void cargarPuntajes(){
        DataBase db=new DataBase(this);
        Cursor cursor=db.puntajes();

        if(cursor.moveToFirst()){
            int i=0;
            do{
                top5[i]=cursor.getDouble(0);
                i++;
            }while (cursor.moveToNext());
        }
    }



}
