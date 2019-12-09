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
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    RecyclerView recyclerView;
    MyAdapter adapter;
    List<NotificationModel> notifications = new ArrayList<>();
    NotificationReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, notifications);
        recyclerView.setAdapter(adapter);

        AlertDialogUtils.buildNotificationServiceAlertDialog(
                this,
                ACTION_NOTIFICATION_LISTENER_SETTINGS,
                "Notification Listener Service",
                "For the the app. to work you need to enable the Notification Listener Service. Enable it now?",
                "Yes",
                "No").show();
        receiver = new NotificationReceiver();
        registerReceiver(receiver, new IntentFilter(UPDATE_UI));
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