package com.autumntechcreation.click4panditcustomer.ui.newpujasamagrilistdetails

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ProdImgModel {
    @SerializedName("prodImgId")
    @Expose
     var prodImgId: Any? = null

    @SerializedName("prodMasterId")
    @Expose
    var prodMasterId: Int? = null

    @SerializedName("updtStamp")
    @Expose
    var updtStamp: Any? = null

    @SerializedName("updtUser")
    @Expose
    var updtUser: Any? = null

    @SerializedName("orglStamp")
    @Expose
    var orglStamp: Any? = null

    @SerializedName("orglUser")
    @Expose
    var orglUser: Any? = null

    @SerializedName("delFlg")
    @Expose
    var delFlg: Any? = null

    @SerializedName("cloudImgId")
    @Expose
    var cloudImgId: String? = null

    @SerializedName("prodImgDataURL")
    @Expose
    var prodImgDataURL: String? = null

    @SerializedName("orglFileName")
    @Expose
    var orglFileName: String? = null

    @SerializedName("cloudFileName")
    @Expose
    var cloudFileName: String? = null

    @SerializedName("mimeTyp")
    @Expose
    var mimeTyp: String? = null


}