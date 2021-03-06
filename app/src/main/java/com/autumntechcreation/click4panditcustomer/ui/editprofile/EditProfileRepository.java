package com.autumntechcreation.click4panditcustomer.ui.editprofile;

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
import com.autumntechcreation.click4panditcustomer.ui.profile.CustomerGetProfileModel;
import com.autumntechcreation.click4panditcustomer.ui.profile.SaveCustomerprofileModel;
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.JsonObject;

import javax.inject.Inject;

public class EditProfileRepository {
    private AppExecutors mAppExecutors;
    Webservice mWebservice;
    SharedPrefsHelper mSharedPrefsHelper;


    @Inject
    public EditProfileRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mWebservice = webservice;
        this.mSharedPrefsHelper = sharedPrefsHelper;

    }

    public LiveData<Resource<CustomerGetProfileModel>> getForCustomerProfile() {
        return new NetworkBoundResource<CustomerGetProfileModel,CustomerGetProfileModel>(mAppExecutors) {
            private CustomerGetProfileModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull CustomerGetProfileModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable CustomerGetProfileModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<CustomerGetProfileModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<CustomerGetProfileModel>() {
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
            protected LiveData<ApiResponse<CustomerGetProfileModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.GETPROFILE;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.LOGONIDDD, getEmail());


                return mWebservice.customerGetProfile(url,jsonObject);

            }
        }.asLiveData();
    }

    public LiveData<Resource<SaveCustomerprofileModel>> getForEditSaveCustomerProfile(int customeMasterrId,
                                                                                  String fName, String lName,
                                                                                  String mobileNo, String altermobileNo, String emailId) {
        return new NetworkBoundResource<SaveCustomerprofileModel,SaveCustomerprofileModel>(mAppExecutors) {
            private SaveCustomerprofileModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull SaveCustomerprofileModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable SaveCustomerprofileModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<SaveCustomerprofileModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<SaveCustomerprofileModel>() {
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
            protected LiveData<ApiResponse<SaveCustomerprofileModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.SAVEPROFILE;
                String str=null;
                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.LOGONIDDD, getEmail());
                jsonObject.addProperty(AllUrlsAndConfig.CUSTMASTERIDDD, customeMasterrId);
                jsonObject.addProperty(AllUrlsAndConfig.FIRSTNAMEEE, fName);
                jsonObject.addProperty(AllUrlsAndConfig.LASTNAMEEE, lName);
                jsonObject.addProperty(AllUrlsAndConfig.MOBILENOOOO, mobileNo);
                jsonObject.addProperty(AllUrlsAndConfig.ALTERNATEMOBILE, altermobileNo);
                jsonObject.addProperty(AllUrlsAndConfig.EMMAILID, emailId);
                jsonObject.addProperty(AllUrlsAndConfig.PHHONENO, str);
                jsonObject.addProperty(AllUrlsAndConfig.FAXNOO, str);


                return mWebservice.customerSaveProfile(url,jsonObject);

            }
        }.asLiveData();
    }
    public String getEmail(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.EMAIL, null);

    }
    public void storeAlternateMobileNo(String alternateMobileNo){
        mSharedPrefsHelper.put(SharedPrefsHelper.ALTERNATEMOBNO, alternateMobileNo);
    }
}
