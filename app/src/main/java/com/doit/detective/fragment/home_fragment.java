package com.doit.detective.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.doit.detective.R;
import com.doit.detective.card2_activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class home_fragment extends Fragment {

    private View v;

    Location gps_loc;
    Location network_loc;
    Location final_loc;
    double longitude;
    double latitude;
    String userState, userCity;

    public home_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_fragment, container, false);
        getTime();
        getMyLocation();
        configureCardView();
        return v;
    }

    private void getMyLocation() {
        TextView tvState = v.findViewById(R.id.locationState);
        TextView tvCity = v.findViewById(R.id.locationCity);

        LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        try {

            gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gps_loc != null) {
            final_loc = gps_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        } else if (network_loc != null) {
            final_loc = network_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        } else {
            latitude = 0.0;
            longitude = 0.0;
        }

        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);

        try {

            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                userState = addresses.get(0).getAdminArea();
                userCity = addresses.get(0).getLocality();
            } else {
                userCity = "Unknown";
                userState = "Unknown";
            }
            tvCity.setText(userCity);
            tvState.setText(userState);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getTime() {
        TextView tvTime = v.findViewById(R.id.time);
        // HH:mm:ss
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        // get current time
        Date date = new Date(System.currentTimeMillis());
        tvTime.setText(simpleDateFormat.format(date));
    }

    private void configureCardView() {

        CardView cv0 = v.findViewById(R.id.c0);
        cv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog6_fragment badgeDialog = new dialog6_fragment();
                badgeDialog.show(getChildFragmentManager(),"dialog6_fragment");
            }
        });

        CardView cv1 = v.findViewById(R.id.c1);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime();
                getMyLocation();
                Toast.makeText(requireActivity(), "Updated", Toast.LENGTH_SHORT).show();
            }
        });

        CardView cv2 = v.findViewById(R.id.c2);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), card2_activity.class);
                startActivity(intent);
            }
        });

        CardView cv3 = v.findViewById(R.id.c3);
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
                    case Configuration.UI_MODE_NIGHT_YES:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        switchDark();
                        break;
                    case Configuration.UI_MODE_NIGHT_NO:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        switchLight();
                        break;
                }
            }
        });
    }

    private void switchDark() {
        ImageView imgMode = v.findViewById(R.id.modeImg);
        TextView tvMode = v.findViewById(R.id.modeTv);
        imgMode.setImageResource(R.drawable.round_dark_mode_24);
        tvMode.setText("Dark");
    }

    private void switchLight() {
        ImageView imgMode = v.findViewById(R.id.modeImg);
        TextView tvMode = v.findViewById(R.id.modeTv);
        imgMode.setImageResource(R.drawable.round_light_mode_24);
        tvMode.setText("Light");
    }
}