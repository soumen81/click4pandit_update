package com.autumntechcreation.click4panditcustomer.ui.forgetpassword;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityForgetpasswordBinding;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ForgetPasswordActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityForgetpasswordBinding mActivityForgetpasswordBinding;
    ForgetPasswordViewModel mForgetPasswordViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityForgetpasswordBinding= DataBindingUtil.setContentView(this, R.layout.activity_forgetpassword);
        AndroidInjection.inject(this);
        mForgetPasswordViewModel = ViewModelProviders.of(this,viewModelFactory).get(ForgetPasswordViewModel.class);
        mActivityForgetpasswordBinding.setLifecycleOwner(this);
        mActivityForgetpasswordBinding.setForgetPasswordViewModel(mForgetPasswordViewModel);

    }
}