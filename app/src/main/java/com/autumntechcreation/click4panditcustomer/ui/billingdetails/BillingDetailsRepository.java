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
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

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
                                                                String address,String address2,String address3,String mobileNo, String city,String state,String  pincode,
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

                String str=null;
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CUSTPUJABILLINGADDRID, 0);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.UPDATESTAMP, pujaDateTime);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.UPDATEUSER, "");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ORGLSTAMPP, str);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ORGLUSERR, "");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.DELFLGG, "N");
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CUSTMASTERIDD, 1);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CUSTBKGIDD, custBkgId);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.FIRSTNAMEE, firstName);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.LASTNAMEE, lastName);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ADDR1, address);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ADDR2, address2);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ADDR3, address3);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.MOB1, mobileNo);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.MOB2, mobileNo);
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

                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTPUJAADDINFOID, str);
                  // jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTPUJAADDINFOID, "null");
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.UPDATSTAMP, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.UPDATUSER, "");
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ORGLSTAP, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ORIGINALUSER, "");
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.DELETEFLG, "N");
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTMASTERIDDDD, 1);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTBKGGID, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.FIRSTTNAME, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.LASTTNAME, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.MOBBI1, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.MOBBI2, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.EMAIL, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR1, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR2, "");
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR3, "");
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CITYIDD, 1001);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CITYDESCRR, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.STIDD, 1001);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.STDESCRR, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.COUNTRYIDD, 1001);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.POSTALL, str);
                   jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.PUJAADDINFODESCR, str);
                   jsonObject.add("PujaAddlInfoModel", jsonObjectPujaAddlInfoModel);
                Log.e("Result",jsonObject.toString());

                return mWebservice.ProceedtoPay(url,jsonObject);

            }
        }.asLiveData();
    }



    public LiveData<Resource<ProceedtoPayModel>> proceedtoPayForShippingAddress(String pujaDateTime, int custBkgId, String firstName, String lastName,
                                                                 String address,String shippingAddress2,String shippingAddress3, String mobileNo, String city,String state,String pincode,
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
                String str=null;


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
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ADDR2, shippingAddress2);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ADDR3, shippingAddress3);
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
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.DELETEFLG, "N");//
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTMASTERIDDDD, 1);//
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTBKGGID, custBkgId);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.FIRSTTNAME, shippingFirstName);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.LASTTNAME, shippingLastName);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.MOBBI1, shippingMobileNo);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.MOBBI2, shippingMobileNo);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.EMAIL, shippingEmailId);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR1, shippingAddress);//""
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR2, "");//""
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR3, "");//""
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CITYIDD, 1001);//
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CITYDESCRR, "Kolkata");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.STIDD, 1001);//
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.STDESCRR, "West Bengal");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.COUNTRYIDD, 1001);//
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.POSTALL, shippingPostalCode);
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.PUJAADDINFODESCR, additionalInfo);
                jsonObject.add("PujaAddlInfoModel", jsonObjectPujaAddlInfoModel);
                Log.e("Result",jsonObject.toString());


                return mWebservice.ProceedtoPay(url,jsonObject);

            }
        }.asLiveData();
    }









    public LiveData<Resource<CashFreeTokenModel>> getCashFreeToken(String orderCurrency, String orderId,String orderAmount) {
        return new NetworkBoundResource<CashFreeTokenModel,CashFreeTokenModel>(mAppExecutors) {
            private CashFreeTokenModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull CashFreeTokenModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable CashFreeTokenModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<CashFreeTokenModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<CashFreeTokenModel>() {
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
            protected LiveData<ApiResponse<CashFreeTokenModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.CASFREETOKEN;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.ORDERCURRENCY, orderCurrency);
                jsonObject.addProperty(AllUrlsAndConfig.ORDERIDD, orderId);
                jsonObject.addProperty(AllUrlsAndConfig.ORDERAMT, orderAmount);

                return mWebservice.cashFreeToken(url,jsonObject);

            }
        }.asLiveData();
    }
    public LiveData<Resource<UpdatedInvoicesModel>> updateinvoice(int orderId) {
        return new NetworkBoundResource<UpdatedInvoicesModel,UpdatedInvoicesModel>(mAppExecutors) {
            private UpdatedInvoicesModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull UpdatedInvoicesModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable UpdatedInvoicesModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<UpdatedInvoicesModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<UpdatedInvoicesModel>() {
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
            protected LiveData<ApiResponse<UpdatedInvoicesModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.UPDATEINVOICE;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.LOGONIDDDDD, getEmail());
                jsonObject.addProperty(AllUrlsAndConfig.ORDERIDDD, orderId);


                return mWebservice.updateInvoice(url,jsonObject);

            }
        }.asLiveData();
    }


    public LiveData<Resource<SendEmailForCustInvoiceModel>> sendCustomerInvoice(CustBkg custBkg, CustInvoice custInvoice,
                                                                                List<PujaSamagriForDelivery> listPujaSamagriForDelivery,
                                                                                List<PujaPrcdr> listPujaPrcdcr,
                                                                                List<PujasamagriHH> listpujasamagriHHList) {
        return new NetworkBoundResource<SendEmailForCustInvoiceModel,SendEmailForCustInvoiceModel>(mAppExecutors) {
            private SendEmailForCustInvoiceModel resultsDb;
            @Override
            protected void saveCallResult(@NonNull SendEmailForCustInvoiceModel data) {
                resultsDb=data;
            }

            @Override
            protected boolean shouldFetch(@Nullable SendEmailForCustInvoiceModel data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<SendEmailForCustInvoiceModel>loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                }else {
                    return new LiveData<SendEmailForCustInvoiceModel>() {
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
            protected LiveData<ApiResponse<SendEmailForCustInvoiceModel>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.SENDCUSTOMERFORCUSTINVOICE;

                Log.e("URL",url);
                JsonObject jsonObject = new JsonObject();
                Gson gson = new Gson();
                JsonElement jelem = gson.fromJson(gson.toJson(custBkg), JsonElement.class);
                JsonObject jobj = jelem.getAsJsonObject();

                JsonElement jelemInvoice = gson.fromJson(gson.toJson(custInvoice), JsonElement.class);
                JsonObject jobj2 = jelemInvoice.getAsJsonObject();

                jsonObject.add("custBkg", jobj);
                jsonObject.add("custInvoice", jobj2);


                //PujaSamagriForDelivery List
                JsonElement jelemInvoice3 = gson.fromJson(gson.toJson(listPujaSamagriForDelivery), JsonElement.class);
                JsonArray jsonArrayReportsPathObject = new JsonArray();
                JsonArray jobj3 = jelemInvoice3.getAsJsonArray();
                jsonObject.add("pujaSamagriForDeliveryList", jobj3);
                //PujaPrcdcr List
                JsonElement jelemInvoice4 = gson.fromJson(gson.toJson(listPujaPrcdcr), JsonElement.class);
                JsonArray jsonArrayPujaPrcdcrObject = new JsonArray();
                JsonArray jobj4 = jelemInvoice4.getAsJsonArray();
                jsonObject.add("pujaPrcdrList", jobj4);

                //pujasamagriHHList List
                JsonElement jelemInvoice5 = gson.fromJson(gson.toJson(listpujasamagriHHList), JsonElement.class);
                JsonArray jsonArraypujasamagriHHListObject = new JsonArray();
                JsonArray jobj5 = jelemInvoice5.getAsJsonArray();
                jsonObject.add("pujasamagriHHList", jobj5);
                Log.e("ResultSendEmail",jsonObject.toString());




                return mWebservice.sendEmailForInvoice(url,jsonObject);

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
