package com.jhs.fourthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.jhs.fourthapp.service.MusicService;

import java.util.Timer;
import java.util.TimerTask;

public class MusicActivity extends AppCompatActivity {

    ToggleButton tbAct, tbService;
    SeekBar seekBar;

    MediaPlayer mediaPlayer;

    Intent intent;
    ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        tbAct = findViewById(R.id.tbAct);
        tbService = findViewById(R.id.tbService);
        seekBar = findViewById(R.id.seekBar);

        mediaPlayer = MediaPlayer.create(MusicActivity.this, R.raw.sample);

        intent = new Intent(MusicActivity.this, MusicService.class);

//        seekBar.setMax(mediaPlayer.getDuration());
//
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//                if(mediaPlayer.isPlaying()){
//                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
//                }
//
//            }
//        },0, 100);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };


        tbAct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    mediaPlayer.start();
                }else{
                    mediaPlayer.pause();
                }

            }
        });

        tbService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){

//                    bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                    startService(intent);

                }else{
//                    unbindService(serviceConnection);
                    stopService(intent);

                }

            }
        });
    }
}