package com.doit.detective;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.doit.detective.databinding.ActivityMainBinding;
import com.doit.detective.fragment.badge1;
import com.doit.detective.fragment.badge_fragment;
import com.doit.detective.fragment.go_fragment;
import com.doit.detective.fragment.home_fragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    BottomAppBar bottomAppBar;

    home_fragment HomeFragment=new home_fragment();
    go_fragment GoFragment=new go_fragment();
    badge_fragment BadgeFragment=new badge_fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.button_app_bar);

        getSupportFragmentManager().beginTransaction().replace(R.id.root_layout,HomeFragment).commit();

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.root_layout, HomeFragment).commit();
                        return true;
                    case R.id.badge:
                        getSupportFragmentManager().beginTransaction().replace(R.id.root_layout, GoFragment).commit();
                        return true;
                }
                if (item.getItemId() == R.id.badge){getSupportFragmentManager().beginTransaction().replace(R.id.root_layout,GoFragment).commit();}
                return false;
            }
        });
    }
}