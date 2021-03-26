package com.autumntechcreation.click4panditcustomer.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.autumntechcreation.click4panditcustomer.MainViewModel;
import com.autumntechcreation.click4panditcustomer.di.qualifiers.ViewModelKey;
import com.autumntechcreation.click4panditcustomer.splash.SplashViewModel;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginViewModel;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterViewModel;
import com.autumntechcreation.click4panditcustomer.viewmodel.ProjectViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ProjectViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel splashViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    abstract ViewModel bindRegisterViewModel(RegisterViewModel registerViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);
}
