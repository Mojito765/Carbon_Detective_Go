package com.doit.detective;

import static com.doit.detective.LocationService.myLocationList;
import static com.doit.detective.StatisticsActivity.chopsticks_weight;

import android.Manifest;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.doit.detective.fragment.dialog7_fragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class GoActivity extends AppCompatActivity {

    private static final int request_FINE_LOCATION = 99;
    private static final int request_BACKGROUND_LOCATION = 100;
    public static double transportation_weight = 0;
    public static double finalDistance = 0;
    public static double final_carbon_footprint = 0;

    LocationService mLocationService = new LocationService();
    Intent mServiceIntent;

    TextView timerText;
    Button stopStartButton;

    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    boolean timerStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);

        timerText = findViewById(R.id.record_status);
        stopStartButton = findViewById(R.id.startStopButton);

        timer = new Timer();

//        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        // 取得SharedPreference
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        // 建立一個Key名稱為app_cfp的資料，預設值為0
        getPrefs.getFloat("app_cfp", 0);
        // 建立一個Key名稱為app_mileage的資料，預設值為0
        getPrefs.getFloat("app_mileage", 0);

        btnTransportation();

        // back_to_top start
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
        // back_to_top end

        Toolbar myChildToolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

    }

    private void resetTapped() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(GoActivity.this)
                .setTitle("Stop recording")
                .setMessage("Are you sure you want to stop recording?")
                .setPositiveButton("Stop", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (timerTask != null) {
                            timerTask.cancel();
                            setButtonUI("START", R.color.seed);
                            time = 0.0;
                            timerStarted = false;
//                            timerText.setText(formatTime(0, 0, 0));

                            stopService();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                });
        builder.create();
        builder.show();
    }

    public void startStopTapped(View view) {
        // Vibrate
        setVibrate();

        if (!timerStarted) {

            if (ActivityCompat.checkSelfPermission(GoActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                    if (ActivityCompat.checkSelfPermission(GoActivity.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {

                        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(GoActivity.this)
                                .setTitle(getString(R.string.ask_background_location_permission))
                                .setMessage(getString(R.string.background_location_permission_message))
                                .setPositiveButton("Setup All-the-time Access", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        requestBackgroundLocationPermission();
                                        dialogInterface.dismiss();
                                    }
                                })
                                .setNegativeButton("Keep Only-while-using Access", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startService();

                                        dialogInterface.dismiss();
                                    }
                                });
                        builder.create();
                        builder.show();

                    } else if (ActivityCompat.checkSelfPermission(GoActivity.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        startService();
                    }
                } else {
                    startService();
                }

            } else if (ActivityCompat.checkSelfPermission(GoActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(GoActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(GoActivity.this)
                            .setTitle(getString(R.string.ask_location_permission))
                            .setMessage(getString(R.string.location_permission_message))
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    requestFineLocationPermission();
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create();
                    builder.show();

                } else {
                    requestFineLocationPermission();
                }
            }
        } else {
            resetTapped();
        }
    }

    private void setButtonUI(String start, int color) {
        stopStartButton.setText(start);
        stopStartButton.setTextColor(ContextCompat.getColor(this, color));
    }

    private void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private String getTimerText() {
        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours) {
        return String.format(Locale.getDefault(), "%02d", hours) + " : " + String.format(Locale.getDefault(), "%02d", minutes) + " : " + String.format(Locale.getDefault(), "%02d", seconds);
    }

    // Transportation weight
    private void btnTransportation() {
        MaterialButtonToggleGroup materialButtonToggleGroup = findViewById(R.id.toggleButton);

        materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    setVibrate();
                    if (checkedId == R.id.train) {
                        transportation_weight = 0.06;
                    } else if (checkedId == R.id.moped) {
                        transportation_weight = 0.046;
                    } else if (checkedId == R.id.car) {
                        transportation_weight = 0.173;
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == request_FINE_LOCATION) {

            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        requestBackgroundLocationPermission();
                    }
                }

            } else {
                Toast.makeText(this, "ACCESS_FINE_LOCATION permission denied", Toast.LENGTH_LONG).show();
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:com.doit.detective")
                    ));
                }
            }

        } else if (requestCode == request_BACKGROUND_LOCATION) {

            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, R.string.background_location_permission_granted, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, R.string.background_location_permission_denied, Toast.LENGTH_LONG).show();
            }
        }

    }

    private void setVibrate() {
        Vibrator myVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        myVibrator.vibrate(50);
    }

    private void startService() {
        timerStarted = true;
        setButtonUI("STOP", com.google.android.material.R.color.design_default_color_error);

        startTimer();

        mLocationService = new LocationService();
        mServiceIntent = new Intent(this, mLocationService.getClass());
        if (!Utility.isMyServiceRunning(mLocationService.getClass(), this)) {
            startService(mServiceIntent);
            Toast.makeText(this, "Start recording", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Already recording", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopService() {
        mLocationService = new LocationService();
        mServiceIntent = new Intent(this, mLocationService.getClass());
        if (Utility.isMyServiceRunning(mLocationService.getClass(), this)) {
            stopService(mServiceIntent);
            Toast.makeText(this, "Recorded", Toast.LENGTH_SHORT).show();
            saveTravel();
        } else {
            Toast.makeText(this, "Not recording", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void requestBackgroundLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                request_BACKGROUND_LOCATION);
    }

    private void requestFineLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, request_FINE_LOCATION);
    }

    public void saveTravel() {
        try {
            for (int i = 0; i < myLocationList.size(); i += 2) {
                finalDistance += LocationService.getDistanceFromLatLon(myLocationList.get(i), myLocationList.get(i + 1),
                        myLocationList.get(i + 2), myLocationList.get(i + 3));
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("OutOfBounds");
        }

        //step3 setText
        setResultTV();

        //clear record
        myLocationList.clear();
        finalDistance = 0;
    }

    //step3
    private void setResultTV() {
        TextView tvDistance = findViewById(R.id.carbon_footprint);
        TextView tvTotalDistance = findViewById(R.id.all_move);
        TextView tvTotalFootprint = findViewById(R.id.all_carbon_footprint);
        TextView tvTotalChopsticks = findViewById(R.id.chopsticks);

//        calculate carbon footprint
        final_carbon_footprint = finalDistance * transportation_weight;

//        calculate app total
        // 取得SharedPreference
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        // 取得Key的資料
        float app_cfp = getPrefs.getFloat("app_cfp", 0);
        float app_mileage = getPrefs.getFloat("app_mileage", 0);
        // 取得Editor
        SharedPreferences.Editor editor = getPrefs.edit();
        // 將user_cfp的值設為cfp加總
        float user_cfp = (float) (app_cfp + final_carbon_footprint);
        editor.putFloat("app_cfp", user_cfp);
        // 將user_mileage的值設為mileage加總
        float user_mileage = (float) (app_mileage + finalDistance);
        editor.putFloat("app_mileage", user_mileage);
        // apply
        editor.apply();

//        round off
        double round_final_carbon_footprint = Math.round(final_carbon_footprint * 10.0) / 10.0;
        double round_finalDistance = Math.round(finalDistance * 10.0) / 10.0;
        double myChopsticks = final_carbon_footprint / chopsticks_weight;

//        Double.toString
        String fCF = Double.toString(final_carbon_footprint);
        String fD = Double.toString(finalDistance);
        String tD = Double.toString(round_finalDistance);
        String tF = Double.toString(round_final_carbon_footprint);
        String tC = Integer.toString((int) myChopsticks);

//        setText
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
        if (item.getItemId() == R.id.help) {
            dialog7_fragment badgeDialog = new dialog7_fragment();
            badgeDialog.show(getSupportFragmentManager(), "dialog7_fragment");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}