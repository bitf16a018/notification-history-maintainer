package co.dear.notificationhistorymaintainer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AnimationActivity extends AppCompatActivity implements NotificationTouchHelperListener {
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
                    "Facebook",
                    "Somebody mentioned you in a comment.",
                    String.valueOf(System.currentTimeMillis()),
                    "com.facebook", R.drawable.facebook
            ));
            notifications.add(new Notification(
                    1,
                    "WhatsApp",
                    "You may have new messages.",
                    String.valueOf(System.currentTimeMillis()),
                    "com.whatsapp", R.drawable.whatsapp
            ));
            notifications.add(new Notification(
                    1,
                    "Instagram",
                    "lorem ispum. dhur futay mu. na parh. tu ay parh k li lena ay. khach jai na hove taa. ja kam kr apna. hor koi kam ni tenu? ajay v parh reya ay. tera kush ni ho skda.",
                    String.valueOf(System.currentTimeMillis()),
                    "com.facebook", R.drawable.instagram
            ));
        }


        adapter = new NotificationAdapter(this, notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback callback = new NotificationTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(callback).attachToRecyclerView(recyclerView);
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
}