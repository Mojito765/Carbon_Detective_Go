package com.doit.detective.fragment;

import static com.doit.detective.fragment.badge_fragment.badge3_unlock;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.doit.detective.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class dialog3_fragment extends DialogFragment {

    public dialog3_fragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater().inflate(R.layout.badge3_dialog, new LinearLayout(getActivity()), false);
        // Build dialog
        Dialog builder = new Dialog(getActivity(), android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        Button btnOk = view.findViewById(R.id.close);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.dismiss();
            }
        });

        ImageView badgeLock = builder.findViewById(R.id.lock3);
        TextView badgeTime = builder.findViewById(R.id.badgeTime3);
        if (badge3_unlock) {
            badgeLock.setImageResource(R.drawable.round_lock_open_24);
            String date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
            badgeTime.setText("Unlocked: " + date);
        }

        return builder;
    }
}