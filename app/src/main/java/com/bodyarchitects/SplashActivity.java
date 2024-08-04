package com.bodyarchitects;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sp=getSharedPreferences(Constantsp.PREF,MODE_PRIVATE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(sp.getString(Constantsp.USERID,"").equalsIgnoreCase(""))
                {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this,Newdashboard.class);
                    startActivity(intent);
                    finish();
                }
            }
        },500);
    }
}