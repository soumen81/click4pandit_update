package com.autumntechcreation.click4panditcustomer.ui.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityLoginBinding;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordActivity;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;

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

        mLoginViewModel.getonClickSignUpPage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                Intent in=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(in);
            }
        });
        mLoginViewModel.getonClickForgetPasswordPage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                Intent in=new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(in);
            }
        });

        mActivityLoginBinding.tvLoginSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(in);
            }
        });
    }
}
