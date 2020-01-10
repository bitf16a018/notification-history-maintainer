package co.dear.notificationhistorymaintainer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AnimationActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NotificationAdapter adapter;
    List<Notification> notifications = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        recyclerView = findViewById(R.id.recycler_view);


        for (int i = 0; i < 10; i++) {
            notifications.add(new Notification(
                    1,
                    "facebook",
                    "Somebody mentioned you in a comment.",
                    String.valueOf(System.currentTimeMillis()),
                    "com.facebook", R.drawable.facebook
            ));
            notifications.add(new Notification(
                    1,
                    "whatsapp",
                    "You may have new messages.",
                    String.valueOf(System.currentTimeMillis()),
                    "com.whatsapp", R.drawable.whatsapp
            ));
            notifications.add(new Notification(
                    1,
                    "instagram",
                    "somebody added to their story. clock to checkout",
                    String.valueOf(System.currentTimeMillis()),
                    "com.facebook", R.drawable.instagram
            ));
        }


        adapter = new NotificationAdapter(this, notifications);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
