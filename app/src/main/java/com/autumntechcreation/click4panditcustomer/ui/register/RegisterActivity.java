package com.autumntechcreation.click4panditcustomer.ui.register;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityRegisterBinding;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dagger.android.AndroidInjection;

public class RegisterActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    ActivityRegisterBinding mActivityRegisterBinding;
    RegisterViewModel mRegisterViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        AndroidInjection.inject(this);


        mRegisterViewModel = ViewModelProviders.of(this,viewModelFactory).get(RegisterViewModel.class);
        mActivityRegisterBinding.setLifecycleOwner(this);

        mActivityRegisterBinding.setRegisterViewModel(mRegisterViewModel);



        mRegisterViewModel.getonClickLoginPage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                Intent in=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(in);
            }
        });






        mRegisterViewModel.getonClickRegisterPage().observe(this, new Observer<Void>() {


            @Override
            public void onChanged(@Nullable Void _) {
                if(mActivityRegisterBinding.edtTxtFirstName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter First Name...")
                            .show();
                }else if(mActivityRegisterBinding.edtTxtLastName.getText().toString().trim().equals("")) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Last Name...")
                            .show();

                }else if (!Patterns.PHONE.matcher(mActivityRegisterBinding.edtTxtMobileNo.getText().toString()).matches()||

                        (mActivityRegisterBinding.edtTxtMobileNo.getText().toString().trim().equals(""))||(mActivityRegisterBinding.edtTxtMobileNo.getText().toString().trim().length()<10)){
                    //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();

                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();

                }
                /*else if(Static.isValidEmail(mActivityRegisterBinding.edtTxtEmail.getText().toString())||
                        (mActivityRegisterBinding.edtTxtEmail.getText().toString().trim().equals(""))){
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Valid Email...")
                            .show();

                } */

                else if (!Patterns.EMAIL_ADDRESS.matcher(mActivityRegisterBinding.edtTxtEmail.getText().toString()).matches()||
                        (mActivityRegisterBinding.edtTxtEmail.getText().toString().trim().equals(""))){
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Valid  Email Address...")
                            .show();
                }
                else if(mActivityRegisterBinding.edtTxtPassword.getText().toString().trim().equals("")) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Password...")
                            .show();
                }else if (TextUtils.isEmpty(mActivityRegisterBinding.edtTxtPassword.getText().toString()) || mActivityRegisterBinding.edtTxtPassword.getText().toString().length() < 6) {

                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("You must have 6 characters in your password")
                            .show();

                }else if(mActivityRegisterBinding.edtTxtConfirmPassword.getText().toString().trim().equals("")) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Confirm Password...")
                            .show();
                }else if(!mActivityRegisterBinding.edtTxtConfirmPassword.getText().toString().equals(mActivityRegisterBinding.edtTxtPassword.getText().toString())){
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Your password does'nt match with confirm password")
                            .show();
                }else {
                    mRegisterViewModel.getRegisterResult(mActivityRegisterBinding.edtTxtFirstName.getText().toString(), mActivityRegisterBinding.edtTxtLastName.getText().toString(),
                            mActivityRegisterBinding.edtTxtMobileNo.getText().toString(), mActivityRegisterBinding.edtTxtEmail.getText().toString(),
                            mActivityRegisterBinding.edtTxtPassword.getText().toString(), mActivityRegisterBinding.edtTxtConfirmPassword.getText().toString(), "CUST")
                            .observe(RegisterActivity.this, RegisterActivity.this::handleRegistration);

                }


            }
        });






    }
    /*public  boolean isValidIPhone()
    {
        if(mActivityRegisterBinding.edtTxtMobileNo.getText().toString().length()==0)
            return false;
        Pattern PHONE_PATTERN = Pattern.compile(Static.PHONE_REGEX);
        return PHONE_PATTERN.matcher((CharSequence) mActivityRegisterBinding.edtTxtMobileNo.getText().toString()).matches();
    }*/
    private void handleRegistration(Resource<RegisterResponse> resource) {
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
                    } else if (!Static.isNetworkAvailable(RegisterActivity.this) && resource.data==null) {

                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(this.getString(R.string.nointernet))
                                .setContentText(this.getString(R.string.nointernetdetails))
                                .show();

                    }

                    break;
                case LOADING:
                    Log.e("handleRegisterResponse", "LOADING");
                    DisplayDialog.getInstance().showAlertDialog(RegisterActivity.this, RegisterActivity.this.getString(R.string.please_wait));


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
                                .setContentText(this.getString(R.string.register))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        Intent in=new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(in);
                                    }
                                })
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                    }
                                })

                                .show();

                        mActivityRegisterBinding.edtTxtFirstName.setText("");
                        mActivityRegisterBinding.edtTxtLastName.setText("");
                        mActivityRegisterBinding.edtTxtMobileNo.setText("");
                        mActivityRegisterBinding.edtTxtEmail.setText("");
                        mActivityRegisterBinding.edtTxtPassword.setText("");
                        mActivityRegisterBinding.edtTxtConfirmPassword.setText("");

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



