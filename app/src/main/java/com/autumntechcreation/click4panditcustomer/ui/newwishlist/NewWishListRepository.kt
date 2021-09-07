package com.autumntechcreation.click4panditcustomer.ui.newwishlist

import android.util.Log
import androidx.lifecycle.LiveData
import com.autumntechcreation.click4panditcustomer.network.*
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper
import com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist.NewAddtoCartListModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitAddtoCartOrBuyNowModel
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig
import com.google.gson.JsonObject
import javax.inject.Inject

class NewWishListRepository @Inject constructor(
    private val mAppExecutors: AppExecutors, private  val mWebservice: Webservice, private val mSharedPrefsHelper: SharedPrefsHelper
){

    fun getWishListItem(): LiveData<Resource<List<NewWishListItemModel>>>? {
        return object : NetworkBoundResource<List<NewWishListItemModel>, List<NewWishListItemModel>>(mAppExecutors) {
            private var resultsDb: List<NewWishListItemModel>? = null

            override fun shouldFetch(data: List<NewWishListItemModel>?): Boolean {
                return true
            }

            override fun saveCallResult(item: List<NewWishListItemModel>) {
                resultsDb = item
            }

            override fun loadFromDb(): LiveData<List<NewWishListItemModel>> {
                return if (resultsDb == null) {
                    AbsentLiveData.create()
                } else {
                    object : LiveData<List<NewWishListItemModel>>() {
                        protected override fun onActive() {
                            super.onActive()
                            value = resultsDb
                        }
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<List<NewWishListItemModel>>> {
                Log.e("BoundResource", "createCall")
                var url: String = "";

                url = AllUrlsAndConfig.STORE_BASE_URL + AllUrlsAndConfig.WISHLISTITEM
                var jsonObject = JsonObject()
                jsonObject.addProperty(AllUrlsAndConfig.LOGID,getEmail())
                return mWebservice.getNewWishList(url,jsonObject)
            }

        }.asLiveData()
    }

    fun getRemoveForWishListItem(productCustWishListId:Int,prodMasterId:Int,prodCustWishLisQty:Int,prodCustscWishListRate:Double): LiveData<Resource<DeleteWishListModel>>?{
        return object : NetworkBoundResource<DeleteWishListModel, DeleteWishListModel>(mAppExecutors){
            private var resultsDb: DeleteWishListModel? = null

            override fun shouldFetch(data: DeleteWishListModel?): Boolean {
                return true
            }

            override fun saveCallResult(item: DeleteWishListModel) {
                resultsDb = item
            }

            override fun loadFromDb(): LiveData<DeleteWishListModel> {
                return if (resultsDb == null) {
                    AbsentLiveData.create()
                } else {
                    object : LiveData<DeleteWishListModel>() {
                        protected override fun onActive() {
                            super.onActive()
                            value = resultsDb
                        }
                    }
                }
            }
            override fun createCall(): LiveData<ApiResponse<DeleteWishListModel>> {
                Log.e("BoundResource", "createCall")

                val url = AllUrlsAndConfig.STORE_BASE_URL+AllUrlsAndConfig.REMOVEWISHLISTITEM
                val str: String? = null
                var jsonObject= JsonObject()
                jsonObject.addProperty(AllUrlsAndConfig.LOOGOONID,getEmail())
                jsonObject.addProperty(AllUrlsAndConfig.PRODUCTCUSTWISHLISTID,productCustWishListId)
                jsonObject.addProperty(AllUrlsAndConfig.DDELFLG,"Y")
                jsonObject.addProperty(AllUrlsAndConfig.PRODMASTERID,prodMasterId)
                jsonObject.addProperty(AllUrlsAndConfig.PRODCUSTSCWISHLISTQTY,prodCustWishLisQty)
                jsonObject.addProperty(AllUrlsAndConfig.PRODCUSTSCWISHLISTRATE,prodCustscWishListRate)
                jsonObject.addProperty(AllUrlsAndConfig.CURIID,1001)

                return mWebservice.getDeleteWishListItem(url,jsonObject )
            }

        }.asLiveData()
    }

    fun getEmail(): String? {
        return mSharedPrefsHelper[SharedPrefsHelper.EMAIL, null]
    }
}