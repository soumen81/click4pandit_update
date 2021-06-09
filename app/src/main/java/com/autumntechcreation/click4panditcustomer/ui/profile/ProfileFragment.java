package com.autumntechcreation.click4panditcustomer.ui.profile;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentProfileBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordAcitivity;
import com.autumntechcreation.click4panditcustomer.ui.differentpujalocation.DifferentPujaLocationFragment;
import com.autumntechcreation.click4panditcustomer.ui.differentpujalocation.DifferentPujaLocationViewModel;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryFragmentDirections;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummeryModel;
import com.autumntechcreation.click4panditcustomer.util.ImageProcessClass;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.app.Activity.RESULT_OK;
import static androidx.navigation.Navigation.findNavController;

public class ProfileFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ProfileViewModel mProfileViewModel;
    FragmentProfileBinding mFragmentProfileBinding;
    private View mView;
    NavController navController;
    int custMasterId,custMasterProfImgId,custMasterIdvalue;
    String newMasterProfileImageid;
    String firstName,lastName,emailId,mobileNo,imgActionValue;
    private String Document_img1="";
    Bitmap fixBitmap;

    byte[] byteArray ;
    String updateCusProfileImgid,updateCustMasterId,updateLogonId,updateDelFlg,updateCloudImgid,updateorglFileName,
            updateCloudFileName,updateMimeType,updateImgAction,updateFileData;


    String ConvertImage,fileName,encoded,fileData,custMasterProfileImageModel;


    String uuidInString,cloudFileName,newLogonId,newDelFlag,newCloudImgId,newOriginalFileName,
            newCloudFileName,custMasterImgId,
            newmimeType,newImgAction="",newFileDate;
    boolean check = true;

    ByteArrayOutputStream byteArrayOutputStream;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        mFragmentProfileBinding.setLifecycleOwner(this);

        return mFragmentProfileBinding.getRoot();
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

        mProfileViewModel = ViewModelProviders.of(ProfileFragment.this, viewModelFactory).get(ProfileViewModel.class);
        mFragmentProfileBinding.setProfileViewModel(mProfileViewModel);
        UUID uuid = UUID.randomUUID();
        uuidInString = uuid.toString();
        cloudFileName=uuidInString+".jpg";
        mFragmentProfileBinding.imgVwEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(mView).navigate(ProfileFragmentDirections.actionProfileFragmentToEditprofileFragment());
            }
        });

        mProfileViewModel.customerGetProfile().observe(getActivity(),
                ProfileFragment.this::handlegetCustomerProfile);

        mFragmentProfileBinding.tvSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFragmentProfileBinding.edtTxtFName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter First Name...")
                            .show();
                } else if(mFragmentProfileBinding.edtTxtLastName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Last Name...")
                            .show();
                } else if (!Patterns.PHONE.matcher(mFragmentProfileBinding.edtTxtMobileNo.getText().toString()).matches()||

                        (mFragmentProfileBinding.edtTxtMobileNo.getText().toString().trim().equals(""))||(mFragmentProfileBinding.edtTxtMobileNo.getText().toString().trim().length()<10)){
                    //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(mFragmentProfileBinding.edtTxtEmail.getText().toString()).matches()||
                        (mFragmentProfileBinding.edtTxtEmail.getText().toString().trim().equals(""))){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Valid  Email Address...")
                            .show();
                }else {
                    mProfileViewModel.getForSaveCustomerProfile(custMasterId, firstName, lastName, mobileNo, mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().toString(), emailId).observe(getActivity(), ProfileFragment.this::handleSaveCustomerProfile);
                }
            }
        });

        mFragmentProfileBinding.imgvwUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View  v) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);


            }
        });


    }
    @Override
    public void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();


            try {

                fixBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

                mFragmentProfileBinding.imgVwProfile.setImageBitmap(fixBitmap);
                mFragmentProfileBinding.tvInitial.setVisibility(View.GONE);
                mFragmentProfileBinding.imgFace.setVisibility(View.GONE);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                fixBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();

                encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                Log.e("Encoded",encoded);
                //fileName = new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
                fileName = "fav.jpg";
                if(newImgAction.equals("UPDATE")){
                    mProfileViewModel.getAddProfileImageUpload(Integer.parseInt(newMasterProfileImageid) ,custMasterId,null,null,null,null,uuidInString,newOriginalFileName,cloudFileName,"image/jpeg","UPDATE",newFileDate).observe(getActivity(),ProfileFragment.this::handleUploadForImage);
                }else{

                    mProfileViewModel.getProfileImageUpload(null, null, null, null, null, null, uuidInString, fileName, cloudFileName, "image/jpeg","ADD", encoded).observe(getActivity(), ProfileFragment.this::handleUploadForImage);
                }


            } catch (IOException e) {

                e.printStackTrace();
            }
        }
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

                    if(resource.data.custMasterProfileImageModel==null){
                        mFragmentProfileBinding.edtTxtFName.setText(firstName);
                        mFragmentProfileBinding.edtTxtLastName.setText(lastName);
                        mFragmentProfileBinding.edtTxtMobileNo.setText(mobileNo);
                        mFragmentProfileBinding.edtTxtEmail.setText(emailId);
                        mFragmentProfileBinding.edtTxtEmail.setEnabled(false);



                    }else{
                            try {

                                newMasterProfileImageid = String.valueOf(resource.data.custMasterProfileImageModel.custMasterProfImgId);
                            }catch(Exception e){
                                e.printStackTrace();
                            }

                        newLogonId=resource.data.custMasterProfileImageModel.logonId;
                        newDelFlag=resource.data.custMasterProfileImageModel.delFlg;
                        newCloudImgId=resource.data.custMasterProfileImageModel.cloudImgId;
                        newOriginalFileName=resource.data.custMasterProfileImageModel.orglFileName;
                        newCloudFileName=resource.data.custMasterProfileImageModel.cloudFileName;
                        newmimeType=resource.data.custMasterProfileImageModel.mimeTyp;
                        newImgAction=resource.data.custMasterProfileImageModel.imgAction;
                        newFileDate=resource.data.custMasterProfileImageModel.fileData;

                        mFragmentProfileBinding.edtTxtFName.setText(firstName);
                        mFragmentProfileBinding.edtTxtLastName.setText(lastName);
                        mFragmentProfileBinding.edtTxtMobileNo.setText(mobileNo);
                        mFragmentProfileBinding.edtTxtEmail.setText(emailId);
                        //imgActionValue=customerGetProfileModel.custMasterProfileImageModel.imgAction;
                        if(newImgAction==null && newImgAction=="" ){
                            newImgAction="ADD";
                        }
                        custMasterProfileImageModel=String.valueOf(resource.data.custMasterProfileImageModel);

                    }
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
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText(this.getString(R.string.success))
                                .setContentText(this.getString(R.string.profilesave))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                    }
                                }).show();


                    }
                    DisplayDialog.getInstance().dismissAlertDialog();
                    break;
                default:
                    DisplayDialog.getInstance().dismissAlertDialog();

                    break;
            }
        }
    } private void handleUploadForImage(Resource<CustomerGetProfileModel> resource) {
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
                   // if(resource.data.custMasterProfileImageModel.returnStatus.equals("SUCCESS")) {

                       /* updateCusProfileImgid = String.valueOf(resource.data.custMasterProfileImageModel.custMasterProfImgId);
                        updateCustMasterId = String.valueOf(resource.data.custMasterProfileImageModel.custMasterId);
                        updateLogonId = resource.data.custMasterProfileImageModel.logonId;
                        updateDelFlg = resource.data.custMasterProfileImageModel.delFlg;
                        updateCloudImgid = resource.data.custMasterProfileImageModel.cloudImgId;
                        updateorglFileName = resource.data.custMasterProfileImageModel.orglFileName;
                        updateCloudFileName = resource.data.custMasterProfileImageModel.cloudFileName;
                        updateMimeType = resource.data.custMasterProfileImageModel.mimeTyp;
                        updateImgAction = resource.data.custMasterProfileImageModel.imgAction;
                        updateFileData = resource.data.custMasterProfileImageModel.fileData;
*/

                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText(this.getString(R.string.success))
                                .setContentText(this.getString(R.string.imageupload))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                    }
                                }).show();


                   // }
                    DisplayDialog.getInstance().dismissAlertDialog();
                    break;
                default:
                    DisplayDialog.getInstance().dismissAlertDialog();

                    break;
            }
        }
    }
}
