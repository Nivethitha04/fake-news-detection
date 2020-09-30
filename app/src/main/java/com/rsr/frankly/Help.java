package com.rsr.frankly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Help extends AppCompatActivity {

    ImageButton back_at_help, home_at_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        back_at_help = findViewById(R.id.back_at_help);
        home_at_help = findViewById(R.id.home_at_help);


        home_at_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Help.this, Home.class));
            }
        });

        back_at_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Help.this, Home.class));
    }

}