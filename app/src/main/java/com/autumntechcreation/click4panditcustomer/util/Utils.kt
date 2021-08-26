package com.autumntechcreation.click4panditcustomer.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.MimeTypeMap
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.autumntechcreation.click4panditcustomer.R
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class Utils {
    companion object {
        val IPV4_REGEX =
            "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))"
        val PHONE_REGEX = "^[0-9]{10}$"

        //        TimeZone tz = TimeZone.getTimeZone("UTC");
        //        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        //        df.setTimeZone(tz);
        //        String nowAsISO = df.format(new Date());
        //        Log.e("nowAsISO", nowAsISO );
        //        Date c = Calendar.getInstance().getTime();
        //        System.out.println("Current time => " + c);
        //
        //        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //        String formattedDate = df.format(c);
        //        return formattedDate;
        val currentDate: String
            get() {

                val mydate = Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                val yestr = dateFormat.format(mydate)
                return yestr
            }

        fun getMimeType(url: String): String? {
            val extension = url.substring(url.lastIndexOf(".") + 1)
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }

        fun isBigFile(file: File): Boolean {
            // Get length of file in bytes
            val fileSizeInBytes = file.length()
            // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
            val fileSizeInKB = fileSizeInBytes / 1024
            // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
            val fileSizeInMB = fileSizeInKB / 1024

            return fileSizeInMB > 32
        }


        fun formatStringDate(olDFormat: String, newFormat: String, strDate: String): String {
            var date: Date? = null
            try {
                date = SimpleDateFormat(olDFormat, Locale.ENGLISH).parse(strDate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return SimpleDateFormat(newFormat, Locale.ENGLISH).format(date)
        }

        /**
         * @param context
         * @param view
         * @param msg     message to show in snackbar
         */
        fun showSnackBar(context: Context, view: View, msg: String) {
            val snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
            val group = snackbar.view
            group.setBackgroundColor(ContextCompat.getColor(context, R.color.redColor))
            snackbar.show()
        }

        /**
         * @param context
         * @param view
         * @param msg     message to show in snackbar
         */
        fun showSnackBarInternet(context: Context, view: View, msg: String) {
            val snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
            val group = snackbar.view
            group.setBackgroundColor(ContextCompat.getColor(context, R.color.blackColor))
            snackbar.show()


            //        snackbar.setAction("Dismiss", new View.OnClickListener() {
            //            @Override
            //            public void onClick(View v) {
            //                snackbar.dismiss();
            //            }
            //        });

        }

        /**
         * Hide the soft keyboard from screen for edit text only
         *
         * @param context context of current visible activity
         * @param view    clicked view
         */
        fun hideSoftKeyBoard(context: Context, view: View) {
            try {
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            } catch (e: Exception) {
                // TODO: handle exception
                e.printStackTrace()
            }

        }

        fun isEnterpriseNameValid(enterPrizeName: String?): Boolean {
            return if (enterPrizeName == null) false else enterPrizeName.length > 0
        }

        fun isDatabaseNameValid(databaseName: String?): Boolean {
            return if (databaseName == null) false else databaseName.length > 0
        }

        fun isValidIPV4(appUrl: String?): Boolean {
            if (appUrl == null)
                return false
            val IPV4_PATTERN = Pattern.compile(IPV4_REGEX)
            return IPV4_PATTERN.matcher(appUrl).matches()
        }

        fun isValidIPhone(mobileNumber: String?): Boolean {
            if (mobileNumber == null)
                return false
            val PHONE_PATTERN = Pattern.compile(PHONE_REGEX)
            return PHONE_PATTERN.matcher(mobileNumber).matches()
        }

        fun isValidEmail(email: String?): Boolean {
            if (email == null)
                return false
            if (!TextUtils.isEmpty(email))
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    return true
                }
            return false
        }


        fun isValidUserName(username: String?): Boolean {
            return if (username == null) false else username.length > 0
        }

        fun isValidPassword(password: String?): Boolean {
            return if (password == null) false else password.length > 0
        }

        fun isPasswordMatches(password: String, newPassword: String): Boolean {
            return password == newPassword
        }


        fun getFileChooserIntent(contentType: String): Intent {
            val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
            val openGalleryIntent: Intent

            openGalleryIntent =
                Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            if (isKitKat) {
                openGalleryIntent.addCategory(Intent.CATEGORY_OPENABLE)
            }
            openGalleryIntent.type = contentType
            openGalleryIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            return openGalleryIntent
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }


        var CACHE_DIRECTORY: String? = null

        var imageFilePath: String? = null
        fun createPhotoFile(mActivity: Activity): File {
//        val file = File(CACHE_DIRECTORY)
//        if (file.exists() && file.listFiles() != null) {
//            for (f in file.listFiles()!!) {
//                f.delete()
//            }
//        }
//
//        return File(CACHE_DIRECTORY + mActivity.getString(R.string.app_name) + System.currentTimeMillis() + ".png")

            val timeStamp = SimpleDateFormat(
                "yyyyMMdd_HHmmss",
                Locale.getDefault()
            ).format(Date())
            val imageFileName = "IMG_" + timeStamp + "_"
            val storageDir = mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
            )

            imageFilePath = image.absolutePath
            return image

        }

        fun getUri(mContext: Context?, mFile: File): Uri {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (mContext != null) {
                    FileProvider.getUriForFile(mContext, mContext.applicationContext.packageName + ".fileprovider", mFile)
                } else {
                    Uri.fromFile(mFile)
                }
            } else {
                Uri.fromFile(mFile)
            }
        }
    }


    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }




}