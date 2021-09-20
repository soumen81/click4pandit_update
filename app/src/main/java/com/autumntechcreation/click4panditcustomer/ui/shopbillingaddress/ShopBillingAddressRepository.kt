package com.autumntechcreation.click4panditcustomer.ui.shopbillingaddress

import android.util.Log
import androidx.lifecycle.LiveData
import com.autumntechcreation.click4panditcustomer.network.*
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper
import com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress.DeliveryAddressModel
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig
import com.google.gson.Gson
import com.google.gson.JsonObject
import javax.inject.Inject

class ShopBillingAddressRepository @Inject constructor(private val mAppExecutors: AppExecutors,
                                                       private  val mWebservice: Webservice, private val mSharedPrefsHelper: SharedPrefsHelper
) {





    fun getEmail(): String? {
        return mSharedPrefsHelper[SharedPrefsHelper.EMAIL, null]
    }
    fun getcartCount(): String? {
        return mSharedPrefsHelper[SharedPrefsHelper.CARTCOUNT, null]
    }
    fun storeUpdateCartCount(updateCartCount: String?) {
        mSharedPrefsHelper.put(SharedPrefsHelper.UPDATECARTCOUNT, updateCartCount)
    }
    fun getFirstName(): String? {
        return mSharedPrefsHelper[SharedPrefsHelper.FIRSTNAME, null]
    }

    fun getLastName(): String? {
        return mSharedPrefsHelper[SharedPrefsHelper.LASTNAME, null]
    }

    fun getMobile(): String? {
        return mSharedPrefsHelper[SharedPrefsHelper.MOBILE, null]
    }



}