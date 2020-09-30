package com.rsr.frankly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.rsr.frankly.api.AllNewsResponse;
import com.rsr.frankly.api.News;
import com.rsr.frankly.api.NewsAdapter;
import com.rsr.frankly.api.RetrofitClient;

import java.util.List;

public class Dashboard extends AppCompatActivity {

    ImageButton back_at_dashboard, home_at_dashboard;

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> News;

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        back_at_dashboard = findViewById(R.id.back_at_dashboard);
        home_at_dashboard = findViewById(R.id.home_at_dashboard);

        back_at_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        home_at_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Home.class));
            }
        });

        loading = ProgressDialog.show(this, "Loading", "Please Wait...", false, false);

        recyclerView = findViewById(R.id.recyclerView_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {

            Call<AllNewsResponse> call = RetrofitClient.getInstance().getApi().getNews();

            call.enqueue(new Callback<AllNewsResponse>() {
                @Override
                public void onResponse(Call<AllNewsResponse> call, Response<AllNewsResponse> response) {

                    assert response.body() != null;
                    News = response.body().getNews();

                    if (!News.isEmpty()) {

                        adapter = new NewsAdapter(Dashboard.this, News);
                        recyclerView.setAdapter(adapter);

                    }

                    loading.dismiss();


                }

                @Override
                public void onFailure(Call<AllNewsResponse> call, Throwable t) {

                }
            });

        }catch (Exception e){
            loading.dismiss();
            e.printStackTrace();
            showErrDialog();
        }
        

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Dashboard.this, Home.class));
    }

    private void showErrDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
        builder.setMessage("Something went wrong ! Try again later !")
                .setCancelable(false)
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


}