package com.autumntechcreation.click4panditcustomer.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickLogin = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> mclickRegister = new SingleLiveEvent<>();
    RegisterRepository mRegisterRepository;
    private LiveData<Resource<RegisterResponse>> mRegisterResponseResult;

    @Inject
    public RegisterViewModel(RegisterRepository registerRepository) {
        this.mRegisterRepository = registerRepository;
    }


    public LiveData<Resource<RegisterResponse>> getRegisterResult(String firstName, String lastName, String mobile, String emalAddress, String loginForSignUp,
                                                                  String passwordForSignUp, String confirmPassForSignUp, String entityType) {
        mRegisterResponseResult = new MutableLiveData<>();
        mRegisterResponseResult = mRegisterRepository.getRegistration(firstName, lastName, mobile, emalAddress, loginForSignUp, passwordForSignUp,
                confirmPassForSignUp, entityType);
        return mRegisterResponseResult;

    }

    public void clickForRegister(View view) {
        Log.e("Click1", view.getId() + "");
        mclickRegister.call();
    }

    public SingleLiveEvent<Void> getonClickRegisterPage() {
        return mclickRegister;
    }

    public void onClickGoToLogin(View view) {
        Log.e("Click2", view.getId() + "");
        mclickLogin.call();

    }

    public SingleLiveEvent<Void> getonClickLoginPage() {
        return mclickLogin;
    }

}