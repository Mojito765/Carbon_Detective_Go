package com.doit.detective;

import static com.doit.detective.LocationService.finalDistance;
import static com.doit.detective.LocationService.myLocationList;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.doit.detective.fragment.dialog7_fragment;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class go_activity extends AppCompatActivity {

    private static final int MY_FINE_LOCATION_REQUEST = 99;
    private static final int MY_BACKGROUND_LOCATION_REQUEST = 100;

    public static double transportation_weight = 0;
    public static double final_carbon_footprint = 0;

    public static double total_travel = 0;
    public static double total_travel_carbon_footprint = 0;

    LocationService mLocationService = new LocationService();
    Intent mServiceIntent;

    Button startServiceBtn, stopServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);

        topBack();

        topQA();

        btnTransportation();

        startServiceBtn = findViewById(R.id.start_service_btn);
        stopServiceBtn = findViewById(R.id.stop_service_btn);

        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(go_activity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                        if (ActivityCompat.checkSelfPermission(go_activity.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {


                            AlertDialog alertDialog = new AlertDialog.Builder(go_activity.this).create();
                            alertDialog.setTitle(Html.fromHtml(getString(R.string.ask_background_location_permission)));
                            alertDialog.setMessage(getString(R.string.background_location_permission_message));

                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Keep Only-while-using Access",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            starServiceFunc();
                                            dialog.dismiss();
                                        }
                                    });

                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Setup All-the-time Access",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestBackgroundLocationPermission();
                                            dialog.dismiss();
                                        }
                                    });

                            alertDialog.show();


                        } else if (ActivityCompat.checkSelfPermission(go_activity.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {
                            starServiceFunc();
                        }
                    } else {
                        starServiceFunc();
                    }

                } else if (ActivityCompat.checkSelfPermission(go_activity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(go_activity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {


                        AlertDialog alertDialog = new AlertDialog.Builder(go_activity.this).create();
                        alertDialog.setTitle("Location permission required");
                        alertDialog.setMessage("ACCESS_FINE_LOCATION");

                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestFineLocationPermission();
                                        dialog.dismiss();
                                    }
                                });


                        alertDialog.show();

                    } else {
                        requestFineLocationPermission();
                    }
                }

            }
        });

        stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopServiceFunc();
            }
        });


    }

    //01/24
    private void btnTransportation() {
        MaterialButtonToggleGroup materialButtonToggleGroup = findViewById(R.id.toggleButton);

        materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    if (checkedId == R.id.trans1) {
                        transportation_weight = 0.06;
                    } else if (checkedId == R.id.trans2) {
                        transportation_weight = 0.046;
                    } else if (checkedId == R.id.trans3) {
                        transportation_weight = 0.173;
                    }
                }
            }
        });
    }

    private void topQA() {
        Button questionButton = findViewById(R.id.question);
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog7_fragment badgeDialog = new dialog7_fragment();
                badgeDialog.show(getSupportFragmentManager(), "dialog7_fragment");
            }
        });
    }

    private void topBack() {
        Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

//        Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_LONG).show();

        if (requestCode == MY_FINE_LOCATION_REQUEST) {

            if (grantResults.length != 0 /*grantResults.isNotEmpty()*/ && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        requestBackgroundLocationPermission();
                    }
                }

            } else {
                Toast.makeText(this, "ACCESS_FINE_LOCATION permission denied", Toast.LENGTH_LONG).show();
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                 /*   startActivity(
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", this.getPackageName(), null),),);*/

                    startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:com.doit.detective")
                    ));

                }
            }

        } else if (requestCode == MY_BACKGROUND_LOCATION_REQUEST) {

            if (grantResults.length != 0 /*grantResults.isNotEmpty()*/ && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Background location Permission Granted", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Background location permission denied", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void starServiceFunc() {
        mLocationService = new LocationService();
        mServiceIntent = new Intent(this, mLocationService.getClass());
        if (!Util.isMyServiceRunning(mLocationService.getClass(), this)) {
            startService(mServiceIntent);
            Toast.makeText(this, "Start record", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Recording", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopServiceFunc() {
        mLocationService = new LocationService();
        mServiceIntent = new Intent(this, mLocationService.getClass());
        if (Util.isMyServiceRunning(mLocationService.getClass(), this)) {
            stopService(mServiceIntent);
            Toast.makeText(this, "Stopping", Toast.LENGTH_SHORT).show();
            saveDistance(); // beta
        } else {
            Toast.makeText(this, "Not recording", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void requestBackgroundLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                MY_BACKGROUND_LOCATION_REQUEST);
    }

    private void requestFineLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, MY_FINE_LOCATION_REQUEST);
    }

    public void saveDistance() {
        try {
            for (int i = 0; i < myLocationList.size(); i += 2) {
                finalDistance += LocationService.GetDistanceFromLatLonInKm(myLocationList.get(i), myLocationList.get(i + 1),
                        myLocationList.get(i + 2), myLocationList.get(i + 3));
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("OutOfBounds");
        }
//        Toast.makeText(this,
//                "Distance: " + finalDistance, Toast.LENGTH_LONG).show();
        setResultTV();
        myLocationList.clear();
        finalDistance = 0;
    }

    private void setResultTV() {
        TextView tvDistance = findViewById(R.id.carbon_footprint);

//        calculate carbon footprint
        final_carbon_footprint = finalDistance * transportation_weight;
//        calculate app total
        total_travel_carbon_footprint += final_carbon_footprint;
        total_travel += finalDistance;
//        round off
        final_carbon_footprint = Math.round(final_carbon_footprint * 1000.0) / 1000.0;
        finalDistance = Math.round(finalDistance * 1000.0) / 1000.0;
//        Double.toString
        String fCF = Double.toString(final_carbon_footprint);
        String fD = Double.toString(finalDistance);

        tvDistance.setText(getString(R.string.calculate_result, fD, fCF));
    }

}