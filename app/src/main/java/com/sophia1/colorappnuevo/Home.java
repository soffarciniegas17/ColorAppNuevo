package com.sophia1.colorappnuevo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void botonesInicio(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.bton_play:
                break;
            case R.id.bton_scores:
                break;
            case R.id.bton_setting:
                i=new Intent(this, Setting.class);
                startActivity(i);
                break;
        }
    }
}
