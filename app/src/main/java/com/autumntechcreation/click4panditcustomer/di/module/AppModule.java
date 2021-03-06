package com.autumntechcreation.click4panditcustomer.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.autumntechcreation.click4panditcustomer.intro.IntroductionRepository;
import com.autumntechcreation.click4panditcustomer.network.AppExecutors;
import com.autumntechcreation.click4panditcustomer.network.Webservice;
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper;
import com.autumntechcreation.click4panditcustomer.splash.SplashRepository;
import com.autumntechcreation.click4panditcustomer.ui.address.AddressRepository;
import com.autumntechcreation.click4panditcustomer.ui.addtocart.AddtoCartRepository;
import com.autumntechcreation.click4panditcustomer.ui.addupdateremoveaddress.AddUpdateRemoveRepository;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsRepository;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaRepository;
import com.autumntechcreation.click4panditcustomer.ui.cashfree.CashFreeRepository;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordRepository;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageRepository;
import com.autumntechcreation.click4panditcustomer.ui.contactus.ContactUsRepository;
import com.autumntechcreation.click4panditcustomer.ui.dashboard.DashBoardRepository;
import com.autumntechcreation.click4panditcustomer.ui.differentpujalocation.DifferentPujaLocationRepository;
import com.autumntechcreation.click4panditcustomer.ui.editprofile.EditProfileRepository;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordRepository;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeRepository;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginRepository;
import com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist.NewAddtoCartListRepository;
import com.autumntechcreation.click4panditcustomer.ui.newgiftboxitemdetails.NewGiftBoxDetailsRepository;
import com.autumntechcreation.click4panditcustomer.ui.newgiftboxlist.NewGiftBoxListRepository;

import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemdetails.NewPujaBoxItemDetailsRepository;
import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist.NewPujaBoxItemListRepository;
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemdetails.NewPujaBrassItemDetailsRepository;
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemlist.NewPujaBrassItemListRepository;
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListRepository;
import com.autumntechcreation.click4panditcustomer.ui.newpujasamagrilistdetails.NewPujaSamagriListDetailsRepository;
import com.autumntechcreation.click4panditcustomer.ui.newwishlist.NewWishListRepository;
import com.autumntechcreation.click4panditcustomer.ui.orderdetails.OrderDetailsRepository;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderRepository;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryRepository;
import com.autumntechcreation.click4panditcustomer.ui.privacypolicy.PrivacyPolicyRepository;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileRepository;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListRepository;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemsdetails.PujaBoxItemDetailsRepository;
import com.autumntechcreation.click4panditcustomer.ui.pujabrassitemdetails.PujaBrassItemDetailsRepository;
import com.autumntechcreation.click4panditcustomer.ui.pujabrassitemlist.PujaBrassItemListingRepository;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitRepository;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemKitDetailsRepository;
import com.autumntechcreation.click4panditcustomer.ui.refundpolicy.RefundPolicyRepository;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterRepository;
import com.autumntechcreation.click4panditcustomer.ui.search.SearchRepository;
import com.autumntechcreation.click4panditcustomer.ui.settings.SettingsRepository;
import com.autumntechcreation.click4panditcustomer.ui.shop.ShopRepository;
import com.autumntechcreation.click4panditcustomer.ui.shopbillingaddress.ShopBillingAddressRepository;
import com.autumntechcreation.click4panditcustomer.ui.shopcategory.ShopCategoryRepository;
import com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress.ShopShippingAddressRepository;
import com.autumntechcreation.click4panditcustomer.ui.signout.SignOutRepository;
import com.autumntechcreation.click4panditcustomer.ui.termscondition.TermsConditionRepository;
import com.autumntechcreation.click4panditcustomer.ui.transactionstatus.TransactionStatusRepository;
import com.autumntechcreation.click4panditcustomer.ui.wishlist.WishListRepository;
import com.autumntechcreation.click4panditcustomer.util.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {
        ViewModelModule.class,
        NetworkModule.class,
})
public class AppModule {
    @Provides
    @Singleton
    Webservice provideWebservice(Retrofit retrofit) {
        return retrofit.create(Webservice.class);
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(Application application) {
        return  application.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
    }


    @Provides
    @Singleton
    SplashRepository provideSplashRepository(AppExecutors appExecutors, Webservice webservice,SharedPrefsHelper sharedPrefsHelper) {
        return new SplashRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    RegisterRepository provideRegisterRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new RegisterRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    LoginRepository provideLoginRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new LoginRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    DashBoardRepository provideDashBoardRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new DashBoardRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    ForgetPasswordRepository provideForgetPasswordRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ForgetPasswordRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    ChangePasswordRepository provideChangePasswordRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ChangePasswordRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    HomeRepository provideHomeRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new HomeRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    ChoosePackageRepository provideChoosePackageRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ChoosePackageRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    BookingPujaRepository provideBookingPujaRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new BookingPujaRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    OrderSummaryRepository provideOrderSummaryRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new OrderSummaryRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    BillingDetailsRepository provideBillingDetailsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new BillingDetailsRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    DifferentPujaLocationRepository provideDifferentPujaLocationRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new DifferentPujaLocationRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    ProfileRepository provideProfileRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ProfileRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    EditProfileRepository provideEditProfileRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new EditProfileRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    OrderRepository provideOrderRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new OrderRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    SignOutRepository provideSignOutRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new SignOutRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    ContactUsRepository provideContactUsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ContactUsRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    PrivacyPolicyRepository providePrivacyPolicyRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new PrivacyPolicyRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    RefundPolicyRepository provideRefundPolicyRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new RefundPolicyRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    TermsConditionRepository provideTermsConditionRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new TermsConditionRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    SettingsRepository provideSettingsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new SettingsRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    CashFreeRepository provideCashFreeRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new CashFreeRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    SearchRepository provideSearchRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new SearchRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    OrderDetailsRepository provideOrderDetailsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new OrderDetailsRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    AddressRepository provideAddressRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new AddressRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    AddUpdateRemoveRepository provideAddUpdateRemoveRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new AddUpdateRemoveRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    TransactionStatusRepository provideTransactionStatusRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new TransactionStatusRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    ShopRepository provideShopRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ShopRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    PujaItemKitRepository providePujaItemKitRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new PujaItemKitRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    PujaItemKitDetailsRepository providePujaItemKitDetailsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new PujaItemKitDetailsRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    PujaBoxItemListRepository providePujaBoxItemListRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new PujaBoxItemListRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    PujaBoxItemDetailsRepository providePujaBoxItemDetailsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new PujaBoxItemDetailsRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    PujaBrassItemListingRepository providePujaBrassItemListingRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new PujaBrassItemListingRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    PujaBrassItemDetailsRepository providePujaBrassItemDetailsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new PujaBrassItemDetailsRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    AddtoCartRepository provideAddtoCartRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new AddtoCartRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    WishListRepository provideWishListRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new WishListRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    IntroductionRepository introductionRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new IntroductionRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    ShopCategoryRepository shopCategoryRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ShopCategoryRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    NewPujaItemKitListRepository newPujaItemKitListRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new NewPujaItemKitListRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    NewPujaBoxItemListRepository newPujaBoxItemListRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new NewPujaBoxItemListRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    NewPujaBrassItemListRepository newPujaBrassItemListRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new NewPujaBrassItemListRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    NewGiftBoxListRepository newGiftBoxListRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new NewGiftBoxListRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    NewAddtoCartListRepository newAddtoCartListRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new NewAddtoCartListRepository(appExecutors,webservice,sharedPrefsHelper);
    }
    @Provides
    @Singleton
    NewWishListRepository newWishListRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new NewWishListRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    NewPujaSamagriListDetailsRepository newPujaSamagriListDetailsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new NewPujaSamagriListDetailsRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    NewPujaBrassItemDetailsRepository newPujaBrassItemDetailsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new NewPujaBrassItemDetailsRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    NewPujaBoxItemDetailsRepository newPujaBoxItemDetailsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new NewPujaBoxItemDetailsRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    NewGiftBoxDetailsRepository newGiftBoxDetailsRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new NewGiftBoxDetailsRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    ShopBillingAddressRepository shopBillingAddressRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ShopBillingAddressRepository(appExecutors,webservice,sharedPrefsHelper);
    }

    @Provides
    @Singleton
    ShopShippingAddressRepository shopShippingAddressRepository(AppExecutors appExecutors, Webservice webservice, SharedPrefsHelper sharedPrefsHelper) {
        return new ShopShippingAddressRepository(appExecutors,webservice,sharedPrefsHelper);
    }


}
