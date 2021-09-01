package com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemlist

import android.util.Log
import androidx.lifecycle.LiveData
import com.autumntechcreation.click4panditcustomer.network.*
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig
import com.google.gson.JsonObject
import javax.inject.Inject

class NewPujaBrassItemListRepository @Inject constructor(
    private val mAppExecutors: AppExecutors, private  val mWebservice: Webservice, private val mSharedPrefsHelper: SharedPrefsHelper
){

    fun getPujaItemBrassList(): LiveData<Resource<List<NewPujaItemKitListModel>>>? {
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
    fun getEmail(): String? {
        return mSharedPrefsHelper[SharedPrefsHelper.EMAIL, null]
    }
}