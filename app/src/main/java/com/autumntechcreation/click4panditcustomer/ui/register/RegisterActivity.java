package com.autumntechcreation.click4panditcustomer.ui.register;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityRegisterBinding;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;

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



        mRegisterViewModel.getonClickLoginPage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                Intent in=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(in);
            }
        });




       /* mRegisterViewModel.getRegisterResult(mActivityRegisterBinding.edtTxtFirstName.getText().toString(),mActivityRegisterBinding.edtTxtLastName.getText().toString(),
                "",mActivityRegisterBinding.edtTxtEmail.getText().toString(),mActivityRegisterBinding.edtTxtMobileNo.getText().toString(),
                mActivityRegisterBinding.edtTxtPassword.getText().toString(),mActivityRegisterBinding.edtTxtConfirmPassword.getText().toString(),"CUST")
                .observe(this, this::handlePendingApprovalListing);*/
    }
}
