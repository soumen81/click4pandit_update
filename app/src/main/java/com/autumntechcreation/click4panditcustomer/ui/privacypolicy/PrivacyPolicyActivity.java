package com.autumntechcreation.click4panditcustomer.ui.privacypolicy;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityPrivacypolicyBinding;
import com.autumntechcreation.click4panditcustomer.ui.contactus.ContactUsViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class PrivacyPolicyActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityPrivacypolicyBinding mActivityPrivacypolicyBinding;
    PrivacyPolicyViewModel mPrivacyPolicyViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityPrivacypolicyBinding = DataBindingUtil.setContentView(this, R.layout.activity_privacypolicy);

        AndroidInjection.inject(this);
        mPrivacyPolicyViewModel = ViewModelProviders.of(this, viewModelFactory).get(PrivacyPolicyViewModel.class);
        mActivityPrivacypolicyBinding.setLifecycleOwner(this);
        mActivityPrivacypolicyBinding.setPrivacyPolicyViewModel(mPrivacyPolicyViewModel);

       // String url ="https://www.click4pandit.com/Home/Privacy";
        String url ="https://webapp-click4pandit.azurewebsites.net/Home/Privacy?moblieView=YES";


        mActivityPrivacypolicyBinding.wvPrivacyPolicy.getSettings().setLoadsImagesAutomatically(true);
        mActivityPrivacypolicyBinding.wvPrivacyPolicy.getSettings().setJavaScriptEnabled(true);
        mActivityPrivacypolicyBinding.wvPrivacyPolicy.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mActivityPrivacypolicyBinding.wvPrivacyPolicy.loadUrl(url);

        mActivityPrivacypolicyBinding.imgbackPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}
