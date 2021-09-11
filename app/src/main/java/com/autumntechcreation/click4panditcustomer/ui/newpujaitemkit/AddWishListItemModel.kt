package com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class AddWishListItemModel {
    @SerializedName("returnStatus")
    @Expose
     var returnStatus: String? = null

    @SerializedName("returnErrMsg")
    @Expose
     var returnErrMsg: Any? = null

    @SerializedName("prodCustWshlstId")
    @Expose
     var prodCustWshlstId: Int? = null

    @SerializedName("isSelect")
    @Expose
    var isSelect = false
}