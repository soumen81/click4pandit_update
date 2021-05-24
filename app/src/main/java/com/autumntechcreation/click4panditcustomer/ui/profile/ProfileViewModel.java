package com.autumntechcreation.click4panditcustomer.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordModel;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private LiveData<Resource<CustomerGetProfileModel>> mCustomerGetProfileModelResponse;
    private LiveData<Resource<SaveCustomerprofileModel>> mCustomerSaveProfileModelResponse;
    ProfileRepository mProfileRepository;
    @Inject
    public ProfileViewModel(ProfileRepository profileRepository) {
        this.mProfileRepository=profileRepository;
    }
    public LiveData<Resource<CustomerGetProfileModel>> customerGetProfile() {
        mCustomerGetProfileModelResponse = new MutableLiveData<>();
        mCustomerGetProfileModelResponse = mProfileRepository.getForCustomerProfile();
        return mCustomerGetProfileModelResponse;

    }public LiveData<Resource<SaveCustomerprofileModel>> getForSaveCustomerProfile(int customeMasterrId,
                                                                                   String fName,String lName,
                                                                                   String mobileNo,String altermobileNo,
                                                                                   String emailId) {
        mCustomerSaveProfileModelResponse = new MutableLiveData<>();
        mCustomerSaveProfileModelResponse = mProfileRepository.getForSaveCustomerProfile(customeMasterrId,fName,lName,
                mobileNo,altermobileNo,emailId );
        return mCustomerSaveProfileModelResponse;

    }

}
