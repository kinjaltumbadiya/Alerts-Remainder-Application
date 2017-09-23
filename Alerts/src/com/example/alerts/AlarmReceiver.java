package com.example.alerts;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

 @Override
 public void onReceive(Context arg0, Intent arg1) {
  Toast.makeText(arg0, "Alarm received!", Toast.LENGTH_LONG).show();
 
  NotificationManager notificationManager;
  notificationManager = (NotificationManager) arg0.getSystemService(arg0.NOTIFICATION_SERVICE);

  String notificationTitle = "hello";

  Notification notification = new NotificationCompat.Builder(arg0).setSmallIcon(R.drawable.ic_launcher).setContentTitle(notificationTitle).setContentText(notificationTitle).build();
  notificationManager.notify(0, notification);
  System.out.println("Notification built");
 }

}