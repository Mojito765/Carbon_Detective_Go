package com.doit.detective.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.doit.detective.R;
import com.doit.detective.badge1_activity;
import com.doit.detective.badge2_activity;
import com.doit.detective.badge3_activity;
import com.doit.detective.badge4_activity;
import com.doit.detective.badge6_activity;

public class badge_fragment extends Fragment {

    private View v;

    public badge_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.badge_fragment,container, false);
        configureImageButton();
        return v;
    }

    private void configureImageButton() {
        ImageButton btn1 = (ImageButton) v.findViewById(R.id.badge1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), badge1_activity.class);
                startActivity(intent);
            }
        });

        ImageButton btn2 = (ImageButton) v.findViewById(R.id.badge2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), badge2_activity.class);
                startActivity(intent);
            }
        });

        ImageButton btn3 = (ImageButton) v.findViewById(R.id.badge3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), badge3_activity.class);
                startActivity(intent);
            }
        });

        ImageButton btn4 = (ImageButton) v.findViewById(R.id.badge4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), badge4_activity.class);
                startActivity(intent);
            }
        });

        ImageButton btn5 = (ImageButton) v.findViewById(R.id.badge5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "COMING SOON!", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton btn6 = (ImageButton) v.findViewById(R.id.badge6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), badge6_activity.class);
                startActivity(intent);
            }
        });
    }
}