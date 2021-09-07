package com.autumntechcreation.click4panditcustomer;

import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.ui.login.LoginRepository;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {
    LoginRepository mLoginRepository;
    @Inject
    public MainViewModel(LoginRepository loginRepository){
    this.mLoginRepository=loginRepository;
    }
    public String getUserName(){
        return mLoginRepository.getUserName();
    }

    public String getEmail(){
        return mLoginRepository.getEmail();
    }
    public String getFirstName(){
        return mLoginRepository.getFirstName();
    }

}
