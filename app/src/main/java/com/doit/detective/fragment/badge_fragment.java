package com.doit.detective.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.doit.detective.R;

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
        ImageButton btn1 = v.findViewById(R.id.badge1_img_btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1_fragment badgeDialog = new dialog1_fragment();
                badgeDialog.show(getChildFragmentManager(),"dialog1_fragment");
            }
        });

        ImageButton btn2 = v.findViewById(R.id.badge2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2_fragment badgeDialog = new dialog2_fragment();
                badgeDialog.show(getChildFragmentManager(),"dialog2_fragment");
            }
        });

        ImageButton btn3 = v.findViewById(R.id.badge3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3_fragment badgeDialog = new dialog3_fragment();
                badgeDialog.show(getChildFragmentManager(),"dialog3_fragment");
            }
        });

        ImageButton btn4 = v.findViewById(R.id.badge4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog4_fragment badgeDialog = new dialog4_fragment();
                badgeDialog.show(getChildFragmentManager(),"dialog4_fragment");
            }
        });

        ImageButton btn5 = v.findViewById(R.id.badge5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog5_fragment badgeDialog = new dialog5_fragment();
                badgeDialog.show(getChildFragmentManager(),"dialog5_fragment");
            }
        });

        ImageButton btn6 = v.findViewById(R.id.badge6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Coming soon ~", Toast.LENGTH_LONG).show();
            }
        });
    }
}