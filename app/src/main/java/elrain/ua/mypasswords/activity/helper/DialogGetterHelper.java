package elrain.ua.mypasswords.activity.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import elrain.ua.mypasswords.R;

/**
 * Created by Denis on 11/9/2014.
 */
public final class DialogGetterHelper {

    private static final DialogInterface.OnClickListener CLOSE_LISTENER = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };

    public static AlertDialog getFirstUserDialog(Activity context, DialogInterface.OnClickListener addListener, View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setTitle(R.string.dialog_title_create_user);
        builder.setPositiveButton(context.getString(R.string.dialog_btn_create_user_text), addListener);
        builder.setNegativeButton(context.getString(R.string.dialog_btn_cancel_text), CLOSE_LISTENER);
        return builder.create();
    }
}
