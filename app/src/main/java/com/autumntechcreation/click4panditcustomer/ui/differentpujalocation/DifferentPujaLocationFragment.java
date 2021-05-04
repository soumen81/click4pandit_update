package com.autumntechcreation.click4panditcustomer.ui.differentpujalocation;

import android.os.Bundle;
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
                }else if(mFragmentDifferentpujalocationBinding.edtTxtPincode.getText().toString().trim().equals("")){
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
                    action.setShippingState(mFragmentDifferentpujalocationBinding.edtTxtState.getText().toString());
                    action.setShippingCity(mFragmentDifferentpujalocationBinding.edtTxtCity.getText().toString());
                    action.setShippingPinCode(mFragmentDifferentpujalocationBinding.edtTxtPincode.getText().toString());
                    Navigation.findNavController(mView).navigate(action);
                }
            }
        });

    }
}
