package co.dear.notificationhistorymaintainer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<NotificationModel> notifications;

    MyAdapter(List<NotificationModel> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.notificationId.setText(String.valueOf(notifications.get(position).getId()));
        holder.notificationTitleTextView.setText(notifications.get(position).getTitle());
        holder.notificationDescriptionTextView.setText(notifications.get(position).getDescription());
        holder.appSmallIconImageView.setImageIcon(notifications.get(position).getSmallIcon());
        holder.appLargeIconImageView.setImageIcon(notifications.get(position).getLargeIcon());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
}
