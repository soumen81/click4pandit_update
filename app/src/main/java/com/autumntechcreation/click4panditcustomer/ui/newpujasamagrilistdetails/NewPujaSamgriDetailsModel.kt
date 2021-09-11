package com.autumntechcreation.click4panditcustomer.ui.newpujasamagrilistdetails

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class NewPujaSamgriDetailsModel {
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
     var delFlg: String? = null

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

    @SerializedName("parProdMasterId")
    @Expose
    var parProdMasterId: Any? = null

    @SerializedName("prodMasterName")
    @Expose
    var prodMasterName: String? = null

    @SerializedName("prodMasterDscr")
    @Expose
    var prodMasterDscr: String? = null

    @SerializedName("rmks")
    @Expose
    var rmks: String? = null

    @SerializedName("prodPkgUnit")
    @Expose
    var prodPkgUnit: Int? = null

    @SerializedName("prodPkgUnitTypId")
    @Expose
    var prodPkgUnitTypId: Int? = null

    @SerializedName("prodPkgUnitTypDscr")
    @Expose
    var prodPkgUnitTypDscr: String? = null

    @SerializedName("prodUnit")
    @Expose
    var prodUnit: Int? = null

    @SerializedName("prodUnitTypId")
    @Expose
    var prodUnitTypId: Int? = null

    @SerializedName("prodUnitTypDscr")
    @Expose
    var prodUnitTypDscr: String? = null

    @SerializedName("prodWt")
    @Expose
    var prodWt: Any? = null

    @SerializedName("prodWtUmTypId")
    @Expose
    var prodWtUmTypId: Any? = null

    @SerializedName("prodWtUmTypCode")
    @Expose
    var prodWtUmTypCode: String? = null

    @SerializedName("prodWtUmTypDscr")
    @Expose
    var prodWtUmTypDscr: String? = null

    @SerializedName("prodPrice")
    @Expose
    var prodPrice: Double? = null

    @SerializedName("curId")
    @Expose
    var curId: Int? = null

    @SerializedName("curCode")
    @Expose
    var curCode: String? = null

    @SerializedName("curDscr")
    @Expose
    var curDscr: String? = null

    @SerializedName("prodImgModel")
    @Expose
    var prodImgModel: ProdImgModel? = null

    @SerializedName("prodDimDtlModelList")
    @Expose
    var prodDimDtlModelList: List<Any>? = null

    @SerializedName("prodSubItemDtlModelList")
    @Expose
    var prodSubItemDtlModelList: List<Any>? = null


}