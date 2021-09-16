package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ProdCustOrdModel {
    @SerializedName("prodCustOrdId")
    @Expose
    var prodCustOrdId: Int? = null

    @SerializedName("updtStamp")
    @Expose
    var updtStamp: String? = null

    @SerializedName("updtUser")
    @Expose
    var updtUser: String? = null

    @SerializedName("orglStamp")
    @Expose
    var orglStamp: String? = null

    @SerializedName("orglUser")
    @Expose
    var orglUser: String? = null

    @SerializedName("delFlg")
    @Expose
    var delFlg: String? = null

    @SerializedName("custMasterId")
    @Expose
    var custMasterId: Int? = null

    @SerializedName("prodCustOrdNo")
    @Expose
    var prodCustOrdNo: String? = null

    @SerializedName("prodCustOrdDt")
    @Expose
    var prodCustOrdDt: String? = null

    @SerializedName("compPanNo")
    @Expose
    var compPanNo: String? = null

    @SerializedName("compGstNo")
    @Expose
    var compGstNo: String? = null
}