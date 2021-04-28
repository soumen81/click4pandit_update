package com.autumntechcreation.click4panditcustomer.ui.ordersummary;

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

public class OrderSummaryRepository {
    private AppExecutors mAppExecutors;
    Webservice mWebservice;
    SharedPrefsHelper mSharedPrefsHelper;



    @Inject
    public OrderSummaryRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mWebservice = webservice;
        this.mSharedPrefsHelper = sharedPrefsHelper;

    }

    public LiveData<Resource<OrderSummeryModel>> getRegistration(String firstName, String lastName, String mobile, String loginForSignUp,
                                                                String passwordForSignUp, String confirmPassForSignUp, String entityType) {
        return new NetworkBoundResource<OrderSummeryModel,OrderSummeryModel>(mAppExecutors) {
            private OrderSummeryModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull OrderSummeryModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable OrderSummeryModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<OrderSummeryModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<OrderSummeryModel>() {
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
            protected LiveData<ApiResponse<OrderSummeryModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.NEWORDER;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.BKGSTSID, firstName);
                jsonObject.addProperty(AllUrlsAndConfig.CNCDT, lastName);
                jsonObject.addProperty(AllUrlsAndConfig.CNCFLG, mobile);
                jsonObject.addProperty(AllUrlsAndConfig.CURID, " ");
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGBKGDT, loginForSignUp);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGBKGTIMEONLY, passwordForSignUp);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGID, confirmPassForSignUp);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, entityType);

                return mWebservice.NewOrder(url,jsonObject);

            }
        }.asLiveData();
    }
}
