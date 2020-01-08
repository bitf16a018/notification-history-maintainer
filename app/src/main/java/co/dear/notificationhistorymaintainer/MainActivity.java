package co.dear.notificationhistorymaintainer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String INTENT_FILTER = "just.a.random.dot.separated.string.that.doesnt.make.any.sense";
    public static final String ENABLED_NOTIFICATION_ACCESS_IDENTIFIER = "enabled_notification_listeners";
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    RecyclerView recyclerView;
    MyAdapter adapter;
    List<NotificationModel> notifications = new ArrayList<>();
    NotificationReceiver receiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(notifications);
        recyclerView.setAdapter(adapter);

        if (!AlertDialogUtils.isNotificationListeningServiceEnabled(MainActivity.this)) {
            AlertDialogUtils.buildNotificationServiceAlertDialog(
                    this,
                    ACTION_NOTIFICATION_LISTENER_SETTINGS,
                    getString(R.string.notification_access_dialog_title),
                    getString(R.string.notification_access_dialog_message),
                    getString(R.string.notification_access_dialog_positive_option),
                    getString(R.string.notification_access_dialog_negative_option)).show();
        }

        receiver = new NotificationReceiver();
        registerReceiver(receiver, new IntentFilter(INTENT_FILTER));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!AlertDialogUtils.isNotificationListeningServiceEnabled(MainActivity.this)) {
            AlertDialogUtils.buildNotificationServiceAlertDialog(
                    this,
                    ACTION_NOTIFICATION_LISTENER_SETTINGS,
                    getString(R.string.notification_access_dialog_title),
                    getString(R.string.notification_access_dialog_message),
                    getString(R.string.notification_access_dialog_positive_option),
                    getString(R.string.notification_access_dialog_negative_option)).show();
        }

        registerReceiver(receiver, new IntentFilter(INTENT_FILTER));
    }


    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    private class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            notifications.add(new NotificationModel(
                    intent.getIntExtra("id", 69),
                    (Icon) intent.getParcelableExtra("icon"),
                    intent.getStringExtra("title"),
                    intent.getStringExtra("desc"),
                    intent.getStringExtra("pkg")));
            adapter.notifyDataSetChanged();
        }
    }
}