package com.doit.detective.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.doit.detective.R;
import com.doit.detective.StatisticsActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class home_fragment extends Fragment implements OnMapReadyCallback {

    private View v;

    Location gps_loc;
    Location network_loc;
    Location final_loc;
    double longitude;
    double latitude;
    String userState, userCity;

    MapView mapview;
    GoogleMap mMap;

    public home_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_fragment, container, false);
        getTime();
        getMyLocation();
        configureCardView();

        mapview = v.findViewById(R.id.map);
        mapview.getMapAsync(this);
        mapview.onCreate(savedInstanceState);

        //Init
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getTime();
                handler.postDelayed(this, 1000);
            }
        };

        //Start
        handler.postDelayed(runnable, 1000);

        return v;
    }

    private void getMyLocation() {
        TextView tvState = v.findViewById(R.id.locationState);
        TextView tvCity = v.findViewById(R.id.locationCity);

        LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
//            return;
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
                // 取得SharedPreference
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getContext());
                // 取得Key名稱為app_cfp...的資料
                float app_cfp = getPrefs.getFloat("app_cfp", 0);

                if (app_cfp <= 100) {
                    dialog6_fragment badgeDialog = new dialog6_fragment();
                    badgeDialog.show(getChildFragmentManager(), "dialog6_fragment");
                }
                else {
                    dialog9_fragment badgeDialog = new dialog9_fragment();
                    badgeDialog.show(getChildFragmentManager(), "dialog9_fragment");
                }
            }
        });

        CardView cv1 = v.findViewById(R.id.c1);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireActivity(), "Updating", Toast.LENGTH_SHORT).show();
                getTime();
                getMyLocation();
            }
        });

        CardView cv2 = v.findViewById(R.id.c2);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), StatisticsActivity.class);
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
                        break;
                    case Configuration.UI_MODE_NIGHT_NO:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        try {
            // Add a marker in current location
            LatLng currentLocation = new LatLng(final_loc.getLatitude(), final_loc.getLongitude());
            mMap.addMarker(new MarkerOptions().position(currentLocation).title("You're here!"));
            // Move the camera and zoom in
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12.0f));
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mapview.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapview.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapview.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapview.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapview.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapview.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapview.onLowMemory();
    }
}