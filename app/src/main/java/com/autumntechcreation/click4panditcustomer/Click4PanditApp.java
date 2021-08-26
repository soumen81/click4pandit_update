package com.autumntechcreation.click4panditcustomer;

import android.app.Activity;
import android.app.Application;


import androidx.fragment.app.Fragment;

import com.autumntechcreation.click4panditcustomer.di.AppInjector;
import com.autumntechcreation.click4panditcustomer.network.ConnectivityReceiver;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import timber.log.Timber;

public class Click4PanditApp extends Application implements HasActivityInjector, HasSupportFragmentInjector {
    private static Click4PanditApp mInstance;
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingFragmentAndroidInjector;
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        AppInjector.init(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public static synchronized Click4PanditApp getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingFragmentAndroidInjector;
    }
}
