package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaViewModel;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragmentDirections;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.navigation.Navigation.findNavController;

public class BillingDetailsFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    BillingDetailsViewModel mBillingDetailsViewModel;
    FragmentBillingdetailsBinding mFragmentBillingdetailsBinding;
    private View mView;
    NavController navController;
    int orderId,bkgId;
    String orderAmount,shippingAddress,shippingCity,shippingState,shippingPincode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBillingdetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_billingdetails, container, false);
        mFragmentBillingdetailsBinding.setLifecycleOwner(this);

        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingAddress().length()>0) {
            shippingAddress = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingAddress();
            Log.e("SHIPPINGADDRESS", shippingAddress);
        } if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingState().length()>0) {
            shippingState = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingState();
            Log.e("SHIPPINGSTATE", shippingState);
        } if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingCity().length()>0) {
            shippingCity = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingCity();
            Log.e("SHIPPINGCITY", shippingCity);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingPinCode().length()>0) {
            shippingPincode = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingPinCode();
            Log.e("SHIPPINGPINCODE", shippingPincode);
        }


        return mFragmentBillingdetailsBinding.getRoot();
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

        mBillingDetailsViewModel = ViewModelProviders.of(BillingDetailsFragment.this, viewModelFactory).get(BillingDetailsViewModel.class);
        mFragmentBillingdetailsBinding.setBillingDetailsViewModel(mBillingDetailsViewModel);

        mBillingDetailsViewModel.getOnClickBillingDiffLocation().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                findNavController(mView).navigate(BillingDetailsFragmentDirections.actionBillingDetailsFragmentToDifferentPujaLocationFragment());
            }
        });


        mFragmentBillingdetailsBinding.tvProceedtoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFragmentBillingdetailsBinding.edtTxtFirstName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter First Name...")
                            .show();
                } else if(mFragmentBillingdetailsBinding.edtTxtLastName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Last Name...")
                            .show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(mFragmentBillingdetailsBinding.edtTxtEmail.getText().toString()).matches()||
                        (mFragmentBillingdetailsBinding.edtTxtEmail.getText().toString().trim().equals(""))){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Valid  Email Address...")
                            .show();
                } else if (!Patterns.PHONE.matcher(mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString()).matches()||

                        (mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString().trim().equals(""))||(mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString().trim().length()<10)){
                    //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();
                } else if(mFragmentBillingdetailsBinding.edtTxtAddress.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Address...")
                            .show();
                }else if(mFragmentBillingdetailsBinding.edtTxtState.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter State...")
                            .show();
                }else if(mFragmentBillingdetailsBinding.edtTxtCity.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter City...")
                            .show();
                }else if(mFragmentBillingdetailsBinding.edtTxtPincode.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter PinCode...")
                            .show();
                }else{
                    if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderId()>0) {
                        orderId = BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderId();
                        Log.e("orderId", "" + orderId);
                    }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBkgId()>0) {
                        bkgId = BillingDetailsFragmentArgs.fromBundle(getArguments()).getBkgId();
                        Log.e("bkgId", "" + bkgId);
                    }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderAmount().length()>0){
                        orderAmount=BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderAmount();
                        Log.e("ORDERAMOUNT",orderAmount);
                    }

                }
            }
        });
            mFragmentBillingdetailsBinding.tvLocation.setText(shippingAddress+","+shippingState+","+shippingCity+","+shippingPincode);
    }
    }
