package com.autumntechcreation.click4panditcustomer.ui.dashboard;

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
import com.autumntechcreation.click4panditcustomer.databinding.ActivityDashboardBinding;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class DashBoardActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityDashboardBinding mActivityDashboardBinding;
    DashBoardViewModel mDashBoardViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mActivityDashboardBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        AndroidInjection.inject(this);
        mDashBoardViewModel = ViewModelProviders.of(this,viewModelFactory).get(DashBoardViewModel.class);
        mActivityDashboardBinding.setLifecycleOwner(this);
        mActivityDashboardBinding.setDashBoardViewModel(mDashBoardViewModel);


        mDashBoardViewModel.getonClickLoginPage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                Intent in=new Intent(DashBoardActivity.this, LoginActivity.class);
                startActivity(in);
            }
        });
        mDashBoardViewModel.getonClickRegisterPage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                Intent in=new Intent(DashBoardActivity.this, RegisterActivity.class);
                startActivity(in);
            }
        });
    }
}
