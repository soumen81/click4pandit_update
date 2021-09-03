package com.autumntechcreation.click4panditcustomer.ui.newgiftboxlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist.NewPujaBoxItemListViewModel
import javax.inject.Inject

class NewGiftBoxListFactory @Inject constructor(
    private val mNewGiftBoxListViewModel: NewGiftBoxListViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewGiftBoxListViewModel::class.java)) {
            return mNewGiftBoxListViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}