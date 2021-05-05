package com.autumntechcreation.click4panditcustomer.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import com.autumntechcreation.click4panditcustomer.R;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPrefsHelper {
    public static String USERNAME = "userName";
    public static String FIRSTNAME = "firstName";
    public static String LASTNAME = "lastName";
    public static String MOBILE = "mobile";
    public static String ENTERED_HOME_ACTIVITY = "ENTERED_HOME_ACTIVITY";
    public static String EMAIL = "email";

    private SharedPreferences mSharedPreferences;

    @Inject
    public SharedPrefsHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public static void getInstance(Context context){
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void put(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public void put(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public void put(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public Integer get(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public Float get(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public Boolean get(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void deleteSavedData(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    public void deleteSavedData() {
        //  mSharedPreferences.edit().remove(key).apply();
        mSharedPreferences.edit().remove(ENTERED_HOME_ACTIVITY).apply();
        mSharedPreferences.edit().remove(USERNAME).apply();
        mSharedPreferences.edit().remove(MOBILE).apply();

    }

}
