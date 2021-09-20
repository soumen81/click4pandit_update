package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import android.util.Log
import androidx.lifecycle.LiveData
import com.autumntechcreation.click4panditcustomer.network.*
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper
import com.autumntechcreation.click4panditcustomer.util.AbsentLiveData
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig
import com.google.gson.Gson
import com.google.gson.JsonObject
import javax.inject.Inject

class ShopShippingAddressRepository @Inject constructor(private val mAppExecutors: AppExecutors,
                                                        private  val mWebservice: Webservice, private val mSharedPrefsHelper: SharedPrefsHelper
) {

    fun getDeliveryAddress(prodOrderId:Int,fName:String,lName:String,mobileNo:String,addr1:String,addr2:String,addr3:String,
                           postal:String): LiveData<Resource<DeliveryAddressModel>>?{
        return object : NetworkBoundResource<DeliveryAddressModel, DeliveryAddressModel>(mAppExecutors){
            private var resultsDb: DeliveryAddressModel? = null

            override fun shouldFetch(data: DeliveryAddressModel?): Boolean {
                return true
            }

            override fun saveCallResult(item: DeliveryAddressModel) {
                resultsDb = item
            }

            override fun loadFromDb(): LiveData<DeliveryAddressModel> {
                return if (resultsDb == null) {
                    AbsentLiveData.create()
                } else {
                    object : LiveData<DeliveryAddressModel>() {
                        protected override fun onActive() {
                            super.onActive()
                            value = resultsDb
                        }
                    }
                }
            }
            override fun createCall(): LiveData<ApiResponse<DeliveryAddressModel>> {
                Log.e("BoundResource", "createCall")

                val url = AllUrlsAndConfig.STORE_BASE_URL+ AllUrlsAndConfig.DELIVERYADDRESS
                val str: String? = null
                val gson = Gson()
                var jsonObject= JsonObject()
                val jsonObjCustOrderInfoModel = JsonObject()
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.ISGUUESTUSER, "N")
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.LGGONID, getEmail())
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.PRODORDERID, prodOrderId)
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.PRODORDERAMOUNT, str)
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.TAXAMOUNT, str)
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.CCURID, str)
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.CUSTORDTYPID, str)

                jsonObject.add("CustOrderInfoModel", jsonObjCustOrderInfoModel)
                val jsonObjShippingAddressModel = JsonObject()
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.PRODCUSTADDLINFOID, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.UPDATESTAMPP, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.UPDATEUSERR, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.OORGLSTAMP, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.OORGLUSER, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.DEELFLGG, "N")
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.CCUSTMASTERID, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.PRODCUSTORDID, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.FNAME, fName)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.LNAME, lName)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.MOBB1, mobileNo)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.MOBB2, "N")
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.EMAILADDRES, getEmail())
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.ADDRR1, addr1)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.ADDRR2, addr2)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.ADDRR3, addr3)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.CCCITYID, 1001)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.CCITYDESCR, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.SSTID, 1001)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.SSTDESCR, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.CCNTRYID, 1001)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.CCNTRYDESCR, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.PPOSTAl, postal)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.SHIPPINGADDLINFODESCR, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.SEQNO, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.DSPPORDD, str)
                jsonObject.add("ShippingAddressModel", jsonObjShippingAddressModel)

                val jsonObjbillingAddressModel= JsonObject()
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.PRODCUSBILLINGADDRID, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRUPDTSTAMP, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRUPDTUSER, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRORGLSTAMP, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRORGLUSER, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRDELFLG, "N")
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRCUSTMASTERID, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRPRODCUSTORDID, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRFIRSTNAME, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRLASTNAME, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRMOB1, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRMOB2, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDREMAILADDR, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRADDR1, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRADDR2, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRADDR3, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRCITYID, 1001)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRCITYDESCR, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRSTID, 1001)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRSTDESCR, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRCNTRYID, 1001)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRCNTRYDESCR, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRPOSTAL, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRSEQNO, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRDSPORD, str)
                jsonObject.add("BillingAddressModel", jsonObjbillingAddressModel)
                Log.e("Result", jsonObject.toString())
                return mWebservice.getDeliveryAddress(url,jsonObject )
            }

        }.asLiveData()
    }






    fun getShopBillingAddress(prodOrderId:Int,fName:String,lName:String,mobileNo:String,addr1:String,addr2:String,addr3:String,
                          postal:String,billingFName:String,billingLName:String,billingMobileNo:String,billingAddr1:String,
                          billingAddr2:String,billingAddr3:String,billingPostal:String): LiveData<Resource<DeliveryAddressModel>>?{
        return object : NetworkBoundResource<DeliveryAddressModel, DeliveryAddressModel>(mAppExecutors){
            private var resultsDb: DeliveryAddressModel? = null

            override fun shouldFetch(data: DeliveryAddressModel?): Boolean {
                return true
            }

            override fun saveCallResult(item: DeliveryAddressModel) {
                resultsDb = item
            }

            override fun loadFromDb(): LiveData<DeliveryAddressModel> {
                return if (resultsDb == null) {
                    AbsentLiveData.create()
                } else {
                    object : LiveData<DeliveryAddressModel>() {
                        protected override fun onActive() {
                            super.onActive()
                            value = resultsDb
                        }
                    }
                }
            }
            override fun createCall(): LiveData<ApiResponse<DeliveryAddressModel>> {
                Log.e("BoundResource", "createCall")

                val url = AllUrlsAndConfig.STORE_BASE_URL+ AllUrlsAndConfig.DELIVERYADDRESS
                val str: String? = null
                val gson = Gson()
                var jsonObject= JsonObject()
                val jsonObjCustOrderInfoModel = JsonObject()
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.ISGUUESTUSER, "N")
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.LGGONID, getEmail())
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.PRODORDERID, prodOrderId)
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.PRODORDERAMOUNT, str)
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.TAXAMOUNT, str)
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.CCURID, str)
                jsonObjCustOrderInfoModel.addProperty(AllUrlsAndConfig.CUSTORDTYPID, str)

                jsonObject.add("CustOrderInfoModel", jsonObjCustOrderInfoModel)
                val jsonObjShippingAddressModel = JsonObject()
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.PRODCUSTADDLINFOID, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.UPDATESTAMPP, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.UPDATEUSERR, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.OORGLSTAMP, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.OORGLUSER, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.DEELFLGG, "N")
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.CCUSTMASTERID, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.PRODCUSTORDID, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.FNAME, fName)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.LNAME, lName)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.MOBB1, mobileNo)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.MOBB2, "N")
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.EMAILADDRES, getEmail())
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.ADDRR1, addr1)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.ADDRR2, addr2)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.ADDRR3, addr3)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.CCCITYID, 1001)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.CCITYDESCR, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.SSTID, 1001)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.SSTDESCR, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.CCNTRYID, 1001)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.CCNTRYDESCR, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.PPOSTAl, postal)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.SHIPPINGADDLINFODESCR, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.SEQNO, str)
                jsonObjShippingAddressModel.addProperty(AllUrlsAndConfig.DSPPORDD, str)
                jsonObject.add("ShippingAddressModel", jsonObjShippingAddressModel)

                val jsonObjbillingAddressModel= JsonObject()
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.PRODCUSBILLINGADDRID, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRUPDTSTAMP, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRUPDTUSER, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRORGLSTAMP, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRORGLUSER, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRDELFLG, "N")
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRCUSTMASTERID, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRPRODCUSTORDID, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRFIRSTNAME, billingFName)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRLASTNAME, billingLName)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRMOB1, billingMobileNo)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRMOB2, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDREMAILADDR, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRADDR1, billingAddr1)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRADDR2, billingAddr2)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRADDR3, billingAddr3)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRCITYID, 1001)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRCITYDESCR, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRSTID, 1001)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRSTDESCR, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRCNTRYID, 1001)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRCNTRYDESCR, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRPOSTAL, billingPostal)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRSEQNO, str)
                jsonObjbillingAddressModel.addProperty(AllUrlsAndConfig.BILLINGADDRDSPORD, str)
                jsonObject.add("BillingAddressModel", jsonObjbillingAddressModel)
                Log.e("Result", jsonObject.toString())
                return mWebservice.getDeliveryAddress(url,jsonObject )
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