package com.autumntechcreation.click4panditcustomer.ui.newwishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist.NewAddtoCartListViewModel
import javax.inject.Inject

class NewWishListFactory @Inject constructor(
    private val mNewWishListViewModel: NewWishListViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewWishListViewModel::class.java)) {
            return mNewWishListViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}