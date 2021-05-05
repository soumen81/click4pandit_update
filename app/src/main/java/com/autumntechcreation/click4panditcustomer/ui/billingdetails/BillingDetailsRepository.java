package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

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

public class BillingDetailsRepository {
    private AppExecutors mAppExecutors;
    Webservice mWebservice;
    SharedPrefsHelper mSharedPrefsHelper;
    boolean isShippingAddress=false;

    @Inject
    public BillingDetailsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mWebservice = webservice;
        this.mSharedPrefsHelper = sharedPrefsHelper;

    }
    public LiveData<Resource<ProceedtoPayModel>> proceedtoPayForBillingAddress(String pujaDateTime, int custBkgId, String firstName, String lastName,
                                                                String address, String mobileNo, String city,String state,String pincode,
                                                                 double orderAmount,int orderId) {
        return new NetworkBoundResource<ProceedtoPayModel,ProceedtoPayModel>(mAppExecutors) {
            private ProceedtoPayModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull ProceedtoPayModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable ProceedtoPayModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<ProceedtoPayModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<ProceedtoPayModel>() {
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
            protected LiveData<ApiResponse<ProceedtoPayModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.PROCEEDTOPAY;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                JsonObject jsonOObjectBillingAddress = new JsonObject();


                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CUSTPUJABILLINGADDRID, 0);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.UPDATESTAMP, pujaDateTime);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.UPDATEUSER, "");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ORGLSTAMPP, pujaDateTime);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ORGLUSERR, "");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.DELFLGG, "N");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CUSTMASTERIDD, 1);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CUSTBKGIDD, custBkgId);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.FIRSTNAMEE, firstName);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.LASTNAMEE, lastName);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ADDR1, address);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ADDR2, "");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ADDR3, "");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.MOB1, mobileNo);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.MOB2, "0");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.EMAILADDR, getEmail());
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CITYID, 1001);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CITYDESCR, city);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.STID, 1001);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.STDESCR, state);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.COUNTRYID, 1001);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.POSTAL, pincode);
                jsonObject.add("BillingAddrModel",jsonOObjectBillingAddress);

                JsonObject jsonObjectCustBkgSummaryDataModel=new JsonObject();

                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.ISGUESTUSERR,"N");
                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.LOGONI,getEmail());
                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.CUSBKGIDD,custBkgId);
                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.ORDERAMOUNT,orderAmount);
                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.ORDERID,orderId);
                jsonObject.add("CustBkgSummaryDataModel",jsonObjectCustBkgSummaryDataModel);

                   JsonObject jsonObjectPujaAddlInfoModel = new JsonObject();
                   String str=null;
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTPUJAADDINFOID, str);
                  // jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTPUJAADDINFOID, "null");
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.UPDATSTAMP, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.UPDATUSER, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ORGLSTAP, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ORIGINALUSER, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.DELETEFLG, "N");
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTMASTERIDDDD, 1);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTBKGGID, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.FIRSTTNAME, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.LASTTNAME, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.MOBBI1, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.MOBBI2, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.EMAIL, "");
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR1, "");
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR2, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR3, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CITYIDD, 1001);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CITYDESCRR, "");
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.STIDD, 1001);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.STDESCRR, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.COUNTRYIDD, 1001);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.POSTALL, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.PUJAADDINFODESCR, str);
                   jsonObject.add("PujaAddlInfoModel", jsonObjectPujaAddlInfoModel);

                return mWebservice.ProceedtoPay(url,jsonObject);

            }
        }.asLiveData();
    }



    public LiveData<Resource<ProceedtoPayModel>> proceedtoPayForShippingAddress(String pujaDateTime, int custBkgId, String firstName, String lastName,
                                                                 String address, String mobileNo, String city,String state,String pincode,
                                                                 double orderAmount,int orderId, String shippingFirstName,String shippingLastName,
                                                                                String shippingMobileNo,String shippingEmailId,String shippingAddress,
                                                                                String shippingPostalCode,String additionalInfo) {
        return new NetworkBoundResource<ProceedtoPayModel,ProceedtoPayModel>(mAppExecutors) {
            private ProceedtoPayModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull ProceedtoPayModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable ProceedtoPayModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<ProceedtoPayModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<ProceedtoPayModel>() {
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
            protected LiveData<ApiResponse<ProceedtoPayModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.PROCEEDTOPAY;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                JsonObject jsonOObjectBillingAddress = new JsonObject();


                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CUSTPUJABILLINGADDRID, 0);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.UPDATESTAMP, pujaDateTime);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.UPDATEUSER, "");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ORGLSTAMPP, pujaDateTime);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ORGLUSERR, "");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.DELFLGG, "N");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CUSTMASTERIDD, 1);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CUSTBKGIDD, custBkgId);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.FIRSTNAMEE, firstName);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.LASTNAMEE, lastName);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ADDR1, address);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ADDR2, "");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ADDR3, "");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.MOB1, mobileNo);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.MOB2, "0");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.EMAILADDR, getEmail());
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CITYID, 1001);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CITYDESCR, city);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.STID, 1001);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.STDESCR, state);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.COUNTRYID, 1001);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.POSTAL, pincode);
                jsonObject.add("BillingAddrModel",jsonOObjectBillingAddress);

                JsonObject jsonObjectCustBkgSummaryDataModel=new JsonObject();

                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.ISGUESTUSERR,"N");
                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.LOGONI,getEmail());
                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.CUSBKGIDD,custBkgId);
                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.ORDERAMOUNT,orderAmount);
                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.ORDERID,orderId);
                jsonObject.add("CustBkgSummaryDataModel",jsonObjectCustBkgSummaryDataModel);

                JsonObject jsonObjectPujaAddlInfoModel = new JsonObject();

                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTPUJAADDINFOID, 0);
                // jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTPUJAADDINFOID, "null");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.UPDATSTAMP, pujaDateTime);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.UPDATUSER, "");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ORGLSTAP, pujaDateTime);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ORIGINALUSER, "");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.DELETEFLG, "N");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTMASTERIDDDD, 1);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTBKGGID, 0);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.FIRSTTNAME, shippingFirstName);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.LASTTNAME, shippingLastName);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.MOBBI1, shippingMobileNo);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.MOBBI2, shippingMobileNo);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.EMAIL, shippingEmailId);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR1, shippingAddress);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR2, "");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR3, "");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CITYIDD, 1001);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CITYDESCRR, "West Bengal");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.STIDD, 1001);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.STDESCRR, "Kolkata");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.COUNTRYIDD, 1001);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.POSTALL, shippingPostalCode);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.PUJAADDINFODESCR, additionalInfo);
                jsonObject.add("PujaAddlInfoModel", jsonObjectPujaAddlInfoModel);

                return mWebservice.ProceedtoPay(url,jsonObject);

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
