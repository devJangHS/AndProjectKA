package com.jhs.fourthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jhs.fourthapp.manager.NotifiManager;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findViewById(R.id.btnShowNoti).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NotifiManager.showNotification(NotificationActivity.this, "제목제목", "내용내용");

                NotifiManager.showNotification(NotificationActivity.this);
            }
        });

        findViewById(R.id.btnCancelNoti).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.cancel(5555);
            }
        });


        IntentFilter filter = new IntentFilter();
        filter.addAction(NotifiManager.NOTI_CLICK_ACTION);


        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Toast.makeText(NotificationActivity.this, "Action : " + intent.getAction(), Toast.LENGTH_SHORT).show();

            }
        };

        registerReceiver(broadcastReceiver, filter);




    }



}