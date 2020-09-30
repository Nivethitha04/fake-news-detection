package com.rsr.frankly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rsr.frankly.api.Constants;

public class EnterLink extends AppCompatActivity {

    TextInputEditText link;
    Button enter_link;

    String Link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_link);

        link = findViewById(R.id.link);
        enter_link = findViewById(R.id.next);

        enter_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Link = link.getText().toString().trim();
                if(Link.isEmpty() || Link.length()<10) {
                    link.setError("Enter valid Link");
                }
                else{
                    Constants.instance().storeValueString("link", Link);
                    Toast.makeText(getApplicationContext(),"Link saved",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EnterLink.this,Login.class));
                }
            }
        });

    }
}
