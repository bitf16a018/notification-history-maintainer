package co.dear.notificationhistorymaintainer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    RecyclerView recyclerView;
    MyAdapter adapter;
    List<NotificationModel> notifications = new ArrayList<>();

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
    }
}