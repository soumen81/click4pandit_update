package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import com.autumntechcreation.click4panditcustomer.network.AppExecutors
import com.autumntechcreation.click4panditcustomer.network.Webservice
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper
import javax.inject.Inject

class ShopShippingAddressRepository @Inject constructor(private val mAppExecutors: AppExecutors,
                                                        private  val mWebservice: Webservice, private val mSharedPrefsHelper: SharedPrefsHelper
) {
}