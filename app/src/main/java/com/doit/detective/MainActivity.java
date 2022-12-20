package com.doit.detective;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.doit.detective.fragment.about_fragment;
import com.doit.detective.fragment.badge_fragment;
import com.doit.detective.fragment.detail_fragment;
import com.doit.detective.fragment.home_fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    ChipNavigationBar chipNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//toolbar
        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout= findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home_fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

//FAB
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

    //drawer btn
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home_fragment()).commit();
                break;
            case R.id.nav_detail:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new detail_fragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new about_fragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//bottom button
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
                                fragment = new badge_fragment();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.root_layout, fragment).commit();
                    }
                });
    }
}