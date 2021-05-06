package com.autumntechcreation.click4panditcustomer.ui.sendenquiry;

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
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterResponse;
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.JsonObject;

import javax.inject.Inject;

public class SendEnquiryRepository {
    private final AppExecutors mAppExecutors;
    SharedPrefsHelper mSharedPrefsHelper;
    Webservice mWebservice;
    @Inject
    public SendEnquiryRepository(AppExecutors appExecutors, Webservice webservice,SharedPrefsHelper mSharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mSharedPrefsHelper = mSharedPrefsHelper;
        this.mWebservice=webservice;
    }



    public LiveData<Resource<SendEnquiryModel>> getSendEnquiry(String address,String emailAddress,String mobile,String name,
                                                               String pujaName,String requirements ) {
        return new NetworkBoundResource<SendEnquiryModel,SendEnquiryModel>(mAppExecutors) {
            private SendEnquiryModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull SendEnquiryModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable SendEnquiryModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<SendEnquiryModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<SendEnquiryModel>() {
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
            protected LiveData<ApiResponse<SendEnquiryModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.SENDENQUIRY;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.ADDRESS, address);
                jsonObject.addProperty(AllUrlsAndConfig.EMAILADDRR, emailAddress);
                jsonObject.addProperty(AllUrlsAndConfig.MOBILEE, mobile);
                jsonObject.addProperty(AllUrlsAndConfig.NAMEE, name);
                jsonObject.addProperty(AllUrlsAndConfig.PUJANAME, pujaName);
                jsonObject.addProperty(AllUrlsAndConfig.REQUIREMENTS, requirements);


                return mWebservice.sendEnquiry(url,jsonObject);

            }
        }.asLiveData();
    }
}
