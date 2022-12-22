package com.doit.detective;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class about_carbon_footprint_activity extends AppCompatActivity {
    private Button btnIntentActivityB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_carbon_footprint);

        btnIntentActivityB = (Button) findViewById(R.id.btn_web);
        btnIntentActivityB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(about_carbon_footprint_activity.this,carbon_webview_activity.class);
                startActivity(intent);
            }
        });
    }
}
