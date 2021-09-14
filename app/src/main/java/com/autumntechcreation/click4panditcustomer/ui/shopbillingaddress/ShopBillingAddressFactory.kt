package com.autumntechcreation.click4panditcustomer.ui.shopbillingaddress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress.ShopShippingAddressViewModel
import javax.inject.Inject

class ShopBillingAddressFactory @Inject constructor(
    private val mShopBillingAddressViewModel: ShopBillingAddressViewModel
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShopBillingAddressViewModel::class.java)) {
            return mShopBillingAddressViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}