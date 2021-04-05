package com.autumntechcreation.click4panditcustomer.di.module;

import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaFragment;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragment;
import com.autumntechcreation.click4panditcustomer.ui.differentpujalocation.DifferentPujaLocationFragment;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeFragment;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryFragment;

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
}
