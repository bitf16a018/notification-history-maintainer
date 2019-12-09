package co.dear.notificationhistorymaintainer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialogUtils.buildNotificationServiceAlertDialog(
                this,
                ACTION_NOTIFICATION_LISTENER_SETTINGS,
                "Notification Listener Service",
                "For the the app. to work you need to enable the Notification Listener Service. Enable it now?",
                "Yes",
                "No").show();
    }
}