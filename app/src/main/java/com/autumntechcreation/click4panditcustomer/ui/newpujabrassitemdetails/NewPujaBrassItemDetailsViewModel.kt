package com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.newpujasamagrilistdetails.NewPujaSamagriListDetailsRepository
import com.autumntechcreation.click4panditcustomer.ui.newpujasamagrilistdetails.NewPujaSamgriDetailsModel
import javax.inject.Inject

class NewPujaBrassItemDetailsViewModel @Inject constructor(private val mNewPujaBrassItemDetailsRepository: NewPujaBrassItemDetailsRepository) : ViewModel() {

    var NewPujaSamgriDetailsLiveData: LiveData<Resource<NewPujaSamgriDetailsModel>>? = null

    fun getpujaSamagriDetails(prodMasterId: Int): LiveData<Resource<NewPujaSamgriDetailsModel>> {
        NewPujaSamgriDetailsLiveData = MutableLiveData()
        NewPujaSamgriDetailsLiveData = mNewPujaBrassItemDetailsRepository.getpujaSamagriDetails(prodMasterId)
        return NewPujaSamgriDetailsLiveData as LiveData<Resource<NewPujaSamgriDetailsModel>>

    }
}