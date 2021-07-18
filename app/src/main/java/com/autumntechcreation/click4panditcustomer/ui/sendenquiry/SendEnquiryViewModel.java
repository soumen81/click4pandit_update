package com.autumntechcreation.click4panditcustomer.ui.sendenquiry;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterResponse;

import javax.inject.Inject;

public class SendEnquiryViewModel extends ViewModel {
    SendEnquiryRepository mSendEnquiryRepository;
    private LiveData<Resource<SendEnquiryModel>> mSendEnquiryResponse;
    @Inject
    public SendEnquiryViewModel(SendEnquiryRepository sendEnquiryRepository){
    this.mSendEnquiryRepository=sendEnquiryRepository;
    }

    public LiveData<Resource<SendEnquiryModel>> getSendEnquiryResult(String address,String emailAddress,String mobile,String name,
                                                                  String pujaName,String requirements) {
        mSendEnquiryResponse = new MutableLiveData<>();
        mSendEnquiryResponse = mSendEnquiryRepository.getSendEnquiry(address, emailAddress, mobile, name, pujaName,
                requirements);
        return mSendEnquiryResponse;

    }
    public String getEmail() {
        return  mSendEnquiryRepository.getEmail();
    }
}
