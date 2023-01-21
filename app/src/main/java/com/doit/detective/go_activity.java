package com.doit.detective;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.doit.detective.fragment.dialog7_fragment;

public class go_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);

        Button btnIntentActivityHome = findViewById(R.id.btn_stop);
        btnIntentActivityHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(go_activity.this,end_activity.class);
                startActivity(intent);
            }
        });

        TextView goTextView=findViewById(R.id.back);
        goTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView questionTextView=findViewById(R.id.question);
        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog7_fragment badgeDialog = new dialog7_fragment();
                badgeDialog.show(getSupportFragmentManager(), "dialog7_fragment");
            }
        });
    }
}
