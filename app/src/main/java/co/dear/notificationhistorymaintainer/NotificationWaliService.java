package co.dear.notificationhistorymaintainer;

import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;


public class NotificationWaliService extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);

        Intent intent = new Intent(MainActivity.UPDATE_UI);
        intent.putExtra("id", sbn.getId());
        intent.putExtra("icon", sbn.getNotification().getSmallIcon());
        intent.putExtra("title", sbn.getNotification().extras.getString("android.title"));
        intent.putExtra("desc", sbn.getNotification().extras.getCharSequence("android.text"));
        intent.putExtra("pkg", sbn.getPackageName());
        intent.putExtra("time", sbn.getPostTime());
        sendBroadcast(intent);
    }
}