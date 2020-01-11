package co.dear.notificationhistorymaintainer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotificationTouchHelperListener {
    public static final String INTENT_FILTER = "just.a.random.dot.separated.string.that.doesnt.make.any.sense";
    public static final String ENABLED_NOTIFICATION_ACCESS_IDENTIFIER = "enabled_notification_listeners";
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    private static final String TAG = MainActivity.class.getName();
    RecyclerView recyclerView;
    NotificationAdapter adapter;
    List<Notification> notifications = new ArrayList<>();
    NotificationReceiver receiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotificationAdapter(this, notifications);
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
        ItemTouchHelper.SimpleCallback callback = new NotificationTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(callback).attachToRecyclerView(recyclerView);
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

    @Override
    public void onSwipe(RecyclerView.ViewHolder holder, int direction, int position) {
        if (holder instanceof NotificationAdapter.NotificationViewHolder) {
            final Notification deletedNotification = notifications.get(holder.getAdapterPosition());
            final int deleteIndex = holder.getAdapterPosition();
            adapter.removeItem(deleteIndex);
            Snackbar snackbar = Snackbar.make(recyclerView, "notification deleted", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.restoreItem(deletedNotification, deleteIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    //(Icon) intent.getParcelableExtra("icon"),
    private class NotificationReceiver extends BroadcastReceiver {

        public final String TAG = NotificationReceiver.class.getName();

        @Override
        public void onReceive(Context context, Intent intent) {
//            int alreadyExistingIndex = -1;
            int id = intent.getIntExtra("id", 1);
//            for (Notification n : notifications) {
//                if (id == n.getId()) {
//                    alreadyExistingIndex = notifications.indexOf(n);
//                }
//            }
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("desc");
            String packageName = intent.getStringExtra("pkg");
            long time = intent.getLongExtra("time", -1);

            long yourmilliseconds = intent.getLongExtra("time", 1);
//            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
//            Date resultdate = new Date(yourmilliseconds);
//            Log.wtf("time", sdf.format(resultdate));
//            String time = sdf.format(resultdate);


//            if (alreadyExistingIndex != -1) {
//                notifications.remove(alreadyExistingIndex);
//            }

            Notification notification = new Notification(id, title, description, String.valueOf(time), packageName, 0);

            //Saving the notification details in database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Notification Details");
            Log.wtf(TAG, "onReceive: " + packageName);
            if (intent.getStringExtra("pkg").equals("com.google.android.apps.messaging"))
                myRef.child("Message").push().setValue(notification);

            if (intent.getStringExtra("pkg").equals("com.whatsapp"))
                myRef.child("Whatsapp").push().setValue(notification);

            if (intent.getStringExtra("pkg").equals("com.facebook.katana"))
                myRef.child("Facebook").push().setValue(notification);

            if (intent.getStringExtra("pkg").equals("com.instagram.android"))
                myRef.child("Instagram").push().setValue(notification);

            notifications.add(0, notification);
            adapter.notifyItemInserted(0);
            recyclerView.scrollToPosition(0);
        }
    }
}