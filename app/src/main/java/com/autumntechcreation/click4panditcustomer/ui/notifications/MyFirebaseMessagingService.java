package com.autumntechcreation.click4panditcustomer.ui.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    String user_id, location, push_order_id, message;
    //SharedPreference share;
    String notificationSettings;
    Context context;

//    private int getNotificationIcon() {
//        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
//        return useWhiteIcon ? R.drawable.ic_small_icon : R.drawable.ic_launcher_web;
//    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        Log.d("Msg", "Message received [" + remoteMessage.getNotification().getBody() + "]");


        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1410 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            String channelId = getString(R.string.default_notification_channel_id);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, channelId)
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.profilepanditsmall)
                            .setContentTitle("Message")
                            .setContentText(remoteMessage.getNotification().getBody())
                            .setAutoCancel(true)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            // Since android Oreo notification channel is needed.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
            }

            Intent buttonIntent = new Intent(getBaseContext(), NotificationReceiver.class);
            PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);
            notificationBuilder.addAction(android.R.drawable.ic_menu_view, "VIEW", pendingIntent);
            notificationBuilder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissIntent);

            notificationManager.notify(1410 /* ID of notification */, notificationBuilder.build());
        } else {

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1410,
                    intent, PendingIntent.FLAG_ONE_SHOT);
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new
                    NotificationCompat.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.profilepanditsmall)
                    .setContentTitle("Message")
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setAutoCancel(true)
                    .setSound(soundUri)
                    .setContentIntent(pendingIntent);


            Intent buttonIntent = new Intent(getBaseContext(), NotificationReceiver.class);
            PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);
            notificationBuilder.addAction(android.R.drawable.ic_menu_view, "VIEW", pendingIntent);
            notificationBuilder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissIntent);



            NotificationManager notificationManager =
                    (NotificationManager)
                            getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(1410, notificationBuilder.build());
        }





    }


}
