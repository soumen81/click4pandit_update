package com.autumntechcreation.click4panditcustomer.ui.sendenquiry;

import android.content.Intent;
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
import com.autumntechcreation.click4panditcustomer.databinding.ActivitySendenquiryBinding;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.autumntechcreation.click4panditcustomer.ui.refundpolicy.RefundPolicyViewModel;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterResponse;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dagger.android.AndroidInjection;

public class SendEnquiryActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivitySendenquiryBinding mActivitySendenquiryBinding;
    SendEnquiryViewModel mSendEnquiryViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivitySendenquiryBinding = DataBindingUtil.setContentView(this, R.layout.activity_sendenquiry);

        AndroidInjection.inject(this);
        mSendEnquiryViewModel = ViewModelProviders.of(this, viewModelFactory).get(SendEnquiryViewModel.class);
        mActivitySendenquiryBinding.setLifecycleOwner(this);
        mActivitySendenquiryBinding.setSendEnquiryViewModel(mSendEnquiryViewModel);
        mActivitySendenquiryBinding.edtTxtBhumiPuja.setText("Bhoomi Puja");
        mActivitySendenquiryBinding.edtTxtEmail.setEnabled(false);
        mActivitySendenquiryBinding.imgbackPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mActivitySendenquiryBinding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mActivitySendenquiryBinding.edtTxtName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(SendEnquiryActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter  Name...")
                            .show();
                }else if (!Patterns.PHONE.matcher(mActivitySendenquiryBinding.edtTxtMobileNo.getText().toString()).matches()||

                        (mActivitySendenquiryBinding.edtTxtMobileNo.getText().toString().trim().equals(""))||(mActivitySendenquiryBinding.edtTxtMobileNo.getText().toString().trim().length()<10)){
                    //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();

                    new SweetAlertDialog(SendEnquiryActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();

                } else if (!Patterns.EMAIL_ADDRESS.matcher(mActivitySendenquiryBinding.edtTxtEmail.getText().toString()).matches()||
                        (mActivitySendenquiryBinding.edtTxtEmail.getText().toString().trim().equals(""))){
                    new SweetAlertDialog(SendEnquiryActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Valid  Email Address...")
                            .show();
                }else if(mActivitySendenquiryBinding.edtTxtAddress.getText().toString().trim().equals("")){
                    new SweetAlertDialog(SendEnquiryActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter  Address...")
                            .show();
                }else if(mActivitySendenquiryBinding.edtTxtBhumiPuja.getText().toString().trim().equals("")){
                    new SweetAlertDialog(SendEnquiryActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter  Bhoomi Puja...")
                            .show();
                }else if(mActivitySendenquiryBinding.edtTxtRequirements.getText().toString().trim().equals("")){
                    new SweetAlertDialog(SendEnquiryActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Requirements...")
                            .show();
                }else{

                    mSendEnquiryViewModel.getSendEnquiryResult(mActivitySendenquiryBinding.edtTxtAddress.getText().toString().trim(),
                            mActivitySendenquiryBinding.edtTxtEmail.getText().toString(),
                            mActivitySendenquiryBinding.edtTxtMobileNo.getText().toString().trim(),
                            mActivitySendenquiryBinding.edtTxtName.getText().toString().trim(),
                            mActivitySendenquiryBinding.edtTxtBhumiPuja.getText().toString().trim(),
                            mActivitySendenquiryBinding.edtTxtRequirements.getText().toString().trim()
                    ) .observe(SendEnquiryActivity.this, SendEnquiryActivity.this::handleSendEnquiryResponse);
                }
            }
        });
    }






    private void handleSendEnquiryResponse(Resource<SendEnquiryModel> resource) {
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
                    } else if (!Static.isNetworkAvailable(SendEnquiryActivity.this) && resource.data==null) {

                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(this.getString(R.string.nointernet))
                                .setContentText(this.getString(R.string.nointernetdetails))
                                .show();

                    }

                    break;
                case LOADING:
                    Log.e("handleRegisterResponse", "LOADING");
                    DisplayDialog.getInstance().showAlertDialog(SendEnquiryActivity.this, SendEnquiryActivity.this.getString(R.string.please_wait));


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
                        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText(this.getString(R.string.success))
                                .setContentText(this.getString(R.string.contactusoon))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        SendEnquiryActivity.this.finish();

                                    }
                                })
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                    }
                                })

                                .show();

                        mActivitySendenquiryBinding.edtTxtAddress.setText("");
                        mActivitySendenquiryBinding.edtTxtBhumiPuja.setText("");
                        mActivitySendenquiryBinding.edtTxtMobileNo.setText("");
                        mActivitySendenquiryBinding.edtTxtEmail.setText("");
                        mActivitySendenquiryBinding.edtTxtRequirements.setText("");
                        mActivitySendenquiryBinding.edtTxtName.setText("");

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
