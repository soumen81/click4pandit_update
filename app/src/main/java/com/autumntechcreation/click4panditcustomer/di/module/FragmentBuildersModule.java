package com.autumntechcreation.click4panditcustomer.di.module;

import com.autumntechcreation.click4panditcustomer.ui.address.AddressFragment;
import com.autumntechcreation.click4panditcustomer.ui.addupdateremoveaddress.AddUpdateRemoveFragment;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaFragment;
import com.autumntechcreation.click4panditcustomer.ui.cashfree.CashFreeFragment;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragment;
import com.autumntechcreation.click4panditcustomer.ui.differentpujalocation.DifferentPujaLocationFragment;
import com.autumntechcreation.click4panditcustomer.ui.editprofile.EditprofileFragment;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeFragment;
import com.autumntechcreation.click4panditcustomer.ui.orderdetails.OrderDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderFragment;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryFragment;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileFragment;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitFragment;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemKitDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.settings.SettingsFragment;
import com.autumntechcreation.click4panditcustomer.ui.shop.ShopFragment;
import com.autumntechcreation.click4panditcustomer.ui.signout.SignOutFragment;
import com.autumntechcreation.click4panditcustomer.ui.transactionstatus.TransactionStatusFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract ChoosePackageFragment contributeChoosePackageFragment();

    @ContributesAndroidInjector
    abstract BookingPujaFragment contributeBookingPujaFragment();

    @ContributesAndroidInjector
    abstract OrderSummaryFragment contributeOrderSummaryFragment();

    @ContributesAndroidInjector
    abstract BillingDetailsFragment contributeBillingDetailsFragment();

    @ContributesAndroidInjector
    abstract DifferentPujaLocationFragment contributeDifferentPujaLocationFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract SettingsFragment contributeSettingsFragment();

    @ContributesAndroidInjector
    abstract EditprofileFragment contributeEditprofileFragment();

    @ContributesAndroidInjector
    abstract OrderFragment contributeOrderFragment();

    @ContributesAndroidInjector
    abstract OrderDetailsFragment contributeOrderDetailsFragment();

    @ContributesAndroidInjector
    abstract SignOutFragment contributeSignOutFragment();

    @ContributesAndroidInjector
    abstract CashFreeFragment contributeCashFreeFragment();

    @ContributesAndroidInjector
    abstract AddressFragment contributeAddressFragment();

    @ContributesAndroidInjector
    abstract AddUpdateRemoveFragment contributeAddUpdateRemoveFragment();

    @ContributesAndroidInjector
    abstract TransactionStatusFragment contributeTransactionStatusFragment();

    @ContributesAndroidInjector
    abstract ShopFragment contributeShopFragment();

    @ContributesAndroidInjector
    abstract PujaItemKitFragment contributePujaItemKitFragment();

    @ContributesAndroidInjector
    abstract PujaItemKitDetailsFragment contributePujaItemKitDetailsFragment();
}
