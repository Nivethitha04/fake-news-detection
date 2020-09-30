package com.rsr.frankly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rsr.frankly.api.Constants;
import com.rsr.frankly.api.DefaultResponse;
import com.rsr.frankly.api.RetrofitClient;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    Button create_account, login, forgot_password;

    TextInputEditText login_user_id, login_password;

    String user_id, password;

    AlertDialog.Builder alertDialog;
    AlertDialog alertDialog1;

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Constants.instance(this.getApplicationContext());

        String link = (Constants.instance().fetchValueString("link"));


        if(link == null){
            startActivity(new Intent(Login.this,EnterLink.class));
        }

        if (!isConnected(this)) {
            showConnDialog();
        }


        setContentView(R.layout.activity_login);


        SessionManager sessionManager = new SessionManager(Login.this);
        if (sessionManager.checkLogin()) {

            HashMap<String, String> userData = sessionManager.getUserData();

            if (userData.get(SessionManager.KEY_USER_ID) == null) {
            } else {
                startActivity(new Intent(Login.this, Home.class));
            }
        }


        create_account = findViewById(R.id.create_account);
        login = findViewById(R.id.login);
        forgot_password = findViewById(R.id.forgot_password);
        login_user_id = findViewById(R.id.login_user_id);
        login_password = findViewById(R.id.login_password);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){

                    loading =  ProgressDialog.show(Login.this,"Logging in...","Please Wait...",false,false);


                    Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().userLogin(user_id,password);

                    call.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                            if(!response.body().getErr()){

                                SessionManager sessionManager = new SessionManager(Login.this);
                                sessionManager.createLoginSession(user_id);

                                loading.dismiss();

                                Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this,Home.class));
                            }else {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(),"Login Failed\n"+response.body().getMsg(),Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<DefaultResponse> call, Throwable t) {

                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgotPassword.class));
            }
        });

    }

    public boolean validate(){
        user_id = login_user_id.getText().toString().trim();
        password = login_password.getText().toString().trim();

        if(user_id.isEmpty()){
            login_user_id.setError("Enter User ID");
            return false;
        }
         if(password.isEmpty()){
            login_password.setError("Enter Password");
            return false;
        }

        return true;

    }

    private void showConnDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
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

    private boolean isConnected(Login login) {

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
        alertDialog = new AlertDialog.Builder(Login.this);
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