package com.bodyarchitects;

import android.content.Intent;
import android.database.Cursor;
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

public class signupActivity extends AppCompatActivity {

    Button signup;
    EditText name, email, phone, password, confirmpassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup=findViewById(R.id.signup_button);
        name=findViewById(R.id.signup_name);
        phone=findViewById(R.id.signup_phone);
        email=findViewById(R.id.signup_email);
        password=findViewById(R.id.signup_password);
        confirmpassword=findViewById(R.id.signup_confirmpassword);

        db=openOrCreateDatabase("BodyArchitects.db", MODE_PRIVATE, null);
        String tablecreate = "CREATE TABLE IF NOT EXISTS USERDATA (Sr_no INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(30), EMAIL VARCHAR(40), PHONE BIGINT, PASSWORD VARCHAR(20))";
        db.execSQL(tablecreate);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().trim().equals("")){
                    name.setError("Name Required");
                }
                else if(email.getText().toString().trim().equals("")){
                    email.setError("Email Required");
                }
                else if(!email.getText().toString().trim().matches(emailPattern)){
                    email.setError("Enter Valid Email");
                }
                else if(phone.getText().toString().trim().equals("")){
                    phone.setError("Phone Number Required");
                }
                else if(password.getText().toString().trim().equals("")){
                    password.setError("Password Required");
                }
                else if(password.getText().toString().trim().length()<6){
                    password.setError("Password should be atleast 6 characters");
                }
                else if(!password.getText().toString().trim().equals(confirmpassword.getText().toString().trim())) {
                    confirmpassword.setError("Password doesnt match");
                }
                else{

                    String emailCheck="SELECT * FROM USERDATA WHERE EMAIL = '"+email.getText().toString().trim()+"'";
                    Cursor cursor= db.rawQuery(emailCheck,null);
                    if(cursor.getCount()>0) {
                        email.setError("Email already Exists");
                    }
                    else {
                        String INSERT = "INSERT INTO USERDATA VALUES (NULL, '" + name.getText().toString() + "','" + email.getText().toString() + "','" + phone.getText().toString() + "','" + password.getText().toString() + "')";
                        db.execSQL(INSERT);
                        Intent intent = new Intent(signupActivity.this, CategoryActivity.class);
                        startActivity(intent);
                        Toast.makeText(signupActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}