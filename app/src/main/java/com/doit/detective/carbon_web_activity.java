package com.doit.detective;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class carbon_web_activity extends AppCompatActivity {
    WebView webview;
    WebSettings webSettings;
    String url="https://cfp-calculate.tw/cfpc/Carbon/WebPage/InstitutionDesc.aspx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_web);

        webview = findViewById(R.id.carbon_webview);
        webSettings=webview.getSettings();
        webSettings.setJavaScriptEnabled(true);//開啟javascript功能
        webview.setWebViewClient(new WebViewClient());//新增瀏覽器客戶端
        webview.loadUrl(url);//讀取url網站

        Toolbar myChildToolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {//如果按下返回鍵&能後退為true
            webview.goBack();//返回上一頁
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
