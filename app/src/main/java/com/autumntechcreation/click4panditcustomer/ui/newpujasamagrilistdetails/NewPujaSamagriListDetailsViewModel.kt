package com.autumntechcreation.click4panditcustomer.ui.newpujasamagrilistdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist.UpdateCartItemCountModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListRepository
import javax.inject.Inject

class NewPujaSamagriListDetailsViewModel @Inject constructor(private val mNewPujaSamagriListDetailsRepository: NewPujaSamagriListDetailsRepository) : ViewModel() {

    var NewPujaSamgriDetailsLiveData: LiveData<Resource<NewPujaSamgriDetailsModel>>? = null

    fun getpujaSamagriDetails(prodMasterId: Int): LiveData<Resource<NewPujaSamgriDetailsModel>> {
        NewPujaSamgriDetailsLiveData = MutableLiveData()
        NewPujaSamgriDetailsLiveData = mNewPujaSamagriListDetailsRepository.getpujaSamagriDetails(prodMasterId)
        return NewPujaSamgriDetailsLiveData as LiveData<Resource<NewPujaSamgriDetailsModel>>

    }
}