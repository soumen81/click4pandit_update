package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class CustOrderInfoModel {
    @SerializedName("isGuestUser")
    @Expose
    var isGuestUser: Any? = null

    @SerializedName("logonId")
    @Expose
    var logonId: Any? = null

    @SerializedName("prodOrderId")
    @Expose
    var prodOrderId: Any? = null

    @SerializedName("prodOrderAmount")
    @Expose
    var prodOrderAmount: Int? = null

    @SerializedName("taxAmount")
    @Expose
    var taxAmount: Int? = null

    @SerializedName("curId")
    @Expose
    var curId: Int? = null

    @SerializedName("custOrdTypId")
    @Expose
    var custOrdTypId: Any? = null
}