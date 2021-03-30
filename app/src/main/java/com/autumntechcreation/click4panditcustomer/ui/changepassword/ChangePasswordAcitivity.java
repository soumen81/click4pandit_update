package com.autumntechcreation.click4panditcustomer.ui.changepassword;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityChangepasswordBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ChangePasswordAcitivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityChangepasswordBinding mActivityChangepasswordBinding;
    ChangePasswordViewModel mChangePasswordViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityChangepasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        AndroidInjection.inject(this);
        mChangePasswordViewModel = ViewModelProviders.of(this,viewModelFactory).get(ChangePasswordViewModel.class);
        mActivityChangepasswordBinding.setLifecycleOwner(this);
        mActivityChangepasswordBinding.setChangePasswordViewModel(mChangePasswordViewModel);

    }
}
