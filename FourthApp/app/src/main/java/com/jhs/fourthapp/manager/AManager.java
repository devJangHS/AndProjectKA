package com.jhs.fourthapp.manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.jhs.fourthapp.receiver.AlarmReceiver;

public class AManager {

    public static void registerAlarm(Context context){


        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction("com.jhs.fourthapp.ALARM");
        intent.putExtra("id", 111);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 111, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000,pendingIntent);

    }
}
