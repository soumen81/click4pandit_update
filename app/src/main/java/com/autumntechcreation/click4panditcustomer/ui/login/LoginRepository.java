package com.autumntechcreation.click4panditcustomer.ui.login;

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

public class LoginRepository {

    private final AppExecutors mAppExecutors;
    SharedPrefsHelper mSharedPrefsHelper;
    Webservice mWebservice;
    @Inject
    public LoginRepository(AppExecutors appExecutors, Webservice webservice,SharedPrefsHelper mSharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mSharedPrefsHelper = mSharedPrefsHelper;
        this.mWebservice=webservice;
    }

    public LiveData<Resource<LoginResponse>> getLogin(String loginId, String password) {
        return new NetworkBoundResource<LoginResponse,LoginResponse>(mAppExecutors) {
            private LoginResponse resultsDb;
            @Override
            protected void saveCallResult(@NonNull LoginResponse data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable LoginResponse data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<LoginResponse>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<LoginResponse>() {
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
            protected LiveData<ApiResponse<LoginResponse>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.LOGIN;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.LOGONID, loginId);
                jsonObject.addProperty(AllUrlsAndConfig.PASSWORD, password);
                //jsonObject.addProperty(AllUrlsAndConfig.DOREMEMBERME, "null");
                jsonObject.addProperty(AllUrlsAndConfig.ENTITYTYPEFORLOGIN, "CUST");
               /* jsonObject.addProperty(AllUrlsAndConfig.FIRSTNAME, "null");
                jsonObject.addProperty(AllUrlsAndConfig.LASTNAME, "null");
                jsonObject.addProperty(AllUrlsAndConfig.MOBILE, "null");*/
                Log.e("Result",jsonObject.toString());

                return mWebservice.Login(url,jsonObject);

            }
        }.asLiveData();
    }




    public void storeUserName(String userName){
        mSharedPrefsHelper.put(SharedPrefsHelper.USERNAME, userName);
    } public void storefirstName(String firstName){
        mSharedPrefsHelper.put(SharedPrefsHelper.FIRSTNAME, firstName);
    } public void storelastName(String firstName){
        mSharedPrefsHelper.put(SharedPrefsHelper.LASTNAME, firstName);
    }
    public void storeMobileNo(String mobile){
        mSharedPrefsHelper.put(SharedPrefsHelper.MOBILE, mobile);
    } public void storeEmail(String email){
        mSharedPrefsHelper.put(SharedPrefsHelper.EMAIL, email);
    }

    public String getUserName(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.USERNAME, null);

    }  public String getFirstName(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.FIRSTNAME, null);

    }

    public String getEmail(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.EMAIL, null);
    }

    public void deleteSharedPreference(){
        mSharedPrefsHelper.deleteSavedData();
    }
}
