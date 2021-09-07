package com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListViewModel
import javax.inject.Inject

class NewAddtoCartListFactory @Inject constructor(
    private val mNewAddtoCartListViewModel: NewAddtoCartListViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewAddtoCartListViewModel::class.java)) {
            return mNewAddtoCartListViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}