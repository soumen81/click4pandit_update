package com.autumntechcreation.click4panditcustomer.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.autumntechcreation.click4panditcustomer.network.AppExecutors;
import com.autumntechcreation.click4panditcustomer.network.Webservice;
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper;
import com.autumntechcreation.click4panditcustomer.splash.SplashRepository;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginRepository;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterRepository;
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
}
