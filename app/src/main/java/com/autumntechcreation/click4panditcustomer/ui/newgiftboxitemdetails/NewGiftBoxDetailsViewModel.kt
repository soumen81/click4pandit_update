package com.autumntechcreation.click4panditcustomer.ui.newgiftboxitemdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemdetails.NewPujaBoxItemDetailsRepository
import com.autumntechcreation.click4panditcustomer.ui.newpujasamagrilistdetails.NewPujaSamgriDetailsModel
import javax.inject.Inject

class NewGiftBoxDetailsViewModel @Inject constructor(private val mNewGiftBoxDetailsRepository: NewGiftBoxDetailsRepository) : ViewModel() {

    var NewPujaSamgriDetailsLiveData: LiveData<Resource<NewPujaSamgriDetailsModel>>? = null

    fun getpujaSamagriDetails(prodMasterId: Int): LiveData<Resource<NewPujaSamgriDetailsModel>> {
        NewPujaSamgriDetailsLiveData = MutableLiveData()
        NewPujaSamgriDetailsLiveData = mNewGiftBoxDetailsRepository.getpujaSamagriDetails(prodMasterId)
        return NewPujaSamgriDetailsLiveData as LiveData<Resource<NewPujaSamgriDetailsModel>>

    }
}