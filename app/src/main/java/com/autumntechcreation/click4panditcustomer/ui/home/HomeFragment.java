package com.autumntechcreation.click4panditcustomer.ui.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentHomeBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;

import javax.inject.Inject;

public class HomeFragment  extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
     FragmentHomeBinding mFragmentHomeBinding;
    HomeViewModel mHomeViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mFragmentHomeBinding.setLifecycleOwner(this);

        return mFragmentHomeBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mHomeViewModel = ViewModelProviders.of(HomeFragment.this, viewModelFactory).get(HomeViewModel.class);
        mFragmentHomeBinding.setHomeViewModel(mHomeViewModel);

    }
}
