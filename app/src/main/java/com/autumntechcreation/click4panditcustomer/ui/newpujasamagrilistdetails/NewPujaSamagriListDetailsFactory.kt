package com.autumntechcreation.click4panditcustomer.ui.newpujasamagrilistdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListViewModel
import javax.inject.Inject

class NewPujaSamagriListDetailsFactory @Inject constructor(
    private val mNewPujaSamagriListDetailsViewModel: NewPujaSamagriListDetailsViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewPujaSamagriListDetailsViewModel::class.java)) {
            return mNewPujaSamagriListDetailsViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}