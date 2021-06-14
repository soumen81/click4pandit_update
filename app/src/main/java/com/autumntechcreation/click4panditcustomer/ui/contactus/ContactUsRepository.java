package com.autumntechcreation.click4panditcustomer.ui.contactus;

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
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordModel;
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.JsonObject;

import javax.inject.Inject;

public class ContactUsRepository {

    private final AppExecutors mAppExecutors;
    SharedPrefsHelper mSharedPrefsHelper;
    Webservice mWebservice;
    @Inject
    public ContactUsRepository(AppExecutors appExecutors, Webservice webservice,SharedPrefsHelper mSharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mSharedPrefsHelper = mSharedPrefsHelper;
        this.mWebservice=webservice;
    }


    public LiveData<Resource<ContactUsModel>> getContactUs(String name, String mobile,String email, String address,String explain) {
        return new NetworkBoundResource<ContactUsModel,ContactUsModel>(mAppExecutors) {
            private ContactUsModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull ContactUsModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable ContactUsModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<ContactUsModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<ContactUsModel>() {
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
            protected LiveData<ApiResponse<ContactUsModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.SENDEMAILCONTACT;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.NAME, name);
                jsonObject.addProperty(AllUrlsAndConfig.MOBBILE, mobile);
                jsonObject.addProperty(AllUrlsAndConfig.EMAILADDRRESS, email);
                jsonObject.addProperty(AllUrlsAndConfig.ADDRESSS, address);
                jsonObject.addProperty(AllUrlsAndConfig.EXPLAINYOURISSUE, explain);

                return mWebservice.SendEmailForContactUs(url,jsonObject);

            }
        }.asLiveData();

    }
    public String getEmail(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.EMAIL, null);

    }
}
