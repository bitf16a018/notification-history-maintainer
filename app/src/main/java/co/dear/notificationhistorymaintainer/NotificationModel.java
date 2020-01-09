package co.dear.notificationhistorymaintainer;

import android.graphics.drawable.Icon;

class NotificationModel {
    private int id;
    private Icon smallIcon;
    private Icon largeIcon;
    private String title, description, packageName;

    NotificationModel() {
    }

    NotificationModel(int id, Icon smallIcon, Icon largeIcon, String title, String description, String packageName) {
        this.id = id;
        this.smallIcon = smallIcon;
        this.title = title;
        this.description = description;
        this.packageName = packageName;
        this.largeIcon = largeIcon;
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
}
