package com.autumntechcreation.click4panditcustomer.ui.orderdetails;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragmentArgs;
import com.autumntechcreation.click4panditcustomer.util.Static;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class OrderDetailsFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentOrderdetailsBinding mFragmentOrderdetailsBinding;
    OrderDetailsViewModel orderDetailsViewModel;
    private View mView;
    NavController navController;
    String packageTypeName,orderDesc,pujaPackageDesc,custOrderDate,orderNo,pujaSubCategoryDesc,bkgpkgAmount,bkgpkgTaxAmount,bkgpkgTotalAmount,statusBill;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentOrderdetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_orderdetails, container, false);
        mFragmentOrderdetailsBinding.setLifecycleOwner(this);

        packageTypeName= OrderDetailsFragmentArgs.fromBundle(getArguments()).getPackageTypeName();
        Log.e("packageTypeName",""+packageTypeName);
        orderDesc= OrderDetailsFragmentArgs.fromBundle(getArguments()).getOrderDesc();
        Log.e("orderDesc",""+orderDesc);
        pujaPackageDesc= OrderDetailsFragmentArgs.fromBundle(getArguments()).getPujaPackageDesc();
        Log.e("pujaPackageDesc",""+pujaPackageDesc);
        custOrderDate= OrderDetailsFragmentArgs.fromBundle(getArguments()).getCustOrderDate();
        Log.e("custOrderDate",""+custOrderDate);
        orderNo= OrderDetailsFragmentArgs.fromBundle(getArguments()).getCustOrderNo();
        Log.e("orderNo",""+orderNo);
        pujaSubCategoryDesc= OrderDetailsFragmentArgs.fromBundle(getArguments()).getCustPujaSubCategoryDesc();
        Log.e("pujaSubCategoryDesc",""+pujaSubCategoryDesc);
        bkgpkgAmount= OrderDetailsFragmentArgs.fromBundle(getArguments()).getCustBkgPkgAmount();
        Log.e("bkgpkgAmount",""+bkgpkgAmount);
        bkgpkgTaxAmount= OrderDetailsFragmentArgs.fromBundle(getArguments()).getCustBkgPkgTaxAmount();
        Log.e("bkgpkgTaxAmount",""+bkgpkgTaxAmount);
        bkgpkgTotalAmount= OrderDetailsFragmentArgs.fromBundle(getArguments()).getCustBkgPkgTotalAmount();
        Log.e("bkgpkgTotalAmount",""+bkgpkgTotalAmount);
        statusBill= OrderDetailsFragmentArgs.fromBundle(getArguments()).getBkgstsDesc();
        Log.e("statusBill",""+statusBill);

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

        mFragmentOrderdetailsBinding.tvPackageValue.setText(packageTypeName);
        mFragmentOrderdetailsBinding.tvPanditDetails.setText(orderDesc);
        mFragmentOrderdetailsBinding.tvDateTimeValue.setText(Static.convertToDate(custOrderDate));
        mFragmentOrderdetailsBinding.tvAllPujaSamagriesValue.setText(pujaPackageDesc);
        mFragmentOrderdetailsBinding.tvDakshinaValue.setText(orderNo);
        mFragmentOrderdetailsBinding.tvSamagriDetails.setText(pujaSubCategoryDesc);
        mFragmentOrderdetailsBinding.tvSubTotalValue.setText(bkgpkgAmount);
        mFragmentOrderdetailsBinding.tvTotalValue.setText(bkgpkgTotalAmount);

        double dd=Double.parseDouble(bkgpkgTaxAmount)/2;
        mFragmentOrderdetailsBinding.tvCgstValue.setText(Double.toString(dd));
        mFragmentOrderdetailsBinding.tvSgstValue.setText(Double.toString(dd));

        if(statusBill.equalsIgnoreCase("PENDING")){
            mFragmentOrderdetailsBinding.tvAmountStatus.setText("The Amount is Pending ");
            mFragmentOrderdetailsBinding.tvAmountStatus.setBackgroundColor(Color.parseColor("#d23e34"));
        }else if(statusBill.equalsIgnoreCase("ACCEPTED")){
            mFragmentOrderdetailsBinding.tvAmountStatus.setText("The Amount is Paid ");
            mFragmentOrderdetailsBinding.tvAmountStatus.setBackgroundColor(Color.parseColor("#F3D439"));
        }
    }

    }
