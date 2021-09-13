package com.autumntechcreation.click4panditcustomer.ui.newgiftboxitemdetails

import android.util.Log
import androidx.lifecycle.LiveData
import com.autumntechcreation.click4panditcustomer.network.*
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper
import com.autumntechcreation.click4panditcustomer.ui.newpujasamagrilistdetails.NewPujaSamgriDetailsModel
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig
import com.google.gson.JsonObject
import javax.inject.Inject

class NewGiftBoxDetailsRepository @Inject constructor(
    private val mAppExecutors: AppExecutors, private  val mWebservice: Webservice, private val mSharedPrefsHelper: SharedPrefsHelper
){
    fun getpujaSamagriDetails(prodMasterId: Int): LiveData<Resource<NewPujaSamgriDetailsModel>>?{
        return object : NetworkBoundResource<NewPujaSamgriDetailsModel, NewPujaSamgriDetailsModel>(mAppExecutors){
            private var resultsDb: NewPujaSamgriDetailsModel? = null

            override fun shouldFetch(data: NewPujaSamgriDetailsModel?): Boolean {
                return true
            }

            override fun saveCallResult(item: NewPujaSamgriDetailsModel) {
                resultsDb = item
            }

            override fun loadFromDb(): LiveData<NewPujaSamgriDetailsModel> {
                return if (resultsDb == null) {
                    AbsentLiveData.create()
                } else {
                    object : LiveData<NewPujaSamgriDetailsModel>() {
                        protected override fun onActive() {
                            super.onActive()
                            value = resultsDb
                        }
                    }
                }
            }
            override fun createCall(): LiveData<ApiResponse<NewPujaSamgriDetailsModel>> {
                Log.e("BoundResource", "createCall")

                val url = AllUrlsAndConfig.STORE_BASE_URL+ AllUrlsAndConfig.PUJASAMAGRIDETAILS
                val str: String? = null
                var jsonObject= JsonObject()
                jsonObject.addProperty(AllUrlsAndConfig.PROMASTERID,prodMasterId)


                return mWebservice.getPujaSamagriDetails(url,jsonObject )
            }

        }.asLiveData()
    }


    fun getEmail(): String? {
        return mSharedPrefsHelper[SharedPrefsHelper.EMAIL, null]
    }



}