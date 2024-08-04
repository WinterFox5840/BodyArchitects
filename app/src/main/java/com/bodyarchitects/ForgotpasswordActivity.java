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

public class ForgotpasswordActivity extends AppCompatActivity {
    Button forgotpassword;
    EditText email, password, confirmpassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        forgotpassword=findViewById(R.id.Forgotpassword_button);
        email=findViewById(R.id.Forgotpassword_email);
        password=findViewById(R.id.Forgotpassword_newpassword);
        confirmpassword=findViewById(R.id.Forgotpassword_confirmpassword);

        db=openOrCreateDatabase("BodyArchitects.db", MODE_PRIVATE, null);
        String tablecreate = "CREATE TABLE IF NOT EXISTS USERDATA (Sr_no INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(30), EMAIL VARCHAR(40), PHONE BIGINT, PASSWORD VARCHAR(20))";
        db.execSQL(tablecreate);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().trim().equals("")){
                    email.setError("Email Required");
                }
                else if(!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Enter Valid Email");
                }
                else if(password.getText().toString().trim().equals("")){
                    password.setError("Password Required");
                }
                else if(password.getText().toString().trim().length()<6){
                    password.setError("Password should be atleast 6 characters");
                }
                else if(!password.getText().toString().trim().equals(confirmpassword.getText().toString().trim())){
                    confirmpassword.setError("Password doesnt match");
                }
                else {
                    String userCheck = "SELECT * FROM USERDATA WHERE EMAIL = '" + email.getText().toString().trim() + "'";
                    Cursor cursor = db.rawQuery(userCheck, null);
                    if (cursor.getCount() > 0) {
                                                Intent intent = new Intent(ForgotpasswordActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(ForgotpasswordActivity.this, "Password Reset Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        email.setError("Account doesnt exist");
                    }
                }
            }
        });

    }
}