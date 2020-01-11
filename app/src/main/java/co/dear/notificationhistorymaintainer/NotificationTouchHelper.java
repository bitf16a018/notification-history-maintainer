package co.dear.notificationhistorymaintainer;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationTouchHelper extends ItemTouchHelper.SimpleCallback {
    private NotificationTouchHelperListener listener;

    public NotificationTouchHelper(int dragDirs, int swipeDirs, NotificationTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (listener != null) {
            listener.onSwipe(viewHolder, direction, viewHolder.getAdapterPosition());
        }
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        View foregroundView = ((NotificationAdapter.NotificationViewHolder) viewHolder).notificationContainer;
        getDefaultUIUtil().clearView(foregroundView);

    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }
    
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
//        super.onSelectedChanged(viewHolder, actionState);
        if (viewHolder != null) {
            View foregroundView = ((NotificationAdapter.NotificationViewHolder) viewHolder).notificationContainer;
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View foregroundView = ((NotificationAdapter.NotificationViewHolder) viewHolder).notificationContainer;
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View foregroundView = ((NotificationAdapter.NotificationViewHolder) viewHolder).notificationContainer;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
    }
}
