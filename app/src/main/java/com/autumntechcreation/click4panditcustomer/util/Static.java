package com.autumntechcreation.click4panditcustomer.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.autumntechcreation.click4panditcustomer.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class Static {

    public static final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
    public static final String PHONE_REGEX = "^[0-9]{10}$";
    public static long exitTime;
    public static Activity activity;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static String getIndianRupee(String value) {
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        return format.format(new BigDecimal(value));
    }
    public static boolean validMobileNo(String number)
    {
        return android.util.Patterns.PHONE.matcher(number).matches();
    }


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

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static String[] getYearMonthDate(String inputDate) {
        return inputDate.split("-");
    }
    public static String curentDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

    }
    public static long dateToTimestamp(String input) {
        //  String str_date=mCalender+"-"+day+"-"+yr;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long output = date.getTime() / 1000L;
        String str = Long.toString(output);
        return Long.parseLong(str) * 1000;
    }
    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
    public static String convertToDate(String strDate) {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formatter2= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");//2020-08-19T18:30:00.000Z
        Date date = null;
        try {
            date = formatter1.parse(strDate);
        } catch (ParseException e) {
            try{
                date = formatter2.parse(strDate);
            }catch (ParseException e2){
                e2.printStackTrace();
            }
            e.printStackTrace();
        }
        SimpleDateFormat formatter3 = new SimpleDateFormat("dd/MM/yyyy");
        strDate = formatter3.format(date);
        System.out.println("Date Format with MM/dd/yyyy : " + strDate);
        return strDate;
    }

    public static String convertNewDate(String strDate) {
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatter1.parse(strDate);
        } catch (ParseException e) {


            e.printStackTrace();
        }
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
        strDate = formatter3.format(date);
        System.out.println("Date Format with MM/dd/yyyy : " + strDate);
        return strDate;
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
    public byte[] convertBitmaptoByte(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return byteArray;
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public static String convertPickDate(String strDate) {
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss");
        Date date = null;
        try {
            date = formatter1.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        strDate = formatter2.format(date);
        System.out.println("Date Format with MM/dd/yyyy : " + strDate);
        return strDate;
    }
    public static String newConvertTwentyfourHourToDate(String input) {
        // Format of the date defined in the input String
        DateFormat dateFormat
                = new SimpleDateFormat("hh:mm aa");

        // Change the pattern into 24 hour formate
        DateFormat formate
                = new SimpleDateFormat("HH:mm:ss");
        Date time = null;
        String output = "";

        // Converting the input String to Date
        try {
            time = dateFormat.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Changing the format of date
        // and storing it in
        // String
        output = formate.format(time);
        return output;
    }

    public static String getMimeType(Uri uri) {
        String mimeType = null;
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
            ContentResolver cr = activity.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }
}
