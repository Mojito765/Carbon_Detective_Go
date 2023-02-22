package com.doit.detective;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.doit.detective.fragment.badge_fragment;
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

// 取得SharedPreference
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
// 建立Key名稱為status_badge的資料，預設值為0（未解鎖）
        getPrefs.getInt("status_badge1", 0);
        getPrefs.getInt("status_badge2", 0);
        getPrefs.getInt("status_badge4", 0);
        getPrefs.getInt("status_badge5", 0);

//toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.root_layout, new home_fragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_carbon);
        }

//FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.FAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GoActivity.class));
            }
        });
//chipNavigationBar
        chipNavigationBar = findViewById(R.id.chip_app_bar);
        chipNavigationBar.setItemSelected(R.id.home,
                true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root_layout,
                        new home_fragment()).commit();
//bottomAppBar
        bottomMenu();
    }

    //drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_carbon:
                Intent i = new Intent(MainActivity.this, AboutCarbonActivity.class);
                startActivity(i);
                break;
            case R.id.nav_about:
                Intent j = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(j);
                break;
            case R.id.nav_support:
                Intent k = new Intent(Intent.ACTION_SEND);
                k.setType("message/rfc822");
                k.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@mail.com"});
                k.putExtra(Intent.EXTRA_SUBJECT, "Carbon Detective Go Feedback");
                k.putExtra(Intent.EXTRA_TEXT, "Tell us about your experience...");
                try {
                    startActivity(Intent.createChooser(k, "Contact Us"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "No email clients installed.", Toast.LENGTH_SHORT).show();
                }
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

    //bottomAppBar
    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener
                (new ChipNavigationBar.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int i) {
                        Fragment fragment = null;
                        switch (i) {
                            case R.id.home:
                                fragment = new home_fragment();
                                break;
                            case R.id.badge:
                                fragment = new badge_fragment();
                                break;
                        }
                        assert fragment != null;
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.root_layout, fragment).commit();
                    }
                });
    }
}