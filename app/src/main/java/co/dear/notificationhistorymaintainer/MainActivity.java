package co.dear.notificationhistorymaintainer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    //(Icon) intent.getParcelableExtra("icon"),
    private class NotificationReceiver extends BroadcastReceiver {

        public final String TAG = NotificationReceiver.class.getName();

        @Override
        public void onReceive(Context context, Intent intent) {

            int id = intent.getIntExtra("id", 1);
            if (notifications.contains(id)) {
                return;
            }
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("desc");
            String packageName = intent.getStringExtra("pkg");

            long yourmilliseconds = intent.getLongExtra("time", 1);
//            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
//            Date resultdate = new Date(yourmilliseconds);
//            Log.wtf("time", sdf.format(resultdate));
//            String time = sdf.format(resultdate);


            NotificationModel notificationModel = new NotificationModel(id, title, description, packageName, String.valueOf(yourmilliseconds));

            //Saving the notification details in database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Notification Details");
            Log.wtf(TAG, "onReceive: " + packageName);
            if (intent.getStringExtra("pkg").equals("com.google.android.apps.messaging"))
                myRef.child("Message").push().setValue(notificationModel);

            if (intent.getStringExtra("pkg").equals("com.whatsapp"))
                myRef.child("Whatsapp").push().setValue(notificationModel);

            if (intent.getStringExtra("pkg").equals("com.facebook.katana"))
                myRef.child("Facebook").push().setValue(notificationModel);

            if (intent.getStringExtra("pkg").equals("com.instagram.android"))
                myRef.child("Instagram").push().setValue(notificationModel);

            notifications.add(notificationModel);
            adapter.notifyDataSetChanged();


        }
    }
}