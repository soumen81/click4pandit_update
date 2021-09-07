package com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit

import com.autumntechcreation.click4panditcustomer.ui.home.ReturnCartValue

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class NewPujaItemKitAddtoCartOrBuyNowModel {
    @SerializedName("returnStatus")
    @Expose
     var returnStatus: String? = null

    @SerializedName("returnErrMsg")
    @Expose
     var returnErrMsg: Any? = null

    @SerializedName("returnCartValue")
    @Expose
     var returnCartValue: ReturnCartValue? = null


}