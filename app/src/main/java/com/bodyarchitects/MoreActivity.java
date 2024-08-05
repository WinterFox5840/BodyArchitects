package com.bodyarchitects;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MoreActivity extends AppCompatActivity {

    Button profile,body,logout;
    Button home,workouts,more;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        home=findViewById(R.id.more_home);
        workouts=findViewById(R.id.more_workouts);
        more=findViewById(R.id.more_more);
        profile=findViewById(R.id.more_profile);
        logout=findViewById(R.id.more_logout);
        sp=getSharedPreferences(Constantsp.PREF,MODE_PRIVATE);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoreActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        workouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoreActivity.this, WorkoutsPage.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoreActivity.this, Newdashboard.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(Constantsp.USERID,"").commit();
                sp.edit().putString(Constantsp.NAME,"").commit();
                sp.edit().putString(Constantsp.EMAIL,"").commit();
                sp.edit().putString(Constantsp.PHONE,"").commit();
                sp.edit().putString(Constantsp.PASSWORD,"").commit();

                Intent intent= new Intent(MoreActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}