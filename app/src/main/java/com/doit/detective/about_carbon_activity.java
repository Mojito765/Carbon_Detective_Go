package com.doit.detective;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class about_carbon_activity extends AppCompatActivity {
    private Button btnIntentActivityB;
    private Button btnIntentActivityH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_carbon);

        btnIntentActivityB = (Button) findViewById(R.id.btn_web);
        btnIntentActivityB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(about_carbon_activity.this, carbon_web_activity.class);
                startActivity(intent);
            }
        });

        btnIntentActivityH = (Button) findViewById(R.id.btn_home);
        btnIntentActivityH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(about_carbon_activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
