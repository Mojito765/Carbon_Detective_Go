package com.doit.detective;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.doit.detective.fragment.dialog8_fragment;
import com.google.android.material.appbar.AppBarLayout;

public class StatisticsActivity extends AppCompatActivity {

    static final double STRAW_WEIGHT = 0.00533;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        setupToolbar();
        setStatisticTV();
        setupScrollButton();
    }

    private void setupToolbar() {
        Toolbar myChildToolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupScrollButton() {
        Button btnUp = findViewById(R.id.btn_up);
        NestedScrollView nestedScrollView = findViewById(R.id.nested_scroll_view);
        AppBarLayout appBarLayout = findViewById(R.id.appBarLayout);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nestedScrollView.fullScroll(View.FOCUS_UP);
                appBarLayout.setExpanded(true);
            }
        });
    }

    private void setStatisticTV() {
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        float app_cfp = getPrefs.getFloat("app_cfp", 0);
        float app_mileage = getPrefs.getFloat("app_mileage", 0);

        TextView tvDistance = findViewById(R.id.carbon_footprint);
        TextView tvTotalDistance = findViewById(R.id.all_move);
        TextView tvTotalFootprint = findViewById(R.id.all_carbon_footprint);
        TextView tvTotalStraw = findViewById(R.id.straw);

        double total_travel2 = Math.round(app_mileage * 10.0) / 10.0;
        double total_travel_carbon_footprint2 = Math.round(app_cfp * 10.0) / 10.0;
        double myStraw = app_cfp / STRAW_WEIGHT;

        String fD = Float.toString(app_mileage);
        String fCF = Float.toString(app_cfp);
        String tD = Double.toString(total_travel2);
        String tF = Double.toString(total_travel_carbon_footprint2);
        String tS = Integer.toString((int) myStraw);

        tvTotalDistance.setText(tD);
        tvTotalFootprint.setText(tF);
        tvTotalStraw.setText(tS);
        tvDistance.setText(getString(R.string.calculate_result, fD, fCF));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.help) {
            dialog8_fragment badgeDialog = new dialog8_fragment();
            badgeDialog.show(getSupportFragmentManager(), "dialog8_fragment");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
