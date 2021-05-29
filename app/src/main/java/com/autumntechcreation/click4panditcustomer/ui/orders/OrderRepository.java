package com.autumntechcreation.click4panditcustomer.ui.orders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.autumntechcreation.click4panditcustomer.network.ApiResponse;
import com.autumntechcreation.click4panditcustomer.network.AppExecutors;
import com.autumntechcreation.click4panditcustomer.network.NetworkBoundResource;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.network.Webservice;
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageListModel;
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;

public class OrderRepository {
    private AppExecutors mAppExecutors;
    Webservice mWebservice;
    SharedPrefsHelper mSharedPrefsHelper;


    @Inject
    public OrderRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mWebservice = webservice;
        this.mSharedPrefsHelper = sharedPrefsHelper;

    }

    public LiveData<Resource<List<OrderListModel>>> getOrderList(String orderDateCriteria) {
        return new NetworkBoundResource<List<OrderListModel>, List<OrderListModel>>(mAppExecutors) {
            private List<OrderListModel> resultsDb;

            @Override
            protected void saveCallResult(@NonNull List<OrderListModel> data) {
                resultsDb = data;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<OrderListModel> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<OrderListModel>> loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                } else {
                    return new LiveData<List<OrderListModel>>() {
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
            protected LiveData<ApiResponse<List<OrderListModel>>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.ORDERLIST;
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.LOGINID, getEmail());
                jsonObject.addProperty(AllUrlsAndConfig.ORDERDATECRITERIA, orderDateCriteria);
                jsonObject.addProperty(AllUrlsAndConfig.PAGEINDEX, 1);
                jsonObject.addProperty(AllUrlsAndConfig.PAGESIZE, 5);
                return mWebservice.getOrderList(url,jsonObject);

            }
        }.asLiveData();

    }

    public String getEmail(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.EMAIL, null);

    }
}
