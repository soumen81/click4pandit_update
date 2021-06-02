package com.autumntechcreation.click4panditcustomer.ui.forgetpassword;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityForgetpasswordBinding;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginResponse;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dagger.android.AndroidInjection;

public class ForgetPasswordActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityForgetpasswordBinding mActivityForgetpasswordBinding;
    ForgetPasswordViewModel mForgetPasswordViewModel;
    String uuidInString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityForgetpasswordBinding= DataBindingUtil.setContentView(this, R.layout.activity_forgetpassword);
        AndroidInjection.inject(this);
        mForgetPasswordViewModel = ViewModelProviders.of(this,viewModelFactory).get(ForgetPasswordViewModel.class);
        mActivityForgetpasswordBinding.setLifecycleOwner(this);
        mActivityForgetpasswordBinding.setForgetPasswordViewModel(mForgetPasswordViewModel);




        UUID uuid = UUID.randomUUID();
        uuidInString = uuid.toString();
         mActivityForgetpasswordBinding.tvSubmit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!Patterns.EMAIL_ADDRESS.matcher(mActivityForgetpasswordBinding.edtTxtEmail.getText().toString()).matches()||
                         (mActivityForgetpasswordBinding.edtTxtEmail.getText().toString().trim().equals(""))){
                     new SweetAlertDialog(ForgetPasswordActivity.this, SweetAlertDialog.ERROR_TYPE)
                             .setTitleText("Error")
                             .setContentText("Please Enter EmailId...")
                             .show();
                 }else{
                     mForgetPasswordViewModel.getForgetPassword(mActivityForgetpasswordBinding.edtTxtEmail.getText().toString(),
                             uuidInString).observe(ForgetPasswordActivity.this,
                             ForgetPasswordActivity.this::handleForgetPassword);
                 }
             }
         });

         mActivityForgetpasswordBinding.imgbackPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
    }



    private void handleForgetPassword(Resource<ForgetPasswordModel> resource) {
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
                    } else if (!Static.isNetworkAvailable(ForgetPasswordActivity.this) && resource.data==null) {

                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(this.getString(R.string.nointernet))
                                .setContentText(this.getString(R.string.nointernetdetails))
                                .show();

                    }
                    break;
                case LOADING:
                    Log.e("handleForgetResponse", "LOADING");
                    DisplayDialog.getInstance().showAlertDialog(ForgetPasswordActivity.this, ForgetPasswordActivity.this.getString(R.string.please_wait));


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
                        mForgetPasswordViewModel.getForTriggerPassword(mActivityForgetpasswordBinding.edtTxtEmail.getText().toString(),
                                uuidInString).observe(ForgetPasswordActivity.this,
                                ForgetPasswordActivity.this::handleTriggerMail);
                    }
                    DisplayDialog.getInstance().dismissAlertDialog();
                    break;
                default:
                    DisplayDialog.getInstance().dismissAlertDialog();

                    break;
            }
        }
    }






    private void handleTriggerMail(Resource<TriggerMailModel> resource) {
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
                    } else if (!Static.isNetworkAvailable(ForgetPasswordActivity.this) && resource.data==null) {

                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(this.getString(R.string.nointernet))
                                .setContentText(this.getString(R.string.nointernetdetails))
                                .show();

                    }
                    break;
                case LOADING:
                    Log.e("handleForgetResponse", "LOADING");
                    DisplayDialog.getInstance().showAlertDialog(ForgetPasswordActivity.this, ForgetPasswordActivity.this.getString(R.string.please_wait));


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
                        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText(this.getString(R.string.forgetsuccess))
                                .setContentText(this.getString(R.string.checkmail))
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
