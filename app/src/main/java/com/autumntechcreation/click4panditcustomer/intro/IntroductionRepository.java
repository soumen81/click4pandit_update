package com.autumntechcreation.click4panditcustomer.intro;

import com.autumntechcreation.click4panditcustomer.network.AppExecutors;
import com.autumntechcreation.click4panditcustomer.network.Webservice;
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper;

import javax.inject.Inject;

public class IntroductionRepository {
    private final AppExecutors mAppExecutors;
    SharedPrefsHelper mSharedPrefsHelper;
    Webservice mWebservice;
    @Inject
    public IntroductionRepository(AppExecutors appExecutors, Webservice webservice,SharedPrefsHelper mSharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mSharedPrefsHelper = mSharedPrefsHelper;
        this.mWebservice=webservice;
    }
}
