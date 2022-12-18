package com.doit.detective.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.doit.detective.R;

public class badge_fragment extends AppCompatActivity {

    ImageButton imageButtonbadge1;
    ImageButton imageButtonbadge2;
    ImageButton imageButtonbadge3;
    ImageButton imageButtonbadge4;
    ImageButton imageButtonbadge5;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //將上面已設定完成的元件設定成畫面上所存在的對應元件。
        //對應的元件在於XML檔案的自身Button、ImageButton所設定的id名稱。
        imageButtonbadge1 = findViewById(R.id.badge1);
        //以上設定完初始之後這個button所指向的元件就是自己設定在畫面上的button。
        //imageButton也是同樣做法。
        imageButtonbadge1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //執行事件
                Intent intent = new Intent();
                intent.setClass(badge_fragment.this, badge1.class);
                startActivity(intent);
            }
        });

        imageButtonbadge2 = findViewById(R.id.badge2);
        //以上設定完初始之後這個button所指向的元件就是自己設定在畫面上的button。
        //imageButton也是同樣做法。
        imageButtonbadge2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //執行事件
                Intent intent = new Intent();
                intent.setClass(badge_fragment.this, badge2.class);
                startActivity(intent);
            }
        });

        imageButtonbadge3 = findViewById(R.id.badge3);
        //以上設定完初始之後這個button所指向的元件就是自己設定在畫面上的button。
        //imageButton也是同樣做法。
        imageButtonbadge3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //執行事件
                Intent intent = new Intent();
                intent.setClass(badge_fragment.this, badge3.class);
                startActivity(intent);
            }
        });

        imageButtonbadge4 = findViewById(R.id.badge4);
        //以上設定完初始之後這個button所指向的元件就是自己設定在畫面上的button。
        //imageButton也是同樣做法。
        imageButtonbadge4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //執行事件
                Intent intent = new Intent();
                intent.setClass(badge_fragment.this, badge4.class);
                startActivity(intent);
            }
        });

        imageButtonbadge5 = findViewById(R.id.badge5);
        //以上設定完初始之後這個button所指向的元件就是自己設定在畫面上的button。
        //imageButton也是同樣做法。
        imageButtonbadge5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //執行事件
                Intent intent = new Intent();
                intent.setClass(badge_fragment.this, badge5.class);
                startActivity(intent);
            }
        });

    }

}

