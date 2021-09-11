package com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemdetails.NewPujaBrassItemDetailsViewModel
import javax.inject.Inject

class NewPujaBoxItemDetailsFactory @Inject constructor(
    private val mNewPujaBoxItemDetailsViewModel: NewPujaBoxItemDetailsViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewPujaBoxItemDetailsViewModel::class.java)) {
            return mNewPujaBoxItemDetailsViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}