package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentBillingdetailsBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaFragment;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaViewModel;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragmentDirections;
import com.autumntechcreation.click4panditcustomer.ui.differentpujalocation.DifferentPujaLocationFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryFragment;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryFragmentDirections;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummeryModel;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.cashfree.pg.CFPaymentService;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.ContentValues.TAG;
import static androidx.navigation.Navigation.findNavController;
import static com.cashfree.pg.CFPaymentService.PARAM_APP_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_CURRENCY;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_NOTE;

public class BillingDetailsFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    BillingDetailsViewModel mBillingDetailsViewModel;
    FragmentBillingdetailsBinding mFragmentBillingdetailsBinding;
    private View mView;
    NavController navController;
    int orderId,bkgId,shippingbkgId,shippingOrderId;
    String orderAmount,shippingAddress,shippingCity,shippingState,shippingPincode,pujaDatetime,shippingFirstName,
    shippingLastName,shippingemail,shippingAlternateMobile,shippingAdditionalInfo,shippingOrderAmount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBillingdetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_billingdetails, container, false);
        mFragmentBillingdetailsBinding.setLifecycleOwner(this);

       if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getDateTime().length()>0) {
            pujaDatetime = BillingDetailsFragmentArgs.fromBundle(getArguments()).getDateTime();
            Log.e("PUJADATETIME", pujaDatetime);
        }
        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderId()>0) {
            orderId = BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderId();
            Log.e("orderId", "" + orderId);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBkgId()>0) {
            bkgId = BillingDetailsFragmentArgs.fromBundle(getArguments()).getBkgId();
            Log.e("bkgId", "" + bkgId);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderAmount().length()>0){
            orderAmount=BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderAmount();
            Log.e("ORDERAMOUNT",orderAmount);
        }

        return mFragmentBillingdetailsBinding.getRoot();
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

        mBillingDetailsViewModel = ViewModelProviders.of(BillingDetailsFragment.this, viewModelFactory).get(BillingDetailsViewModel.class);
        mFragmentBillingdetailsBinding.setBillingDetailsViewModel(mBillingDetailsViewModel);

        mBillingDetailsViewModel.getOnClickBillingDiffLocation().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                BillingDetailsFragmentDirections.ActionBillingDetailsFragmentToDifferentPujaLocationFragment action=
                        BillingDetailsFragmentDirections.actionBillingDetailsFragmentToDifferentPujaLocationFragment();
                action.setDateTime(pujaDatetime);
                action.setShippingBkgId(bkgId);
                action.setOrderAmount(orderAmount);
                action.setOrderId(orderId);
                Navigation.findNavController(mView).navigate(action);


                // findNavController(mView).navigate(BillingDetailsFragmentDirections.actionBillingDetailsFragmentToDifferentPujaLocationFragment());
            }
        });


        String firstName=mBillingDetailsViewModel.getFirstName();
        String lastName=mBillingDetailsViewModel.getLastName();
        String emailId=mBillingDetailsViewModel.getEmail();
        String mobileNo=mBillingDetailsViewModel.getMobile();
        mFragmentBillingdetailsBinding.edtTxtFirstName.setText(firstName);
        mFragmentBillingdetailsBinding.edtTxtLastName.setText(lastName);
        mFragmentBillingdetailsBinding.edtAlternateMobileNo.setText(mobileNo);
        mFragmentBillingdetailsBinding.edtTxtEmail.setText(emailId);
        mFragmentBillingdetailsBinding.edtTxtState.setText("West Bengal");
        mFragmentBillingdetailsBinding.edtTxtCity.setText("Kolkata");



        mFragmentBillingdetailsBinding.tvProceedtoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFragmentBillingdetailsBinding.edtTxtFirstName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter First Name...")
                            .show();
                } else if(mFragmentBillingdetailsBinding.edtTxtLastName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Last Name...")
                            .show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(mFragmentBillingdetailsBinding.edtTxtEmail.getText().toString()).matches()||
                        (mFragmentBillingdetailsBinding.edtTxtEmail.getText().toString().trim().equals(""))){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Valid  Email Address...")
                            .show();
                } else if (!Patterns.PHONE.matcher(mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString()).matches()||

                        (mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString().trim().equals(""))||(mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString().trim().length()<10)){
                    //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();
                } else if(mFragmentBillingdetailsBinding.edtTxtAddress.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter Address...")
                            .show();
                }else if(mFragmentBillingdetailsBinding.edtTxtState.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter State...")
                            .show();
                }else if(mFragmentBillingdetailsBinding.edtTxtCity.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter City...")
                            .show();
                }else if(mFragmentBillingdetailsBinding.edtTxtPincode.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Please Enter PinCode...")
                            .show();
                }else{





                    if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getStatusShippingId()==1){
                        String shippingDateTime="";
                        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getDateTime().length()>0) {
                          shippingDateTime = BillingDetailsFragmentArgs.fromBundle(getArguments()).getDateTime();
                            Log.e("SHIPPINGDATETIME", shippingDateTime);
                        }
                        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingAddress().length()>0) {
                            shippingAddress = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingAddress();
                            Log.e("SHIPPINGADDRESS", shippingAddress);
                        } if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingState().length()>0) {
                            shippingState = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingState();
                            Log.e("SHIPPINGSTATE", shippingState);
                        } if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingCity().length()>0) {
                            shippingCity = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingCity();
                            Log.e("SHIPPINGCITY", shippingCity);
                        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingPinCode().length()>0) {
                            shippingPincode = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingPinCode();
                            Log.e("SHIPPINGPINCODE", shippingPincode);
                        }
                        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingFname().length()>0){
                             shippingFirstName=BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingFname();
                            Log.e("SHIPPINGFIRSTNAME", shippingFirstName);
                        }
                        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingLname().length()>0){
                             shippingLastName=BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingLname();
                            Log.e("SHIPPINGLASTNAME", shippingLastName);
                        }
                        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingemail().length()>0){
                             shippingemail=BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingemail();
                            Log.e("SHIPPINGEMAIL", shippingemail);
                        }
                        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingMobile().length()>0){
                             shippingAlternateMobile=BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingMobile();
                            Log.e("SHIPPINGMOBILE", shippingAlternateMobile);
                        }
                        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getAdditionalInfo().length()>0){
                             shippingAdditionalInfo=BillingDetailsFragmentArgs.fromBundle(getArguments()).getAdditionalInfo();
                            Log.e("SHIPPINGFADDITIONALINFO", shippingAdditionalInfo);
                        }
                        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBkgId()>0) {
                            shippingbkgId = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingBkgId();
                            Log.e("shippingbkgId", "" + shippingbkgId);
                        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBkgId()>0) {
                            shippingOrderId = BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderId();
                            Log.e("shippingOrderId", "" + shippingOrderId);
                        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBkgId()>0) {
                            shippingOrderAmount = BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderAmount();
                            Log.e("shippingOrderAmount", "" + shippingOrderAmount);
                        }
                        mFragmentBillingdetailsBinding.tvLocation.setVisibility(View.VISIBLE);
                        mFragmentBillingdetailsBinding.imgvwLoc.setVisibility(View.VISIBLE);
                        mFragmentBillingdetailsBinding.tvLocation.setText(shippingAddress+","+shippingState+","+shippingCity+","+shippingPincode);

                        mBillingDetailsViewModel.getProceedToPayForShippingAddress(shippingDateTime,shippingbkgId,mFragmentBillingdetailsBinding.edtTxtFirstName.getText().toString(),mFragmentBillingdetailsBinding.edtTxtLastName.getText().toString(),
                                mFragmentBillingdetailsBinding.edtTxtAddress.getText().toString(),mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString(),
                                mFragmentBillingdetailsBinding.edtTxtCity.getText().toString(),mFragmentBillingdetailsBinding.edtTxtState.getText().toString(),mFragmentBillingdetailsBinding.edtTxtPincode.getText().toString(),Double.parseDouble(shippingOrderAmount),shippingOrderId,shippingFirstName,shippingLastName,shippingAlternateMobile,
                                shippingemail,shippingAddress, shippingPincode,shippingAdditionalInfo
                                ).observe(getActivity(), BillingDetailsFragment.this::handleProceedToPay);

                    }
                    else{
                        mBillingDetailsViewModel.getProceedToPayForBillingAddress(pujaDatetime,bkgId,mFragmentBillingdetailsBinding.edtTxtFirstName.getText().toString(),
                                mFragmentBillingdetailsBinding.edtTxtLastName.getText().toString(),mFragmentBillingdetailsBinding.edtTxtAddress.getText().toString(),
                                mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString(),mFragmentBillingdetailsBinding.edtTxtCity.getText().toString(),
                                mFragmentBillingdetailsBinding.edtTxtState.getText().toString(),mFragmentBillingdetailsBinding.edtTxtPincode.getText().toString(),Double.parseDouble(orderAmount),
                                orderId).observe(getActivity(), BillingDetailsFragment.this::handleProceedToPay);
                    }

                }
            }
        });



    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK){


            //Same request code for all payment APIs.
            Log.d(TAG, "ReqCode : " + CFPaymentService.REQ_CODE);
            Log.d(TAG, "API Response : ");
            //Prints all extras. Replace with app logic.
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null)
                    for (String key : bundle.keySet()) {
                        if (bundle.getString(key) != null) {
                            Log.d(TAG, key + " : " + bundle.getString(key));
                        }
                    }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void handleProceedToPay(Resource<ProceedtoPayModel> resource) {
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
                                .setContentText(this.getString(R.string.billgenerate))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                        mBillingDetailsViewModel.getCashFreeToken("INR",String.valueOf(orderId),orderAmount).observe(getActivity(), BillingDetailsFragment.this::handleCashFreeToken);
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


    private void handleCashFreeToken(Resource<CashFreeTokenModel> resource) {
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
                    Log.e("handleCashFreeTokenResponse", "LOADING");
                    DisplayDialog.getInstance().showAlertDialog(getActivity(), getActivity().getString(R.string.please_wait));


                    break;
                case SUCCESS:
                    Log.e("handleCashFreeTokenResponse", "SUCCESS");
                    // Log.e("handleLoginResponse",resource.message);
                    Log.e("handleCashFreeTokenResponse", resource.status + "");
                    Log.e("handleCashFreeTokenResponse", resource.data + "");
                    Gson gson = new Gson();
                    String json = gson.toJson(resource.data);
                    Log.e("handleCashFreeTokenResponse", json + "");
                    if ( resource.data.status.equals("OK")) {
                        String cashFreeToken=resource.data.cftoken;
                        Log.e("CFTOKEN",cashFreeToken);
                        Log.e("GETINPUT", String.valueOf(getInputParams()));

                        CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
                        cfPaymentService.setOrientation(0);
                        cfPaymentService.doPayment(getActivity(), getInputParams(), cashFreeToken, "TEST", "#784BD2", "#FFFFFF", false);


                    }
                    DisplayDialog.getInstance().dismissAlertDialog();
                    break;
                default:
                    DisplayDialog.getInstance().dismissAlertDialog();

                    break;
            }
        }
    }

    private Map<String, String> getInputParams() {




      String appId = "6159303c6dd0fdc88e24a424f39516";
        String strorderId = String.valueOf(orderId);
        String strorderAmount = orderAmount;
        String orderNote = "Puja";
        String customerName = mBillingDetailsViewModel.getFirstName()+" "+mBillingDetailsViewModel.getLastName();
        String customerPhone = mBillingDetailsViewModel.getMobile();
        String customerEmail = mBillingDetailsViewModel.getEmail();


        Map<String, String> params = new HashMap<>();

        params.put(PARAM_APP_ID, appId);
        params.put(PARAM_ORDER_ID, strorderId);
        params.put(PARAM_ORDER_AMOUNT, strorderAmount);
        params.put(PARAM_ORDER_NOTE, orderNote);
        params.put(PARAM_CUSTOMER_NAME, customerName);
        params.put(PARAM_CUSTOMER_PHONE, customerPhone);
        params.put(PARAM_CUSTOMER_EMAIL, customerEmail);
        params.put(PARAM_ORDER_CURRENCY, "INR");

        return params;
    }



    }
