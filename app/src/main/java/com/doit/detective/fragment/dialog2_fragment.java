package com.doit.detective.fragment;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.doit.detective.R;

import java.util.Objects;

public class dialog2_fragment extends DialogFragment {

    public dialog2_fragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater().inflate(R.layout.badge2_dialog, new LinearLayout(getActivity()), false);
        // Build dialog
        Dialog builder = new Dialog(requireActivity(), android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(builder.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        Button btnOk = view.findViewById(R.id.close);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.dismiss();
            }
        });

        ImageView badgeLock = builder.findViewById(R.id.lock2);
        TextView badgeTime = builder.findViewById(R.id.badgeTime2);

        // 取得SharedPreference
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getContext());
        // 取得Key名稱為status_badge2的資料
        int b2 = getPrefs.getInt("status_badge2", 0);
        String badge2Time = getPrefs.getString("time_badge2", "0");

        if (b2 == 1) {
            badgeLock.setImageResource(R.drawable.round_lock_open_24);

            badgeTime.setText(badge2Time);
        }

        return builder;
    }
}