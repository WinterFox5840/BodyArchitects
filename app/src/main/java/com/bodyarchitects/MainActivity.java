package com.bodyarchitects;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    Button signin;
    EditText email,password;
    TextView forgotpassword,signup;
    SQLiteDatabase db;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        email=findViewById((R.id.main_email));
        password=findViewById((R.id.main_password));
        signin=findViewById((R.id.main_signin));
        signup = findViewById((R.id.main_signup));
        forgotpassword = findViewById(R.id.main_forgotpassword);

        db=openOrCreateDatabase("BodyArchitects.db", MODE_PRIVATE, null);
        String tablecreate = "CREATE TABLE IF NOT EXISTS USERDATA (Sr_no INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(30), EMAIL VARCHAR(40), PHONE BIGINT, PASSWORD VARCHAR(20))";
        db.execSQL(tablecreate);

        sp=getSharedPreferences(Constantsp.PREF,MODE_PRIVATE);


        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(email.getText().toString().trim().equals("")){
                    email.setError("Email Required");
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Enter Valid Email");
                } else if (password.getText().toString().trim().equals("")) {
                    password.setError("Password Required");
                } else if (email.getText().toString().trim().length()<6) {
                    password.setError("Password must be atleast 6 characters");
                }
                else {
                    String checkAcc="SELECT * FROM USERDATA WHERE EMAIL = '"+email.getText().toString().trim()+"' AND PASSWORD = '"+password.getText().toString().trim()+"'";
                    Cursor cursor= db.rawQuery(checkAcc,null);
                    if(cursor.getCount()>0) {
                        while (cursor.moveToNext()){
                            sp.edit().putString(Constantsp.USERID,cursor.getString(0)).commit();
                            sp.edit().putString(Constantsp.NAME,cursor.getString(1)).commit();
                            sp.edit().putString(Constantsp.EMAIL,cursor.getString(2)).commit();
                            sp.edit().putString(Constantsp.PHONE,cursor.getString(3)).commit();
                            sp.edit().putString(Constantsp.PASSWORD,cursor.getString(4)).commit();
                        }

                        System.out.println("Login Successful");
                        Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Newdashboard.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Account doesnt exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent=new Intent(MainActivity.this, signupActivity.class);
                startActivity(intent);

            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ForgotpasswordActivity.class);
                startActivity(intent);
            }
        });
    }

}
