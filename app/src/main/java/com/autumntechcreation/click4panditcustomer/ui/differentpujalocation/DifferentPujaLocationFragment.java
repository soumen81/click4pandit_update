package com.autumntechcreation.click4panditcustomer.ui.differentpujalocation;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentDifferentpujalocationBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsViewModel;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.navigation.Navigation.findNavController;

public class DifferentPujaLocationFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentDifferentpujalocationBinding mFragmentDifferentpujalocationBinding;
    DifferentPujaLocationViewModel mDifferentPujaLocationViewModel;
    private View mView;
    NavController navController;
    String pujaDatetime,shippingorderAmount,getPujaAmount,getSgstvalue,getCgstvalue,getBillingAddress,
            getBillingAddress2,getBillingAddress3,getBillingPinCode;
    int shippingbkgId,shippingorderId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentDifferentpujalocationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_differentpujalocation, container, false);
        mFragmentDifferentpujalocationBinding.setLifecycleOwner(this);
        if(DifferentPujaLocationFragmentArgs.fromBundle(getArguments()).getDateTime().length()>0) {
            pujaDatetime = DifferentPujaLocationFragmentArgs.fromBundle(getArguments()).getDateTime();
            Log.e("PUJADATETIME", pujaDatetime);
        }
        if(DifferentPujaLocationFragmentArgs.fromBundle(getArguments()).getBkgId()>0) {
            shippingbkgId = DifferentPujaLocationFragmentArgs.fromBundle(getArguments()).getShippingBkgId();
            Log.e("bkgId", "" + shippingbkgId);
        }
        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderId()>0) {
            shippingorderId = BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderId();
            Log.e("shippingorderId", "" + shippingorderId);
        }
        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderAmount().length()>0){
           shippingorderAmount=BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderAmount();
            Log.e("SHIPPINGORDERAMOUNT",shippingorderAmount);
        }
        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getPujaAmount().length()>0){
            getPujaAmount=BillingDetailsFragmentArgs.fromBundle(getArguments()).getPujaAmount();
            Log.e("PUJAAMOUNT",getPujaAmount);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getSgstvalue().length()>0){
            getSgstvalue=BillingDetailsFragmentArgs.fromBundle(getArguments()).getSgstvalue();
            Log.e("SGSSTVALUE",getSgstvalue);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getCgstvalue().length()>0){
            getCgstvalue=BillingDetailsFragmentArgs.fromBundle(getArguments()).getCgstvalue();
            Log.e("CGSTVALUE",getCgstvalue);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress().length()>0){
            getBillingAddress=BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress();
            Log.e("BILLINGADDRESS",getBillingAddress);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress2().length()>0){
            getBillingAddress2=BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress2();
            Log.e("BILLINGADDRESS2",getBillingAddress2);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress3().length()>0){
            getBillingAddress3=BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress3();
            Log.e("BILLINGADDRES3",getBillingAddress3);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingPincode().length()>0){
            getBillingPinCode=BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingPincode();
            Log.e("BILLINGPINCODE",getBillingPinCode);
        }
        return mFragmentDifferentpujalocationBinding.getRoot();
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

        mDifferentPujaLocationViewModel = ViewModelProviders.of(DifferentPujaLocationFragment.this, viewModelFactory).get(DifferentPujaLocationViewModel.class);
        mFragmentDifferentpujalocationBinding.setBillingDetailsViewModel(mDifferentPujaLocationViewModel);


        mFragmentDifferentpujalocationBinding.tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFragmentDifferentpujalocationBinding.edtTxtFirstName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter First Name...")
                            .show();
                }else if(mFragmentDifferentpujalocationBinding.edtTxtLastName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Last Name...")
                            .show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(mFragmentDifferentpujalocationBinding.edtTxtEmail.getText().toString()).matches()||
                        (mFragmentDifferentpujalocationBinding.edtTxtEmail.getText().toString().trim().equals(""))){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Valid  Email Address...")
                            .show();
                }else if (!Patterns.PHONE.matcher(mFragmentDifferentpujalocationBinding.edtAlternateMobileNo.getText().toString()).matches()||

                        (mFragmentDifferentpujalocationBinding.edtAlternateMobileNo.getText().toString().trim().equals(""))||(mFragmentDifferentpujalocationBinding.edtAlternateMobileNo.getText().toString().trim().length()<10)){
                    //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();
                } else if(mFragmentDifferentpujalocationBinding.edtTxtAddress.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Address...")
                            .show();
                }else if(mFragmentDifferentpujalocationBinding.edtTxtState.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter State...")
                            .show();
                }else if(mFragmentDifferentpujalocationBinding.edtTxtCity.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter City...")
                            .show();
                }else if(mFragmentDifferentpujalocationBinding.edtTxtPincode.getText().toString().trim().equals("")||(mFragmentDifferentpujalocationBinding.edtTxtPincode.getText().toString().length()<6)){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter PinCode...")
                            .show();
                }else if(mFragmentDifferentpujalocationBinding.edtTxtAdditionalInfo.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Additional Info...")
                            .show();
                }else{
                    DifferentPujaLocationFragmentDirections.ActionDifferentPujaLocationFragmentToBillingDetailsFragment action=
                            DifferentPujaLocationFragmentDirections.actionDifferentPujaLocationFragmentToBillingDetailsFragment();
                    action.setShippingAddress(mFragmentDifferentpujalocationBinding.edtTxtAddress.getText().toString());
                    action.setShippingAddress2(mFragmentDifferentpujalocationBinding.edtTxtAddress2.getText().toString());
                    action.setShippingAddress3(mFragmentDifferentpujalocationBinding.edtTxtAddress3.getText().toString());
                    action.setShippingState(mFragmentDifferentpujalocationBinding.edtTxtState.getText().toString());
                    action.setShippingCity(mFragmentDifferentpujalocationBinding.edtTxtCity.getText().toString());
                    action.setShippingPinCode(mFragmentDifferentpujalocationBinding.edtTxtPincode.getText().toString());
                    action.setShippingFname(mFragmentDifferentpujalocationBinding.edtTxtFirstName.getText().toString());
                    action.setShippingLname(mFragmentDifferentpujalocationBinding.edtTxtLastName.getText().toString());
                    action.setShippingemail(mFragmentDifferentpujalocationBinding.edtTxtEmail.getText().toString());
                    action.setAdditionalInfo(mFragmentDifferentpujalocationBinding.edtTxtAdditionalInfo.getText().toString());
                    action.setShippingMobile(mFragmentDifferentpujalocationBinding.edtAlternateMobileNo.getText().toString());
                    action.setDateTime(pujaDatetime);
                    action.setShippingBkgId(shippingbkgId);
                    action.setOrderAmount(shippingorderAmount);
                    action.setPujaAmount(getPujaAmount);
                    action.setCgstvalue(getCgstvalue);
                    action.setSgstvalue(getSgstvalue);
                    action.setOrderId(shippingorderId);
                    action.setBillingAddress(getBillingAddress);
                    action.setBillingPincode(getBillingPinCode);
                    action.setBillingAddress2(getBillingAddress2);
                    action.setBillingAddress3(getBillingAddress3);

                    action.setStatusShippingId(1);
                    Navigation.findNavController(mView).navigate(action);
                }
            }
        });

    }
}
