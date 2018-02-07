package com.pf.testalarm;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

/**
 * @author zhaopf
 * @version 1.0
 * @QQ 1308108803
 * @date 2018/2/7
 * 震动
 * boolean hasVibrator () // 检查硬件是否有振动器
 * void vibrate (long milliseconds) // 控制手机制动milliseconds毫秒
 * void vibrate (long[] pattern,  int repeat) // 让手机以指定pattern模式震动。
 * void cancel () // 关闭震动
 */
public class AlarmVibrateUtil {

    private static Vibrator vibrator;

    /**
     * 震动milliseconds毫秒
     *
     * @param context
     * @param milliseconds
     */
    public static void vibrate(final Context context, long milliseconds) {
        if (null == vibrator) {
            vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
            vibrator.vibrate(milliseconds);
        }
    }

    /**
     * 以pattern[]方式震动
     * vibrate(new int[]{100,200,300,400},2)是指：
     * 先等待100ms，震动200ms，再等待300ms，震动400ms，接着就从pattern[2]的位置开始重复，
     * 就是继续的等待300ms，震动400ms，一直重复下去。
     * 当然传入0就是从开头一直重复下去，传入-1就是不重复震动。
     *
     * @param context
     * @param pattern
     * @param repeat
     */
    public static void vibrate(final Context context, long[] pattern, int repeat) {
        if (null == vibrator) {
            vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
            vibrator.vibrate(pattern, repeat);
        }
    }

    /**
     * 取消震动
     *
     */
    public static void cancelVirate() {
        if (null != vibrator) {
            vibrator.cancel();
            vibrator = null;
        }
    }
}