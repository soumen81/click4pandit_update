package com.autumntechcreation.click4panditcustomer.ui.shopcategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ShopCategoryFactory @Inject constructor(
    private val mShopCategoryViewModel: ShopCategoryViewModel
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShopCategoryViewModel::class.java)) {
            return mShopCategoryViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}