package com.pf.testalarm;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

import java.io.IOException;

/**
 * @author zhaopf
 * @version 1.0
 * @QQ 1308108803
 * @date 2018/2/7
 * void setDataSource (Context context , Uri uri)//根据Uri设置音频，当然还有其他几个重载的方法来指定特定的音频。
 * void setLooping (boolean looping)//设置是否循环播放
 * void prepare ()//让MediaPlayer真正去装载音频文件
 * void start ()//开始或恢复播放
 * void pause ()//暂停播放，调用start()可以恢复播放
 * void stop ()//停止播放
 * boolean isPlaying ()//是否正在播放
 * void release ()//释放与此MediaPlayer关联的资源
 */
public class AlarmMediaPlayer {

    private static MediaPlayer mMediaPlayer;

    public static void playing(Context context) {
        if (null != mMediaPlayer) {
            return;
        }
        try {
            // 用于获取手机默认铃声的Uri
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(context, uri);
            // 告诉mediaPlayer播放的是铃声流
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        }
    }
}