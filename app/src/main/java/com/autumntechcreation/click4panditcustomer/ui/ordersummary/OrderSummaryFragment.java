package com.autumntechcreation.click4panditcustomer.ui.ordersummary;

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
import com.autumntechcreation.click4panditcustomer.databinding.FragmentOrdersummeryBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaFragment;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaFragmentDirections;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaViewModel;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class OrderSummaryFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    OrderSummaryViewModel mOrderSummaryViewModel;
    FragmentOrdersummeryBinding mFragmentOrdersummeryBinding;
    private View mView;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentOrdersummeryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_ordersummery, container, false);
        mFragmentOrdersummeryBinding.setLifecycleOwner(this);

        return mFragmentOrdersummeryBinding.getRoot();
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

        mOrderSummaryViewModel = ViewModelProviders.of(OrderSummaryFragment.this, viewModelFactory).get(OrderSummaryViewModel.class);
        mFragmentOrdersummeryBinding.setOrderSummeryViewModel(mOrderSummaryViewModel);


        mOrderSummaryViewModel.getOnClickConfirmOrder().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                findNavController(mView).navigate(OrderSummaryFragmentDirections.actionOrderSummaryFragmentToBillingDetailsFragment());
            }
        });
    }
}
