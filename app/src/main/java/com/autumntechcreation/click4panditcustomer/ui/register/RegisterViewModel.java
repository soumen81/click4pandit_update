package com.autumntechcreation.click4panditcustomer.ui.register;

import androidx.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickLogin = new SingleLiveEvent<>();
    @Inject
    public RegisterViewModel(){

    }

    public void onClickGoToLogin(View view) {
        Log.e("Click",view.getId()+"");
        mclickLogin.call();

    }
    public SingleLiveEvent<Void> getonClickLoginPage() {
        return mclickLogin;
    }
}
