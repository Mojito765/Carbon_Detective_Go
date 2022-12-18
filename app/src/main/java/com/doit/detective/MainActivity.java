package com.doit.detective;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.doit.detective.fragment.go_fragment;
import com.doit.detective.fragment.home_fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.go_fabtn);
        fab.setOnClickListener(v ->
                new AlertDialog.Builder(this)
                        .setTitle("Oops")
                        .setMessage("緣份到自然就可以用了")
                        .setPositiveButton("OK", (dialog, which) -> {})
                        .show());

        chipNavigationBar = findViewById(R.id.chip_app_bar);
        chipNavigationBar.setItemSelected(R.id.home,
                true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root_layout,
                        new home_fragment()).commit();
        bottomMenu();
    }
    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener
                (new ChipNavigationBar.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int i) {
                        Fragment fragment = null;
                        switch (i){
                            case R.id.home:
                                fragment = new home_fragment();
                                break;
                            case R.id.badge:
                                fragment = new go_fragment();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.root_layout, fragment).commit();
                    }
                });
    }
}