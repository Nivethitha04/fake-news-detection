package com.rsr.frankly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.rsr.frankly.api.Constants;

public class Home extends AppCompatActivity {

    Button my_profile, dashboard, about, help;

    AlertDialog.Builder alertDialog;
    AlertDialog alertDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Constants.instance(this.getApplicationContext());

        String link = (Constants.instance().fetchValueString("link"));


        if(link == null){
            startActivity(new Intent(Home.this,EnterLink.class));
        }

        if (!isConnected(Home.this)) {
            showConnDialog();
        }

        setContentView(R.layout.activity_home);

        my_profile = findViewById(R.id.my_profile);
        dashboard = findViewById(R.id.dashboard);
        about = findViewById(R.id.about);
        help = findViewById(R.id.help);

        my_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, MyProfile.class));
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Dashboard.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, About.class));
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Help.class));
            }
        });



    }


    private void showConnDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setMessage("Please connect to the Internet or WiFi to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private boolean isConnected(Home login) {

        ConnectivityManager connectivityManager = (ConnectivityManager) login.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        showTheDialog();
    }

    public void showTheDialog(){
        alertDialog = new AlertDialog.Builder(Home.this);
        alertDialog.setTitle("Exit !");
        alertDialog.setMessage("Do you want to EXIT this app ?");
        alertDialog.setCancelable(false);

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        alertDialog1 = alertDialog.create();
        alertDialog1.show();
    }

}