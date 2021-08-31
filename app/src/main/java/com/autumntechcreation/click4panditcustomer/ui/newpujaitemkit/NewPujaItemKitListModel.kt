package com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class NewPujaItemKitListModel {
    @SerializedName("totalCount")
    @Expose
     var totalCount: Int? = null

    @SerializedName("rowNo")
    @Expose
     var rowNo: Int? = null

    @SerializedName("prodMasterId")
    @Expose
     var prodMasterId: Int? = null

    @SerializedName("prodCtgryId")
    @Expose
     var prodCtgryId: Int? = null

    @SerializedName("prodCtgryDscr")
    @Expose
     var prodCtgryDscr: String? = null

    @SerializedName("prodTypId")
    @Expose
     var prodTypId: Int? = null

    @SerializedName("prodTypDscr")
    @Expose
     var prodTypDscr: String? = null

    @SerializedName("prodMasterName")
    @Expose
     var prodMasterName: String? = null

    @SerializedName("prodMtrlTypId")
    @Expose
     var prodMtrlTypId: Int? = null

    @SerializedName("prodMtrlTypDscr")
    @Expose
     var prodMtrlTypDscr: String? = null

    @SerializedName("prodWt")
    @Expose
     var prodWt: Double? = null

    @SerializedName("prodWtUmTypId")
    @Expose
     var prodWtUmTypId: Int? = null

    @SerializedName("prodWtDscr")
    @Expose
     var prodWtDscr: String? = null

    @SerializedName("prodUnit")
    @Expose
     var prodUnit: Int? = null

    @SerializedName("prodUnitTypDscr")
    @Expose
     var prodUnitTypDscr: String? = null

    @SerializedName("prodPkgUnit")
    @Expose
     var prodPkgUnit: Int? = null

    @SerializedName("prodPkgTypDscr")
    @Expose
     var prodPkgTypDscr: String? = null

    @SerializedName("curCode")
    @Expose
     var curCode: String? = null

    @SerializedName("prodPrice")
    @Expose
     var prodPrice: Double? = null

    @SerializedName("delFlg")
    @Expose
     var delFlg: String? = null

    @SerializedName("cloudImgId")
    @Expose
     var cloudImgId: String? = null

    @SerializedName("prodImgDataURL")
    @Expose
     var prodImgDataURL: String? = null


}