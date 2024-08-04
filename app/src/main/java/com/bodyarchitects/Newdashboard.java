package com.bodyarchitects;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Newdashboard extends AppCompatActivity {

    TextView welcomeText;
    TextView ex1;
    SharedPreferences sp;
    Button home,workouts,more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdashboard);
        ex1=findViewById(R.id.exercise1);
        welcomeText=findViewById(R.id.welcometext);
        home=findViewById(R.id.dashboard_home);
        workouts=findViewById(R.id.dashboard_workouts);
        more=findViewById(R.id.dashboard_more);

        sp=getSharedPreferences(Constantsp.PREF,MODE_PRIVATE);

        welcomeText.setText("Welcome "+ sp.getString(Constantsp.NAME,"")+"!");

        workouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Newdashboard.this, WorkoutsPage.class);
                startActivity(intent);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Newdashboard.this, MoreActivity.class);
                startActivity(intent);
            }
        });

        ex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Newdashboard.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finishAffinity();
    }
}