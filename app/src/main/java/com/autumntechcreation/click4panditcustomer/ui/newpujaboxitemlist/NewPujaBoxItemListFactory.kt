package com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListViewModel
import javax.inject.Inject

class NewPujaBoxItemListFactory  @Inject constructor(
    private val mNewPujaBoxItemListViewModel: NewPujaBoxItemListViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewPujaBoxItemListViewModel::class.java)) {
            return mNewPujaBoxItemListViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}