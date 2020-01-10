package co.dear.notificationhistorymaintainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private Context context;
    private List<Notification> notifications;

    NotificationAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.iconImageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
        holder.notificationContainer.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));
        holder.titleTextView.setText(notifications.get(position).getTitle());
        holder.descriptionTextView.setText(notifications.get(position).getDescription());
        holder.timeTextView.setText(notifications.get(position).getTime());
        holder.iconImageView.setImageResource(notifications.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout notificationContainer;

        TextView titleTextView, descriptionTextView, timeTextView;
        ImageView iconImageView;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationContainer = itemView.findViewById(R.id.notification_container);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            descriptionTextView = itemView.findViewById(R.id.description_text_view);
            timeTextView = itemView.findViewById(R.id.time_text_view);
            iconImageView = itemView.findViewById(R.id.icon_image_view);
        }
    }
}
