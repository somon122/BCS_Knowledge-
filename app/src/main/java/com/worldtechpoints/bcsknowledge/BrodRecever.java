package com.worldtechpoints.bcsknowledge;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

public class BrodRecever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1);

        if (WifiManager.WIFI_STATE_ENABLED ==wifiState){

           createNotification(context);

        }


    }
    private void createNotification( Context context) {

        NotificationManager notif=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification.Builder
                (context).setContentTitle("SOMON").setContentText("SOMON is jhdjgd").
                setContentTitle("hello").setSmallIcon(R.drawable.bcs_logo).build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);


       /* Notification n = new NotificationCompat.Builder(context)
                .setContentTitle("Wifi Connection")
                .setContentText("Connected to ")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("You're connected to "))
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE))
                .notify(0, n);*/
    }
}




