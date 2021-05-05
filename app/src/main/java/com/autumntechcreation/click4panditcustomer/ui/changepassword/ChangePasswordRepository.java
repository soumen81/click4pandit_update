package com.autumntechcreation.click4panditcustomer.ui.changepassword;

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
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordModel;
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.JsonObject;

import javax.inject.Inject;

public class ChangePasswordRepository {
    private final AppExecutors mAppExecutors;
    SharedPrefsHelper mSharedPrefsHelper;
    Webservice mWebservice;
    @Inject
    public ChangePasswordRepository(AppExecutors appExecutors, Webservice webservice,SharedPrefsHelper mSharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mSharedPrefsHelper = mSharedPrefsHelper;
        this.mWebservice=webservice;
    }

    public LiveData<Resource<ChangePasswordModel>> getChangePassword(String oldPassword,
                                                                        String newPassword,String newConfirmPassword) {
        return new NetworkBoundResource<ChangePasswordModel,ChangePasswordModel>(mAppExecutors) {
            private ChangePasswordModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull ChangePasswordModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable ChangePasswordModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<ChangePasswordModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<ChangePasswordModel>() {
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
            protected LiveData<ApiResponse<ChangePasswordModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.CHANGEPASSWORD;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.LOGONIDDDD, getEmail());
                jsonObject.addProperty(AllUrlsAndConfig.OLDPASSWORD, oldPassword);
                jsonObject.addProperty(AllUrlsAndConfig.NEWPASSWORD, newPassword);
                jsonObject.addProperty(AllUrlsAndConfig.CONFIRMPASSWORD, newConfirmPassword);
                jsonObject.addProperty(AllUrlsAndConfig.ENTITYTYPABRV, "CUST");

                return mWebservice.ChangePassword(url,jsonObject);

            }
        }.asLiveData();

    }
    public String getEmail(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.EMAIL, null);

    }
}
