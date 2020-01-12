package co.dear.notificationhistorymaintainer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

class AppUtils {
    static Drawable getAppIcon(Context context, String packageName) {
        Drawable icon = null;
        try {
            icon = context.getPackageManager().getApplicationIcon(packageName);
            return icon;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
