package com.autumntechcreation.click4panditcustomer.ui.ordersummary;

import android.os.Bundle;
import android.util.Log;
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
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragmentArgs;
import com.autumntechcreation.click4panditcustomer.util.Static;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class OrderSummaryFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    OrderSummaryViewModel mOrderSummaryViewModel;
    FragmentOrdersummeryBinding mFragmentOrdersummeryBinding;
    private View mView;
    NavController navController;
    String pujaName,pujaAmount,pujaDesc,procedure,pujaSamagri,yajaman,locationName,languageName,pujaDate,pujaTime,packageTypeIdDesc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentOrdersummeryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_ordersummery, container, false);
        mFragmentOrdersummeryBinding.setLifecycleOwner(this);

        pujaName= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaName();
        Log.e("PujaName",""+pujaName);
        pujaAmount= OrderSummaryFragmentArgs.fromBundle(getArguments()).getAmount();
        Log.e("pujaAmount",""+pujaAmount);
        pujaDesc= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPackageDesc();
        Log.e("pujaDesc",""+pujaDesc);
        procedure= OrderSummaryFragmentArgs.fromBundle(getArguments()).getProcedure();
        Log.e("Procedure",""+procedure);
        pujaSamagri= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaSamagries();
        Log.e("pujaSamagri",""+pujaSamagri);
        yajaman= OrderSummaryFragmentArgs.fromBundle(getArguments()).getYajaman();
        Log.e("Yajaman",""+yajaman);
        locationName= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaLocation();
        Log.e("LocationName",""+locationName);
        languageName= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaLanguage();
        Log.e("LanguageName",""+languageName);
        pujaDate= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaDate();
        Log.e("PujaDate",""+pujaDate);
        pujaTime= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaTime();
        Log.e("pujaTime",""+pujaTime);
        packageTypeIdDesc= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPackageTypeIdDesc();
        Log.e("packageTypeIdDesc",""+packageTypeIdDesc);
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

        mFragmentOrdersummeryBinding.tvPujaNameValue.setText(pujaName);
        mFragmentOrdersummeryBinding.tvPackageValue.setText(packageTypeIdDesc);
        mFragmentOrdersummeryBinding.tvPanditDetails.setText(pujaDesc);
        mFragmentOrdersummeryBinding.tvPriestPreferenceValue.setText(languageName);
        mFragmentOrdersummeryBinding.tvAllPujaSamagriesValue.setText(pujaSamagri);
        mFragmentOrdersummeryBinding.tvDakshinaValue.setText(procedure);
        mFragmentOrdersummeryBinding.tvAllPujaSamagriesValue.setText(pujaSamagri);
        mFragmentOrdersummeryBinding.tvHavanValue.setText(yajaman );
        mFragmentOrdersummeryBinding.tvDateTimeValue.setText(pujaDate+" " + "at"+" "+ pujaTime );
        mFragmentOrdersummeryBinding.tvSubTotalValue.setText(pujaAmount );


            // the String to int conversion happens here
            double cgst = Double.parseDouble(pujaAmount)*9/100;

            // print out the value after the conversion
            System.out.println("int i = " + cgst);
            double cgstvalue=Static.roundAvoid(cgst,2);
            mFragmentOrdersummeryBinding.tvCgstValue.setText(Double.toString(cgstvalue));


            // the String to int conversion happens here
            double sgst = Double.parseDouble(pujaAmount)*9/100;

            // print out the value after the conversion
            System.out.println("int i = " + sgst);
        double sgstvalue=Static.roundAvoid(sgst,2);
        mFragmentOrdersummeryBinding.tvSgstValue.setText(Double.toString(sgstvalue));




            double dd=cgstvalue+sgstvalue+Double.parseDouble(pujaAmount);

        mFragmentOrdersummeryBinding.tvTotalValue.setText(Double.toString(dd));



    }
}
