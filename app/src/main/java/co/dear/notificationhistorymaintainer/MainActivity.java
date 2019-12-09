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
    public static final String UPDATE_UI = "co.dear.notificationhistorymaintainer.UPDATE_UI";
    public static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
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
        adapter = new MyAdapter(this, notifications);
        recyclerView.setAdapter(adapter);

        if (!AlertDialogUtils.isServiceEnabled(MainActivity.this, ENABLED_NOTIFICATION_LISTENERS)) {
            AlertDialogUtils.buildNotificationServiceAlertDialog(
                    this,
                    ACTION_NOTIFICATION_LISTENER_SETTINGS,
                    getString(R.string.notification_access_dialog_title),
                    getString(R.string.notification_access_dialog_message),
                    getString(R.string.notification_access_dialog_positive_option),
                    getString(R.string.notification_access_dialog_negative_option)).show();
        }

        receiver = new NotificationReceiver();
        registerReceiver(receiver, new IntentFilter(UPDATE_UI));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!AlertDialogUtils.isServiceEnabled(MainActivity.this, ENABLED_NOTIFICATION_LISTENERS)) {
            AlertDialogUtils.buildNotificationServiceAlertDialog(
                    this,
                    ACTION_NOTIFICATION_LISTENER_SETTINGS,
                    getString(R.string.notification_access_dialog_title),
                    getString(R.string.notification_access_dialog_message),
                    getString(R.string.notification_access_dialog_positive_option),
                    getString(R.string.notification_access_dialog_negative_option)).show();
        }

        if (receiver == null) receiver = new NotificationReceiver();
        registerReceiver(receiver, new IntentFilter(UPDATE_UI));
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    private class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            notifications.add(new NotificationModel(
                    intent.getIntExtra("id", -1),
                    (Icon) intent.getParcelableExtra("icon"),
                    intent.getStringExtra("title"),
                    intent.getStringExtra("desc"),
                    intent.getStringExtra("pkg")));
            adapter.notifyDataSetChanged();
        }
    }
}