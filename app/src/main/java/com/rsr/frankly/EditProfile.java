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
import com.rsr.frankly.api.DefaultResponse;
import com.rsr.frankly.api.RetrofitClient;
import com.rsr.frankly.api.SingleResponse;
import com.rsr.frankly.api.SingleUser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EditProfile extends AppCompatActivity {

    Button change_password, save;

    TextInputEditText edit_user_id, edit_fname, edit_lname, edit_dob, edit_mail_id, edit_mobile_no, edit_designation;
    TextView edit_show_name;

    String user_id, fname, lname, dob, mail_id, mobile_no, designation;

    private List<SingleUser> singleUser;
    String id;

    ImageButton back_at_edit, home_at_edit;

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        change_password = findViewById(R.id.change_password);
        save = findViewById(R.id.save);
        edit_user_id = findViewById(R.id.edit_user_id);
        edit_fname = findViewById(R.id.edit_fname);
        edit_lname = findViewById(R.id.edit_lname);
        edit_dob = findViewById(R.id.edit_dob);
        edit_mail_id = findViewById(R.id.edit_mail_id);
        edit_mobile_no = findViewById(R.id.edit_mobile_no);
        edit_designation = findViewById(R.id.edit_designation);
        edit_show_name = findViewById(R.id.edit_show_name);
        back_at_edit = findViewById(R.id.back_at_edit);
        home_at_edit = findViewById(R.id.home_at_edit);

        back_at_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        home_at_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfile.this, Home.class));
            }
        });

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfile.this, ForgotPassword.class));
            }
        });

        loading =  ProgressDialog.show(EditProfile.this,"Loading...","Please Wait...",false,false);

        SessionManager sessionManager = new SessionManager(EditProfile.this);
        HashMap<String, String> userData = sessionManager.getUserData();

        id = userData.get(SessionManager.KEY_USER_ID);

        Call<SingleResponse> call1 = RetrofitClient.getInstance().getApi().getSingleUser(id);

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

                        edit_show_name.setText(user.getFirstName() + " " +user.getLastName());

                        edit_user_id.setText(user.getUserId());
                        edit_fname.setText(user.getFirstName());
                        edit_lname.setText(user.getLastName());
                        edit_dob.setText(user.getDob());
                        edit_mail_id.setText(user.getMailId());
                        edit_mobile_no.setText(user.getMobileNo());
                        edit_designation.setText(user.getDesignation());

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


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {

                    loading =  ProgressDialog.show(EditProfile.this,"Saving...","Please Wait...",false,false);


                    Call<DefaultResponse> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .updateUser(user_id, fname, lname, dob, mail_id, mobile_no, designation);


                    call.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                            if (response.body().getErr() == false) {

                                DefaultResponse dr = response.body();
                                Toast.makeText(EditProfile.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                                loading.dismiss();

                                startActivity(new Intent(EditProfile.this,MyProfile.class));

                            } else{
                                Toast.makeText(EditProfile.this, "Try again later", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DefaultResponse> call, Throwable t) {

                            Toast.makeText(EditProfile.this, t.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });

                }

            }
        });

    }

    public boolean validate(){
        user_id = edit_user_id.getText().toString().trim();
        fname = edit_fname.getText().toString().trim();
        lname = edit_lname.getText().toString().trim();
        dob = edit_dob.getText().toString().trim();
        mail_id = edit_mail_id.getText().toString().trim();
        mobile_no = edit_mobile_no.getText().toString().trim();
        designation = edit_designation.getText().toString().trim();

        if(user_id.isEmpty()){
            edit_user_id.setError("Enter User ID");
            return false;
        }
        if(fname.isEmpty()){
            edit_fname.setError("Enter First Name");
            return false;
        }
        if(lname.isEmpty()){
            edit_lname.setError("Enter Last Name");
            return false;
        }
        if(dob.isEmpty()){
            edit_dob.setError("Enter DOB");
            return false;
        }
        if(mail_id.isEmpty()){
            edit_mail_id.setError("Enter Mail ID");
            return false;
        }
        if(mobile_no.isEmpty()){
            edit_mobile_no.setError("Enter Mobile No");
            return false;
        }
        if(designation.isEmpty()){
            edit_designation.setError("Enter Designation");
            return false;
        }

        return true;

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(EditProfile.this, MyProfile.class));
    }

}