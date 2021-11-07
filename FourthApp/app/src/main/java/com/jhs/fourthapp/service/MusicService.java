package com.jhs.fourthapp.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.jhs.fourthapp.R;

public class MusicService extends Service {

    MediaPlayer mediaPlayer;


    public MusicService() {
    }

    @Override
    public void onCreate() {

        Log.d("Music Service", "onCreate");

        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.d("Music Service", "onBind");

        mediaPlayer = MediaPlayer.create(MusicService.this, R.raw.sample);
        mediaPlayer.start();

        return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("Music Service", "onStartCommand");


        mediaPlayer = MediaPlayer.create(MusicService.this, R.raw.sample);
        mediaPlayer.start();


        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        Log.d("Music Service", "onDestroy");

        if(mediaPlayer != null) {

            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}