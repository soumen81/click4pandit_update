package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Value {
    @SerializedName("shippingAddressModel")
    @Expose
     var shippingAddressModel: ShippingAddressModel? = null

    @SerializedName("billingAddressModel")
    @Expose
    var billingAddressModel: BillingAddressModel? = null

    @SerializedName("prodCustOrdModel")
    @Expose
    var prodCustOrdModel: ProdCustOrdModel? = null

    @SerializedName("custOrderInfoModel")
    @Expose
    var custOrderInfoModel: CustOrderInfoModel? = null

    @SerializedName("prodCustOrdDtlModelList")
    @Expose
    var prodCustOrdDtlModelList: List<ProdCustOrdDtlModel>? = null
}