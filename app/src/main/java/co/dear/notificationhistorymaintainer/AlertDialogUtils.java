package co.dear.notificationhistorymaintainer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

class AlertDialogUtils {
    static AlertDialog buildNotificationServiceAlertDialog(final Context context, final String action, String title, String message, @Nullable String positiveOption, @Nullable String negativeOption) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton(positiveOption,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        context.startActivity(new Intent(action));
                    }
                });
        alertDialogBuilder.setNegativeButton(negativeOption,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                });
        return (alertDialogBuilder.create());
    }
}
