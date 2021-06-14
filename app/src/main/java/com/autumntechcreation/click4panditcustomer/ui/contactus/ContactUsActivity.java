package com.autumntechcreation.click4panditcustomer.ui.contactus;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityContactusBinding;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordAcitivity;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordModel;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordViewModel;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dagger.android.AndroidInjection;

public class ContactUsActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityContactusBinding mActivityContactusBinding;
    ContactUsViewModel contactUsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityContactusBinding = DataBindingUtil.setContentView(this, R.layout.activity_contactus);

        AndroidInjection.inject(this);
        contactUsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ContactUsViewModel.class);
        mActivityContactusBinding.setLifecycleOwner(this);
        mActivityContactusBinding.setContactUsViewModel(contactUsViewModel);


       /* String url ="https://www.click4pandit.com/Home/ContactUs";

        mActivityContactusBinding.wvContactUs.getSettings().setLoadsImagesAutomatically(true);
        mActivityContactusBinding.wvContactUs.getSettings().setJavaScriptEnabled(true);
        mActivityContactusBinding.wvContactUs.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mActivityContactusBinding.wvContactUs.loadUrl(url);*/

        mActivityContactusBinding.imgbackContactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mActivityContactusBinding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mActivityContactusBinding.edtTxtFirstName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(ContactUsActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter  Name...")
                            .show();
                }else if (!Patterns.PHONE.matcher(mActivityContactusBinding.edtTxtMobileNo.getText().toString()).matches()||

                        (mActivityContactusBinding.edtTxtMobileNo.getText().toString().trim().equals(""))||(mActivityContactusBinding.edtTxtMobileNo.getText().toString().trim().length()<10)){
                    //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();

                    new SweetAlertDialog(ContactUsActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();

                }else if (!Patterns.EMAIL_ADDRESS.matcher(mActivityContactusBinding.edtTxtEmail.getText().toString()).matches()||
                        (mActivityContactusBinding.edtTxtEmail.getText().toString().trim().equals(""))){
                    new SweetAlertDialog(ContactUsActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Valid  Email Address...")
                            .show();
                }else if(mActivityContactusBinding.edtTxtAddress.getText().toString().trim().equals("")){
                    new SweetAlertDialog(ContactUsActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Address...")
                            .show();
                }else if(mActivityContactusBinding.edtTxtMessage.getText().toString().trim().equals("")){
                    new SweetAlertDialog(ContactUsActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Message...")
                            .show();
                }else{
                    contactUsViewModel.getContactUsByEmail(mActivityContactusBinding.edtTxtFirstName.getText().toString(),
                            mActivityContactusBinding.edtTxtMobileNo.getText().toString(),mActivityContactusBinding.edtTxtEmail.getText().toString(),mActivityContactusBinding.edtTxtAddress.getText().toString(),
                            mActivityContactusBinding.edtTxtMessage.getText().toString()).observe(ContactUsActivity.this,
                            ContactUsActivity.this::handleContactUsSendEmail);

                }
            }
        });


    }
    private void handleContactUsSendEmail(Resource<ContactUsModel> resource) {
        if (resource != null) {

            switch (resource.status) {
                case ERROR:

                    DisplayDialog.getInstance().dismissAlertDialog();
                    if (resource.message != null &&  resource.data==null) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(resource.message);

                            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(jsonObject.getString("error"))
                                    .setContentText(jsonObject.getString("error_description"))
                                    .show();

                        } catch (JSONException e) {
                            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Error")
                                    .setContentText("Unhandle Error")
                                    .show();
                        }
                    } else if (!Static.isNetworkAvailable(ContactUsActivity.this) && resource.data==null) {

                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(this.getString(R.string.nointernet))
                                .setContentText(this.getString(R.string.nointernetdetails))
                                .show();

                    }
                    break;
                case LOADING:
                    Log.e("handleForgetResponse", "LOADING");
                    DisplayDialog.getInstance().showAlertDialog(ContactUsActivity.this, ContactUsActivity.this.getString(R.string.please_wait));


                    break;
                case SUCCESS:
                    Log.e("handleForgetPasswordResponse", "SUCCESS");
                    // Log.e("handleLoginResponse",resource.message);
                    Log.e("handleForgetPasswordResponse", resource.status + "");
                    Log.e("handleForgetPasswordResponse", resource.data + "");
                    Gson gson = new Gson();
                    String json = gson.toJson(resource.data);
                    Log.e("handleForgetPasswordResponse", json + "");
                    if ( resource.data.returnStatus.equals("SUCCESS")) {
                        new SweetAlertDialog(ContactUsActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText(this.getString(R.string.success))
                                .setContentText(this.getString(R.string.thankcontactus))
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
