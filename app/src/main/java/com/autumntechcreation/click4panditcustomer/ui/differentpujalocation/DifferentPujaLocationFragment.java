package com.autumntechcreation.click4panditcustomer.ui.differentpujalocation;

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
import androidx.navigation.NavController;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentDifferentpujalocationBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsViewModel;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class DifferentPujaLocationFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentDifferentpujalocationBinding mFragmentDifferentpujalocationBinding;
    DifferentPujaLocationViewModel mDifferentPujaLocationViewModel;
    private View mView;
    NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentDifferentpujalocationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_differentpujalocation, container, false);
        mFragmentDifferentpujalocationBinding.setLifecycleOwner(this);

        return mFragmentDifferentpujalocationBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        navController=findNavController(mView);
        ((MainActivity) getActivity()).setToolbar(false,true,false,true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mDifferentPujaLocationViewModel = ViewModelProviders.of(DifferentPujaLocationFragment.this, viewModelFactory).get(DifferentPujaLocationViewModel.class);
        mFragmentDifferentpujalocationBinding.setBillingDetailsViewModel(mDifferentPujaLocationViewModel);

    }
}
