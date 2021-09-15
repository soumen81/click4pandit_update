package com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class NewProductOrderModel {
    @SerializedName("returnStatus")
    @Expose
     var returnStatus: String? = null

    @SerializedName("returnErrMsg")
    @Expose
    var returnErrMsg: Any? = null

    @SerializedName("prodOrderId")
    @Expose
    var prodOrderId: Int? = null
}