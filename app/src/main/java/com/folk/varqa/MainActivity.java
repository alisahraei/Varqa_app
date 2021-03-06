package com.folk.varqa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
WebView webView;
String url = "https://web.varqa.ir/";
boolean doubleBackToExitPressedOnce = false;
ProgressBar progressBar;
TextView textview;
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.pb);
        textview = findViewById(R.id.tv);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setScrollbarFadingEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setScrollContainer(false);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setSavePassword(false);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setAppCacheEnabled(true);
        webView.addJavascriptInterface(this, "jsinterface");
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webView.loadUrl(url);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if(progress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    textview.setVisibility(View.VISIBLE);
                }
                progressBar.setProgress(progress);
                if(progress == 100) {
                    progressBar.setVisibility(ProgressBar.GONE);
                    textview.setVisibility(View.GONE);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        }
    }
}
