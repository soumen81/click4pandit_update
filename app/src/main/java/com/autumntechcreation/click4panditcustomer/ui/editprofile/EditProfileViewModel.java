package com.autumntechcreation.click4panditcustomer.ui.editprofile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.profile.CustomerGetProfileModel;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileRepository;
import com.autumntechcreation.click4panditcustomer.ui.profile.SaveCustomerprofileModel;

import javax.inject.Inject;

public class EditProfileViewModel extends ViewModel {
    EditProfileRepository mEditProfileRepository;
    private LiveData<Resource<CustomerGetProfileModel>> mCustomerGetProfileModelResponse;
    private LiveData<Resource<SaveCustomerprofileModel>> mCustomerSaveProfileModelResponse;
    @Inject
    public EditProfileViewModel(EditProfileRepository editProfileRepository) {
        this.mEditProfileRepository=editProfileRepository;
    }

    public LiveData<Resource<CustomerGetProfileModel>> customerGetProfile() {
        mCustomerGetProfileModelResponse = new MutableLiveData<>();
        mCustomerGetProfileModelResponse = mEditProfileRepository.getForCustomerProfile();
        return mCustomerGetProfileModelResponse;

    }
    public LiveData<Resource<SaveCustomerprofileModel>> getForSaveCustomerProfile(int customeMasterrId,
                                                                                  String fName,String lName,
                                                                                  String mobileNo,String altermobileNo,
                                                                                  String emailId) {
        mCustomerSaveProfileModelResponse = new MutableLiveData<>();
        mCustomerSaveProfileModelResponse = mEditProfileRepository.getForEditSaveCustomerProfile(customeMasterrId,fName,lName,
                mobileNo,altermobileNo,emailId );
        return mCustomerSaveProfileModelResponse;

    }
    public String storeAlternateMobileNo(String alternateMobNo) {
        mEditProfileRepository.storeAlternateMobileNo(alternateMobNo);
        return alternateMobNo;
    }
}
