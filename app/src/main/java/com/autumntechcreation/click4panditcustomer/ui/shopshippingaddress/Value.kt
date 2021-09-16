package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Value {
    @SerializedName("shippingAddressModel")
    @Expose
    private val shippingAddressModel: ShippingAddressModel? = null

    @SerializedName("billingAddressModel")
    @Expose
    private val billingAddressModel: BillingAddressModel? = null

    @SerializedName("prodCustOrdModel")
    @Expose
    private val prodCustOrdModel: ProdCustOrdModel? = null

    @SerializedName("custOrderInfoModel")
    @Expose
    private val custOrderInfoModel: CustOrderInfoModel? = null

    @SerializedName("prodCustOrdDtlModelList")
    @Expose
    private val prodCustOrdDtlModelList: List<ProdCustOrdDtlModel>? = null
}