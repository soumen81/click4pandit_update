package com.autumntechcreation.click4panditcustomer.splash;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivitySplashBinding;
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper;
import com.autumntechcreation.click4panditcustomer.ui.dashboard.DashBoardActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SplashScreenActivity extends AppCompatActivity {


    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    SplashViewModel mSplashViewModel;
    ActivitySplashBinding mActivitySplashBinding;
    Handler handler;
    SharedPreferences sp;

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
                sp = getSharedPreferences("login",MODE_PRIVATE);
                if(sp.getBoolean("logged",false)){
                    Intent i = new Intent(SplashScreenActivity.this,MainActivity.class);
                    startActivity(i);
                }else {

                    Intent intent = new Intent(SplashScreenActivity.this, DashBoardActivity.class);
                    startActivity(intent);
                    finish();
                    sp.edit().putBoolean("logged",true).apply();
                }
            }
        },6000);
    }
}
