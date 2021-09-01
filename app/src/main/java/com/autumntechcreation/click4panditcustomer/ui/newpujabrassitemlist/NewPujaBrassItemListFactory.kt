package com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist.NewPujaBoxItemListViewModel
import javax.inject.Inject

class NewPujaBrassItemListFactory @Inject constructor(
    private val mNewPujaBrassItemListViewModel: NewPujaBrassItemListViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewPujaBrassItemListViewModel::class.java)) {
            return mNewPujaBrassItemListViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}