package com.autumntechcreation.click4panditcustomer.di.module;

import com.autumntechcreation.click4panditcustomer.ui.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();
}
