package com.autumntechcreation.click4panditcustomer.ui.dashboard;

import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import javax.inject.Inject;

public class DashBoardViewModel extends ViewModel {

    private SingleLiveEvent<Void> mclickLogin = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> mclickRegister = new SingleLiveEvent<>();
    @Inject
    public DashBoardViewModel(){

    }


    public void onClickGoToLogin(View view) {
        Log.e("Click",view.getId()+"");
        mclickLogin.call();

    }
    public SingleLiveEvent<Void> getonClickLoginPage() {
        return mclickLogin;
    }

    public void onClickGoToRegister(View view) {
        Log.e("Click",view.getId()+"");
        mclickRegister.call();

    }
    public SingleLiveEvent<Void> getonClickRegisterPage() {
        return mclickRegister;
    }
}
