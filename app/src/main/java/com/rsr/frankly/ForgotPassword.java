package com.rsr.frankly;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rsr.frankly.api.DefaultResponse;
import com.rsr.frankly.api.RetrofitClient;

public class ForgotPassword extends AppCompatActivity {

    Button reset_password;

    TextInputEditText forgot_user_id, new_password, confirm_new_password;

    String user_id, password, confirm_password;

    ImageButton back_at_forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        reset_password = findViewById(R.id.reset_password);
        forgot_user_id = findViewById(R.id.forgot_user_id);
        new_password = findViewById(R.id.new_password);
        confirm_new_password = findViewById(R.id.confirm_new_password);
        back_at_forgot = findViewById(R.id.back_at_forgot);

        back_at_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().reset_password(password, user_id);
                    call.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                            assert response.body() != null;
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPassword.this,Login.class));
                        }

                        @Override
                        public void onFailure(Call<DefaultResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }

    public boolean validate(){
        user_id = forgot_user_id.getText().toString().trim();
        password = new_password.getText().toString().trim();
        confirm_password = confirm_new_password.getText().toString().trim();

        if(user_id.isEmpty()){
            forgot_user_id.setError("Enter User ID");
            return false;
        }
            if(password.isEmpty()){
            new_password.setError("Enter Password");
            return false;
        }
        if(confirm_password.isEmpty()){
            confirm_new_password.setError("Enter Confirm Password");
            return false;
        }
        if(!password.equals(confirm_password)){
            confirm_new_password.setError("Password do not match");
            return false;
        }

        return true;

    }


}