package com.doit.detective;

import static com.doit.detective.go_activity.total_travel;
import static com.doit.detective.go_activity.total_travel_carbon_footprint;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class total_activity extends AppCompatActivity {

    public static double chopsticks_weight = 0.05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.topAppBar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        //display total travel
        setStatisticTV();
    }

    private void setStatisticTV() {
        TextView tvTotalDistance = findViewById(R.id.all_move);
        TextView tvTotalFootprint = findViewById(R.id.all_carbon_footprint);
        TextView tvTotalChopsticks = findViewById(R.id.chopsticks);
        total_travel = Math.round(total_travel * 1000.0) / 1000.0;
        total_travel_carbon_footprint = Math.round(total_travel_carbon_footprint * 1000.0) / 1000.0;
        double myChopsticks = total_travel_carbon_footprint / chopsticks_weight;
        myChopsticks = Math.round(myChopsticks);
        String tD = Double.toString(total_travel);
        String tF = Double.toString(total_travel_carbon_footprint);
        String tC = Integer.toString((int) myChopsticks);
        tvTotalDistance.setText(tD);
        tvTotalFootprint.setText(tF);
        tvTotalChopsticks.setText(tC);
    }
}