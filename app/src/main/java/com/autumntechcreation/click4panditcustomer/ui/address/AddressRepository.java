package com.autumntechcreation.click4panditcustomer.ui.address;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.autumntechcreation.click4panditcustomer.network.ApiResponse;
import com.autumntechcreation.click4panditcustomer.network.AppExecutors;
import com.autumntechcreation.click4panditcustomer.network.NetworkBoundResource;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.network.Webservice;
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderListModel;
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;

public class AddressRepository {
    private AppExecutors mAppExecutors;
    Webservice mWebservice;
    SharedPrefsHelper mSharedPrefsHelper;



    @Inject
    public AddressRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mWebservice = webservice;
        this.mSharedPrefsHelper = sharedPrefsHelper;

    }


    public LiveData<Resource<List<AddressListModel>>> getAddressList() {
        return new NetworkBoundResource<List<AddressListModel>, List<AddressListModel>>(mAppExecutors) {
            private List<AddressListModel> resultsDb;

            @Override
            protected void saveCallResult(@NonNull List<AddressListModel> data) {
                resultsDb = data;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<AddressListModel> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<AddressListModel>> loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                } else {
                    return new LiveData<List<AddressListModel>>() {
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
            protected LiveData<ApiResponse<List<AddressListModel>>> createCall() {
                String str=null;
                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.ADDRESSLIST;
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.LOGID, getEmail());
                jsonObject.addProperty(AllUrlsAndConfig.SHIPPINGADDRID, str);

                return mWebservice.getAddressList(url,jsonObject);

            }
        }.asLiveData();

    }
    public String getEmail(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.EMAIL, null);

    } public String getFirstName(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.FIRSTNAME, null);

    } public String getLastName(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.LASTNAME, null);

    } public String getMobile(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.MOBILE, null);

    }
}
