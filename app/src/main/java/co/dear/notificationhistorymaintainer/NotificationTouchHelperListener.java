package co.dear.notificationhistorymaintainer;

import androidx.recyclerview.widget.RecyclerView;

interface NotificationTouchHelperListener {
    void onSwipe(RecyclerView.ViewHolder holder, int direction, int position);
}
