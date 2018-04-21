package com.example.rashmi.viewflipper;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Rashmi on 26-03-2018.
 */

public class AudioService extends Service {
    MediaPlayer mdx;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flag, int startId){
        mdx = MediaPlayer.create(this,R.raw.music2);
        mdx.setLooping(true);
        mdx.start();
        return START_STICKY;
    }
    public void onDestroy(){
        super.onDestroy();
        mdx.stop();
    }

}
