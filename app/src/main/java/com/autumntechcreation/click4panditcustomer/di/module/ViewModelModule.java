package com.autumntechcreation.click4panditcustomer.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.autumntechcreation.click4panditcustomer.MainViewModel;
import com.autumntechcreation.click4panditcustomer.di.qualifiers.ViewModelKey;
import com.autumntechcreation.click4panditcustomer.splash.SplashViewModel;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaViewModel;
import com.autumntechcreation.click4panditcustomer.ui.cashfree.CashFreeViewModel;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordViewModel;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageViewModel;
import com.autumntechcreation.click4panditcustomer.ui.contactus.ContactUsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.dashboard.DashBoardViewModel;
import com.autumntechcreation.click4panditcustomer.ui.differentpujalocation.DifferentPujaLocationViewModel;
import com.autumntechcreation.click4panditcustomer.ui.editprofile.EditProfileViewModel;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordViewModel;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeViewModel;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginViewModel;
import com.autumntechcreation.click4panditcustomer.ui.orderdetails.OrderDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.orderdetails.OrderDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderViewModel;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryViewModel;
import com.autumntechcreation.click4panditcustomer.ui.privacypolicy.PrivacyPolicyViewModel;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileViewModel;
import com.autumntechcreation.click4panditcustomer.ui.refundpolicy.RefundPolicyViewModel;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterViewModel;
import com.autumntechcreation.click4panditcustomer.ui.sendenquiry.SendEnquiryViewModel;
import com.autumntechcreation.click4panditcustomer.ui.settings.SettingsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.signout.SignOutViewModel;
import com.autumntechcreation.click4panditcustomer.ui.termscondition.TermsConditionViewModel;
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

    @Binds
    @IntoMap
    @ViewModelKey(OrderSummaryViewModel.class)
    abstract ViewModel bindOrderSummaryViewModel(OrderSummaryViewModel orderSummaryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BillingDetailsViewModel.class)
    abstract ViewModel bindBillingDetailsViewModel(BillingDetailsViewModel billingDetailsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DifferentPujaLocationViewModel.class)
    abstract ViewModel bindDifferentPujaLocationViewModel(DifferentPujaLocationViewModel differentPujaLocationViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel.class)
    abstract ViewModel bindSettingsViewModel(SettingsViewModel settingsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel.class)
    abstract ViewModel bindEditProfileViewModel(EditProfileViewModel editProfileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel.class)
    abstract ViewModel bindOrderViewModel(OrderViewModel orderViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(OrderDetailsViewModel.class)
    abstract ViewModel bindOrderDetailsViewModel(OrderDetailsViewModel orderDetailsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SignOutViewModel.class)
    abstract ViewModel bindSignOutViewModel(SignOutViewModel signOutViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel.class)
    abstract ViewModel bindChangePasswordViewModel(ChangePasswordViewModel changePasswordViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ContactUsViewModel.class)
    abstract ViewModel bindContactUsViewModel(ContactUsViewModel contactUsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PrivacyPolicyViewModel.class)
    abstract ViewModel bindPrivacyPolicyViewModel(PrivacyPolicyViewModel privacyPolicyViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RefundPolicyViewModel.class)
    abstract ViewModel bindRefundPolicyViewModel(RefundPolicyViewModel refundPolicyViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(TermsConditionViewModel.class)
    abstract ViewModel bindTermsConditionViewModel(TermsConditionViewModel termsConditionViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SendEnquiryViewModel.class)
    abstract ViewModel bindSendEnquiryViewModel(SendEnquiryViewModel sendEnquiryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CashFreeViewModel.class)
    abstract ViewModel bindCashFreeViewModel(CashFreeViewModel cashFreeViewModel);
}
