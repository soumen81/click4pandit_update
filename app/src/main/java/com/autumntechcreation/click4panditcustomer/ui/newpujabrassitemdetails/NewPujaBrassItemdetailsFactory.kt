package com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autumntechcreation.click4panditcustomer.ui.newpujasamagrilistdetails.NewPujaSamagriListDetailsViewModel
import javax.inject.Inject

class NewPujaBrassItemdetailsFactory @Inject constructor(
    private val mNewPujaBrassItemDetailsViewModel: NewPujaBrassItemDetailsViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewPujaBrassItemDetailsViewModel::class.java)) {
            return mNewPujaBrassItemDetailsViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}