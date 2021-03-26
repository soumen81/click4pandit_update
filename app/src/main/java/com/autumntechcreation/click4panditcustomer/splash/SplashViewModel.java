package com.autumntechcreation.click4panditcustomer.splash;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

public class SplashViewModel extends ViewModel {
    private SplashRepository mSplashRepository;


    @Inject
    public SplashViewModel(SplashRepository splashRepository){
        this.mSplashRepository = splashRepository;
    }
}
