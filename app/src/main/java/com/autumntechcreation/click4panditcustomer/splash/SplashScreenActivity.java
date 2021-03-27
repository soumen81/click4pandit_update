package com.autumntechcreation.click4panditcustomer.splash;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivitySplashBinding;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginViewModel;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SplashScreenActivity extends AppCompatActivity {


    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    SplashViewModel mSplashViewModel;
    ActivitySplashBinding mActivitySplashBinding;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        AndroidInjection.inject(this);
        mSplashViewModel = ViewModelProviders.of(this,viewModelFactory).get(SplashViewModel.class);
        mActivitySplashBinding.setLifecycleOwner(this);
        mActivitySplashBinding.setSplashViewModel(mSplashViewModel);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreenActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        },6000);
    }
}
