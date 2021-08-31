package com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class NewPujaItemKitListFactory @Inject constructor(
    private val mNewPujaItemKitListViewModel: NewPujaItemKitListViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewPujaItemKitListViewModel::class.java)) {
            return mNewPujaItemKitListViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}