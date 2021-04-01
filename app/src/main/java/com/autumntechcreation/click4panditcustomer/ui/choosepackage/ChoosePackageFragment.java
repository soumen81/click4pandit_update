package com.autumntechcreation.click4panditcustomer.ui.choosepackage;

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
import com.autumntechcreation.click4panditcustomer.databinding.FragmentChoosepackageBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeFragment;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeViewModel;

import javax.inject.Inject;

public class ChoosePackageFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentChoosepackageBinding mFragmentChoosepackageBinding;
    ChoosePackageViewModel mChoosePackageViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentChoosepackageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_choosepackage, container, false);
        mFragmentChoosepackageBinding.setLifecycleOwner(this);

        return mFragmentChoosepackageBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mChoosePackageViewModel = ViewModelProviders.of(ChoosePackageFragment.this, viewModelFactory).get(ChoosePackageViewModel.class);
        mFragmentChoosepackageBinding.setChoosePackageViewModel(mChoosePackageViewModel);

    }
}
