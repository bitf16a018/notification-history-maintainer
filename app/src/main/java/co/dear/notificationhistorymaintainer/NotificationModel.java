package co.dear.notificationhistorymaintainer;

import android.graphics.drawable.Icon;

public class NotificationModel {
    private String time;
    private int id;
    private Icon smallIcon;
    private Icon largeIcon;
    private String title, description, packageName;

    NotificationModel() {
    }

    NotificationModel(int id, String title, String description, String packageName, String time) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.packageName = packageName;
        this.time = time;
    }

    Icon getLargeIcon() {
        return largeIcon;
    }

    void setLargeIcon(Icon largeIcon) {
        this.largeIcon = largeIcon;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    Icon getSmallIcon() {
        return smallIcon;
    }

    void setSmallIcon(Icon smallIcon) {
        this.smallIcon = smallIcon;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    String getPackageName() {
        return packageName;
    }

    void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
