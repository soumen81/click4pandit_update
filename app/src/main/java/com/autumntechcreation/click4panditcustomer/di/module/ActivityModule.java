package com.autumntechcreation.click4panditcustomer.di.module;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.splash.SplashScreenActivity;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordAcitivity;
import com.autumntechcreation.click4panditcustomer.ui.contactus.ContactUsActivity;
import com.autumntechcreation.click4panditcustomer.ui.dashboard.DashBoardActivity;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordActivity;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.autumntechcreation.click4panditcustomer.ui.privacypolicy.PrivacyPolicyActivity;
import com.autumntechcreation.click4panditcustomer.ui.refundpolicy.RefundPolicyActivity;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.autumntechcreation.click4panditcustomer.ui.sendenquiry.SendEnquiryActivity;
import com.autumntechcreation.click4panditcustomer.ui.termscondition.TermsConditionActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract SplashScreenActivity contributeSplashScreenActivity();

    @ContributesAndroidInjector
    abstract RegisterActivity contributeRegisterActivity();

    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector
    abstract DashBoardActivity contributeDashBoardActivity();

    @ContributesAndroidInjector
    abstract ForgetPasswordActivity contributeForgetPasswordActivity();

    @ContributesAndroidInjector
    abstract ChangePasswordAcitivity contributeChangePasswordAcitivity();

    @ContributesAndroidInjector
    abstract ContactUsActivity contributeContactUsActivity();

    @ContributesAndroidInjector
    abstract PrivacyPolicyActivity contributePrivacyPolicyActivity();

    @ContributesAndroidInjector
    abstract RefundPolicyActivity contributeRefundPolicyActivity();

    @ContributesAndroidInjector
    abstract TermsConditionActivity contributeTermsConditionActivity();

    @ContributesAndroidInjector
    abstract SendEnquiryActivity contributeSendEnquiryActivity();


}
