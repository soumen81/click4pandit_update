package com.autumntechcreation.click4panditcustomer.ui.bookpuja;

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
import com.autumntechcreation.click4panditcustomer.ui.home.PujaTypesModel;
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;

public class BookingPujaRepository {

    private AppExecutors mAppExecutors;
    Webservice mWebservice;
    SharedPrefsHelper mSharedPrefsHelper;


    @Inject
    public BookingPujaRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mWebservice = webservice;
        this.mSharedPrefsHelper = sharedPrefsHelper;

    }

    public LiveData<Resource<List<BookingLocationModel>>> getLocationList(String cityName) {
        return new NetworkBoundResource<List<BookingLocationModel>, List<BookingLocationModel>>(mAppExecutors) {
            private List<BookingLocationModel> resultsDb;

            @Override
            protected void saveCallResult(@NonNull List<BookingLocationModel> data) {
                resultsDb = data;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<BookingLocationModel> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<BookingLocationModel>> loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                } else {
                    return new LiveData<List<BookingLocationModel>>() {
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
            protected LiveData<ApiResponse<List<BookingLocationModel>>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.LOCATIONLIST;
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.SUBLCITYMINTHREECHARS, cityName);
                Log.e("Result",jsonObject.toString());
                return mWebservice.getLocationList(url,jsonObject);

            }
        }.asLiveData();

    }
    public LiveData<Resource<List<BookingLanguageModel>>> getLanguageList() {
        return new NetworkBoundResource<List<BookingLanguageModel>, List<BookingLanguageModel>>(mAppExecutors) {
            private List<BookingLanguageModel> resultsDb;

            @Override
            protected void saveCallResult(@NonNull List<BookingLanguageModel> data) {
                resultsDb = data;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<BookingLanguageModel> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<BookingLanguageModel>> loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                } else {
                    return new LiveData<List<BookingLanguageModel>>() {
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
            protected LiveData<ApiResponse<List<BookingLanguageModel>>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.LANGUAGELIST;
                return mWebservice.getLanguageList(url);

            }
        }.asLiveData();

    }
}
