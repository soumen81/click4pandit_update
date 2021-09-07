package com.autumntechcreation.click4panditcustomer.ui.newwishlist

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class NewWishListItemModel {
    @SerializedName("prodCustWshlstId")
    @Expose
     var prodCustWshlstId: Int? = null

    @SerializedName("prodCustWshlstDt")
    @Expose
    var prodCustWshlstDt: String? = null

    @SerializedName("prodMasterId")
    @Expose
    var prodMasterId: Int? = null

    @SerializedName("prodMasterName")
    @Expose
    var prodMasterName: String? = null

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

    @SerializedName("prodMtrlTypId")
    @Expose
    var prodMtrlTypId: Int? = null

    @SerializedName("prodMtrlTypDscr")
    @Expose
    var prodMtrlTypDscr: String? = null

    @SerializedName("prodUnit")
    @Expose
    var prodUnit: Int? = null

    @SerializedName("prodUnitTypId")
    @Expose
    var prodUnitTypId: Int? = null

    @SerializedName("prodUnitTypDscr")
    @Expose
    var prodUnitTypDscr: String? = null

    @SerializedName("prodPkgUnit")
    @Expose
    var prodPkgUnit: Int? = null

    @SerializedName("prodPkgUnitTypId")
    @Expose
    var prodPkgUnitTypId: Int? = null

    @SerializedName("prodPkgUnitTypDscr")
    @Expose
    var prodPkgUnitTypDscr: String? = null

    @SerializedName("prodWt")
    @Expose
    var prodWt: Double? = null

    @SerializedName("prodWtUmTypId")
    @Expose
    var prodWtUmTypId: Int? = null

    @SerializedName("prodWtUmTypDscr")
    @Expose
    var prodWtUmTypDscr: String? = null

    @SerializedName("prodCustWshlstQty")
    @Expose
    var prodCustWshlstQty: Int? = null

    @SerializedName("prodCustWshlstRate")
    @Expose
    var prodCustWshlstRate: Double? = null

    @SerializedName("curId")
    @Expose
    var curId: Int? = null

    @SerializedName("cloudImgId")
    @Expose
    var cloudImgId: String? = null

    @SerializedName("prodImgDataURL")
    @Expose
    var prodImgDataURL: String? = null
}