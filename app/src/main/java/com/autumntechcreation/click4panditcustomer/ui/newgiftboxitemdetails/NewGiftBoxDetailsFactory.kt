package com.autumntechcreation.click4panditcustomer.ui.newgiftboxitemdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemdetails.NewPujaBoxItemDetailsViewModel
import javax.inject.Inject

class NewGiftBoxDetailsFactory  @Inject constructor(
    private val mNewGiftBoxDetailsViewModel: NewGiftBoxDetailsViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewGiftBoxDetailsViewModel::class.java)) {
            return mNewGiftBoxDetailsViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}