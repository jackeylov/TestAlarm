package com.pf.testalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

/**
 * @author zhaopf
 * @version 1.0
 * @QQ 1308108803
 * @date 2018/2/7
 * 开启闹钟
 */
public class AlarmHelper {

    private static final String TAG = "AlarmHelper";

    private static AlarmManager am;
    private static PendingIntent pendingIntent;

    /**
     * 开启闹钟
     *
     * @param context
     * @param delayTime 闹钟间隔时间
     * @param isRepeat  是否是重复闹钟
     */
    public static void startAlarm(Context context, int delayTime, boolean isRepeat) {
        if (null == context) {
            throw new NullPointerException("context cannot be null.");
        }
        if (delayTime <= 0) {
            throw new RuntimeException("The delay time should not be less than 0.");
        }
        if (null != am) {
            Toast.makeText(context, "已经开启闹钟了", Toast.LENGTH_SHORT).show();
            return;
        }
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("msg", "value");
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        long times = SystemClock.elapsedRealtime() + delayTime * 60 * 1000;
        // 重复闹钟
        if (isRepeat) {
            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, times,
                    delayTime * 60 * 1000, pendingIntent);
        } else {
            am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, times, pendingIntent);
        }
        Log.e(TAG, "startAlarm: 闹钟开始");
    }

    public static void cancenAlarm() {
        if (am != null && pendingIntent != null) {
            am.cancel(pendingIntent);
            pendingIntent = null;
            am = null;
            Log.e(TAG, "cancenAlarm: 闹钟关闭");
        }
    }
}