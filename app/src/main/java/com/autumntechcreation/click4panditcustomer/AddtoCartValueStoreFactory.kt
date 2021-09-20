package com.autumntechcreation.click4panditcustomer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autumntechcreation.click4panditcustomer.ui.newgiftboxitemdetails.NewGiftBoxDetailsViewModel
import javax.inject.Inject

class AddtoCartValueStoreFactory @Inject constructor(
    private val mAddtoCartValueStoreViewModel: AddtoCartValueStoreViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddtoCartValueStoreViewModel::class.java)) {
            return mAddtoCartValueStoreViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}