package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.network.Resource
import javax.inject.Inject

class ShopShippingAddressViewModel @Inject constructor(private val mShopShippingAddressRepository: ShopShippingAddressRepository) : ViewModel() {

    var mDeliveryAddressModelResponse: LiveData<Resource<DeliveryAddressModel>>? = null
    var mBillingAddressModelResponse: LiveData<Resource<DeliveryAddressModel>>? = null



    fun getDeliveryAddress(prodOrderId:Int,fName:String,lName:String,mobileNo:String,addr1:String,addr2:String,addr3:String,
                           postal:String): LiveData<Resource<DeliveryAddressModel>>? {
        mDeliveryAddressModelResponse = MutableLiveData<Resource<DeliveryAddressModel>>()
        mDeliveryAddressModelResponse = mShopShippingAddressRepository.getDeliveryAddress(
            prodOrderId, fName, lName, mobileNo, addr1, addr2, addr3,
            postal)
        return mDeliveryAddressModelResponse
    }


    fun getShopBillingAddress(prodOrderId:Int,fName:String,lName:String,mobileNo:String,addr1:String,addr2:String,addr3:String,
                          postal:String,billingFName:String,billingLName:String,billingMobileNo:String,billingAddr1:String,
                          billingAddr2:String,billingAddr3:String,billingPostal:String): LiveData<Resource<DeliveryAddressModel>>? {

        mBillingAddressModelResponse = MutableLiveData<Resource<DeliveryAddressModel>>()
        mBillingAddressModelResponse = mShopShippingAddressRepository.getShopBillingAddress(prodOrderId, fName, lName, mobileNo, addr1, addr2, addr3,
            postal,billingFName,billingLName,billingMobileNo,billingAddr1,billingAddr2,billingAddr3,billingPostal)

        return mBillingAddressModelResponse
    }

    fun getEmail(): String? {
        return mShopShippingAddressRepository.getEmail()
    }

    fun getFirstName(): String? {
        return mShopShippingAddressRepository.getFirstName()
    }

    fun getLastName(): String? {
        return mShopShippingAddressRepository.getLastName()
    }

    fun getMobile(): String? {
        return mShopShippingAddressRepository.getMobile()
    }
}