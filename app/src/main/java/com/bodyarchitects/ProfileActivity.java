package com.bodyarchitects;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    EditText name, email, phone, password;
    Button save;
    SQLiteDatabase db;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=findViewById(R.id.profile_name);
        phone=findViewById(R.id.profile_phone);
        email=findViewById(R.id.profile_email);
        password=findViewById(R.id.profile_password);
        save=findViewById(R.id.profile_button);
        sp=getSharedPreferences(Constantsp.PREF,MODE_PRIVATE);

        db=openOrCreateDatabase("BodyArchitects.db", MODE_PRIVATE, null);
        String tablecreate = "CREATE TABLE IF NOT EXISTS USERDATA (Sr_no INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(30), EMAIL VARCHAR(40), PHONE BIGINT, PASSWORD VARCHAR(20))";
        db.execSQL(tablecreate);


        String nameShow=sp.getString(Constantsp.NAME, "");
        String phoneShow=sp.getString(Constantsp.PHONE, "");
        String emailShow=sp.getString(Constantsp.EMAIL, "");

        name.setText(nameShow);
        phone.setText(phoneShow);
        email.setText(emailShow);

        name.setEnabled(false);
        phone.setEnabled(false);
        email.setEnabled(false);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().trim().equals("")){
                    password.setError("Password Required");
                }
                else if(password.getText().toString().trim().length()<6){
                    password.setError("Password should be atleast 6 characters");
                }
                else {
                    String updatePass = "UPDATE USERDATA SET PASSWORD='"+password.getText().toString().trim()+"' WHERE EMAIL = '"+sp.getString(Constantsp.EMAIL,"")+"'";
                    db.execSQL(updatePass);
                    sp.edit().putString(Constantsp.PASSWORD,password.getText().toString().trim()).commit();

                    Toast.makeText(ProfileActivity.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}