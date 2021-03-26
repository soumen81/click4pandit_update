package com.autumntechcreation.click4panditcustomer;

import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.autumntechcreation.click4panditcustomer.databinding.ActivityMainBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding activityMainBinding;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        AndroidInjection.inject(this);
        // Specify the current activity as the lifecycle owner.
        activityMainBinding.setLifecycleOwner(this);
    }
}
