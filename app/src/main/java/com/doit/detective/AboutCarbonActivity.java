package com.doit.detective;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutCarbonActivity extends AppCompatActivity {
    private static final int BUTTON_WEB_ID = R.id.btn_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_carbon);

        initializeViews();
    }

    private void initializeViews() {
        Toolbar myChildToolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(myChildToolbar);

        Button btnWeb = findViewById(BUTTON_WEB_ID);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCarbonWebActivity();
            }
        });

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void startCarbonWebActivity() {
        Intent intent = new Intent(this, CarbonWebActivity.class);
        startActivity(intent);
    }
}
