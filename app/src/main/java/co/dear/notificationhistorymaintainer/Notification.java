package co.dear.notificationhistorymaintainer;

public class Notification {
    int id;
    String title, description, time, packageName;
    int icon;

    public Notification() {
    }

    public Notification(int id, String title, String description, String time, String packageName, int icon) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
        this.packageName = packageName;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
