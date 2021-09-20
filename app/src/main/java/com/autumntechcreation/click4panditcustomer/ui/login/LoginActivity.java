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
import android.widget.TextView;

import com.autumntechcreation.click4panditcustomer.AddtoCartValueStoreViewModel;
import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityLoginBinding;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordActivity;
import com.autumntechcreation.click4panditcustomer.ui.home.CartItemCountModel;
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
    private AddtoCartValueStoreViewModel viewModel;
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
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_validemail))
                            .show();
                }else if(TextUtils.isEmpty(mActivityLoginBinding.edtTxtPassword.getText().toString()) || mActivityLoginBinding.edtTxtPassword.getText().toString().length() < 8){
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_pass))
                            .show();
                }else{
                    mLoginViewModel.getLoginResult(mActivityLoginBinding.edtTxtEmail.getText().toString(),
                            mActivityLoginBinding.edtTxtPassword.getText().toString()).observe(LoginActivity.this,
                            LoginActivity.this::handleLoginResponse);

                }

            }
        });

       /* viewModel = new ViewModelProvider(this).get(AddtoCartValueStoreViewModel.class);
        viewModel.getSelectedItem().observe(this, item -> {
            // Perform an action with the latest item data
        });*/

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
                        String firstName=resource.data.firstName;
                        String lastName=resource.data.lastName;
                        mLoginViewModel.storeUserName(userName);
                        mLoginViewModel.storeMobileNo(mobileNo);
                        mLoginViewModel.storefirstName(firstName);
                        mLoginViewModel.storelastName(lastName);
                        String emailAddress=mLoginViewModel.storeEmail(mActivityLoginBinding.edtTxtEmail.getText().toString());
                        Log.e("emailAddress",emailAddress);
                        mLoginViewModel.getCartCountItem().observe(LoginActivity.this, LoginActivity.this::handleAddtoCartItemCount);




                    }else if(resource.data.returnStatus.equals("FAILED")){
                        String returnMsg= (String) resource.data.returnErrMsg;
                        Log.e("ERROR",returnMsg);



                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(this.getString(R.string.failed))
                                .setContentText(returnMsg)
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




    private void handleAddtoCartItemCount(Resource<CartItemCountModel> resource) {
        if (resource != null) {

            switch (resource.status) {
                case ERROR:

                    DisplayDialog.getInstance().dismissAlertDialog();
                    if (resource.message != null &&  resource.data==null) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(resource.message);

                            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(jsonObject.getString("error"))
                                    .setContentText(jsonObject.getString("error_description"))
                                    .show();

                        } catch (JSONException e) {
                            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Error")
                                    .setContentText("Unhandle Error")
                                    .show();
                        }
                    } else if (!Static.isNetworkAvailable(LoginActivity.this) && resource.data==null) {

                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(this.getString(R.string.nointernet))
                                .setContentText(this.getString(R.string.nointernetdetails))
                                .show();

                    }
                    break;
                case LOADING:
                    Log.e("handleForgetResponse", "LOADING");
                    DisplayDialog.getInstance().showAlertDialog(LoginActivity.this, LoginActivity.this.getString(R.string.please_wait));


                    break;
                case SUCCESS:
                    Log.e("handleForgetPasswordResponse", "SUCCESS");
                    // Log.e("handleLoginResponse",resource.message);
                    Log.e("handleForgetPasswordResponse", resource.status + "");
                    Log.e("handleForgetPasswordResponse", resource.data + "");
                    Gson gson = new Gson();
                    String json = gson.toJson(resource.data);
                    Log.e("handleForgetPasswordResponse", json + "");
                    //TextView textview = (TextView) getActivity().findViewById(R.id.tvCartCount);
                    if(resource.data.getReturnStatus().equals("SUCCESS")){
                        if(resource.data.getReturnCartValue()==null){
                            Intent in = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(in);
                            sp.edit().putBoolean("logged", true).apply();
                            finish();
                        }else {
                            int cartCount = resource.data.getReturnCartValue().getCartItemCount();
                            Log.e("CARTCOUNT", String.valueOf(cartCount));
                            mLoginViewModel.storeCartCount(String.valueOf(cartCount));
                            Log.e("STORECARTCOUNT", mLoginViewModel.storeCartCount(String.valueOf(cartCount)));

                            Intent in = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(in);
                            sp.edit().putBoolean("logged", true).apply();
                            finish();
                        }
                       /* if(cartCount>0) {
                            textview.setText(String.valueOf(cartCount));
                        }*/
                    }/*else if(resource.data.getReturnCartValue().equals(null)){
                        textview.setVisibility(View.GONE);
                    }*/

                    DisplayDialog.getInstance().dismissAlertDialog();
                    break;
                default:
                    DisplayDialog.getInstance().dismissAlertDialog();

                    break;
            }
        }
    }
}
