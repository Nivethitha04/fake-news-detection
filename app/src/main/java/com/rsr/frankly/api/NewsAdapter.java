package com.rsr.frankly.api;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rsr.frankly.Help;
import com.rsr.frankly.Home;
import com.rsr.frankly.R;
import com.rsr.frankly.ViewNews;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context mCtx;
    private List<News> News;

    public NewsAdapter(Context mCtx, List<News> News) {
        this.mCtx = mCtx;
        this.News = News;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.card_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        final News news = News.get(position);

//        holder.card_user_dob.setText(news.getDob());

        holder.title.setText(news.getTitle());
        holder.time.setText(news.getTime());

        try {
            if (Float.parseFloat(news.getSimilar()) > Float.parseFloat(news.getDissimilar())) {
                holder.real.setVisibility(View.VISIBLE);
                holder.fake.setVisibility(View.GONE);
            } else {
                holder.real.setVisibility(View.GONE);
                holder.fake.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            holder.real.setVisibility(View.GONE);
            holder.fake.setVisibility(View.VISIBLE);
        }

        holder.card_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mCtx, ViewNews.class);
                i.putExtra("url", news.getUrl());
                mCtx.startActivity(i);

                Toast.makeText(mCtx, news.getUrl(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return News.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView title, time;
        LinearLayout card_news;
        ImageView real, fake;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

//            profile = itemView.findViewById(R.id.card_user_profile);
            time = itemView.findViewById(R.id.time);
            title = itemView.findViewById(R.id.title);
            card_news = itemView.findViewById(R.id.card_news);
            real = itemView.findViewById(R.id.real);
            fake = itemView.findViewById(R.id.fake);

        }
    }


}
