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

    public static double chopsticks_weight = 0.05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

//        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        Toolbar myChildToolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        //display total travel
        setStatisticTV();

        Button btnUp = findViewById(R.id.btn_up);
        NestedScrollView nestedScrollView = findViewById(R.id.nested_scroll_view);
        AppBarLayout appBarLayout = findViewById(R.id.appBarLayout);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nestedScrollView.fullScroll(View.FOCUS_UP);
//                nestedScrollView.scrollTo(0,0);
                appBarLayout.setExpanded(true);
            }
        });
    }

    private void setStatisticTV() {
        // 取得SharedPreference
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        // 取得Key名稱為app_cfp的資料
        float app_cfp = getPrefs.getFloat("app_cfp", 0);
        // 取得Key名稱為app_mileage的資料
        float app_mileage = getPrefs.getFloat("app_mileage", 0);

        TextView tvDistance = findViewById(R.id.carbon_footprint);
        TextView tvTotalDistance = findViewById(R.id.all_move);
        TextView tvTotalFootprint = findViewById(R.id.all_carbon_footprint);
        TextView tvTotalChopsticks = findViewById(R.id.chopsticks);

        // round off
        double total_travel2 = Math.round(app_mileage * 10.0) / 10.0;
        double total_travel_carbon_footprint2 = Math.round(app_cfp * 10.0) / 10.0;
        double myChopsticks = Math.round(app_cfp / chopsticks_weight);

        // Double.toString
        String fCF = Float.toString(app_cfp);
        String fD = Float.toString(app_mileage);
        String tD = Double.toString(total_travel2);
        String tF = Double.toString(total_travel_carbon_footprint2);
        String tC = Integer.toString((int) myChopsticks);

        // setText
        tvTotalDistance.setText(tD);
        tvTotalFootprint.setText(tF);
        tvTotalChopsticks.setText(tC);
        tvDistance.setText(getString(R.string.calculate_result, fD, fCF));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                dialog8_fragment badgeDialog = new dialog8_fragment();
                badgeDialog.show(getSupportFragmentManager(), "dialog8_fragment");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}