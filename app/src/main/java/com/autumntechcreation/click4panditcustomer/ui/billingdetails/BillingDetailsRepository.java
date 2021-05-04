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


    @Inject
    public BillingDetailsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mWebservice = webservice;
        this.mSharedPrefsHelper = sharedPrefsHelper;

    }
    public LiveData<Resource<ProceedtoPayModel>> getProceedtoPay(String firstName, String lastName, String mobile, String loginForSignUp,
                                                                String passwordForSignUp, String confirmPassForSignUp, String entityType) {
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


                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CUSTPUJABILLINGADDRID, firstName);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CUSTPUJABILLINGADDRID, lastName);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.MOBILE, mobile);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.EMAILADDRESS, getEmail());
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.LOGINIDFORSIGNUP, loginForSignUp);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.PASSWORDFORSIGNUP, passwordForSignUp);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.CONFIRMPASSWORDFORSIGNUP, confirmPassForSignUp);
                jsonOObjectBillingAddress.addProperty(AllUrlsAndConfig.ENTITYTYPEABRV, entityType);
                jsonObject.add("BillingAddrModel",jsonOObjectBillingAddress);

                JsonObject jsonObjectCustBkgSummaryDataModel=new JsonObject();

                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.ISGUESTUSERR,"");
                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.LOGONI,getEmail());
                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.CUSBKGIDD,"");
                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.ORDERAMOUNT,"");
                jsonObjectCustBkgSummaryDataModel.addProperty(AllUrlsAndConfig.ORDERID,"");
                jsonObject.add("CustBkgSummaryDataModel",jsonObjectCustBkgSummaryDataModel);

                JsonObject jsonObjectPujaAddlInfoModel=new JsonObject();
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTPUJAADDINFOID,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.UPDATSTAMP,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.UPDATUSER,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ORGLSTAP,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ORIGINALUSER,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.DELETEFLG,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTMASTERIDDDD,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CUSTBKGGID,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.FIRSTTNAME,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.LASTTNAME,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.MOBBI1,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.MOBBI2,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.EMAIL,getEmail());
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR1,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR2,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.ADDDR3,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CITYIDD,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.CITYDESCRR,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.STIDD,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.STDESCRR,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.COUNTRYIDD,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.POSTALL,"");
                jsonObjectPujaAddlInfoModel.addProperty(AllUrlsAndConfig.PUJAADDINFODESCR,"");
                jsonObject.add("PujaAddlInfoModel",jsonObjectPujaAddlInfoModel);


                return mWebservice.ProceedtoPay(url,jsonObject);

            }
        }.asLiveData();
    }
    public String getEmail(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.EMAIL, null);

    }

}
