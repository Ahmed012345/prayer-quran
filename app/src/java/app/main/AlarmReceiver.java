package com.zaker.android.sapeh.app.main;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.zaker.android.sapeh.R;
import com.zaker.android.sapeh.app.main.activitymain.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String My_Chanel = "mychannel";
        String Chanel_Name = "menu";

        NotificationCompat.BigPictureStyle bigPictureStyle = new
                NotificationCompat.BigPictureStyle();

        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_kaba1)).build();

        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel(My_Chanel,
                    Chanel_Name, NotificationManager.IMPORTANCE_HIGH));
        }

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);


        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.
                TYPE_NOTIFICATION);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,My_Chanel)

                .setSmallIcon(R.drawable.ic_kaaba)
                .setContentTitle(context.getString(R.string.il))
                .setContentText(context.getString(R.string.ill))
                .setSound(alarmSound)
                .setStyle(bigPictureStyle)
                .setAutoCancel(true)
                .setWhen(when)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        assert notificationManager != null;
        notificationManager.notify(0, builder.build());


    }


}
