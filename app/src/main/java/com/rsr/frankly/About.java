package com.rsr.frankly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class About extends AppCompatActivity {

    ImageButton back_at_about, home_at_about;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        back_at_about = findViewById(R.id.back_at_about);
        home_at_about = findViewById(R.id.home_at_about);

        back_at_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        home_at_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(About.this, Home.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(About.this, Home.class));
    }

}