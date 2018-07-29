package com.sophia1.colorappnuevo;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    private ImageView logoApp;
    private Animation aparece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        logoApp=findViewById(R.id.logo_app);
        aparece= AnimationUtils.loadAnimation(this,R.anim.aparecer);
        aparece.setFillAfter(true);
        logoApp.startAnimation(aparece);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              iniciaHome();
            }
        },2000);
    }
    @TargetApi(21)
    public void iniciaHome(){
        Intent intent=new Intent (this, Home.class);


        Pair pair=new Pair<View,String>(logoApp,"logo_appT");

        ActivityOptions op=ActivityOptions.makeSceneTransitionAnimation(this,pair);
        startActivity(intent, op.toBundle());
        finish();

    }
}
