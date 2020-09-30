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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rsr.frankly.api.DefaultResponse;
import com.rsr.frankly.api.RetrofitClient;

public class Register extends AppCompatActivity {

    TextInputEditText reg_fname, reg_lname, reg_dob, reg_user_id, reg_mail_id, reg_mobile_no, reg_designation, reg_password, reg_confirm_password;

    Button register, goto_login;

    String user_id, fname, lname, dob, mail_id, mobile_no, designation, password, confirm_password;

    ImageButton back_at_register;

    ProgressDialog loading;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.register);
        goto_login = findViewById(R.id.goto_login);
        reg_fname = findViewById(R.id.reg_fname);
        reg_lname = findViewById(R.id.reg_lname);
        reg_dob = findViewById(R.id.reg_dob);
        reg_user_id = findViewById(R.id.reg_user_id);
        reg_mail_id = findViewById(R.id.reg_mail_id);
        reg_mobile_no = findViewById(R.id.reg_mobile_no);
        reg_designation = findViewById(R.id.reg_designation);
        reg_password = findViewById(R.id.reg_password);
        reg_confirm_password = findViewById(R.id.reg_confirm_password);
        back_at_register = findViewById(R.id.back_at_register);

        back_at_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {

                    loading =  ProgressDialog.show(Register.this,"Registering...","Please Wait...",false,false);

                    Call<DefaultResponse> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .create_user(user_id, fname, lname, dob, mail_id, mobile_no, designation, password);


                    call.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                            loading.dismiss();

                            if (response.code() == 201) {

                                DefaultResponse dr = response.body();
                                Toast.makeText(Register.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                                Toast.makeText(Register.this, "Registered Successfull", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(Register.this,Login.class));

                            } else if (response.code() == 422) {
                                Toast.makeText(Register.this, "User already exist", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DefaultResponse> call, Throwable t) {

                            Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });

                }
            }
        });

        goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

    }

    public boolean validate(){
        user_id = reg_user_id.getText().toString().trim();
        fname = reg_fname.getText().toString().trim();
        lname = reg_lname.getText().toString().trim();
        dob = reg_dob.getText().toString().trim();
        mail_id = reg_mail_id.getText().toString().trim();
        mobile_no = reg_mobile_no.getText().toString().trim();
        designation = reg_designation.getText().toString().trim();
        password = reg_password.getText().toString().trim();
        confirm_password = reg_confirm_password.getText().toString().trim();

        if(user_id.isEmpty()){
            reg_user_id.setError("Enter User ID");
            return false;
        }
        if(fname.isEmpty()){
            reg_fname.setError("Enter First Name");
            return false;
        }
        if(lname.isEmpty()){
            reg_lname.setError("Enter Last Name");
            return false;
        }
        if(dob.isEmpty()){
            reg_dob.setError("Enter DOB");
            return false;
        }
        if(mail_id.isEmpty()){
            reg_mail_id.setError("Enter Mail ID");
            return false;
        }
        if(mobile_no.isEmpty()){
            reg_mobile_no.setError("Enter Mobile No");
            return false;
        }
        if(designation.isEmpty()){
            reg_designation.setError("Enter Designation");
            return false;
        }
        if(password.isEmpty()){
            reg_password.setError("Enter Password");
            return false;
        }
        if(confirm_password.isEmpty()){
            reg_confirm_password.setError("Enter Confirm Password");
            return false;
        }
        if(!password.equals(confirm_password)){
            reg_confirm_password.setError("Password do not match");
            return false;
        }

        return true;

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Register.this, Login.class));
    }

}