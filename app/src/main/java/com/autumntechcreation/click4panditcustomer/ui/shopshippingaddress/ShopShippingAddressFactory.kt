package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autumntechcreation.click4panditcustomer.ui.shopcategory.ShopCategoryViewModel
import javax.inject.Inject

class ShopShippingAddressFactory @Inject constructor(
    private val mShopShippingAddressViewModel: ShopShippingAddressViewModel
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShopShippingAddressViewModel::class.java)) {
            return mShopShippingAddressViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}