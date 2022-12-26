package com.doit.detective;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class end_activity extends AppCompatActivity {

    private Button btnIntentActivityH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        btnIntentActivityH = (Button) findViewById(R.id.btn_home);
        btnIntentActivityH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(end_activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
