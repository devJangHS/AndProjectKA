package com.jhs.fourthapp.manager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.jhs.fourthapp.MusicActivity;
import com.jhs.fourthapp.R;

public class NotifiManager {

    static final int NOTI_ID = 5555;
    static public final String NOTI_CLICK_ACTION = "com.jhs.fourthapp.noticlick";

    static public void showNotification(Context context, String title, String msg){

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "FourthAppNoti")
                .setContentTitle(title)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText("세부 내용 세부 내용 세부 내용 세부 내용 \n 세부 내용 \n 세부 내용 " +
//                        "\n 세부 내용 세부 내용 세부 내용 세부 내용 \n 세부 내용 \n 세부 내용"));
                .setAutoCancel(true)
                .setContentText(msg);


        Intent intent = new Intent(context, MusicActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );

        builder.setContentIntent(pendingIntent);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            builder.setSmallIcon(R.drawable.ic_launcher_foreground);

            NotificationChannel channel = new NotificationChannel("FourthAppNoti", "테스트 채널", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("설명 설명");

            notificationManager.createNotificationChannel(channel);

        }else{
            builder.setSmallIcon(R.mipmap.ic_launcher);
        }

        Notification notification = builder.build();

        notificationManager.notify((int)System.currentTimeMillis(), notification);

    }

    static public void showNotification(Context context){

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "FourthAppNoti")
                .setContentTitle("커스텀 노티")
                .setContentText("");


        Toast.makeText(context, context.getPackageName(), Toast.LENGTH_SHORT).show();

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.custom_noti);
        remoteViews.setTextViewText(R.id.tvOnNoti, "텍스트 텍스트");


        Intent clickIntent = new Intent(NOTI_CLICK_ACTION);
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btnOnNoti, clickPendingIntent);

        builder.setCustomContentView(remoteViews);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            builder.setSmallIcon(R.drawable.ic_launcher_foreground);

            NotificationChannel channel = new NotificationChannel("FourthAppNoti", "테스트 채널", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("설명 설명");

            notificationManager.createNotificationChannel(channel);

        }else{
            builder.setSmallIcon(R.mipmap.ic_launcher);
        }

        Notification notification = builder.build();

        notificationManager.notify(NOTI_ID, notification);

    }




    static public void cancelNotification(Context context){

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTI_ID);

    }

}
