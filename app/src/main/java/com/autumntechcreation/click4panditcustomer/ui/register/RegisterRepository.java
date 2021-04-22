package com.autumntechcreation.click4panditcustomer.ui.register;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.autumntechcreation.click4panditcustomer.network.ApiResponse;
import com.autumntechcreation.click4panditcustomer.network.AppExecutors;
import com.autumntechcreation.click4panditcustomer.network.NetworkBoundResource;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.network.Webservice;
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper;
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.JsonObject;

import javax.inject.Inject;

public class RegisterRepository {
    private final AppExecutors mAppExecutors;
    SharedPrefsHelper mSharedPrefsHelper;
    Webservice mWebservice;
    @Inject
    public RegisterRepository(AppExecutors appExecutors, Webservice webservice,SharedPrefsHelper mSharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mSharedPrefsHelper = mSharedPrefsHelper;
        this.mWebservice=webservice;
    }

    public LiveData<Resource<RegisterResponse>> getRegistration(String firstName,String lastName,String mobile,String loginForSignUp,
                                                                String passwordForSignUp,String confirmPassForSignUp,String entityType) {
        return new NetworkBoundResource<RegisterResponse,RegisterResponse>(mAppExecutors) {
            private RegisterResponse resultsDb;
            @Override
            protected void saveCallResult(@NonNull RegisterResponse data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable RegisterResponse data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<RegisterResponse>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<RegisterResponse>() {
                        @Override
                        protected void onActive() {
                            super.onActive();
                            setValue(resultsDb);
                        }
                    };
                }
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<RegisterResponse>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.SIGNUP;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.FIRSTNAME, firstName);
                jsonObject.addProperty(AllUrlsAndConfig.LASTNAME, lastName);
                jsonObject.addProperty(AllUrlsAndConfig.MOBILE, mobile);
                jsonObject.addProperty(AllUrlsAndConfig.EMAILADDRESS, " ");
                jsonObject.addProperty(AllUrlsAndConfig.LOGINIDFORSIGNUP, loginForSignUp);
                jsonObject.addProperty(AllUrlsAndConfig.PASSWORDFORSIGNUP, passwordForSignUp);
                jsonObject.addProperty(AllUrlsAndConfig.CONFIRMPASSWORDFORSIGNUP, confirmPassForSignUp);
                jsonObject.addProperty(AllUrlsAndConfig.ENTITYTYPEABRV, entityType);

                return mWebservice.Register(url,jsonObject);

            }
        }.asLiveData();
    }



}
