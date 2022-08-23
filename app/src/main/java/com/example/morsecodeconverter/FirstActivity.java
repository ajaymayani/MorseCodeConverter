package com.example.morsecodeconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    Button btnTextToMorsecode,btnMorsecodeToText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btnMorsecodeToText = findViewById(R.id.btnMorsecodeToText);
        btnTextToMorsecode = findViewById(R.id.btnTextToMorsecode);

        btnTextToMorsecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this,MainActivity.class).putExtra("action","textToMorsecode"));
            }
        });

        btnMorsecodeToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this,MainActivity.class).putExtra("action","morsecodeToText"));
            }
        });
    }
}