package co.dear.notificationhistorymaintainer;

import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;


public class NotificationWaliService extends NotificationListenerService {
    public static final String TAG = NotificationWaliService.class.getName();

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);

        Intent intent = new Intent(MainActivity.INTENT_FILTER);
        intent.putExtra("id", sbn.getId());
        intent.putExtra("small_icon", sbn.getNotification().getSmallIcon());
        intent.putExtra("large_icon", sbn.getNotification().getLargeIcon());
        intent.putExtra("title", sbn.getNotification().extras.getString("android.title"));
        intent.putExtra("desc", sbn.getNotification().extras.getCharSequence("android.text"));
        intent.putExtra("pkg", sbn.getPackageName());
        intent.putExtra("time", sbn.getPostTime());
//        Notification.Action[] actions = sbn.getNotification().;
        sendBroadcast(intent);
    }
}