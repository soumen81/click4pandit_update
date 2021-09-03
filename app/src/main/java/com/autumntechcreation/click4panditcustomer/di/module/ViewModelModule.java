package com.autumntechcreation.click4panditcustomer.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.autumntechcreation.click4panditcustomer.MainViewModel;
import com.autumntechcreation.click4panditcustomer.di.qualifiers.ViewModelKey;
import com.autumntechcreation.click4panditcustomer.intro.IntroductionViewModel;
import com.autumntechcreation.click4panditcustomer.splash.SplashViewModel;
import com.autumntechcreation.click4panditcustomer.ui.address.AddressViewModel;
import com.autumntechcreation.click4panditcustomer.ui.addtocart.AddtoCartViewModel;
import com.autumntechcreation.click4panditcustomer.ui.addupdateremoveaddress.AddUpdateRemoveViewModel;
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
import com.autumntechcreation.click4panditcustomer.ui.newgiftboxlist.NewGiftBoxListViewModel;

import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist.NewPujaBoxItemListViewModel;
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemlist.NewPujaBrassItemListViewModel;
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListViewModel;
import com.autumntechcreation.click4panditcustomer.ui.orderdetails.OrderDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.orderdetails.OrderDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderViewModel;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryViewModel;
import com.autumntechcreation.click4panditcustomer.ui.privacypolicy.PrivacyPolicyViewModel;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemsdetails.PujaBoxItemDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujabrassitemdetails.PujaBrassItemDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujabrassitemlist.PujaBrassItemListingViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemKitDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.refundpolicy.RefundPolicyViewModel;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterViewModel;
import com.autumntechcreation.click4panditcustomer.ui.search.SearchViewModel;
import com.autumntechcreation.click4panditcustomer.ui.sendenquiry.SendEnquiryViewModel;
import com.autumntechcreation.click4panditcustomer.ui.settings.SettingsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.shop.ShopViewModel;
import com.autumntechcreation.click4panditcustomer.ui.shopcategory.ShopCategoryViewModel;
import com.autumntechcreation.click4panditcustomer.ui.signout.SignOutViewModel;
import com.autumntechcreation.click4panditcustomer.ui.termscondition.TermsConditionViewModel;
import com.autumntechcreation.click4panditcustomer.ui.transactionstatus.TransactionStatusViewModel;
import com.autumntechcreation.click4panditcustomer.ui.wishlist.WishListViewModel;
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

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    abstract ViewModel bindSearchViewModel(SearchViewModel searchViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddUpdateRemoveViewModel.class)
    abstract ViewModel bindAddUpdateRemoveViewModel(AddUpdateRemoveViewModel addUpdateRemoveViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddressViewModel.class)
    abstract ViewModel bindAddressViewModel(AddressViewModel addUpdateRemoveViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TransactionStatusViewModel.class)
    abstract ViewModel bindTransactionStatusViewModel(TransactionStatusViewModel transactionStatusViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ShopViewModel.class)
    abstract ViewModel bindShopViewModel(ShopViewModel shopViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PujaItemKitViewModel.class)
    abstract ViewModel bindPujaItemKitViewModel(PujaItemKitViewModel pujaItemKitViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PujaItemKitDetailsViewModel.class)
    abstract ViewModel bindPujaItemKitDetailsViewModel(PujaItemKitDetailsViewModel pujaItemKitDetailsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PujaBoxItemListViewModel.class)
    abstract ViewModel bindPujaBoxItemListViewModel(PujaBoxItemListViewModel pujaBoxItemListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PujaBoxItemDetailsViewModel.class)
    abstract ViewModel bindPujaBoxItemDetailsViewModel(PujaBoxItemDetailsViewModel pujaBoxItemDetailsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PujaBrassItemListingViewModel.class)
    abstract ViewModel bindPujaBrassItemListingViewModel(PujaBrassItemListingViewModel pujaBrassItemListingViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PujaBrassItemDetailsViewModel.class)
    abstract ViewModel bindPujaBrassItemDetailsViewModel(PujaBrassItemDetailsViewModel pujaBrassItemDetailsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddtoCartViewModel.class)
    abstract ViewModel bindAddtoCartViewModel(AddtoCartViewModel addtoCartViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WishListViewModel.class)
    abstract ViewModel bindWishListViewModel(WishListViewModel wishListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(IntroductionViewModel.class)
    abstract ViewModel bindIntroductionViewModel(IntroductionViewModel introductionViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ShopCategoryViewModel.class)
    abstract ViewModel bindShopCategoryViewModel(ShopCategoryViewModel shopCategoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewPujaItemKitListViewModel.class)
    abstract ViewModel bindNewPujaItemKitListViewModel(NewPujaItemKitListViewModel newPujaItemKitListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewPujaBoxItemListViewModel.class)
    abstract ViewModel bindNewPujaBoxItemListViewModel(NewPujaBoxItemListViewModel newPujaBoxItemListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewPujaBrassItemListViewModel.class)
    abstract ViewModel bindNewPujaBrassItemListViewModel(NewPujaBrassItemListViewModel newPujaBrassItemListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewGiftBoxListViewModel.class)
    abstract ViewModel bindNewGiftBoxListViewModel(NewGiftBoxListViewModel newGiftBoxListViewModel);



}
