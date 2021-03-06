package com.autumntechcreation.click4panditcustomer.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.home.CartItemCountModel;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickSignUp = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> mclickLogin = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> mclickForgetPassword = new SingleLiveEvent<>();
    LoginRepository mLoginRepository;
    private LiveData<Resource<LoginResponse>> mLoginResponseResult;
    private LiveData<Resource<CartItemCountModel>> mCartItemCountModelResponse;
    @Inject
    public LoginViewModel(LoginRepository loginRepository) {
        this.mLoginRepository = loginRepository;
    }


    public LiveData<Resource<LoginResponse>> getLoginResult(String loginId, String password) {
        mLoginResponseResult = new MutableLiveData<>();
        mLoginResponseResult = mLoginRepository.getLogin(loginId, password);
        return mLoginResponseResult;

    }
    public LiveData<Resource<CartItemCountModel>> getCartCountItem() {
        mCartItemCountModelResponse=new MutableLiveData<>();
        mCartItemCountModelResponse=mLoginRepository.getCartCountItem();
        return mCartItemCountModelResponse;
    }

    public void onClickForLogin(View view) {
        Log.e("Click", view.getId() + "");
        mclickLogin.call();
    }

    public SingleLiveEvent<Void> getonClickLoginPage() {
        return mclickLogin;
    }


    public void onClickGoToSignUp(View view) {
        Log.e("Click", view.getId() + "");
        mclickSignUp.call();

    }

    public SingleLiveEvent<Void> getonClickSignUpPage() {
        return mclickSignUp;
    }


    public void onClickGoToForgetPassword(View view) {
        Log.e("Click", view.getId() + "");
        mclickForgetPassword.call();

    }

    public SingleLiveEvent<Void> getonClickForgetPasswordPage() {
        return mclickForgetPassword;
    }


    public void storeUserName(String userName) {
        mLoginRepository.storeUserName(userName);
    } public void storefirstName(String firstName) {
        mLoginRepository.storefirstName(firstName);
    } public void storelastName(String lastName) {
        mLoginRepository.storelastName(lastName);
    }

    public void storeMobileNo(String mobileNo) {
        mLoginRepository.storeMobileNo(mobileNo);
    }
 public String storeEmail(String email) {
        mLoginRepository.storeEmail(email);
     return email;
 }

    public String storeCartCount(String cartCount) {
        mLoginRepository.storeCartCount(cartCount);
        return cartCount;
    }

    public void deleteSharedPreference() {
        mLoginRepository.deleteSharedPreference();
    }
}