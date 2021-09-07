package com.autumntechcreation.click4panditcustomer.ui.newwishlist

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class DeleteWishListModel {
    @SerializedName("returnStatus")
    @Expose
     var returnStatus: String? = null

    @SerializedName("returnErrMsg")
    @Expose
     var returnErrMsg: Any? = null

    @SerializedName("prodCustWshlstId")
    @Expose
     var prodCustWshlstId: Int? = null
}