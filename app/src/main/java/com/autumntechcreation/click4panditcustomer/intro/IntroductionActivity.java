package com.autumntechcreation.click4panditcustomer.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityIntroBinding;
import com.autumntechcreation.click4panditcustomer.splash.SplashViewModel;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class IntroductionActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    IntroductionViewModel mIntroductionViewModel;
    ActivityIntroBinding mActivityIntroBinding;
    private int[]mImager={R.drawable.ggg,R.drawable.ultimate5,R.drawable.right5};
    private String[]mImagetitle=new String[]{"Pandit1,Pandit2,Pandit3,Pandit4,Pandit5"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mActivityIntroBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro);
        AndroidInjection.inject(this);
        mIntroductionViewModel = ViewModelProviders.of(this,viewModelFactory).get(IntroductionViewModel.class);
        mActivityIntroBinding.setLifecycleOwner(this);
        mActivityIntroBinding.setIntroViewModel(mIntroductionViewModel);

        mActivityIntroBinding.carousal.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImager[position]);
            }
        });
        mActivityIntroBinding.tvStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(IntroductionActivity.this, LoginActivity.class);
                startActivity(in);
            }
        });
       /* mActivityIntroBinding.carousal.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(IntroductionActivity.this,mImagetitle[position],Toast.LENGTH_SHORT).show();
            }
        });*/
        mActivityIntroBinding.carousal.setPageCount(mImager.length);
    }
}
