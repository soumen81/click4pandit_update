package com.autumntechcreation.click4panditcustomer.ui.addupdateremoveaddress;

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
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.CashFreeTokenModel;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderListModel;
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;

public class AddUpdateRemoveRepository {
    private AppExecutors mAppExecutors;
    Webservice mWebservice;
    SharedPrefsHelper mSharedPrefsHelper;



    @Inject
    public AddUpdateRemoveRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mWebservice = webservice;
        this.mSharedPrefsHelper = sharedPrefsHelper;

    }

    public LiveData<Resource<AddAddressModel>> getAddAddress(String action,int shippingAddressId,int custMasterId,String orglStamp,String firstName,String lastName,String address1,String pincode ) {
        return new NetworkBoundResource<AddAddressModel,AddAddressModel>(mAppExecutors) {
            private AddAddressModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull AddAddressModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable AddAddressModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<AddAddressModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<AddAddressModel>() {
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
            protected LiveData<ApiResponse<AddAddressModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.ADDADDRESS;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                String str=null;
                jsonObject.addProperty(AllUrlsAndConfig.ADDRACTION, action);
                jsonObject.addProperty(AllUrlsAndConfig.SHIPADDRID, shippingAddressId);
                jsonObject.addProperty(AllUrlsAndConfig.UPDATESTMP, orglStamp);
                jsonObject.addProperty(AllUrlsAndConfig.UPDATEUSR, getEmail());
                jsonObject.addProperty(AllUrlsAndConfig.ORGLSTMP, orglStamp);
                jsonObject.addProperty(AllUrlsAndConfig.ORGLUSR, getEmail());
                jsonObject.addProperty(AllUrlsAndConfig.DELFLLG, "N");
                jsonObject.addProperty(AllUrlsAndConfig.CUSTMASTEID, custMasterId);
                jsonObject.addProperty(AllUrlsAndConfig.LOID, getEmail());
                jsonObject.addProperty(AllUrlsAndConfig.FNME,firstName);
                jsonObject.addProperty(AllUrlsAndConfig.LNME, lastName);
                jsonObject.addProperty(AllUrlsAndConfig.ADDR11, address1);
                jsonObject.addProperty(AllUrlsAndConfig.ADDR22, str);
                jsonObject.addProperty(AllUrlsAndConfig.ADD33, str);
                jsonObject.addProperty(AllUrlsAndConfig.CITID, str);
                jsonObject.addProperty(AllUrlsAndConfig.CITDESC, str);
                jsonObject.addProperty(AllUrlsAndConfig.STIDDD, str);
                jsonObject.addProperty(AllUrlsAndConfig.STDESC, "WEST BENGAL");
                jsonObject.addProperty(AllUrlsAndConfig.CNTRYID, str);
                jsonObject.addProperty(AllUrlsAndConfig.CNTRYDESC, "INDIA");
                jsonObject.addProperty(AllUrlsAndConfig.POST, pincode);
                jsonObject.addProperty(AllUrlsAndConfig.ISSDEFAULT, "N");
                jsonObject.addProperty(AllUrlsAndConfig.SEQQNO, str);
                jsonObject.addProperty(AllUrlsAndConfig.DSPORD, str);

                return mWebservice.customerAddAddress(url,jsonObject);

            }
        }.asLiveData();
    }
    public LiveData<Resource<AddAddressModel>> getNewAddAddress(String action,String firstName,String lastName,String address1,String pincode ) {
        return new NetworkBoundResource<AddAddressModel,AddAddressModel>(mAppExecutors) {
            private AddAddressModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull AddAddressModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable AddAddressModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<AddAddressModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<AddAddressModel>() {
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
            protected LiveData<ApiResponse<AddAddressModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.ADDADDRESS;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                String str=null;
                jsonObject.addProperty(AllUrlsAndConfig.ADDRACTION, action);
                jsonObject.addProperty(AllUrlsAndConfig.SHIPADDRID, str);
                jsonObject.addProperty(AllUrlsAndConfig.UPDATESTMP, str);
                jsonObject.addProperty(AllUrlsAndConfig.UPDATEUSR, getEmail());
                jsonObject.addProperty(AllUrlsAndConfig.ORGLSTMP, str);
                jsonObject.addProperty(AllUrlsAndConfig.ORGLUSR, getEmail());
                jsonObject.addProperty(AllUrlsAndConfig.DELFLLG, "N");
                jsonObject.addProperty(AllUrlsAndConfig.CUSTMASTEID, str);
                jsonObject.addProperty(AllUrlsAndConfig.LOID, getEmail());
                jsonObject.addProperty(AllUrlsAndConfig.FNME,firstName);
                jsonObject.addProperty(AllUrlsAndConfig.LNME, lastName);
                jsonObject.addProperty(AllUrlsAndConfig.ADDR11, address1);
                jsonObject.addProperty(AllUrlsAndConfig.ADDR22, str);
                jsonObject.addProperty(AllUrlsAndConfig.ADD33, str);
                jsonObject.addProperty(AllUrlsAndConfig.CITID, str);
                jsonObject.addProperty(AllUrlsAndConfig.CITDESC, str);
                jsonObject.addProperty(AllUrlsAndConfig.STIDDD, str);
                jsonObject.addProperty(AllUrlsAndConfig.STDESC, "WEST BENGAL");
                jsonObject.addProperty(AllUrlsAndConfig.CNTRYID, str);
                jsonObject.addProperty(AllUrlsAndConfig.CNTRYDESC, "INDIA");
                jsonObject.addProperty(AllUrlsAndConfig.POST, pincode);
                jsonObject.addProperty(AllUrlsAndConfig.ISSDEFAULT, "N");
                jsonObject.addProperty(AllUrlsAndConfig.SEQQNO, str);
                jsonObject.addProperty(AllUrlsAndConfig.DSPORD, str);

                return mWebservice.customerAddAddress(url,jsonObject);

            }
        }.asLiveData();
    }
    public String getEmail(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.EMAIL, null);

    }
    public String getFirstName(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.FIRSTNAME, null);

    } public String getLastName(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.LASTNAME, null);

    } public String getMobile(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.MOBILE, null);

    }
}
