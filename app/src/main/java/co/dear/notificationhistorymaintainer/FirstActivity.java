package co.dear.notificationhistorymaintainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        findViewById(R.id.whatsapp_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, WhatsAppNotificationsActivity.class));
            }
        });
        findViewById(R.id.instagram_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, InstagramNotificationActivity.class));
            }
        });
        findViewById(R.id.facebook_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, FacebookNotificationActivity.class));
            }
        });
        findViewById(R.id.messages_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, MessagesNotificationsActivity.class));
            }
        });
        findViewById(R.id.help_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, HelpActivity.class));
            }
        });
        findViewById(R.id.settings_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, SettingsActivity.class));
            }
        });
    }
}
