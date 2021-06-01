package com.autumntechcreation.click4panditcustomer.ui.addupdateremoveaddress;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.ProceedtoPayModel;

import javax.inject.Inject;

public class AddUpdateRemoveViewModel extends ViewModel {
    AddUpdateRemoveRepository mAddUpdateRemoveRepository;
    public LiveData<Resource<AddAddressModel>> mAddAddressModelResponse;
    @Inject
    public AddUpdateRemoveViewModel(AddUpdateRemoveRepository addUpdateRemoveRepository) {
        this.mAddUpdateRemoveRepository=addUpdateRemoveRepository;
    }
    public LiveData<Resource<AddAddressModel>> getAddAddress(String action,String firstName,String lastName,String address1,String pincode) {
        mAddAddressModelResponse = new MutableLiveData<>();
        mAddAddressModelResponse = mAddUpdateRemoveRepository.getAddAddress(action,firstName,lastName,address1,pincode);
        return mAddAddressModelResponse;

    }

}
