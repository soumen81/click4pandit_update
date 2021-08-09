package com.autumntechcreation.click4panditcustomer.ui.editprofile;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
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
import com.autumntechcreation.click4panditcustomer.databinding.FragmentEditprofileBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.autumntechcreation.click4panditcustomer.ui.profile.CustomerGetProfileModel;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileFragment;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileFragmentDirections;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileViewModel;
import com.autumntechcreation.click4panditcustomer.ui.profile.SaveCustomerprofileModel;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.navigation.Navigation.findNavController;

public class EditprofileFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    EditProfileViewModel mEditProfileViewModel;
    FragmentEditprofileBinding mFragmentEditprofileBinding;
    private View mView;
    NavController navController;
    int custMasterId;
    String firstName,lastName,emailId,mobileNo,imgActionValue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentEditprofileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_editprofile, container, false);
        mFragmentEditprofileBinding.setLifecycleOwner(this);

        return mFragmentEditprofileBinding.getRoot();
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

        mEditProfileViewModel = ViewModelProviders.of(EditprofileFragment.this, viewModelFactory).get(EditProfileViewModel.class);
        mFragmentEditprofileBinding.setEditProfileViewModel(mEditProfileViewModel);

        mFragmentEditprofileBinding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(mView).navigate(EditprofileFragmentDirections.actionEditprofileFragmentToProfileFragment());
            }
        });
        mEditProfileViewModel.customerGetProfile().observe(getActivity(),
                EditprofileFragment.this::handlegetCustomerProfile);

        mFragmentEditprofileBinding.tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFragmentEditprofileBinding.edtTxtFName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter First Name...")
                            .show();
                } else if(mFragmentEditprofileBinding.edtTxtLName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Last Name...")
                            .show();
                } else if (!Patterns.PHONE.matcher(mFragmentEditprofileBinding.edtTxtMobile.getText().toString()).matches()||

                        (mFragmentEditprofileBinding.edtTxtMobile.getText().toString().trim().equals(""))||(mFragmentEditprofileBinding.edtTxtMobile.getText().toString().trim().length()<10)){
                    //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(mFragmentEditprofileBinding.edtTxtEmail.getText().toString()).matches()||
                        (mFragmentEditprofileBinding.edtTxtEmail.getText().toString().trim().equals(""))){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Valid  Email Address...")
                            .show();
                }else if  (mFragmentEditprofileBinding.edtTxtAlternateMobile.getText().toString().length()>0 &&
                        !Patterns.PHONE.matcher(mFragmentEditprofileBinding.edtTxtAlternateMobile.getText().toString()).matches()) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();
                }else if(mFragmentEditprofileBinding.edtTxtMobile.getText().toString().equals(mFragmentEditprofileBinding.edtTxtAlternateMobile.getText().toString())){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.mobilenodoesnotmatch))
                            .show();
                }else if(mFragmentEditprofileBinding.edtTxtAlternateMobile.getText().toString().length()==0){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.mobilenoblank))
                            .show();
                }else if (!Patterns.PHONE.matcher(mFragmentEditprofileBinding.edtTxtAlternateMobile.getText().toString()).matches()||

                        (mFragmentEditprofileBinding.edtTxtAlternateMobile.getText().toString().trim().equals(""))||(mFragmentEditprofileBinding.edtTxtAlternateMobile.getText().toString().trim().length()<10)){
                    //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();
                }

                else{
                    mEditProfileViewModel.getForSaveCustomerProfile(custMasterId, firstName, lastName, mobileNo, mFragmentEditprofileBinding.edtTxtAlternateMobile.getText().toString(), emailId).observe(getActivity(), EditprofileFragment.this::handleSaveCustomerProfile);
                }
            }
        });
    }


    private void handlegetCustomerProfile(Resource<CustomerGetProfileModel> resource) {
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

                    firstName=resource.data.custMasterProfileDataModel.firstName;
                    lastName=resource.data.custMasterProfileDataModel.lastName;
                    emailId=resource.data.custMasterProfileDataModel.emailId;
                    mobileNo=resource.data.custMasterProfileDataModel.mobile;

                    custMasterId=resource.data.custMasterProfileDataModel.custMasterId;


                        mFragmentEditprofileBinding.edtTxtFName.setText(firstName);
                        mFragmentEditprofileBinding.edtTxtFName.setEnabled(false);
                        mFragmentEditprofileBinding.edtTxtLName.setText(lastName);
                        mFragmentEditprofileBinding.edtTxtLName.setEnabled(false);
                        mFragmentEditprofileBinding.edtTxtMobile.setText(mobileNo);
                        mFragmentEditprofileBinding.edtTxtMobile.setEnabled(false);
                        mFragmentEditprofileBinding.edtTxtEmail.setText(emailId);
                        mFragmentEditprofileBinding.edtTxtEmail.setEnabled(false);





                    DisplayDialog.getInstance().dismissAlertDialog();
                    break;
                default:
                    DisplayDialog.getInstance().dismissAlertDialog();

                    break;
            }
        }
    }



    private void handleSaveCustomerProfile(Resource<SaveCustomerprofileModel> resource) {
        if (resource != null) {

            switch (resource.status) {
                case ERROR:
                    DisplayDialog.getInstance().dismissAlertDialog();
                    if (resource.message != null && resource.data == null) {
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
                    } else if (!Static.isNetworkAvailable(getActivity()) && resource.data == null) {

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
                    if (resource.data.returnStatus.equals("SUCCESS")) {

                       String strAlternateMobileNo= mEditProfileViewModel.storeAlternateMobileNo(mFragmentEditprofileBinding.edtTxtAlternateMobile.getText().toString());
                       Log.e("ALTERNATENO",strAlternateMobileNo);
                       // findNavController(mView).navigate(EditprofileFragmentDirections.actionEditprofileFragmentToProfileFragment());



                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText(this.getString(R.string.success))
                                .setContentText(this.getString(R.string.editprofilesucess))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();

                                    }
                                })


                                .show();

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
