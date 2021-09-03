package com.autumntechcreation.click4panditcustomer.ui.shopcategory

import android.util.Log
import androidx.lifecycle.LiveData
import com.autumntechcreation.click4panditcustomer.network.*
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig
import com.google.gson.JsonObject
import javax.inject.Inject

class ShopCategoryRepository @Inject constructor(private val mAppExecutors: AppExecutors,
                                                 private  val mWebservice: Webservice,private val mSharedPrefsHelper: SharedPrefsHelper) {
    fun getShopCategoryList(): LiveData<Resource<List<ShopCategoryModel>>>? {
        return object : NetworkBoundResource<List<ShopCategoryModel>, List<ShopCategoryModel>>(mAppExecutors) {
            private var resultsDb: List<ShopCategoryModel>? = null

            override fun shouldFetch(data: List<ShopCategoryModel>?): Boolean {
                return true
            }

            override fun saveCallResult(item: List<ShopCategoryModel>) {
                resultsDb = item
            }

            override fun loadFromDb(): LiveData<List<ShopCategoryModel>> {
                return if (resultsDb == null) {
                    AbsentLiveData.create()
                } else {
                    object : LiveData<List<ShopCategoryModel>>() {
                        protected override fun onActive() {
                            super.onActive()
                            value = resultsDb
                        }
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<List<ShopCategoryModel>>> {
                Log.e("BoundResource", "createCall")
                var url: String = "";

                url = AllUrlsAndConfig.STORE_BASE_URL + AllUrlsAndConfig.SHOPCATEGORYLIST

                return mWebservice.getShopCategoryList(url)
            }

        }.asLiveData()
    }
}