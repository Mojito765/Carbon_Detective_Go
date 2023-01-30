package com.doit.detective.fragment;

import static com.doit.detective.go_activity.total_travel;
import static com.doit.detective.go_activity.total_travel_carbon_footprint;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.doit.detective.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class badge_fragment extends Fragment {
    // !!!!!!!!!testing!!!!!!!!! implements android.location.LocationListener
    private View v;

    public static boolean badge1_unlock = false;
    public static boolean badge2_unlock = false;
    public static boolean badge3_unlock = false;
    public static boolean badge4_unlock = false;
    public static boolean badge5_unlock = false;

    public badge_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.badge_fragment, container, false);
        configureImageButton();

        //badge1
        if (total_travel_carbon_footprint >= 0) {
            badge1_unlock = true;
        }

        //badge2
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        // get current time
        Date date = new Date(System.currentTimeMillis());
        if (simpleDateFormat.format(date).equals("00:00")) {
            badge2_unlock = true;
        }

        //badge3
        /* !!!!!!!!!testing!!!!!!!!!
        LocationManager lm = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        this.onLocationChanged((Location) null);
        !!!!!!!!!testing!!!!!!!!! */

        //badge4
        if (total_travel >= 100) {
            badge4_unlock = true;
        }

        //badge5
        if (getBatteryLevel() < 20) {
            badge5_unlock = true;
        }

        return v;
    }

/* !!!!!!!!!testing!!!!!!!!!
    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            //can't get speed
//            test.setText("null km/h");
        } else {
            //convert to km/hour

            int speed = (int) ((location.getSpeed() * 3600) / 1000);
            if (speed > 500) {
                badge3_unlock = true;
            }
//            test.setText(speed + " km/h");
        }
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onProviderDisabled(String provider) {


    }
!!!!!!!!!testing!!!!!!!!! */

    private int getBatteryLevel() {
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = requireContext().registerReceiver(null, iFilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level / (float) scale;
        return (int) (batteryPct * 100);
    }

    private void configureImageButton() {
        ImageButton btn1 = v.findViewById(R.id.badge_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1_fragment badgeDialog = new dialog1_fragment();
                badgeDialog.show(getChildFragmentManager(), "dialog1_fragment");
            }
        });

        ImageButton btn2 = v.findViewById(R.id.badge_btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2_fragment badgeDialog = new dialog2_fragment();
                badgeDialog.show(getChildFragmentManager(), "dialog2_fragment");
            }
        });

        ImageButton btn3 = v.findViewById(R.id.badge_btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3_fragment badgeDialog = new dialog3_fragment();
                badgeDialog.show(getChildFragmentManager(), "dialog3_fragment");
            }
        });

        ImageButton btn4 = v.findViewById(R.id.badge_btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog4_fragment badgeDialog = new dialog4_fragment();
                badgeDialog.show(getChildFragmentManager(), "dialog4_fragment");
            }
        });

        ImageButton btn5 = v.findViewById(R.id.badge_btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog5_fragment badgeDialog = new dialog5_fragment();
                badgeDialog.show(getChildFragmentManager(), "dialog5_fragment");
            }
        });

        ImageButton btn6 = v.findViewById(R.id.badge_btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Stay tuned", Toast.LENGTH_LONG).show();
            }
        });
    }
}