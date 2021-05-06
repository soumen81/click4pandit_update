package com.autumntechcreation.click4panditcustomer.ui.termscondition;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityTermsconditionBinding;
import com.autumntechcreation.click4panditcustomer.ui.contactus.ContactUsViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class TermsConditionActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityTermsconditionBinding mActivityTermsconditionBinding;
    TermsConditionViewModel mTermsConditionViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityTermsconditionBinding = DataBindingUtil.setContentView(this, R.layout.activity_termscondition);

        AndroidInjection.inject(this);
        mTermsConditionViewModel = ViewModelProviders.of(this, viewModelFactory).get(TermsConditionViewModel.class);
        mActivityTermsconditionBinding.setLifecycleOwner(this);
        mActivityTermsconditionBinding.setTermsConditionViewModel(mTermsConditionViewModel);


        String url ="https://www.click4pandit.com/Home/Terms";

        mActivityTermsconditionBinding.wvTermsCondition.getSettings().setLoadsImagesAutomatically(true);
        mActivityTermsconditionBinding.wvTermsCondition.getSettings().setJavaScriptEnabled(true);
        mActivityTermsconditionBinding.wvTermsCondition.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mActivityTermsconditionBinding.wvTermsCondition.loadUrl(url);

        mActivityTermsconditionBinding.imgbackTermsCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }





}
