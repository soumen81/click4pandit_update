package com.autumntechcreation.click4panditcustomer.ui.profile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Base64.*;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import java.nio.file.Files;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static androidx.navigation.Navigation.findNavController;

public class ProfileFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ProfileViewModel mProfileViewModel;
    FragmentProfileBinding mFragmentProfileBinding;
    private View mView;
    NavController navController;
    int custMasterId,custMasterProfImgId,custMasterIdvalue,newCustMasterId,newCustMasterImageId;
    double donewCustMasterId;
    String newMasterProfileImageid;
    String firstName,lastName,emailId,mobileNo,imgActionValue;
    private String Document_img1="";
    Bitmap fixBitmap;
    Bitmap decodedImage;
    FileInputStream fis = null;
    private static final int PERMISSION_REQUEST_CODE = 1;
    byte[] byteArray ;
    String updateCusProfileImgid,updateCustMasterId,updateLogonId,updateDelFlg,updateCloudImgid,updateorglFileName,
            updateCloudFileName,updateMimeType,updateImgAction,updateFileData;


    String ConvertImage,fileName,encoded,fileData,custMasterProfileImageModel;

    String imageString="";
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
        ((MainActivity) getActivity()).setToolbar(true,true,false,true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mProfileViewModel = ViewModelProviders.of(ProfileFragment.this, viewModelFactory).get(ProfileViewModel.class);
        mFragmentProfileBinding.setProfileViewModel(mProfileViewModel);
        UUID uuid = UUID.randomUUID();
        uuidInString = uuid.toString();
        cloudFileName=uuidInString+".jpeg";
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
               /* if(mFragmentProfileBinding.edtTxtFName.getText().toString().trim().equals("")){
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
                }*//*if(mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().toString().length()<10 ||(!Patterns.PHONE.matcher(mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().toString()).matches())){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Valid  MobileNo...")
                            .show();
                }*//*
                else {*/
                   // mProfileViewModel.getForSaveCustomerProfile(custMasterId, firstName, lastName, mobileNo, mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().toString(), emailId).observe(getActivity(), ProfileFragment.this::handleSaveCustomerProfile);
               // }

                if(mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().length()>9||(!Patterns.PHONE.matcher(mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().toString()).matches())) {


                    mProfileViewModel.getForSaveCustomerProfile(custMasterId, firstName, lastName, mobileNo, mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().toString(), emailId).observe(getActivity(), ProfileFragment.this::handleSaveCustomerProfile);
                }else if(!Patterns.PHONE.matcher(mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().toString()).matches()||
                        mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().toString().length()>9||
                       mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().toString().trim().length()<10){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();
                }
                else{
                    mProfileViewModel.getForSaveCustomerProfile(custMasterId, firstName, lastName, mobileNo,"", emailId).observe(getActivity(), ProfileFragment.this::handleSaveCustomerProfile);
                }
            }
        });

        mFragmentProfileBinding.imgvwUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View  v) {
                selectImage();
            }
        });






    }
    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkPermission()) {
                Uri selectedImage = data.getData();
                String picturePath = this.getRealPathFromURI(selectedImage);
                Log.d("Picture Path", picturePath);

                //Bitmap bitmap = ((BitmapDrawable)  mFragmentProfileBinding.imgVwProfile.getDrawable()).getBitmap();
                // Bitmap bitmap = null;


                //File f=new File("C:\\Users\\Soumen\\Downloads\\pandit.png");

                // You can use the API that requires the permission.

                        File objFile = new File(picturePath);
                        Log.e("FILEEEE", objFile + "");
                        if (objFile.exists()) {
                            Log.e("EXIST", objFile.exists() + "");
                            if (objFile.canRead()) {
                                Log.e("READ", objFile.canRead() + "");
                            }
                            if (objFile.isFile()) {
                                Log.e("ISFILE", objFile.isFile() + "");
                            }
                            if (objFile.getTotalSpace() > 0) {
                                Log.e("TOTALSPACE", objFile.getTotalSpace() + "");
                            }


                            try {
                                fis = new FileInputStream(objFile);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        byte[] data1 = new byte[(int) objFile.length()];
                        try {
                            fis.read(data1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        data1 = bos.toByteArray();
                        Log.e("DATAAAA1", data1 + "");
                    }
                }else{
                    requestPermission();
                }
              /*  Path path = Paths.get(picturePath);
                try {
                    byte[] data2 = Files.readAllBytes(path);
                    Log.e("DATA2222",data2+"");
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }


                /*InputStream inputStream = getActivity().getAssets().open(f);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                mFragmentProfileBinding.imgVwProfile.setImageBitmap(bitmap);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
                byte[] byteArray = stream.toByteArray();
                Bitmap compressedBitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
                mFragmentProfileBinding.imgVwProfile.setImageBitmap(compressedBitmap);*/
                /*try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mFragmentProfileBinding.imgVwProfile.setImageBitmap(bitmap);
                mFragmentProfileBinding.tvInitial.setVisibility(View.GONE);
                mFragmentProfileBinding.imgFace.setVisibility(View.GONE);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageInByte = baos.toByteArray();
                imageString = Base64.encodeToString(imageInByte, Base64.DEFAULT);*/

                if (newImgAction.equals("UPDATE")) {
                    mProfileViewModel.getAddProfileImageUpload(newCustMasterImageId, newCustMasterId, null, null, null, null, newCloudImgId,newOriginalFileName, newCloudFileName, newmimeType, "UPDATE", imageString).observe(getActivity(), ProfileFragment.this::handleUploadForImage);
                } else {

                    mProfileViewModel.getProfileImageUpload(uuidInString, cloudFileName, "ADD", imageString).observe(getActivity(), ProfileFragment.this::handleUploadForImage);
                }
            }

    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(getActivity(), "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    public void bitMaptoraw(Bitmap bitmap){
        int width = bitmap.getWidth();

        int height = bitmap.getHeight();



        int[] pixels = new int[width * height];

        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        byte[] pixelArray = new byte[pixels.length];

        for(int i = 0; i <pixels.length ; i++) {

            int pixel = pixels[i];

            int A = Color.alpha(pixel);

            int R = Color.red(pixel);

            int G = Color.green(pixel);

            int B = Color.blue(pixel);

            pixelArray[i] = (byte) (0.2989 * R + 0.5870 * G + 0.1140 * B);


        }
    }

    private void selectImage() {
        final CharSequence[] options = { "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                 if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }


  /*   if (newImgAction.equals("UPDATE")) {
        mProfileViewModel.getAddProfileImageUpload(newCustMasterImageId, newCustMasterId, null, null, null, null, newCloudImgId,newOriginalFileName, newCloudFileName, newmimeType, "UPDATE", imageString).observe(getActivity(), ProfileFragment.this::handleUploadForImage);
    } else {

        mProfileViewModel.getProfileImageUpload(uuidInString, cloudFileName, "ADD", imageString).observe(getActivity(), ProfileFragment.this::handleUploadForImage);
    }*/



    public String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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

                        donewCustMasterId=(Double)resource.data.custMasterProfileImageModel.custMasterId;
                        newCustMasterId=(int)donewCustMasterId;

                        newCustMasterImageId=resource.data.custMasterProfileImageModel.custMasterProfImgId;

                        mFragmentProfileBinding.edtTxtFName.setText(firstName);
                        mFragmentProfileBinding.edtTxtLastName.setText(lastName);
                        mFragmentProfileBinding.edtTxtMobileNo.setText(mobileNo);
                        mFragmentProfileBinding.edtTxtEmail.setText(emailId);
                        //imgActionValue=customerGetProfileModel.custMasterProfileImageModel.imgAction;
                        if(newImgAction==null && newImgAction=="" ){
                            newImgAction="ADD";
                        }
                        custMasterProfileImageModel=String.valueOf(resource.data.custMasterProfileImageModel);

                      byte[] imageBytes = Base64.decode(newFileDate, Base64.DEFAULT);
                         decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        mFragmentProfileBinding.imgVwProfile.setImageBitmap(decodedImage);
                        mFragmentProfileBinding.tvInitial.setVisibility(View.GONE);
                        mFragmentProfileBinding.imgFace.setVisibility(View.GONE);
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
                    Log.e("handleImageResponse", json + "");



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
