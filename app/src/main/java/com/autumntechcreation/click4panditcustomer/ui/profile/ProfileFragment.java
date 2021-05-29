package com.autumntechcreation.click4panditcustomer.ui.profile;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

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
    int custMasterId;
    String firstName,lastName,emailId,mobileNo;
    private String Document_img1="";
    Bitmap FixBitmap;
    ByteArrayOutputStream byteArrayOutputStream ;
    byte[] byteArray ;


    String ConvertImage ;

    String GetImageNameFromEditText;

    HttpURLConnection httpURLConnection ;

    URL url;

    OutputStream outputStream;

    BufferedWriter bufferedWriter ;

    int RC ;

    BufferedReader bufferedReader ;

    StringBuilder stringBuilder;
    ProgressDialog progressDialog ;
    boolean check = true;
   // String ServerUploadPath ="http://androidblog.esy.es/upload-image-server.php" ;
    String ServerUploadPath ="https://webapi-click4pandit.azurewebsites.net/api/Profile/SaveCustProfileImage" ;

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
                mProfileViewModel.getForSaveCustomerProfile(custMasterId,firstName,lastName,mobileNo,mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().toString(),emailId).observe(getActivity(),ProfileFragment.this::handleSaveCustomerProfile);
            }
        });
        //String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
        mFragmentProfileBinding.imgvwUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                FixBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

                mFragmentProfileBinding.imgVwProfile.setImageBitmap(FixBitmap);
                mFragmentProfileBinding.tvInitial.setVisibility(View.GONE);
                mFragmentProfileBinding.imgFace.setVisibility(View.GONE);
               // UploadImageToServer();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
    public void UploadImageToServer(){

        FixBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byteArray = byteArrayOutputStream.toByteArray();

        ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                progressDialog = ProgressDialog.show(getActivity(),"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                progressDialog.dismiss();

                Toast.makeText(getActivity(),string1,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {

                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put("cloudFileName", GetImageNameFromEditText);
                HashMapParams.put("cloudImgId", GetImageNameFromEditText);
                HashMapParams.put("custMasterId", GetImageNameFromEditText);
                HashMapParams.put("custMasterId", GetImageNameFromEditText);
                HashMapParams.put("custMasterProfImgId", GetImageNameFromEditText);
                HashMapParams.put("delFlg", GetImageNameFromEditText);
                HashMapParams.put("fileData", GetImageNameFromEditText);
                HashMapParams.put("fileData", GetImageNameFromEditText);
                HashMapParams.put("imgAction", GetImageNameFromEditText);
                HashMapParams.put("logonId", GetImageNameFromEditText);
                HashMapParams.put("mimeTyp", GetImageNameFromEditText);
                HashMapParams.put("orglFileName", GetImageNameFromEditText);
                HashMapParams.put("orglStamp", GetImageNameFromEditText);
                HashMapParams.put("orglUser", GetImageNameFromEditText);
                HashMapParams.put("updtStamp", GetImageNameFromEditText);

               // HashMapParams.put(ImageName, ConvertImage);

                String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
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
                   //if ( resource.data.custMasterProfileDataModel!=null) {
                     firstName=resource.data.custMasterProfileDataModel.firstName;
                     lastName=resource.data.custMasterProfileDataModel.lastName;
                     emailId=resource.data.custMasterProfileDataModel.emailId;
                     mobileNo=resource.data.custMasterProfileDataModel.mobile;
                     custMasterId=resource.data.custMasterProfileDataModel.custMasterId;
                    mFragmentProfileBinding.edtTxtFName.setText(firstName);
                    mFragmentProfileBinding.edtTxtLastName.setText(lastName);
                    mFragmentProfileBinding.edtTxtMobileNo.setText(mobileNo);
                    mFragmentProfileBinding.edtTxtEmail.setText(emailId);

                   // }
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
    }
}
