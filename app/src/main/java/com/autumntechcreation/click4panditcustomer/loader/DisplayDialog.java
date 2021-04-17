package com.autumntechcreation.click4panditcustomer.loader;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import com.autumntechcreation.click4panditcustomer.R;


/**
 * Purpose of this Class is to display different dialog in application for example : Progress Dialog, Information Dialog
 */
public class DisplayDialog {

    private static com.autumntechcreation.click4panditcustomer.loader.DisplayDialog ourInstance = new com.autumntechcreation.click4panditcustomer.loader.DisplayDialog();

    private AlertDialog mAlertDialog;


    /**
     * A Progress Dialog object
     */
    private Dialog mProgressDialog;

    private DisplayDialog() {
    }

    public static com.autumntechcreation.click4panditcustomer.loader.DisplayDialog getInstance() {
        return ourInstance;
    }


    public AlertDialog showAlertDialog(final Context context, String message) {
        if (mAlertDialog == null) {
            mAlertDialog = new com.autumntechcreation.click4panditcustomer.loader.SpotsDialog(context, R.style.Custom);
            mAlertDialog.setMessage(message);
            mAlertDialog.setCanceledOnTouchOutside(false);
            mAlertDialog.getWindow().setBackgroundDrawableResource(R.color.lightgrey);
            mAlertDialog.show();
        }
        return mAlertDialog;
    }

    public void dismissAlertDialog() {
        try {
            if (mAlertDialog != null && mAlertDialog.isShowing()) {
                mAlertDialog.dismiss();
                mAlertDialog = null;
            }
        } catch (IllegalArgumentException e) {

        }

    }


}
