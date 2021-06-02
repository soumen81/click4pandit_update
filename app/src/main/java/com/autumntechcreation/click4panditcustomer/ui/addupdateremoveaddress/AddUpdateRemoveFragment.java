package com.autumntechcreation.click4panditcustomer.ui.addupdateremoveaddress;

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
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentAddupdateremoveBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.address.AddressFragment;
import com.autumntechcreation.click4panditcustomer.ui.address.AddressViewModel;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.ProceedtoPayModel;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaFragmentArgs;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.navigation.Navigation.findNavController;

public class AddUpdateRemoveFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentAddupdateremoveBinding mFragmentAddupdateremoveBinding;
    AddUpdateRemoveViewModel mAddUpdateRemoveViewModel;
    private View mView;
    NavController navController;
    String addAction="",updateAction="",removeAction="",getFirstName,getLastName,getAddress1,getCity,
            getState,getPincode,updtStamp,orglStamp;
    int shippingAddressId,custMasterId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mFragmentAddupdateremoveBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_addupdateremove, container, false);
        mFragmentAddupdateremoveBinding.setLifecycleOwner(this);

        if(AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getFirstName().length()>0) {
            getFirstName = AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getFirstName();
            Log.e("getFirstName", "" + getFirstName);
        }  if(AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getLastName().length()>0) {
            getLastName = AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getLastName();
            Log.e("getLastName", "" + getLastName);
        } if(AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getAddress1().length()>0) {
            getAddress1 = AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getAddress1();
            Log.e("getAddress1", "" + getAddress1);
        } if(AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getCity().length()>0) {
            getCity = AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getCity();
            Log.e("getCity", "" + getCity);
        } if(AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getState().length()>0) {
            getState = AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getState();
            Log.e("getState", "" + getState);
        } if(AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getPincode().length()>0) {
            getPincode = AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getPincode();
            Log.e("getPincode", "" + getPincode);
        }if(AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getShippingAddressId()!=0){
            shippingAddressId=AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getShippingAddressId();
        }if(AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getCustMasterId()!=0){
            custMasterId=AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getCustMasterId();
        } if(AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getUpdtStamp().length()>0) {
            updtStamp = AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getUpdtStamp();
            Log.e("updtStamp", "" + updtStamp);
        } if(AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getOrglStamp().length()>0) {
            orglStamp = AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getOrglStamp();
            Log.e("orglStamp", "" + orglStamp);
        }

            addAction = AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getAddAction();
            Log.e("addAction", "" + addAction);
        if(addAction.equals("ADD")) {
            mFragmentAddupdateremoveBinding.tvUpdateConfirm.setVisibility(View.GONE);
            mFragmentAddupdateremoveBinding.removeConfirm.setVisibility(View.GONE);
            mFragmentAddupdateremoveBinding.tvAddConfirm.setVisibility(View.VISIBLE);
            mFragmentAddupdateremoveBinding.edtTxtCity.setText("Kolkata");
            mFragmentAddupdateremoveBinding.edtTxtState.setText("West Bengal");
        }
            updateAction = AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getUpdateAction();
            Log.e("updateAction", "" + updateAction);
        if(updateAction.equals("UPDATE")) {
            mFragmentAddupdateremoveBinding.tvUpdateConfirm.setVisibility(View.VISIBLE);
            mFragmentAddupdateremoveBinding.removeConfirm.setVisibility(View.GONE);
            mFragmentAddupdateremoveBinding.tvAddConfirm.setVisibility(View.GONE);
            mFragmentAddupdateremoveBinding.edtTxtFirstName.setText(getFirstName);
            mFragmentAddupdateremoveBinding.edtTxtLastName.setText(getLastName);
            mFragmentAddupdateremoveBinding.edtTxtAddress1.setText(getAddress1);
            mFragmentAddupdateremoveBinding.edtTxtCity.setText("Kolkata");
            mFragmentAddupdateremoveBinding.edtTxtState.setText("West Bengal");
            mFragmentAddupdateremoveBinding.edtTxtPinCode.setText(getPincode);
        }
            removeAction = AddUpdateRemoveFragmentArgs.fromBundle(getArguments()).getRemoveAction();
            Log.e("removeAction", "" + removeAction);
        if(removeAction.equals("DELETE")) {
            mFragmentAddupdateremoveBinding.tvUpdateConfirm.setVisibility(View.GONE);
            mFragmentAddupdateremoveBinding.removeConfirm.setVisibility(View.VISIBLE);
            mFragmentAddupdateremoveBinding.tvAddConfirm.setVisibility(View.GONE);
            mFragmentAddupdateremoveBinding.edtTxtFirstName.setText(getFirstName);
            mFragmentAddupdateremoveBinding.edtTxtLastName.setText(getLastName);
            mFragmentAddupdateremoveBinding.edtTxtAddress1.setText(getAddress1);
            mFragmentAddupdateremoveBinding.edtTxtCity.setText("Kolkata");
            mFragmentAddupdateremoveBinding.edtTxtState.setText("West Bengal");
            mFragmentAddupdateremoveBinding.edtTxtPinCode.setText(getPincode);
        }



        return mFragmentAddupdateremoveBinding.getRoot();

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

        mAddUpdateRemoveViewModel = ViewModelProviders.of(AddUpdateRemoveFragment.this, viewModelFactory).get(AddUpdateRemoveViewModel.class);
        mFragmentAddupdateremoveBinding.setAddUpdateRemoveViewModel(mAddUpdateRemoveViewModel);

        mFragmentAddupdateremoveBinding.tvAddConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFragmentAddupdateremoveBinding.edtTxtFirstName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter First Name...")
                            .show();
                } else if(mFragmentAddupdateremoveBinding.edtTxtLastName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Last Name...")
                            .show();
                }else if(mFragmentAddupdateremoveBinding.edtTxtAddress1.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Address1...")
                            .show();
                }
            else if(mFragmentAddupdateremoveBinding.edtTxtCity.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter City...")
                            .show();
                }
            else if(mFragmentAddupdateremoveBinding.edtTxtState.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter State...")
                            .show();
                } else if(mFragmentAddupdateremoveBinding.edtTxtPinCode.getText().toString().trim().equals("")||(mFragmentAddupdateremoveBinding.edtTxtPinCode.getText().toString().length()<6)){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter PinCode...")
                            .show();
                }else{



                    mAddUpdateRemoveViewModel.getNewAddAddress(addAction,
                            mFragmentAddupdateremoveBinding.edtTxtFirstName.getText().toString(),
                            mFragmentAddupdateremoveBinding.edtTxtLastName.getText().toString(), mFragmentAddupdateremoveBinding.edtTxtAddress1.getText().toString(), mFragmentAddupdateremoveBinding.edtTxtPinCode.getText().toString()).observe(getActivity(), AddUpdateRemoveFragment.this::handleAddAdrress);
                }


            }
        });



            mFragmentAddupdateremoveBinding.tvUpdateConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAddUpdateRemoveViewModel.getAddAddress(updateAction,shippingAddressId,custMasterId,orglStamp,
                            mFragmentAddupdateremoveBinding.edtTxtFirstName.getText().toString(),
                            mFragmentAddupdateremoveBinding.edtTxtLastName.getText().toString(),
                            mFragmentAddupdateremoveBinding.edtTxtAddress1.getText().toString(),
                            mFragmentAddupdateremoveBinding.edtTxtPinCode.getText().toString())
                            .observe(getActivity(), AddUpdateRemoveFragment.this::handleAddAdrress);
                }
            });


            mFragmentAddupdateremoveBinding.removeConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAddUpdateRemoveViewModel.getAddAddress(removeAction,shippingAddressId,custMasterId,orglStamp,
                            mFragmentAddupdateremoveBinding.edtTxtFirstName.getText().toString(),
                            mFragmentAddupdateremoveBinding.edtTxtLastName.getText().toString(),
                            mFragmentAddupdateremoveBinding.edtTxtAddress1.getText().toString(),
                            mFragmentAddupdateremoveBinding.edtTxtPinCode.getText().toString())
                            .observe(getActivity(), AddUpdateRemoveFragment.this::handleAddAdrress);
                }
            });


    }



    private void handleAddAdrress(Resource<AddAddressModel> resource) {
        if (resource != null) {

            switch (resource.status) {
                case ERROR:
                    DisplayDialog.getInstance().dismissAlertDialog();
                    if (resource.message != null &&  resource.data==null) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(resource.message);

                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(jsonObject.getString("error"))
                                    .setContentText(jsonObject.getString("error_description"))
                                    .show();

                        } catch (JSONException e) {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Error")
                                    .setContentText("Unhandle Error")
                                    .show();
                        }
                    } else if (!Static.isNetworkAvailable(getActivity()) && resource.data==null) {

                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(this.getString(R.string.nointernet))
                                .setContentText(this.getString(R.string.nointernetdetails))
                                .show();

                    }

                    break;
                case LOADING:
                    Log.e("handleRegisterResponse", "LOADING");
                    DisplayDialog.getInstance().showAlertDialog(getActivity(), getActivity().getString(R.string.please_wait));


                    break;
                case SUCCESS:
                    Log.e("handleRegisterResponse", "SUCCESS");
                    // Log.e("handleLoginResponse",resource.message);
                    Log.e("handleRegisterResponse", resource.status + "");
                    Log.e("handleRegisterResponse", resource.data + "");
                    Gson gson = new Gson();
                    String json = gson.toJson(resource.data);
                    Log.e("handleRegisterResponse", json + "");
                    if ( resource.data.returnStatus.equals("SUCCESS")) {
                       AddUpdateRemoveFragmentDirections.ActionAddUpdateRemoveFragmentToAddressFragment action=
                               AddUpdateRemoveFragmentDirections.actionAddUpdateRemoveFragmentToAddressFragment();
                        Navigation.findNavController(mView).navigate(action);



                    }
                    DisplayDialog.getInstance().dismissAlertDialog();
                    break;
                default:
                    DisplayDialog.getInstance().dismissAlertDialog();

                    break;
            }
        }
    }
}
