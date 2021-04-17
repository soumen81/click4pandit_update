package com.autumntechcreation.click4panditcustomer.ui.orderdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentOrderdetailsBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class OrderDetailsFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentOrderdetailsBinding mFragmentOrderdetailsBinding;
    private View mView;
    NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentOrderdetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_orderdetails, container, false);
        mFragmentOrderdetailsBinding.setLifecycleOwner(this);

        return mFragmentOrderdetailsBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        navController=findNavController(mView);
        ((MainActivity) getActivity()).setToolbar(false,true,false,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    }
