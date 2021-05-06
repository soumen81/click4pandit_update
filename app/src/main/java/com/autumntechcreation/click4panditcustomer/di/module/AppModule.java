package com.autumntechcreation.click4panditcustomer.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.autumntechcreation.click4panditcustomer.network.AppExecutors;
import com.autumntechcreation.click4panditcustomer.network.Webservice;
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper;
import com.autumntechcreation.click4panditcustomer.splash.SplashRepository;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsRepository;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaRepository;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordRepository;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageRepository;
import com.autumntechcreation.click4panditcustomer.ui.contactus.ContactUsRepository;
import com.autumntechcreation.click4panditcustomer.ui.dashboard.DashBoardRepository;
import com.autumntechcreation.click4panditcustomer.ui.differentpujalocation.DifferentPujaLocationRepository;
import com.autumntechcreation.click4panditcustomer.ui.editprofile.EditProfileRepository;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordRepository;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeRepository;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginRepository;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderRepository;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryRepository;
import com.autumntechcreation.click4panditcustomer.ui.privacypolicy.PrivacyPolicyRepository;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileRepository;
import com.autumntechcreation.click4panditcustomer.ui.refundpolicy.RefundPolicyRepository;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterRepository;
import com.autumntechcreation.click4panditcustomer.ui.settings.SettingsRepository;
import com.autumntechcreation.click4panditcustomer.ui.signout.SignOutRepository;
import com.autumntechcreation.click4panditcustomer.ui.termscondition.TermsConditionRepository;
import com.autumntechcreation.click4panditcustomer.util.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {
        ViewModelModule.class,
        NetworkModule.class,
})
public class AppModule {
    @Provides
    @Singleton
    Webservice provideWebservice(Retrofit retrofit) {
        return retrofit.create(Webservice.class);
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(Application application) {
        return  application.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
    }


    @Provides
    @Singleton
    SplashRepository provideSplashRepository(AppExecutors appExecutors, Webservice webservice,SharedPrefsHelper sharedPrefsHelper) {
        return new SplashRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    RegisterRepository provideRegisterRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new RegisterRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    LoginRepository provideLoginRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new LoginRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    DashBoardRepository provideDashBoardRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new DashBoardRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    ForgetPasswordRepository provideForgetPasswordRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ForgetPasswordRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    ChangePasswordRepository provideChangePasswordRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ChangePasswordRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    HomeRepository provideHomeRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new HomeRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    ChoosePackageRepository provideChoosePackageRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ChoosePackageRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    BookingPujaRepository provideBookingPujaRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new BookingPujaRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    OrderSummaryRepository provideOrderSummaryRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new OrderSummaryRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    BillingDetailsRepository provideBillingDetailsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new BillingDetailsRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    DifferentPujaLocationRepository provideDifferentPujaLocationRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new DifferentPujaLocationRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    ProfileRepository provideProfileRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ProfileRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    EditProfileRepository provideEditProfileRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new EditProfileRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    OrderRepository provideOrderRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new OrderRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    SignOutRepository provideSignOutRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new SignOutRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    ContactUsRepository provideContactUsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ContactUsRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    PrivacyPolicyRepository providePrivacyPolicyRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new PrivacyPolicyRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    RefundPolicyRepository provideRefundPolicyRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new RefundPolicyRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    TermsConditionRepository provideTermsConditionRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new TermsConditionRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    SettingsRepository provideSettingsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new SettingsRepository(appExecutors,webservice,sharedPrefsHelper);
    }
}
