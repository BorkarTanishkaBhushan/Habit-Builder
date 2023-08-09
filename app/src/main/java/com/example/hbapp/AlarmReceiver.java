package com.example.hbapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
    com.example.hbapp.AddHabit obj = new com.example.hbapp.AddHabit();
        Intent i = new Intent(context, HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,i,0);

        String Group_Notification = "com.android.example.HBApp";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "HBApp")
                .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
                .setContentTitle("Habit Builder")
                .setContentText("Its time for your Habit")
                .setGroup(Group_Notification)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);
        builder.build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());

    }

}
