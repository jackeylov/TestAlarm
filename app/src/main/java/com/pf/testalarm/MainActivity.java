package com.pf.testalarm;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.SoftReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNumber;
    private CheckBox cbIsloop;
    private Button btnClock;
    private Button btnCancleClock;
    private static SoftReference<Handler> handlerSoftReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = findViewById(R.id.et_number);
        cbIsloop = findViewById(R.id.cb_isloop);
        btnClock = findViewById(R.id.btn_clock);
        btnCancleClock = findViewById(R.id.btn_cancle_clock);

        btnClock.setOnClickListener(this);
        btnCancleClock.setOnClickListener(this);
        handlerSoftReference = new SoftReference<Handler>(new MHandler());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clock:
                String etContent = etNumber.getText().toString();
                if (TextUtils.isEmpty(etContent)) {
                    Toast.makeText(this, "请输入时间间隔", Toast.LENGTH_SHORT).show();
                    return;
                }
                int number = Integer.valueOf(etContent);
                boolean checked = cbIsloop.isChecked();
                AlarmHelper.startAlarm(this, number, checked);
                break;
            case R.id.btn_cancle_clock:
                AlarmHelper.cancenAlarm();
                break;
            default:
                break;
        }
    }

    private class MHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (null == msg) {
                return;
            }
            int what = msg.what;
            if (what == AlarmDialogUtil.DialogUtil_FLAG) {
                // 显示弹框
                AlarmDialogUtil.showDialog(MainActivity.this);
                // 震动
                AlarmVibrateUtil.vibrate(MainActivity.this, new long[]{500, 500}, 0);
                // 铃声
                AlarmMediaPlayer.playing(MainActivity.this);
            }
        }
    }

    public static SoftReference<Handler> getHandlerSoftReference() {
        return handlerSoftReference;
    }
}