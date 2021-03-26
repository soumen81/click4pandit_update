package com.autumntechcreation.click4panditcustomer.ui.dashboard;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityDashboardBinding;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginViewModel;

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
    }
}
