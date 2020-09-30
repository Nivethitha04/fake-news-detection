package com.rsr.frankly;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.rsr.frankly.AdBlocker;

public class ViewNews extends AppCompatActivity {

    ImageButton back_at_news, home_at_news;

    WebView news_webView;

    String url = "https://news.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news);

        AdBlocker.init(ViewNews.this);

        back_at_news = findViewById(R.id.back_at_news);
        home_at_news = findViewById(R.id.home_at_news);
        news_webView = findViewById(R.id.news_webView);

        back_at_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        home_at_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewNews.this, Home.class));
            }
        });

        url = getIntent().getExtras().getString("url");

       news_webView.setWebViewClient(new WebViewClient() {
           private Map<String, Boolean> loadedUrls = new HashMap<>();

           @TargetApi(Build.VERSION_CODES.HONEYCOMB)
           @Override
           public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
               boolean ad;
               if (!loadedUrls.containsKey(url)) {
                   ad = AdBlocker.isAd(url);
                   loadedUrls.put(url, ad);
               } else {
                   ad = false;
               }
               return ad ? AdBlocker.createEmptyResource() :
                       super.shouldInterceptRequest(view, url);
           }
       });
        news_webView.loadUrl(url);



    }

    private WebResourceResponse getTextWebResource(InputStream data) {
        return new WebResourceResponse("text/plain", "UTF-8", data);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewNews.this, Dashboard.class));
    }


}
