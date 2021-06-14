package com.autumntechcreation.click4panditcustomer.ui.contactus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordModel;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordRepository;

import javax.inject.Inject;

public class ContactUsViewModel extends ViewModel {
    private LiveData<Resource<ContactUsModel>> mContactUsModelResponse;
    ContactUsRepository mContactUsRepository;
    @Inject
    public ContactUsViewModel(ContactUsRepository contactUsRepository ){
        this.mContactUsRepository=contactUsRepository;
    }
    public LiveData<Resource<ContactUsModel>> getContactUsByEmail(String name, String mobile,String email, String address,String explain) {
        mContactUsModelResponse = new MutableLiveData<>();
        mContactUsModelResponse = mContactUsRepository.getContactUs( name,mobile,email,address,explain);
        return mContactUsModelResponse;

    }
}
