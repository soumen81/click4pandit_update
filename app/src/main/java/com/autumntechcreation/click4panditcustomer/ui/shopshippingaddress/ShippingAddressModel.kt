package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ShippingAddressModel {
    @SerializedName("prodCustAddlInfoId")
    @Expose
    var prodCustAddlInfoId: Int? = null

    @SerializedName("updtStamp")
    @Expose
    var updtStamp: String? = null

    @SerializedName("updtUser")
    @Expose
     val updtUser: String? = null

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

    @SerializedName("prodCustOrdId")
    @Expose
    var prodCustOrdId: Int? = null

    @SerializedName("firstName")
    @Expose
    var firstName: String? = null

    @SerializedName("lastName")
    @Expose
    var lastName: String? = null

    @SerializedName("mob1")
    @Expose
    var mob1: String? = null

    @SerializedName("mob2")
    @Expose
    var mob2: String? = null

    @SerializedName("emailAddr")
    @Expose
    var emailAddr: String? = null

    @SerializedName("addr1")
    @Expose
    var addr1: String? = null

    @SerializedName("addr2")
    @Expose
    var addr2: String? = null

    @SerializedName("addr3")
    @Expose
    var addr3: String? = null

    @SerializedName("ctyId")
    @Expose
    var ctyId: Int? = null

    @SerializedName("cityDescr")
    @Expose
    var cityDescr: String? = null

    @SerializedName("stId")
    @Expose
    var stId: Int? = null

    @SerializedName("stDescr")
    @Expose
    var stDescr: String? = null

    @SerializedName("cntryId")
    @Expose
    var cntryId: Int? = null

    @SerializedName("cntryDescr")
    @Expose
    var cntryDescr: String? = null

    @SerializedName("postal")
    @Expose
    var postal: String? = null

    @SerializedName("shippingAddlInfoDscr")
    @Expose
    var shippingAddlInfoDscr: Any? = null

    @SerializedName("seqNo")
    @Expose
    var seqNo: Any? = null

    @SerializedName("dspOrd")
    @Expose
    var dspOrd: Any? = null
}