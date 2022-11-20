package com.example.myapplication.view;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication.R;
import com.example.myapplication.model.LinkDatabase;
import com.example.myapplication.model.Link;

public class WebActivity extends AppCompatActivity {
    private WebView webView;
    private Button btSave, btHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        String intentUrl = getIntent().getStringExtra("URL");

        LinkDatabase db = Room.databaseBuilder(getApplicationContext(), LinkDatabase.class, "production")
                .allowMainThreadQueries()
                .build();

        btHome = findViewById(R.id.btHome);
        btHome.setOnClickListener(view -> webView.loadUrl("https://duckduckgo.com"));
        btSave = findViewById(R.id.btSave);
        btSave.setOnClickListener((view) -> {
            db.linkDao().insert(new Link(getUrl()));
            Log.d(TAG, "link title " + webView.getTitle());
        });

        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        if (intentUrl == null) {
            webView.loadUrl(getUrl());
        } else webView.loadUrl(intentUrl);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void saveUrl(String url) {
        SharedPreferences sp = getSharedPreferences("SP_WEB_VIEW_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("SAVED_URL", url);
        editor.apply();
    }

    public String getUrl() {
        SharedPreferences sp = getSharedPreferences("SP_WEB_VIEW_PREFS", MODE_PRIVATE);
        return sp.getString("SAVED_URL", "https://duckduckgo.com");
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            saveUrl(url);
        }
    }
}