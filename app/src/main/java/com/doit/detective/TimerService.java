package com.doit.detective;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class TimerService extends Service {
    private Handler handler;
    private Runnable runnable;
    private final int delay = 1000; // 每秒觸發一次計時器
    private int seconds = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                // 在這裡執行計時相關的任務
                seconds++; // 增加計時器的秒數
                // 在此處更新UI或執行其他相關操作

                // 延遲一段時間後再次觸發計時
                handler.postDelayed(this, delay);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 開始計時
        handler.postDelayed(runnable, delay);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // 停止計時
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
