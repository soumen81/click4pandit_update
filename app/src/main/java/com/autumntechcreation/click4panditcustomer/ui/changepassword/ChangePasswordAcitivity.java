package com.autumntechcreation.click4panditcustomer.ui.changepassword;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityChangepasswordBinding;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordActivity;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordModel;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dagger.android.AndroidInjection;

public class ChangePasswordAcitivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityChangepasswordBinding mActivityChangepasswordBinding;
    ChangePasswordViewModel mChangePasswordViewModel;


    //change
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityChangepasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_changepassword);

        AndroidInjection.inject(this);
        mChangePasswordViewModel = ViewModelProviders.of(this,viewModelFactory).get(ChangePasswordViewModel.class);
        mActivityChangepasswordBinding.setLifecycleOwner(this);
        mActivityChangepasswordBinding.setChangePasswordViewModel(mChangePasswordViewModel);

        mActivityChangepasswordBinding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mActivityChangepasswordBinding.edtTxtOldPassword.getText().toString().trim().equals("")){
                    new SweetAlertDialog(ChangePasswordAcitivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Old Password")
                            .show();
                }else if(mActivityChangepasswordBinding.edtTxtOldPassword.getText().toString().length() < 8){
                    new SweetAlertDialog(ChangePasswordAcitivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("You must have 8 characters in your password")
                            .show();
                }else if(mActivityChangepasswordBinding.edtTxtNewPassword.getText().toString().trim().equals("")){
                    new SweetAlertDialog(ChangePasswordAcitivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter New Password...")
                            .show();
                }else if(mActivityChangepasswordBinding.edtTxtNewPassword.getText().toString().length() < 8){
                    new SweetAlertDialog(ChangePasswordAcitivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("You must have 8 characters in your password")
                            .show();
                }else if(mActivityChangepasswordBinding.edtTxtConfirmPassword.getText().toString().trim().equals("")){
                    new SweetAlertDialog(ChangePasswordAcitivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Confirm New Password...")
                            .show();
                }else if(!mActivityChangepasswordBinding.edtTxtNewPassword.getText().toString().equals(mActivityChangepasswordBinding.edtTxtConfirmPassword.getText().toString())){
                    new SweetAlertDialog(ChangePasswordAcitivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Your password does'nt match with confirm password")
                            .show();
                }else if (TextUtils.isEmpty(mActivityChangepasswordBinding.edtTxtNewPassword.getText().toString()) || mActivityChangepasswordBinding.edtTxtConfirmPassword.getText().toString().length() < 8) {

                    new SweetAlertDialog(ChangePasswordAcitivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("You must have 8 characters in your password")
                            .show();

                }else {
                    mChangePasswordViewModel.getChangePassword(mActivityChangepasswordBinding.edtTxtOldPassword.getText().toString(),
                            mActivityChangepasswordBinding.edtTxtNewPassword.getText().toString(),mActivityChangepasswordBinding.edtTxtConfirmPassword.getText().toString()).observe(ChangePasswordAcitivity.this,
                                    ChangePasswordAcitivity.this::handleChangePassword);
                }
            }
        });

    mActivityChangepasswordBinding.imgbackChangePassword.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
    }


    private void handleChangePassword(Resource<ChangePasswordModel> resource) {
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
                    } else if (!Static.isNetworkAvailable(ChangePasswordAcitivity.this) && resource.data==null) {

                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(this.getString(R.string.nointernet))
                                .setContentText(this.getString(R.string.nointernetdetails))
                                .show();

                    }
                    break;
                case LOADING:
                    Log.e("handleForgetResponse", "LOADING");
                    DisplayDialog.getInstance().showAlertDialog(ChangePasswordAcitivity.this, ChangePasswordAcitivity.this.getString(R.string.please_wait));


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
                        new SweetAlertDialog(ChangePasswordAcitivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText(this.getString(R.string.success))
                                .setContentText(this.getString(R.string.changepasswordgenerate))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                    }
                                }).show();

                    }else if(resource.data.returnStatus.equals("FAILED")){
                        new SweetAlertDialog(ChangePasswordAcitivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(this.getString(R.string.wrongemailid))
                                .setContentText(this.getString(Integer.parseInt(resource.data.returnErrMsg)))
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
