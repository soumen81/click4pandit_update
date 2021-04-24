package com.autumntechcreation.click4panditcustomer.ui.choosepackage;

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

public class ChoosePackageRepository {
    private AppExecutors mAppExecutors;
    Webservice mWebservice;
    SharedPrefsHelper mSharedPrefsHelper;



    @Inject
    public ChoosePackageRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        this.mAppExecutors = appExecutors;
        this.mWebservice = webservice;
        this.mSharedPrefsHelper = sharedPrefsHelper;

    }

    public LiveData<Resource<List<ChoosePackageListModel>>> getPujaPackageList(int pujaSubCategoryId) {
        return new NetworkBoundResource<List<ChoosePackageListModel>, List<ChoosePackageListModel>>(mAppExecutors) {
            private List<ChoosePackageListModel> resultsDb;

            @Override
            protected void saveCallResult(@NonNull List<ChoosePackageListModel> data) {
                resultsDb = data;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<ChoosePackageListModel> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<ChoosePackageListModel>> loadFromDb() {
                if (resultsDb == null) {
                    return AbsentLiveData.create();
                } else {
                    return new LiveData<List<ChoosePackageListModel>>() {
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
            protected LiveData<ApiResponse<List<ChoosePackageListModel>>> createCall() {

                String url= AllUrlsAndConfig.BASE_URL+AllUrlsAndConfig.PUJAPACKAGE;
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AllUrlsAndConfig.PUJAPACKAGESUBCATEGORYID, pujaSubCategoryId);
                return mWebservice.getPujaPackageList(url,jsonObject);

            }
        }.asLiveData();

    }
}
