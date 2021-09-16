package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class DeliveryAddressModel {
    @SerializedName("returnStatus")
    @Expose
     var returnStatus: String? = null

    @SerializedName("returnErrMsg")
    @Expose
    var returnErrMsg: Any? = null

    @SerializedName("value")
    @Expose
    var value: Value? = null
}