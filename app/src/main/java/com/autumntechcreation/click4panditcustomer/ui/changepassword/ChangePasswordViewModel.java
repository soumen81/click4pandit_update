package com.autumntechcreation.click4panditcustomer.ui.changepassword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordModel;

import javax.inject.Inject;

public class ChangePasswordViewModel extends ViewModel {
    ChangePasswordRepository mChangePasswordRepository;
    private LiveData<Resource<ChangePasswordModel>> mChangePasswordModelResponse;
    @Inject
    public ChangePasswordViewModel(ChangePasswordRepository changePasswordRepository){
        this.mChangePasswordRepository=changePasswordRepository;
    }
    public LiveData<Resource<ChangePasswordModel>> getChangePassword( String oldPass,String newPass,String confirmPass) {
        mChangePasswordModelResponse = new MutableLiveData<>();
        mChangePasswordModelResponse = mChangePasswordRepository.getChangePassword( oldPass,newPass,confirmPass);
        return mChangePasswordModelResponse;

    }
}
