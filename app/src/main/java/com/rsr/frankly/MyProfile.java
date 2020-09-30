package com.rsr.frankly;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rsr.frankly.api.RetrofitClient;
import com.rsr.frankly.api.SingleResponse;
import com.rsr.frankly.api.SingleUser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MyProfile extends AppCompatActivity {

    Button edit_profile, logout;

    TextInputEditText pro_user_id, pro_fname, pro_lname, pro_dob, pro_mail_id, pro_mobile_no, pro_designation;
    TextView pro_show_name;

    private List<SingleUser> singleUser;
    String user_id;

    ImageButton back_at_profile, home_at_profile;


    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        edit_profile = findViewById(R.id.edit_profile);
        logout = findViewById(R.id.logout);
        pro_user_id = findViewById(R.id.pro_user_id);
        pro_fname = findViewById(R.id.pro_fname);
        pro_lname = findViewById(R.id.pro_lname);
        pro_dob = findViewById(R.id.pro_dob);
        pro_mail_id = findViewById(R.id.pro_mail_id);
        pro_mobile_no = findViewById(R.id.pro_mobile_no);
        pro_designation = findViewById(R.id.pro_designation);
        pro_show_name = findViewById(R.id.pro_show_name);
        back_at_profile = findViewById(R.id.back_at_profile);
        home_at_profile = findViewById(R.id.home_at_profile);

        home_at_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyProfile.this, Home.class));
            }
        });

        back_at_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        loading =  ProgressDialog.show(MyProfile.this,"Loading...","Please Wait...",false,false);


        SessionManager sessionManager = new SessionManager(MyProfile.this);
        HashMap<String, String> userData = sessionManager.getUserData();

        user_id = userData.get(SessionManager.KEY_USER_ID);


        Call<SingleResponse> call1 = RetrofitClient.getInstance().getApi().getSingleUser(user_id);


        call1.enqueue(new Callback<SingleResponse>() {
            @Override
            public void onResponse(Call<SingleResponse> call, Response<SingleResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    singleUser = response.body().getSingleUser();
                    if (singleUser.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "User Not Exist", Toast.LENGTH_SHORT).show();
                    } else {
                        SingleUser user = singleUser.get(0);

                        pro_show_name.setText(user.getFirstName() + " " +user.getLastName());

                        pro_user_id.setText(user.getUserId());
                        pro_fname.setText(user.getFirstName());
                        pro_lname.setText(user.getLastName());
                        pro_dob.setText(user.getDob());
                        pro_mail_id.setText(user.getMailId());
                        pro_mobile_no.setText(user.getMobileNo());
                        pro_designation.setText(user.getDesignation());

                        loading.dismiss();

                    }
                } else {
                    try {
                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show(); // this will tell you why your api doesnt work most of time
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<SingleResponse> call, Throwable t) {

            }
        });


        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyProfile.this, EditProfile.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SessionManager sessionManager1 = new SessionManager(MyProfile.this);
                sessionManager1.logout();

                Toast.makeText(MyProfile.this, "Logout Successfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MyProfile.this, Login.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MyProfile.this, Home.class));
    }

}