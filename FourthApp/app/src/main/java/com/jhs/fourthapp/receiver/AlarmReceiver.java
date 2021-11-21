package com.jhs.fourthapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jhs.fourthapp.manager.NotifiManager;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotifiManager.showNotification(context);
    }
}