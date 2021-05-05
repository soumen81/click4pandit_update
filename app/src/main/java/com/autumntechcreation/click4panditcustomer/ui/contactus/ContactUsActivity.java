package com.autumntechcreation.click4panditcustomer.ui.contactus;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityContactusBinding;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ContactUsActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityContactusBinding mActivityContactusBinding;
    ContactUsViewModel contactUsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityContactusBinding = DataBindingUtil.setContentView(this, R.layout.activity_contactus);

        AndroidInjection.inject(this);
        contactUsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ContactUsViewModel.class);
        mActivityContactusBinding.setLifecycleOwner(this);
        mActivityContactusBinding.setContactUsViewModel(contactUsViewModel);


        String url ="https://www.click4pandit.com/Home/ContactUs";

        mActivityContactusBinding.wvContactUs.getSettings().setLoadsImagesAutomatically(true);
        mActivityContactusBinding.wvContactUs.getSettings().setJavaScriptEnabled(true);
        mActivityContactusBinding.wvContactUs.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mActivityContactusBinding.wvContactUs.loadUrl(url);

        mActivityContactusBinding.imgbackContactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    }
