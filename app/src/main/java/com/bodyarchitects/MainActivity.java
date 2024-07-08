package com.bodyarchitects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button signin;
    EditText email,password;
    TextView forgotpassword,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        email=findViewById((R.id.main_email));
        password=findViewById((R.id.main_password));
        signin=findViewById((R.id.main_signin));
        signup = findViewById((R.id.main_signup));
        forgotpassword = findViewById((R.id.main_forgotpassword));


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
                    System.out.println("Login Successful");
                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,DashboardActivity.class);
                    startActivity(intent);
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
                Intent intent=new Intent(MainActivity.this,ForgotpasswordActivity.class);
                startActivity(intent);
            }
        });
    }

}