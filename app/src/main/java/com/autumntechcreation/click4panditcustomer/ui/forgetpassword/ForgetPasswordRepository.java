package com.autumntechcreation.click4panditcustomer.ui.forgetpassword;

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
import com.autumntechcreation.click4panditcustomer.ui.login.LoginResponse;
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.JsonObject;

import javax.inject.Inject;

public class ForgetPasswordRepository {
    private final AppExecutors mAppExecutors;
    SharedPrefsHelper mSharedPrefsHelper;
    Webservice mWebservice;
    @Inject
    public ForgetPasswordRepository(AppExecutors appExecutors, Webservice webservice,SharedPrefsHelper mSharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mSharedPrefsHelper = mSharedPrefsHelper;
        this.mWebservice=webservice;
    }

    public LiveData<Resource<ForgetPasswordModel>> getForSetNewPassword(String loginId, String deviceId) {
        return new NetworkBoundResource<ForgetPasswordModel,ForgetPasswordModel>(mAppExecutors) {
            private ForgetPasswordModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull ForgetPasswordModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable ForgetPasswordModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<ForgetPasswordModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<ForgetPasswordModel>() {
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
            protected LiveData<ApiResponse<ForgetPasswordModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.FORGETPASSWORD;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.LOGONIDDD, loginId);
                jsonObject.addProperty(AllUrlsAndConfig.ENTITYTYPABRV, "CUST");
                jsonObject.addProperty(AllUrlsAndConfig.SETNEWPASSWORDLINK, deviceId);

                return mWebservice.ForgetPassword(url,jsonObject);

            }
        }.asLiveData();
    }
 public LiveData<Resource<TriggerMailModel>> getTriggerPassword(String loginId, String deviceId) {
        return new NetworkBoundResource<TriggerMailModel,TriggerMailModel>(mAppExecutors) {
            private TriggerMailModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull TriggerMailModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable TriggerMailModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<TriggerMailModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<TriggerMailModel>() {
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
            protected LiveData<ApiResponse<TriggerMailModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.TRIGGERMAIL;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.LOGONIDDD, loginId);
                jsonObject.addProperty(AllUrlsAndConfig.ENTITYTYPABRV, "CUST");
                jsonObject.addProperty(AllUrlsAndConfig.SETNEWPASSWORDLINK, deviceId);

                return mWebservice.TriggerMail(url,jsonObject);

            }
        }.asLiveData();
    }




    public String getEmail(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.EMAIL, null);

    }

}
