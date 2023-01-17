package com.doit.detective.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.doit.detective.R;
import com.doit.detective.test_activity;
import com.doit.detective.card2_activity;
import com.doit.detective.go_activity;

public class home_fragment extends Fragment {

    private View v;

    public home_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_fragment, container, false);
        configureCardView();
        return v;
    }
    private void configureCardView() {
        CardView cv1 = (CardView) v.findViewById(R.id.c1);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), go_activity.class);
                startActivity(intent);
            }
        });

        CardView cv2 = (CardView) v.findViewById(R.id.c2);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), card2_activity.class);
                startActivity(intent);
            }
        });

        CardView cv3 = (CardView) v.findViewById(R.id.c3);
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), test_activity.class);
                startActivity(intent);
            }
        });
    }
}