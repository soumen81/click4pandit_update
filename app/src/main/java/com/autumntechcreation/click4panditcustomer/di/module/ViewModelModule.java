package com.autumntechcreation.click4panditcustomer.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.autumntechcreation.click4panditcustomer.MainViewModel;
import com.autumntechcreation.click4panditcustomer.di.qualifiers.ViewModelKey;
import com.autumntechcreation.click4panditcustomer.splash.SplashViewModel;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaViewModel;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageViewModel;
import com.autumntechcreation.click4panditcustomer.ui.dashboard.DashBoardViewModel;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordViewModel;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeViewModel;
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

    @Binds
    @IntoMap
    @ViewModelKey(DashBoardViewModel.class)
    abstract ViewModel bindDashBoardViewModel(DashBoardViewModel dashBoardViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ForgetPasswordViewModel.class)
    abstract ViewModel bindForgetPasswordViewModel(ForgetPasswordViewModel forgetPasswordViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChoosePackageViewModel.class)
    abstract ViewModel bindChoosePackageViewModel(ChoosePackageViewModel choosePackageViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BookingPujaViewModel.class)
    abstract ViewModel bindBookingPujaViewModel(BookingPujaViewModel bookingPujaViewModel);
}
