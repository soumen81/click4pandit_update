package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentBillingdetailsBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaFragment;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaViewModel;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragmentDirections;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class BillingDetailsFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    BillingDetailsViewModel mBillingDetailsViewModel;
    FragmentBillingdetailsBinding mFragmentBillingdetailsBinding;
    private View mView;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBillingdetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_billingdetails, container, false);
        mFragmentBillingdetailsBinding.setLifecycleOwner(this);

        return mFragmentBillingdetailsBinding.getRoot();
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

        mBillingDetailsViewModel = ViewModelProviders.of(BillingDetailsFragment.this, viewModelFactory).get(BillingDetailsViewModel.class);
        mFragmentBillingdetailsBinding.setBillingDetailsViewModel(mBillingDetailsViewModel);

        mBillingDetailsViewModel.getOnClickBillingDiffLocation().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                findNavController(mView).navigate(BillingDetailsFragmentDirections.actionBillingDetailsFragmentToDifferentPujaLocationFragment());
            }
        });

    }
    }
