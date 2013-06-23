package es.skastro.android.util.alert;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.InputFilter;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

/**
 * helper for Prompt-Dialog creation
 */
public abstract class StringPrompt extends AlertDialog.Builder implements OnClickListener {
    private final EditText input;

    /**
     * @param context
     * @param title
     * @param message
     * @param default_value
     */
    public StringPrompt(Context context, String title, String message, String default_value) {
        super(context);
        setTitle(title);
        setMessage(message);

        input = new EditText(context);
        input.setImeOptions(EditorInfo.IME_ACTION_GO | EditorInfo.IME_FLAG_NO_ENTER_ACTION);
        input.setText(default_value == null ? "" : default_value);
        input.selectAll();
        setView(input);
        setPositiveButton("Aceptar", this);
        setNegativeButton("Cancelar", this);

        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return (actionId == EditorInfo.IME_ACTION_GO);
            }
        });
    }

    public void setInputType(int extraInputType) {
        int type = InputType.TYPE_CLASS_TEXT | extraInputType;
        input.setInputType(type);
    }

    public void setMaxLength(int maxLength) {
        input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxLength) });
    }

    /**
     * will be called when "cancel" pressed. closes the dialog. can be overridden.
     * 
     * @param dialog
     */
    public void cancelClicked(DialogInterface dialog) {
        dialog.dismiss();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            if (onOkClicked(input.getText().toString())) {
                dialog.dismiss();
            }
        } else {
            cancelClicked(dialog);
            onCancelClicked();
        }
    }

    /**
     * called when "ok" pressed.
     * 
     * @param input
     * @return true, if the dialog should be closed. false, if not.
     */
    abstract public boolean onOkClicked(String value);

    public void onCancelClicked() {
    }
}
