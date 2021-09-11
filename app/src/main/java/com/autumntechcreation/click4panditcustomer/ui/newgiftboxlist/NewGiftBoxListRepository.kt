package com.autumntechcreation.click4panditcustomer.ui.newgiftboxlist

import android.util.Log
import androidx.lifecycle.LiveData
import com.autumntechcreation.click4panditcustomer.network.*
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.AddWishListItemModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitAddtoCartOrBuyNowModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig
import com.google.gson.JsonObject
import javax.inject.Inject

class NewGiftBoxListRepository @Inject constructor(
    private val mAppExecutors: AppExecutors, private  val mWebservice: Webservice, private val mSharedPrefsHelper: SharedPrefsHelper
){
    fun getPujaGiftBoxList(): LiveData<Resource<List<NewPujaItemKitListModel>>>? {
        return object : NetworkBoundResource<List<NewPujaItemKitListModel>, List<NewPujaItemKitListModel>>(mAppExecutors) {
            private var resultsDb: List<NewPujaItemKitListModel>? = null

            override fun shouldFetch(data: List<NewPujaItemKitListModel>?): Boolean {
                return true
            }

            override fun saveCallResult(item: List<NewPujaItemKitListModel>) {
                resultsDb = item
            }

            override fun loadFromDb(): LiveData<List<NewPujaItemKitListModel>> {
                return if (resultsDb == null) {
                    AbsentLiveData.create()
                } else {
                    object : LiveData<List<NewPujaItemKitListModel>>() {
                        protected override fun onActive() {
                            super.onActive()
                            value = resultsDb
                        }
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<List<NewPujaItemKitListModel>>> {
                Log.e("BoundResource", "createCall")
                var url: String = "";

                url = AllUrlsAndConfig.STORE_BASE_URL + AllUrlsAndConfig.PRODUCTITEMKITLIST

                val str: String? = null
                var jsonObject = JsonObject()
                jsonObject.addProperty(AllUrlsAndConfig.LOGID,getEmail())
                jsonObject.addProperty(AllUrlsAndConfig.PRODUCTNAME, str)
                jsonObject.addProperty(AllUrlsAndConfig.PRODUCTMASTERID, str)
                jsonObject.addProperty(AllUrlsAndConfig.DELFFLGG, "N")
                jsonObject.addProperty(AllUrlsAndConfig.PAGESSIZE, 12)
                jsonObject.addProperty(AllUrlsAndConfig.PAGEIINDEX, 1)
                jsonObject.addProperty(AllUrlsAndConfig.PRODUCTIONCATEGORYMODELLIST, str)
                jsonObject.addProperty(AllUrlsAndConfig.PRODUCTIONTYPEMODELLIST, str)
                jsonObject.addProperty(AllUrlsAndConfig.PRODUCTIONMTRLTYPEMODELLIST, str)
                jsonObject.addProperty(AllUrlsAndConfig.PRODUCTIONRANGECRITERIAMODELLIST, str)

                return mWebservice.getPujaPujaItemKitList(url,jsonObject)
            }

        }.asLiveData()
    }



    fun getAddtoCartBuyNowForGiftBox(prodMasterId: Int,productPrice:Int): LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>?{
        return object : NetworkBoundResource<NewPujaItemKitAddtoCartOrBuyNowModel, NewPujaItemKitAddtoCartOrBuyNowModel>(mAppExecutors){
            private var resultsDb: NewPujaItemKitAddtoCartOrBuyNowModel? = null

            override fun shouldFetch(data: NewPujaItemKitAddtoCartOrBuyNowModel?): Boolean {
                return true
            }

            override fun saveCallResult(item: NewPujaItemKitAddtoCartOrBuyNowModel) {
                resultsDb = item
            }

            override fun loadFromDb(): LiveData<NewPujaItemKitAddtoCartOrBuyNowModel> {
                return if (resultsDb == null) {
                    AbsentLiveData.create()
                } else {
                    object : LiveData<NewPujaItemKitAddtoCartOrBuyNowModel>() {
                        protected override fun onActive() {
                            super.onActive()
                            value = resultsDb
                        }
                    }
                }
            }
            override fun createCall(): LiveData<ApiResponse<NewPujaItemKitAddtoCartOrBuyNowModel>> {
                Log.e("BoundResource", "createCall")

                val url = AllUrlsAndConfig.STORE_BASE_URL+AllUrlsAndConfig.ADDTOCARTBUYNOWFORPUJASAMAGRI
                val str: String? = null
                var jsonObject= JsonObject()
                jsonObject.addProperty(AllUrlsAndConfig.ISGUESUSERR,"N")
                jsonObject.addProperty(AllUrlsAndConfig.LOOGONID,getEmail())
                jsonObject.addProperty(AllUrlsAndConfig.PRODCUSTSCID,str)
                jsonObject.addProperty(AllUrlsAndConfig.DELLFLGG,"N")
                jsonObject.addProperty(AllUrlsAndConfig.PRODDMASTERID,prodMasterId)
                jsonObject.addProperty(AllUrlsAndConfig.PRODDCUSTSCDT,str)
                jsonObject.addProperty(AllUrlsAndConfig.PRODDCUSTSCQTY,1)
                jsonObject.addProperty(AllUrlsAndConfig.PRODDCUSTSCRATE,productPrice)
                jsonObject.addProperty(AllUrlsAndConfig.CURRID,1001)

                return mWebservice.getAddtoCartBuyNowForPujaSamagri(url,jsonObject )
            }

        }.asLiveData()
    }



    fun getAddItemForWishGiftBoxListItem(prodMasterId:Int,prodCustWishLisQty:Int,prodCustscWishListRate:Double): LiveData<Resource<AddWishListItemModel>>?{
        return object : NetworkBoundResource<AddWishListItemModel, AddWishListItemModel>(mAppExecutors){
            private var resultsDb: AddWishListItemModel? = null

            override fun shouldFetch(data: AddWishListItemModel?): Boolean {
                return true
            }

            override fun saveCallResult(item: AddWishListItemModel) {
                resultsDb = item
            }

            override fun loadFromDb(): LiveData<AddWishListItemModel> {
                return if (resultsDb == null) {
                    AbsentLiveData.create()
                } else {
                    object : LiveData<AddWishListItemModel>() {
                        protected override fun onActive() {
                            super.onActive()
                            value = resultsDb
                        }
                    }
                }
            }
            override fun createCall(): LiveData<ApiResponse<AddWishListItemModel>> {
                Log.e("BoundResource", "createCall")

                val url = AllUrlsAndConfig.STORE_BASE_URL+AllUrlsAndConfig.ADDTOWISHLIST
                val str: String? = null
                var jsonObject= JsonObject()
                jsonObject.addProperty(AllUrlsAndConfig.LOOGOONID,getEmail())
                jsonObject.addProperty(AllUrlsAndConfig.PRODUCTCUSTWISHLISTID,str)
                jsonObject.addProperty(AllUrlsAndConfig.DDELFLG,"N")
                jsonObject.addProperty(AllUrlsAndConfig.PRODMASTERID,prodMasterId)
                jsonObject.addProperty(AllUrlsAndConfig.PRODCUSTSCWISHLISTQTY,prodCustWishLisQty)
                jsonObject.addProperty(AllUrlsAndConfig.PRODCUSTSCWISHLISTRATE,prodCustscWishListRate)
                jsonObject.addProperty(AllUrlsAndConfig.CURIID,1001)

                return mWebservice.getAddWishListItem(url,jsonObject )
            }

        }.asLiveData()
    }

    fun getEmail(): String? {
        return mSharedPrefsHelper[SharedPrefsHelper.EMAIL, null]
    }

}