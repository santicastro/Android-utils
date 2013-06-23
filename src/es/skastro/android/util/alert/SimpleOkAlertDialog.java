package es.skastro.android.util.alert;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SimpleOkAlertDialog extends AlertDialog {

    public static void show(Context context, String title, String message) {
        (new SimpleOkAlertDialog(context, title, message, defaultOkListener())).show();
    }

    public static void show(Context context, String title, String message, int iconResourceId) {
        SimpleOkAlertDialog s = new SimpleOkAlertDialog(context, title, message, defaultOkListener());
        s.setIcon(iconResourceId);
        s.show();
    }

    public static DialogInterface.OnClickListener defaultOkListener() {
        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        };
    }

    public SimpleOkAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener okListener) {
        super(context);
        setMessage(message);
        setTitle(title);
        setCancelable(false);
        if (okListener == null)
            setButton(BUTTON_POSITIVE, "Aceptar", defaultOkListener());
        else
            setButton(BUTTON_POSITIVE, "Aceptar", okListener);
    }

    public SimpleOkAlertDialog(Context context, String title, String message, int iconId,
            DialogInterface.OnClickListener okListener) {
        this(context, title, message, okListener);
        setIcon(iconId);
    }
}
