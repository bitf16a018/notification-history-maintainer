package co.dear.notificationhistorymaintainer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView notificationId;
    ImageView appLargeIconImageView, appSmallIconImageView;
    TextView notificationTitleTextView, notificationDescriptionTextView;

    MyViewHolder(@NonNull View itemView) {
        super(itemView);
        this.notificationId = itemView.findViewById(R.id.notification_id);
        this.appSmallIconImageView = itemView.findViewById(R.id.app_small_icon_image_view);
        this.appLargeIconImageView = itemView.findViewById(R.id.app_large_icon_image_view);
        this.notificationTitleTextView = itemView.findViewById(R.id.notification_title_text_view);
        this.notificationDescriptionTextView = itemView.findViewById(R.id.notification_description_text_view);
    }
}
