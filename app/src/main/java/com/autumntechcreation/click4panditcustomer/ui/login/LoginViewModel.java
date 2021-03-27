package com.autumntechcreation.click4panditcustomer.ui.login;

import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickSignUp = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> mclickForgetPassword = new SingleLiveEvent<>();
    @Inject
    public LoginViewModel(){

    }


    public void onClickGoToSignUp(View view) {
        Log.e("Click",view.getId()+"");
        mclickSignUp.call();

    }
    public SingleLiveEvent<Void> getonClickSignUpPage() {
        return mclickSignUp;
    }


    public void onClickGoToForgetPassword(View view) {
        Log.e("Click",view.getId()+"");
        mclickForgetPassword.call();

    }
    public SingleLiveEvent<Void> getonClickForgetPasswordPage() {
        return mclickForgetPassword;
    }
}
