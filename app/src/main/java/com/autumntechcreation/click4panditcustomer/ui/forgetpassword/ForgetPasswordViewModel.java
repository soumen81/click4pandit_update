package com.autumntechcreation.click4panditcustomer.ui.forgetpassword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginResponse;

import javax.inject.Inject;

public class ForgetPasswordViewModel extends ViewModel {
    ForgetPasswordRepository mForgetPasswordRepository;
    private LiveData<Resource<ForgetPasswordModel>> mForgetPasswordResponse;
    private LiveData<Resource<TriggerMailModel>> mTriggerMailResponse;
    @Inject
    public ForgetPasswordViewModel(ForgetPasswordRepository forgetPasswordRepository){
        this.mForgetPasswordRepository=forgetPasswordRepository;
    }


    public LiveData<Resource<ForgetPasswordModel>> getForgetPassword(String loginId, String password) {
        mForgetPasswordResponse = new MutableLiveData<>();
        mForgetPasswordResponse = mForgetPasswordRepository.getForSetNewPassword(loginId, password);
        return mForgetPasswordResponse;

    }
    public LiveData<Resource<TriggerMailModel>> getForTriggerPassword(String loginId, String password) {
        mTriggerMailResponse = new MutableLiveData<>();
        mTriggerMailResponse = mForgetPasswordRepository.getTriggerPassword(loginId, password);
        return mTriggerMailResponse;

    }

}
