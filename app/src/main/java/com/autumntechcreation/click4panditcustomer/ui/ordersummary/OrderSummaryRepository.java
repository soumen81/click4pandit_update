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

    public LiveData<Resource<OrderSummeryModel>> getOrderGenerate(int languageId, String pujaLanguageName, double amount, double cgstsgst,
                                                                double totalAmount, String pkgDesc, int pujaPackageId,int noOfPandit,
                                                                  String subCategoryName,int subCategoryId,String locationName,int locationId,
                                                                  String dateTime) {
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
                jsonObject.addProperty(AllUrlsAndConfig.BKGSTSID, 1002);
                jsonObject.addProperty(AllUrlsAndConfig.CNCDT, dateTime);//Booking and date
                jsonObject.addProperty(AllUrlsAndConfig.CNCFLG, "N");
                jsonObject.addProperty(AllUrlsAndConfig.CURID, 1001);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGBKGDT, dateTime);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGBKGTIMEONLY, "");
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGID, 0);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERID, languageId);//LanguageID
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGLANGMASTERNAME, pujaLanguageName);//Language name
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGPKGAMT, amount);//amount
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGPKGTAXAMT, cgstsgst);//cgest+sgst
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGPKGTOTALAMT, totalAmount);//total amount
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGPUJAPKGALLSAMGRIFLG, "Y");
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGPUJAPKGDESC, pkgDesc);//packagedesc
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGPUJAPKGID, pujaPackageId);//packageId
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGPUJAPKGNOOFPANDIT, noOfPandit);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGPUJAPKGNOTE, "");
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGPUJAPKGTYPEDSCR, pkgDesc);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGPUJAPKGTYPEID, pujaPackageId);
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGPUJASUBCTGRYDSCR, subCategoryName);//Subtcategoryname
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGPUJASUBCTGRYID, subCategoryId);//subcategoryid
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGSUBLCLTYNAME, locationName);//LocationName
                jsonObject.addProperty(AllUrlsAndConfig.CUSTBKGSUBLCLTYID, locationId);//Locationid
                jsonObject.addProperty(AllUrlsAndConfig.CUSTMASTERID, 1);
                jsonObject.addProperty(AllUrlsAndConfig.DELFLG, "N");
                jsonObject.addProperty(AllUrlsAndConfig.ISGUESTUSER, "N");
                jsonObject.addProperty(AllUrlsAndConfig.LOGONIDD, getEmail());//emailid
                jsonObject.addProperty(AllUrlsAndConfig.ORGLSTAMP, dateTime);//date
                jsonObject.addProperty(AllUrlsAndConfig.ORGLUSER, dateTime);
                jsonObject.addProperty(AllUrlsAndConfig.TAXTYPID, 1001);
                jsonObject.addProperty(AllUrlsAndConfig.UPDTSTAMP, dateTime);//date
                jsonObject.addProperty(AllUrlsAndConfig.UPDTUSER, "");
                jsonObject.addProperty(AllUrlsAndConfig.ORDERSRCTYPEID, 1003);

                return mWebservice.NewOrder(url,jsonObject);

            }
        }.asLiveData();
    }

    public String getUserName(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.USERNAME, null);

    }public String getEmail(){
        return  mSharedPrefsHelper.get(SharedPrefsHelper.EMAIL, null);

    }
}
