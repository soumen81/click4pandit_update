package com.autumntechcreation.click4panditcustomer.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.autumntechcreation.click4panditcustomer.Click4PanditApp;
import com.autumntechcreation.click4panditcustomer.di.component.DaggerAppComponent;


import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;


public class AppInjector {
    private AppInjector() {}

    public static void init(Click4PanditApp click4PanditApp) {
        DaggerAppComponent.builder().application(click4PanditApp)
                .build().inject(click4PanditApp);

        click4PanditApp
                .registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                        handleActivity(activity);
                    }

                    @Override
                    public void onActivityStarted(Activity activity) {

                    }

                    @Override
                    public void onActivityResumed(Activity activity) {

                    }

                    @Override
                    public void onActivityPaused(Activity activity) {

                    }

                    @Override
                    public void onActivityStopped(Activity activity) {

                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {

                    }

                    // Other methods are omitted for simplification …
                });
    }

    private static void handleActivity(Activity activity) {
        if (activity instanceof HasSupportFragmentInjector) {
            AndroidInjection.inject(activity);
        }
        if (activity instanceof FragmentActivity) {
            ((FragmentActivity) activity).getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(
                            new FragmentManager.FragmentLifecycleCallbacks() {
                                @Override
                                public void onFragmentCreated(FragmentManager fm, Fragment fragment,
                                                              Bundle savedInstanceState) {
                                    if (fragment instanceof Injectable) {
                                        AndroidSupportInjection.inject(fragment);
                                    }
                                }
                            }, true);
        }
    }
}
