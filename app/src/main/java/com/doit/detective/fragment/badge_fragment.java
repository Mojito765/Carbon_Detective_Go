package com.doit.detective.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.doit.detective.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class badge_fragment extends Fragment {
    private View v;

    public badge_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.badge_fragment, container, false);
        configureImageButton();

        // 取得SharedPreference
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getContext());
        // 取得Editor
        SharedPreferences.Editor editor = getPrefs.edit();
        // 取得Key的資料
        float app_cfp = getPrefs.getFloat("app_cfp", 0);
        int badge1Status = getPrefs.getInt("status_badge1", 0);
        int badge2Status = getPrefs.getInt("status_badge2", 0);
        int badge4Status = getPrefs.getInt("status_badge4", 0);
        int badge5Status = getPrefs.getInt("status_badge5", 0);

        //badge1
        if (app_cfp > 0) {
            if (badge1Status != 1) {
                String date1 = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
                editor.putString("time_badge1", "Unlocked: " + date1);
                editor.apply();

                MaterialAlertDialogBuilder iconDialog = new MaterialAlertDialogBuilder(requireContext(), R.style.CustomMaterialAlertDialog)
                        .setTitle("Congratulations!")
                        .setMessage("You've unlocked a new badge, \"Take the first step\".")
                        .setIcon(R.drawable.round_lock_open_24)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog1_fragment badgeDialog = new dialog1_fragment();
                                badgeDialog.show(getChildFragmentManager(), "dialog1_fragment");
                            }
                        });
                iconDialog.show();
            }
            // 將status_badge1的值設為1（解鎖）
            editor.putInt("status_badge1", 1);
            // apply
            editor.apply();
        }

        //badge2
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        // get current time
        Date date = new Date(System.currentTimeMillis());
        if (simpleDateFormat.format(date).equals("00:00")) {
            if (badge2Status != 1) {
                String date2 = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
                editor.putString("time_badge2", "Unlocked: " + date2);
                editor.apply();

                MaterialAlertDialogBuilder iconDialog = new MaterialAlertDialogBuilder(requireContext(), R.style.CustomMaterialAlertDialog)
                        .setTitle("Congratulations!")
                        .setMessage("You've unlocked a new badge, \"Under the moon\".")
                        .setIcon(R.drawable.round_lock_open_24)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog2_fragment badgeDialog = new dialog2_fragment();
                                badgeDialog.show(getChildFragmentManager(), "dialog2_fragment");
                            }
                        });
                iconDialog.show();
            }
            // 將status_badge2的值設為1（解鎖）
            editor.putInt("status_badge2", 1);
            // apply
            editor.apply();
        }

        //badge4
        if (app_cfp >= 100) {
            if (badge4Status != 1) {
                String date4 = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
                editor.putString("time_badge4", "Unlocked: " + date4);
                editor.apply();

                MaterialAlertDialogBuilder iconDialog = new MaterialAlertDialogBuilder(requireContext(), R.style.CustomMaterialAlertDialog)
                        .setTitle("Congratulations!")
                        .setMessage("You've unlocked a new badge, \"A foot to hundred\".")
                        .setIcon(R.drawable.round_lock_open_24)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog4_fragment badgeDialog = new dialog4_fragment();
                                badgeDialog.show(getChildFragmentManager(), "dialog4_fragment");
                            }
                        });
                iconDialog.show();
            }
            // 將status_badge4的值設為1（解鎖）
            editor.putInt("status_badge4", 1);
            // apply
            editor.apply();
        }

        //badge5
        if (getBatteryLevel() < 20) {
            if (badge5Status != 1) {
                String date5 = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
                editor.putString("time_badge5", "Unlocked: " + date5);
                editor.apply();

                MaterialAlertDialogBuilder iconDialog = new MaterialAlertDialogBuilder(requireContext(), R.style.CustomMaterialAlertDialog)
                        .setTitle("Congratulations!")
                        .setMessage("You've unlocked a new badge, \"Are you tired\".")
                        .setIcon(R.drawable.round_lock_open_24)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog5_fragment badgeDialog = new dialog5_fragment();
                                badgeDialog.show(getChildFragmentManager(), "dialog5_fragment");
                            }
                        });
                iconDialog.show();
            }
            // 將status_badge5的值設為1（解鎖）
            editor.putInt("status_badge5", 1);
            // apply
            editor.apply();
        }
        return v;
    }

    private int getBatteryLevel() {
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = requireContext().registerReceiver(null, iFilter);

        assert batteryStatus != null;
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
                // 取得SharedPreference
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getContext());
                int badge1Status = getPrefs.getInt("status_badge1", 0);
                if (badge1Status != 1) {
                    MaterialAlertDialogBuilder iconDialog = new MaterialAlertDialogBuilder(requireContext(), R.style.CustomMaterialAlertDialog)
                            .setTitle("Locked")
                            .setMessage("A journey of a thousand miles begins with a single step.")
                            .setIcon(R.drawable.round_lock_24)
                            .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog1_fragment badgeDialog = new dialog1_fragment();
                                    badgeDialog.show(getChildFragmentManager(), "dialog1_fragment");
                                }
                            });
                    iconDialog.show();
                } else {
                    dialog1_fragment badgeDialog = new dialog1_fragment();
                    badgeDialog.show(getChildFragmentManager(), "dialog1_fragment");
                }
            }
        });

        ImageButton btn2 = v.findViewById(R.id.badge_btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取得SharedPreference
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getContext());
                int badge2Status = getPrefs.getInt("status_badge2", 0);
                if (badge2Status != 1) {
                    MaterialAlertDialogBuilder iconDialog = new MaterialAlertDialogBuilder(requireContext(), R.style.CustomMaterialAlertDialog)
                            .setTitle("Locked")
                            .setMessage("A journey of a thousand miles begins with a single step.")
                            .setIcon(R.drawable.round_lock_24)
                            .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog2_fragment badgeDialog = new dialog2_fragment();
                                    badgeDialog.show(getChildFragmentManager(), "dialog2_fragment");
                                }
                            });
                    iconDialog.show();
                } else {
                    dialog2_fragment badgeDialog = new dialog2_fragment();
                    badgeDialog.show(getChildFragmentManager(), "dialog2_fragment");
                }
            }
        });

        ImageButton btn3 = v.findViewById(R.id.badge_btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取得SharedPreference
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getContext());
                int badge3Status = getPrefs.getInt("status_badge3", 0);
                if (badge3Status != 1) {
                    MaterialAlertDialogBuilder iconDialog = new MaterialAlertDialogBuilder(requireContext(), R.style.CustomMaterialAlertDialog)
                            .setTitle("Locked")
                            .setMessage("A journey of a thousand miles begins with a single step.")
                            .setIcon(R.drawable.round_lock_24)
                            .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog3_fragment badgeDialog = new dialog3_fragment();
                                    badgeDialog.show(getChildFragmentManager(), "dialog3_fragment");
                                }
                            });
                    iconDialog.show();
                } else {
                    dialog3_fragment badgeDialog = new dialog3_fragment();
                    badgeDialog.show(getChildFragmentManager(), "dialog3_fragment");
                }
            }
        });

        ImageButton btn4 = v.findViewById(R.id.badge_btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取得SharedPreference
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getContext());
                int badge4Status = getPrefs.getInt("status_badge4", 0);
                if (badge4Status != 1) {
                    MaterialAlertDialogBuilder iconDialog = new MaterialAlertDialogBuilder(requireContext(), R.style.CustomMaterialAlertDialog)
                            .setTitle("Locked")
                            .setMessage("A journey of a thousand miles begins with a single step.")
                            .setIcon(R.drawable.round_lock_24)
                            .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog4_fragment badgeDialog = new dialog4_fragment();
                                    badgeDialog.show(getChildFragmentManager(), "dialog4_fragment");
                                }
                            });
                    iconDialog.show();
                } else {
                    dialog4_fragment badgeDialog = new dialog4_fragment();
                    badgeDialog.show(getChildFragmentManager(), "dialog4_fragment");
                }
            }
        });

        ImageButton btn5 = v.findViewById(R.id.badge_btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取得SharedPreference
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getContext());
                int badge5Status = getPrefs.getInt("status_badge5", 0);
                if (badge5Status != 1) {
                    MaterialAlertDialogBuilder iconDialog = new MaterialAlertDialogBuilder(requireContext(), R.style.CustomMaterialAlertDialog)
                            .setTitle("Locked")
                            .setMessage("A journey of a thousand miles begins with a single step.")
                            .setIcon(R.drawable.round_lock_24)
                            .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog5_fragment badgeDialog = new dialog5_fragment();
                                    badgeDialog.show(getChildFragmentManager(), "dialog5_fragment");
                                }
                            });
                    iconDialog.show();
                } else {
                    dialog5_fragment badgeDialog = new dialog5_fragment();
                    badgeDialog.show(getChildFragmentManager(), "dialog5_fragment");
                }
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