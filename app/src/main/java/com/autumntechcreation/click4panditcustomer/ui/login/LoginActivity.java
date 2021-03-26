package com.autumntechcreation.click4panditcustomer.ui.login;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityLoginBinding;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class LoginActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityLoginBinding mActivityLoginBinding;
    LoginViewModel mLoginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        AndroidInjection.inject(this);
        mLoginViewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel.class);
        mActivityLoginBinding.setLifecycleOwner(this);
        mActivityLoginBinding.setLoginViewModel(mLoginViewModel);
    }
}
