package com.autumntechcreation.click4panditcustomer.ui.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityLoginBinding;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordActivity;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dagger.android.AndroidInjection;

public class LoginActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityLoginBinding mActivityLoginBinding;
    LoginViewModel mLoginViewModel;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        AndroidInjection.inject(this);
        mLoginViewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel.class);
        mActivityLoginBinding.setLifecycleOwner(this);
        mActivityLoginBinding.setLoginViewModel(mLoginViewModel);

        sp = getSharedPreferences("login",MODE_PRIVATE);



        mLoginViewModel.getonClickSignUpPage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                Intent in=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(in);
            }
        });
        mLoginViewModel.getonClickForgetPasswordPage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                Intent in=new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(in);
            }
        });


        mLoginViewModel.getonClickLoginPage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                if(!Patterns.EMAIL_ADDRESS.matcher(mActivityLoginBinding.edtTxtEmail.getText().toString()).matches()||
                        (mActivityLoginBinding.edtTxtEmail.getText().toString().trim().equals(""))){
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter EmailId...")
                            .show();
                }else if(TextUtils.isEmpty(mActivityLoginBinding.edtTxtPassword.getText().toString()) || mActivityLoginBinding.edtTxtPassword.getText().toString().length() < 6){
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Password...")
                            .show();
                }else{
                    mLoginViewModel.getLoginResult(mActivityLoginBinding.edtTxtEmail.getText().toString(),
                            mActivityLoginBinding.edtTxtPassword.getText().toString()).observe(LoginActivity.this,
                            LoginActivity.this::handleLoginResponse);
                }

            }
        });



    }

    private void handleLoginResponse(Resource<LoginResponse> resource) {
        if (resource != null) {

            switch (resource.status) {
                case ERROR:
                    mLoginViewModel.deleteSharedPreference();
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
                    } else if (!Static.isNetworkAvailable(LoginActivity.this) && resource.data==null) {

                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(this.getString(R.string.nointernet))
                                .setContentText(this.getString(R.string.nointernetdetails))
                                .show();

                    }
                    break;
                case LOADING:
                    Log.e("handleLoginResponse", "LOADING");
                    DisplayDialog.getInstance().showAlertDialog(LoginActivity.this, LoginActivity.this.getString(R.string.please_wait));


                    break;
                case SUCCESS:
                    Log.e("handleLoginResponse", "SUCCESS");
                    // Log.e("handleLoginResponse",resource.message);
                    Log.e("handleLoginResponse", resource.status + "");
                    Log.e("handleLoginResponse", resource.data + "");
                    Gson gson = new Gson();
                    String json = gson.toJson(resource.data);
                    Log.e("handleLoginResponse", json + "");
                    if ( resource.data.returnStatus.equals("SUCCESS")) {
                        String userName=resource.data.firstName + resource.data.lastName;
                        String mobileNo=resource.data.mobile;
                        mLoginViewModel.storeUserName(userName);
                        mLoginViewModel.storeMobileNo(mobileNo);
                        Intent in=new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(in);
                        sp.edit().putBoolean("logged",true).apply();
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
