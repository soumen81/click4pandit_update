package com.autumntechcreation.click4panditcustomer.ui.refundpolicy;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityRefundpolicyBinding;
import com.autumntechcreation.click4panditcustomer.ui.privacypolicy.PrivacyPolicyViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class RefundPolicyActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityRefundpolicyBinding mActivityRefundpolicyBinding;
    RefundPolicyViewModel mRefundPolicyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityRefundpolicyBinding = DataBindingUtil.setContentView(this, R.layout.activity_refundpolicy);

        AndroidInjection.inject(this);
        mRefundPolicyViewModel = ViewModelProviders.of(this, viewModelFactory).get(RefundPolicyViewModel.class);
        mActivityRefundpolicyBinding.setLifecycleOwner(this);
        mActivityRefundpolicyBinding.setRefundPolicyViewModel(mRefundPolicyViewModel);

        //String url ="https://www.click4pandit.com/Home/Refund";
        String url ="https://webapp-click4pandit.azurewebsites.net/Home/Refund?moblieView=YES";

        mActivityRefundpolicyBinding.wvRefundPolicy.getSettings().setLoadsImagesAutomatically(true);
        mActivityRefundpolicyBinding.wvRefundPolicy.getSettings().setJavaScriptEnabled(true);
        mActivityRefundpolicyBinding.wvRefundPolicy.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mActivityRefundpolicyBinding.wvRefundPolicy.loadUrl(url);

        mActivityRefundpolicyBinding.imgbackRefundPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}
