package com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist

import android.util.Log
import androidx.lifecycle.LiveData
import com.autumntechcreation.click4panditcustomer.network.*
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitAddtoCartOrBuyNowModel
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import javax.inject.Inject

class NewAddtoCartListRepository @Inject constructor(
    private val mAppExecutors: AppExecutors, private  val mWebservice: Webservice, private val mSharedPrefsHelper: SharedPrefsHelper
){
    fun getNewAddtoCartList(): LiveData<Resource<List<NewAddtoCartListModel>>>? {
        return object : NetworkBoundResource<List<NewAddtoCartListModel>, List<NewAddtoCartListModel>>(mAppExecutors) {
            private var resultsDb: List<NewAddtoCartListModel>? = null

            override fun shouldFetch(data: List<NewAddtoCartListModel>?): Boolean {
                return true
            }

            override fun saveCallResult(item: List<NewAddtoCartListModel>) {
                resultsDb = item
            }

            override fun loadFromDb(): LiveData<List<NewAddtoCartListModel>> {
                return if (resultsDb == null) {
                    AbsentLiveData.create()
                } else {
                    object : LiveData<List<NewAddtoCartListModel>>() {
                        protected override fun onActive() {
                            super.onActive()
                            value = resultsDb
                        }
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<List<NewAddtoCartListModel>>> {
                Log.e("BoundResource", "createCall")
                var url: String = "";

                url = AllUrlsAndConfig.STORE_BASE_URL + AllUrlsAndConfig.SHOPPINGCARTITEMLIST
                var jsonObject = JsonObject()
                jsonObject.addProperty(AllUrlsAndConfig.LOGID,getEmail())
                return mWebservice.getNewAddtoCartList(url,jsonObject)
            }

        }.asLiveData()
    }




    fun getAddtoCartBuyNowForPujaSamagri(prodMasterId: Int,productPrice:Int): LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>?{
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





    fun getRemoveForPujaItem(productCustScId: Int,prodMasterId:Int,productPrice:Double,prodCustScDate:String,updateCartQuantity:Int): LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>?{
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

                val url = AllUrlsAndConfig.STORE_BASE_URL+AllUrlsAndConfig.REMOVECARTITEMLIST
                val str: String? = null
                var jsonObject= JsonObject()
                jsonObject.addProperty(AllUrlsAndConfig.ISGUESUSERR,"N")
                jsonObject.addProperty(AllUrlsAndConfig.LOOGONID,getEmail())
                jsonObject.addProperty(AllUrlsAndConfig.PRODCUSTSCID,productCustScId)
                jsonObject.addProperty(AllUrlsAndConfig.DELLFLGG,"Y")
                jsonObject.addProperty(AllUrlsAndConfig.PRODDMASTERID,prodMasterId)
                jsonObject.addProperty(AllUrlsAndConfig.PRODDCUSTSCDT,prodCustScDate)
                jsonObject.addProperty(AllUrlsAndConfig.PRODDCUSTSCQTY,updateCartQuantity)
                jsonObject.addProperty(AllUrlsAndConfig.PRODDCUSTSCRATE,productPrice)
                jsonObject.addProperty(AllUrlsAndConfig.CURRID,1001)

                return mWebservice.getAddtoCartBuyNowForPujaSamagri(url,jsonObject )
            }

        }.asLiveData()
    }



    fun getUpdateAddToCart(productCustScId:Int,prodMasterId: Int,productPrice:Double,productCustScDate:String,prodCustscQty:Int): LiveData<Resource<UpdateCartItemCountModel>>?{
        return object : NetworkBoundResource<UpdateCartItemCountModel, UpdateCartItemCountModel>(mAppExecutors){
            private var resultsDb: UpdateCartItemCountModel? = null

            override fun shouldFetch(data: UpdateCartItemCountModel?): Boolean {
                return true
            }

            override fun saveCallResult(item: UpdateCartItemCountModel) {
                resultsDb = item
            }

            override fun loadFromDb(): LiveData<UpdateCartItemCountModel> {
                return if (resultsDb == null) {
                    AbsentLiveData.create()
                } else {
                    object : LiveData<UpdateCartItemCountModel>() {
                        protected override fun onActive() {
                            super.onActive()
                            value = resultsDb
                        }
                    }
                }
            }
            override fun createCall(): LiveData<ApiResponse<UpdateCartItemCountModel>> {
                Log.e("BoundResource", "createCall")

                val url = AllUrlsAndConfig.STORE_BASE_URL+AllUrlsAndConfig.UPDATECARTITEM
                val str: String? = null
                var jsonObject= JsonObject()
                jsonObject.addProperty(AllUrlsAndConfig.ISGUESUSERR,"N")
                jsonObject.addProperty(AllUrlsAndConfig.LOOGONID,getEmail())
                jsonObject.addProperty(AllUrlsAndConfig.PRODCUSTSCID,productCustScId)
                jsonObject.addProperty(AllUrlsAndConfig.DELLFLGG,"N")
                jsonObject.addProperty(AllUrlsAndConfig.PRODDMASTERID,prodMasterId)
                jsonObject.addProperty(AllUrlsAndConfig.PRODDCUSTSCDT,productCustScDate)
                jsonObject.addProperty(AllUrlsAndConfig.PRODDCUSTSCQTY,prodCustscQty)
                jsonObject.addProperty(AllUrlsAndConfig.PRODDCUSTSCRATE,productPrice)
                jsonObject.addProperty(AllUrlsAndConfig.CURRID,1001)

                return mWebservice.getUpdateForPujaSamagri(url,jsonObject )
            }

        }.asLiveData()
    }




    fun getNewProductOrder(listNewAddtoCartModel:List<NewAddtoCartListModel>): LiveData<Resource<NewProductOrderModel>>?{
        return object : NetworkBoundResource<NewProductOrderModel, NewProductOrderModel>(mAppExecutors){
            private var resultsDb: NewProductOrderModel? = null

            override fun shouldFetch(data: NewProductOrderModel?): Boolean {
                return true
            }

            override fun saveCallResult(item: NewProductOrderModel) {
                resultsDb = item
            }

            override fun loadFromDb(): LiveData<NewProductOrderModel> {
                return if (resultsDb == null) {
                    AbsentLiveData.create()
                } else {
                    object : LiveData<NewProductOrderModel>() {
                        protected override fun onActive() {
                            super.onActive()
                            value = resultsDb
                        }
                    }
                }
            }
            override fun createCall(): LiveData<ApiResponse<NewProductOrderModel>> {
                Log.e("BoundResource", "createCall")

                val url = AllUrlsAndConfig.STORE_BASE_URL+ AllUrlsAndConfig.NEWPRODORDER
                val str: String? = null
                val gson = Gson()
                var jsonObject= JsonObject()
                val jsonObjCustOrderInfoModel = JsonObject()
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.ISSGUESTUSER, "N")
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.LOGGGONIDD, getEmail())
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.CUSTORDTYPEID, 1003)
                jsonObject.add("CustOrderInfoModel", jsonObjCustOrderInfoModel)

                //ProdCustScModelList
                val jelemInvoice5: JsonElement = gson.fromJson<JsonElement>(gson.toJson(listNewAddtoCartModel),
                    JsonElement::class.java)
                val jsonProdCustScModelList = JsonArray()
                val jobj5 = jelemInvoice5.asJsonArray
                jsonObject.add("ProdCustScModelList", jobj5)
                Log.e("ResultNewOroductOrder", jsonObject.toString())

                return mWebservice.getNewProductOrder(url,jsonObject )
            }

        }.asLiveData()
    }


    fun getEmail(): String? {
        return mSharedPrefsHelper[SharedPrefsHelper.EMAIL, null]
    }
    fun getcartCount(): String? {
        return mSharedPrefsHelper[SharedPrefsHelper.CARTCOUNT, null]
    }
    fun storeUpdateCartCount(updateCartCount: String?) {
        mSharedPrefsHelper.put(SharedPrefsHelper.UPDATECARTCOUNT, updateCartCount)
    }
}