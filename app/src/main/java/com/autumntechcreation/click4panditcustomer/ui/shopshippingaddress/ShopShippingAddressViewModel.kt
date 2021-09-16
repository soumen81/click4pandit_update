package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.ProceedtoPayModel
import javax.inject.Inject

class ShopShippingAddressViewModel @Inject constructor(private val mShopShippingAddressRepository: ShopShippingAddressRepository) : ViewModel() {

    var mDeliveryAddressModelResponse: LiveData<Resource<DeliveryAddressModel>>? = null

    fun getDeliveryAddress(prodOrderId:Int,fName:String,lName:String,mobileNo:String,addr1:String,addr2:String,addr3:String,
                           postal:String
    ): LiveData<Resource<DeliveryAddressModel>>? {
        mDeliveryAddressModelResponse = MutableLiveData<Resource<DeliveryAddressModel>>()
        mDeliveryAddressModelResponse = mShopShippingAddressRepository.getDeliveryAddress(
            prodOrderId, fName, lName, mobileNo, addr1, addr2, addr3,
            postal)
        return mDeliveryAddressModelResponse
    }
}