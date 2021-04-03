package com.autumntechcreation.click4panditcustomer.ui.bookpuja;

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
import com.autumntechcreation.click4panditcustomer.databinding.FragmentBookingpujaBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;


import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class BookingPujaFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentBookingpujaBinding mFragmentBookingpujaBinding;
    BookingPujaViewModel mBookingPujaViewModel;

    private View mView;
    NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBookingpujaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookingpuja, container, false);
        mFragmentBookingpujaBinding.setLifecycleOwner(this);

        return mFragmentBookingpujaBinding.getRoot();
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

        mBookingPujaViewModel = ViewModelProviders.of(BookingPujaFragment.this, viewModelFactory).get(BookingPujaViewModel.class);
        mFragmentBookingpujaBinding.setBookingPujaViewModel(mBookingPujaViewModel);

    }


}
