package com.bodyarchitects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Spinner spinnerage=findViewById(R.id.Category_ageGroup);
        Spinner spinnerheight=findViewById(R.id.Category_heightGroup);
        Spinner spinnergender=findViewById(R.id.Category_gender);
        Button submitbutton=findViewById(R.id.Category_submit);

//      ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(CategoryActivity.this, R.array.Agegroup, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Agegroup, R.layout.spinner_list);
        adapter.setDropDownViewResource(R.layout.spinner_list);
        spinnerage.setAdapter(adapter);

//        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(CategoryActivity.this,R.array.Agegroup, android.R.layout.simple_spinner_item);
//        spinner.setDropDownViewResource();

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Heightgroup, R.layout.spinner_list);
        adapter1.setDropDownViewResource(R.layout.spinner_list);
        spinnerheight.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Gender, R.layout.spinner_list);
        adapter2.setDropDownViewResource(R.layout.spinner_list);
        spinnergender.setAdapter(adapter2);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });

    }
}