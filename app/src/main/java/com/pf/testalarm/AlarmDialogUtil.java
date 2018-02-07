package com.pf.testalarm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @author zhaopf
 * @version 1.0
 * @QQ 1308108803
 * @date 2018/2/7
 */
public class AlarmDialogUtil {

    public static final int DialogUtil_FLAG = 0x121;
    private static AlertDialog dialog;

    public static void showDialog(final Context context) {
        if (null != dialog) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("时间到了");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogs, int which) {
                dialog.dismiss();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogs) {
                AlarmVibrateUtil.cancelVirate();
                AlarmMediaPlayer.stop();
                dialog = null;
            }
        });
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogs) {
                AlarmVibrateUtil.cancelVirate();
                AlarmMediaPlayer.stop();
                dialog = null;
            }
        });
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}