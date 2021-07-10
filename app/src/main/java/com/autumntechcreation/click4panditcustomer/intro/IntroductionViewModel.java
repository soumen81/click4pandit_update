package com.autumntechcreation.click4panditcustomer.intro;

import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.splash.SplashRepository;

import javax.inject.Inject;

public class IntroductionViewModel extends ViewModel {
    private IntroductionRepository mIntroductionRepository;


    @Inject
    public IntroductionViewModel(IntroductionRepository introductionRepository){
        this.mIntroductionRepository = introductionRepository;
    }
}
