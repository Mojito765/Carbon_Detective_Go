package com.doit.detective;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

public class Utility {

    public static boolean isMyServiceRunning(Class<?> serviceClass, Activity myActivity) {
        ActivityManager manager = (ActivityManager) myActivity.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
