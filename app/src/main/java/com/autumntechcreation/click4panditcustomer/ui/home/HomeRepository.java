package com.autumntechcreation.click4panditcustomer.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.autumntechcreation.click4panditcustomer.di.Injectable;
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

import java.util.List;

import javax.inject.Inject;

public class HomeRepository{
    private AppExecutors mAppExecutors;
    Webservice mWebservice;
    SharedPrefsHelper mSharedPrefsHelper;



    @Inject
    public HomeRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mWebservice = webservice;
        this.mSharedPrefsHelper = sharedPrefsHelper;

    }

    public LiveData<Resource<List<PujaTypesModel>>> getPujaTypesList() {
        return new NetworkBoundResource<List<PujaTypesModel>, List<PujaTypesModel>>(mAppExecutors) {
            private List<PujaTypesModel> resultsDb;

            @Override
            protected void saveCallResult(@NonNull List<PujaTypesModel> data) {
                resultsDb = data;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<PujaTypesModel> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<PujaTypesModel>> loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                } else {
                    return new LiveData<List<PujaTypesModel>>() {
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
            protected LiveData<ApiResponse<List<PujaTypesModel>>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.PUJATYPES;
                return mWebservice.getPujaTypesList(url);

            }
        }.asLiveData();

    }


public LiveData<Resource<List<PujaCategoryModel>>> getPujaCategoriesList(int  pujaCategoryid) {
        return new NetworkBoundResource<List<PujaCategoryModel>, List<PujaCategoryModel>>(mAppExecutors) {
            private List<PujaCategoryModel> resultsDb;

            @Override
            protected void saveCallResult(@NonNull List<PujaCategoryModel> data) {
                resultsDb = data;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<PujaCategoryModel> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<PujaCategoryModel>> loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                } else {
                    return new LiveData<List<PujaCategoryModel>>() {
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
            protected LiveData<ApiResponse<List<PujaCategoryModel>>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.PUJACATEGORIES;
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.PUJACATEGORYID, pujaCategoryid);
                return mWebservice.getPujaCategoryList(url,jsonObject);

            }
        }.asLiveData();

    }



    public LiveData<Resource<CartItemCountModel>> getCartCountItem() {
        return new NetworkBoundResource<CartItemCountModel,CartItemCountModel>(mAppExecutors) {
            private CartItemCountModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull CartItemCountModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable CartItemCountModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<CartItemCountModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<CartItemCountModel>() {
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
            protected LiveData<ApiResponse<CartItemCountModel>> createCall() {

                String url= AllUrlsAndConfig.STORE_BASE_URL+AllUrlsAndConfig.CHECKCARTCOUNT;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.LOGONIDDDD, getEmail());


                return mWebservice.getCartItemCount(url,jsonObject);

            }
        }.asLiveData();

    }
    public String getEmail(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.EMAIL, null);

    }
    public String getcartCount(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.CARTCOUNT, null);
    }
    public String getupdateCartCount(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.UPDATECARTCOUNT, null);
    }
}


