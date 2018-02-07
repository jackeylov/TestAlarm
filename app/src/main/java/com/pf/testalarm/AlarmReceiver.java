package com.pf.testalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.e(TAG, "onReceive: " + intent.getStringExtra("msg"));
        // 显示弹框
        MainActivity.getHandlerSoftReference().get().sendEmptyMessage(AlarmDialogUtil.DialogUtil_FLAG);
    }
}