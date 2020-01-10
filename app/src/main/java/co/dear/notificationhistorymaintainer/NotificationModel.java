package co.dear.notificationhistorymaintainer;

import android.graphics.drawable.Icon;

public class NotificationModel {
    private String time;
    private int id;
    private Icon smallIcon;
    private String title, description, packageName;

    public NotificationModel() {
    }

    NotificationModel(int id, String title, String description, String packageName, String time) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.packageName = packageName;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Icon getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(Icon smallIcon) {
        this.smallIcon = smallIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
