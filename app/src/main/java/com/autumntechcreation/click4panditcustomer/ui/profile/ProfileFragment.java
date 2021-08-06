package com.autumntechcreation.click4panditcustomer.ui.profile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
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
import android.webkit.MimeTypeMap;
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
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordAcitivity;
import com.autumntechcreation.click4panditcustomer.ui.differentpujalocation.DifferentPujaLocationFragment;
import com.autumntechcreation.click4panditcustomer.ui.differentpujalocation.DifferentPujaLocationViewModel;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryFragmentDirections;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummeryModel;
import com.autumntechcreation.click4panditcustomer.ui.transactionstatus.TransactionStatusFragmentArgs;
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
    String uuidInString;
    String cloudFileName;
    String newLogonId;
    String newDelFlag;
    String newCloudImgId;
    String newOriginalFileName;
    String newCloudFileName;
    String custMasterImgId;
    String newmimeType,strMimeType="";
    String newImgAction="",fileExt="",getAlternateNo;
    String newFileDate="",picturePath="",imageName="";
    boolean check = true;
    byte[] imageInByte;
    ByteArrayOutputStream byteArrayOutputStream;
    AssetManager assetManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        mFragmentProfileBinding.setLifecycleOwner(this);

        if(ProfileFragmentArgs.fromBundle(getArguments()).getAlterMobileNo().length()>0) {
            getAlternateNo = ProfileFragmentArgs.fromBundle(getArguments()).getAlterMobileNo();
            Log.e("ALTERNATENO", getAlternateNo);
        }

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
        if(getAlternateNo!=null) {
            mFragmentProfileBinding.edtTxtAlternateMobileNo.setText(getAlternateNo);
        }else{

            mFragmentProfileBinding.edtTxtAlternateMobileNo.setText( mProfileViewModel.getAlternateMobileNo());
        }
        UUID uuid = UUID.randomUUID();
        uuidInString = uuid.toString();
      //  cloudFileName=uuidInString+".jpeg";

        assetManager = getActivity().getAssets();
        mFragmentProfileBinding.imgvwUpdateProfile.setOnClickListener(new View.OnClickListener() {
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
                }else if  (mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().toString().length()>0 &&
                        !Patterns.PHONE.matcher(mFragmentProfileBinding.edtTxtAlternateMobileNo.getText().toString()).matches()) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();
                }
                else {
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
    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri uri = data.getData();
                    //Get picture path
                 picturePath =this.getRealPathFromURI(uri);
                Log.d("Picture Path", picturePath);
                Uri file = Uri.fromFile(new File(picturePath));
                //Name of the file extension
                 fileExt = MimeTypeMap.getFileExtensionFromUrl(file.toString());
                Log.d("FILE Extension", fileExt);
                //Mime Type
                 strMimeType=Static.getMimeType(file);
                Log.e("MIMETYPE",strMimeType);
                //FileName
                File f = new File(picturePath);
                 imageName = f.getName();
                Log.e("ImageName",imageName);

                try {
                    fixBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mFragmentProfileBinding.imgVwProfile.setImageBitmap(fixBitmap);
                mFragmentProfileBinding.tvInitial.setVisibility(View.GONE);
                mFragmentProfileBinding.imgFace.setVisibility(View.GONE);


                //Bitmap bitmap = ((BitmapDrawable)  mFragmentProfileBinding.imgVwProfile.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                fixBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageInByte = baos.toByteArray();
                Log.e("IMAGEBYTE",imageInByte.toString());
                //We are convert the Byte array to Base64 encoded String and Web end Convert from Base64 String to Byte Array for encoding add or update
                imageString = Base64.encodeToString(imageInByte, Base64.DEFAULT);
                Log.e("IMAGE",imageString);



            }




                if (newImgAction.equals("UPDATE")) {
                    mProfileViewModel.getAddProfileImageUpload(newCustMasterImageId, newCustMasterId, null, null, null, null, newCloudImgId,newOriginalFileName, newCloudFileName, newmimeType, "UPDATE", imageString).observe(getActivity(), ProfileFragment.this::handleUploadForImage);
                } else {
                    mProfileViewModel.getAddProfileImageUpload(newCustMasterImageId, newCustMasterId, null, null, null, null, uuidInString,imageName, uuidInString+"."+fileExt, strMimeType, "ADD", imageString).observe(getActivity(), ProfileFragment.this::handleUploadForImage);
                   // mProfileViewModel.getProfileImageUpload(uuidInString, cloudFileName, "ADD",newOriginalFileName, imageString).observe(getActivity(), ProfileFragment.this::handleUploadForImage);

                }
            }

    }
    public String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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


    /* if (newImgAction.equals("UPDATE")) {
        mProfileViewModel.getAddProfileImageUpload(newCustMasterImageId, newCustMasterId, null, null, null, null, newCloudImgId,newOriginalFileName, newCloudFileName, newmimeType, "UPDATE", imageString).observe(getActivity(), ProfileFragment.this::handleUploadForImage);
    } else {

        mProfileViewModel.getProfileImageUpload(uuidInString, cloudFileName, "ADD", imageString).observe(getActivity(), ProfileFragment.this::handleUploadForImage);
    }*/









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
                    //If some one update the image from web end and show the image mobile image then decrypt the image here...
                        if(newFileDate!=null) {
                            byte[] imageBytes = Base64.decode(newFileDate, Base64.DEFAULT);
                            decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                            mFragmentProfileBinding.imgVwProfile.setImageBitmap(decodedImage);
                            mFragmentProfileBinding.tvInitial.setVisibility(View.GONE);
                            mFragmentProfileBinding.imgFace.setVisibility(View.GONE);
                        }
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

                    /*byte[] imageBytes = Base64.decode(newFileDate, Base64.DEFAULT);
                    decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);*/

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
