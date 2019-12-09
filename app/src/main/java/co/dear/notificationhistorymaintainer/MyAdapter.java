package co.dear.notificationhistorymaintainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<NotificationModel> notifications;

    public MyAdapter(Context context, List<NotificationModel> models) {
        this.context = context;
        this.notifications = models;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.notificationTitleTextView.setText(notifications.get(position).getTitle());
        holder.notificationDescriptionTextView.setText(notifications.get(position).getDescription());
        holder.appIconImageView.setImageIcon(notifications.get(position).getSmallIcon());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
}
