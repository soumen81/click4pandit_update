package com.autumntechcreation.click4panditcustomer.util;

import android.app.Activity;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.autumntechcreation.click4panditcustomer.R;

import java.util.regex.Pattern;

public class Static {

    public static final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
    public static final String PHONE_REGEX = "^[0-9]{10}$";
    public static long exitTime;
    public static Activity activity;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);






    public static boolean replaceFragment(final int fragmentContainerResourceId, final FragmentManager fragmentManager, final Fragment nextFragment, final boolean requiredAnimation, final boolean commitAllowingStateLoss) throws IllegalStateException {
        if (nextFragment == null || fragmentManager == null) {
            return false;
        }
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (requiredAnimation) {
            FragmentAnimation.setDefaultFragmentAnimation(fragmentTransaction);
        }
        fragmentTransaction.replace(fragmentContainerResourceId, nextFragment, nextFragment.getClass().getSimpleName());

        if (!commitAllowingStateLoss) {
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.commitAllowingStateLoss();
        }

        return true;
    }

    public static void doExitApp(Activity activity) {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(activity, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            activity.finish();
        }
    }
}
