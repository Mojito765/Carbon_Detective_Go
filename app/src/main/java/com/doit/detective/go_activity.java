package com.doit.detective;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class go_activity extends AppCompatActivity {

    private Button btnIntentActivityH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);

        btnIntentActivityH = (Button) findViewById(R.id.btn_stop);
        btnIntentActivityH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(go_activity.this,end_activity.class);
                startActivity(intent);
            }
        });
    }
}
