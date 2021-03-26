package com.autumntechcreation.click4panditcustomer.sharedpref;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPrefsHelper {
    public static String PREF_KEY_ACCESS_TOKEN = "access-token";
    public static String PREF_KEY_FISCAL_YEAR_ID = "fiscal-year-id";
    public static String PREF_KEY_FISCAL_YEAR_PERIOD_BY_DATE_ID="fiscal-year-period-by-date";
    public static String PREF_KEY_FISCAL_YEAR_ID_LAST_UPDATE = "fiscal-year-id-last-updated";
    public static String USER_ID = "user-id";
    public static String TENANT_ID = "tenantId";
    public static String EMPLOYEE_ID = "emp-id";

    private SharedPreferences mSharedPreferences;

    @Inject
    public SharedPrefsHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
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
}
