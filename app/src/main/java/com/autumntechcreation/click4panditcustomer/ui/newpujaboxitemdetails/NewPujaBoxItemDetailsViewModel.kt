package com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemdetails.NewPujaBrassItemDetailsRepository
import com.autumntechcreation.click4panditcustomer.ui.newpujasamagrilistdetails.NewPujaSamgriDetailsModel
import javax.inject.Inject

class NewPujaBoxItemDetailsViewModel @Inject constructor(private val mNewPujaBoxItemDetailsRepository: NewPujaBoxItemDetailsRepository) : ViewModel() {

    var NewPujaSamgriDetailsLiveData: LiveData<Resource<NewPujaSamgriDetailsModel>>? = null

    fun getpujaSamagriDetails(prodMasterId: Int): LiveData<Resource<NewPujaSamgriDetailsModel>> {
        NewPujaSamgriDetailsLiveData = MutableLiveData()
        NewPujaSamgriDetailsLiveData = mNewPujaBoxItemDetailsRepository.getpujaSamagriDetails(prodMasterId)
        return NewPujaSamgriDetailsLiveData as LiveData<Resource<NewPujaSamgriDetailsModel>>

    }
}