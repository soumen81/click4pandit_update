package com.autumntechcreation.click4panditcustomer.ui.register;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityRegisterBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class RegisterActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    ActivityRegisterBinding mActivityRegisterBinding;
    RegisterViewModel mRegisterViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        AndroidInjection.inject(this);
        mRegisterViewModel = ViewModelProviders.of(this,viewModelFactory).get(RegisterViewModel.class);
        mActivityRegisterBinding.setLifecycleOwner(this);
        mActivityRegisterBinding.setRegisterViewModel(mRegisterViewModel);

    }
}
