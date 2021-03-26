package com.autumntechcreation.click4panditcustomer.di.module;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.splash.SplashScreenActivity;
import com.autumntechcreation.click4panditcustomer.ui.dashboard.DashBoardActivity;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract SplashScreenActivity contributeSplashScreenActivity();

    @ContributesAndroidInjector
    abstract RegisterActivity contributeRegisterActivity();

    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivity();
    @ContributesAndroidInjector
    abstract DashBoardActivity contributeDashBoardActivity();
}
