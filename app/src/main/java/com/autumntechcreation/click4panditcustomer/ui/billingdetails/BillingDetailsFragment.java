package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileFragment;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.cashfree.pg.CFPaymentService;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
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
    boolean valueOfState;
    int orderId,bkgId,shippingbkgId,shippingOrderId;
    String orderAmount,shippingAddress,shippingAddress2,shippingAddress3,shippingCity,shippingState,shippingPincode,pujaDatetime,shippingFirstName,
            billingPinCode="",billingAddress="",billingAddress2="",billingAddress3="",shippingLastName,shippingemail,shippingAlternateMobile,shippingAdditionalInfo,shippingOrderAmount,pujaAmount,cgstValue,sgstValue;

    String paymentorderID = "",paymentMode="",transactionTime="",referenceId="",txMsg="",txStatus="",yajamanList="",
            procedureList="",pujaSamagriList="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBillingdetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_billingdetails, container, false);
        mFragmentBillingdetailsBinding.setLifecycleOwner(this);
       /* if(billingAddress.equals("null")){
            mFragmentBillingdetailsBinding.edtTxtAddress.setText("");
        }*/
        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getYajaman().length()>0){
            yajamanList=BillingDetailsFragmentArgs.fromBundle(getArguments()).getYajaman();
            Log.e("YAJAMANLIST", yajamanList);
        } if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getProcedure().length()>0){
            procedureList=BillingDetailsFragmentArgs.fromBundle(getArguments()).getProcedure();
            Log.e("PROCEDURELIST", procedureList);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getPujaSamagries().length()>0){
            pujaSamagriList=BillingDetailsFragmentArgs.fromBundle(getArguments()).getPujaSamagries();
            Log.e("PUJASAMAGRIESLIST", pujaSamagriList);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getYajaman().length()>0){
            yajamanList=BillingDetailsFragmentArgs.fromBundle(getArguments()).getYajaman();
            Log.e("YAJAMANLIST", yajamanList);
        }

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
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getPujaAmount().length()>0){
            pujaAmount=BillingDetailsFragmentArgs.fromBundle(getArguments()).getPujaAmount();
            Log.e("PUJAAMOUNT",pujaAmount);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getCgstvalue().length()>0){
            cgstValue=BillingDetailsFragmentArgs.fromBundle(getArguments()).getCgstvalue();
            Log.e("CGSTVALUE",cgstValue);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getSgstvalue().length()>0){
            sgstValue=BillingDetailsFragmentArgs.fromBundle(getArguments()).getSgstvalue();
            Log.e("SGSTVALUE",sgstValue);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress2().length()>0){
            billingAddress2=BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress2();
            Log.e("BILLINGADDRESS2",billingAddress2);
            mFragmentBillingdetailsBinding.edtTxtAddress2.setText(billingAddress2);
        }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress3().length()>0){
            billingAddress3=BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress3();
            Log.e("BILLINGADDRESS3",billingAddress3);
            mFragmentBillingdetailsBinding.edtTxtAddress3.setText(billingAddress3);
        }
        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress().length()>0||
                BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress()!=null||
                BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress()!="0") {
            billingAddress = BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingAddress();
            Log.e("SHIPPINGDATETIME", billingAddress);
            mFragmentBillingdetailsBinding.edtTxtAddress.setText(billingAddress);
        }else{
            mFragmentBillingdetailsBinding.edtTxtAddress.setText("");
        }

        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingPincode().length()>0) {
            billingPinCode= BillingDetailsFragmentArgs.fromBundle(getArguments()).getBillingPincode();
            Log.e("SHIPPINGDATETIME", billingPinCode);
            mFragmentBillingdetailsBinding.edtTxtPincode.setText(billingPinCode);
        }else{
            mFragmentBillingdetailsBinding.edtTxtPincode.setText("");
        }


        //Shipping Details value:
        String shippingPujaAmount="";
        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getDateTime().length()>0) {
            shippingPujaAmount = BillingDetailsFragmentArgs.fromBundle(getArguments()).getPujaAmount();
            Log.e("SHIPPINGDATETIME", shippingPujaAmount);
        }
        String shippingCgst="";
        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getCgstvalue().length()>0) {
            shippingCgst = BillingDetailsFragmentArgs.fromBundle(getArguments()).getCgstvalue();
            Log.e("SHIPPINGDATETIME", shippingCgst);
        }
        String shippingSgst="";
        if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getSgstvalue().length()>0) {
            shippingSgst = BillingDetailsFragmentArgs.fromBundle(getArguments()).getSgstvalue();
            Log.e("SHIPPINGDATETIME", shippingSgst);
        }

        mFragmentBillingdetailsBinding.tvSubTotalValue.setText(shippingPujaAmount);
        mFragmentBillingdetailsBinding.tvCgstValue.setText(shippingCgst);
        mFragmentBillingdetailsBinding.tvSgstValue.setText(shippingSgst);


         valueOfState=BillingDetailsFragmentArgs.fromBundle(getArguments()).getStatusShippingId()==1;
        Log.e("STATE",valueOfState+"");//true
        if(valueOfState==true){
            String shippingDateTime="";
            if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getDateTime().length()>0) {
                shippingDateTime = BillingDetailsFragmentArgs.fromBundle(getArguments()).getDateTime();
                Log.e("SHIPPINGDATETIME", shippingDateTime);
            }
            if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingAddress().length()>0) {
                shippingAddress = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingAddress();
                Log.e("SHIPPINGADDRESS", shippingAddress);
            } if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingAddress().length()>0) {
                shippingAddress2 = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingAddress2();
                Log.e("SHIPPINGADDRESS2", shippingAddress2);
            } if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingAddress().length()>0) {
                shippingAddress3 = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingAddress3();
                Log.e("SHIPPINGADDRESS3", shippingAddress3);
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
            if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingBkgId()>0) {
                shippingbkgId = BillingDetailsFragmentArgs.fromBundle(getArguments()).getShippingBkgId();
                Log.e("shippingbkgId", "" + shippingbkgId);
            }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderId()>0) {
                shippingOrderId = BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderId();
                Log.e("shippingOrderId", "" + shippingOrderId);
            }if(BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderAmount().length()>0) {
                shippingOrderAmount = BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderAmount();
                Log.e("shippingOrderAmount", "" + shippingOrderAmount);
            }

        }
       /* if(mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString().length()>0) {
            if (!Patterns.PHONE.matcher(mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString()).matches() ||
                    mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString().length() > 9 ||
                    mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString().trim().length() < 10) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(getResources().getString(R.string.validation_error))
                        .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                        .show();
            }
        }*/



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
                action.setPujaAmount(pujaAmount);
                action.setSgstvalue(sgstValue);
                action.setCgstvalue(cgstValue);
                action.setBillingAddress(mFragmentBillingdetailsBinding.edtTxtAddress.getText().toString());
                action.setBillingPincode(mFragmentBillingdetailsBinding.edtTxtPincode.getText().toString());
                action.setBillingAddress2(mFragmentBillingdetailsBinding.edtTxtAddress2.getText().toString());
                action.setBillingAddress3(mFragmentBillingdetailsBinding.edtTxtAddress3.getText().toString());
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
        mFragmentBillingdetailsBinding.edtMobileNo.setText(mobileNo);
        mFragmentBillingdetailsBinding.edtTxtEmail.setText(emailId);
        mFragmentBillingdetailsBinding.edtTxtState.setText("West Bengal");
        mFragmentBillingdetailsBinding.edtTxtCity.setText("Kolkata");
        mFragmentBillingdetailsBinding.tvSubTotalValue.setText(pujaAmount);
        mFragmentBillingdetailsBinding.tvCgstValue.setText(cgstValue);
        mFragmentBillingdetailsBinding.tvSgstValue.setText(sgstValue);
        mFragmentBillingdetailsBinding.tvTotalValue.setText(orderAmount);




       /* mFragmentBillingdetailsBinding.edtAlternateMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
*/


        mFragmentBillingdetailsBinding.tvProceedtoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(!Patterns.PHONE.matcher(mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString()).matches()||
                        mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString().length()>9||
                        mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString().trim().length()<10){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();
                }*/
                if(mFragmentBillingdetailsBinding.edtTxtFirstName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_firstname))
                            .show();
                } else if(mFragmentBillingdetailsBinding.edtTxtLastName.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_lastname))
                            .show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(mFragmentBillingdetailsBinding.edtTxtEmail.getText().toString()).matches()||
                        (mFragmentBillingdetailsBinding.edtTxtEmail.getText().toString().trim().equals(""))){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_validemail))
                            .show();
                } else if (!Patterns.PHONE.matcher(mFragmentBillingdetailsBinding.edtMobileNo.getText().toString()).matches()||

                        (mFragmentBillingdetailsBinding.edtMobileNo.getText().toString().trim().equals(""))||(mFragmentBillingdetailsBinding.edtMobileNo.getText().toString().trim().length()<10)){
                    //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();
                } else if(mFragmentBillingdetailsBinding.edtTxtAddress.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_address))
                            .show();
                }else if(mFragmentBillingdetailsBinding.edtTxtState.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_state))
                            .show();
                }else if(mFragmentBillingdetailsBinding.edtTxtCity.getText().toString().trim().equals("")){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_city))
                            .show();
                }else if(mFragmentBillingdetailsBinding.edtTxtPincode.getText().toString().trim().equals("")||(mFragmentBillingdetailsBinding.edtTxtPincode.getText().toString().length()<6)){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_pincode))
                            .show();
                }else if  (mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString().trim().length()>0 &&
                        !Patterns.PHONE.matcher(mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString()).matches()) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.please_enter_valid_phone_number))
                            .show();
                }else if(mFragmentBillingdetailsBinding.edtMobileNo.getText().toString().equals(mFragmentBillingdetailsBinding.edtAlternateMobileNo.getText().toString())){
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getResources().getString(R.string.validation_error))
                            .setContentText(getResources().getString(R.string.mobilenodoesnotmatch))
                            .show();
                }
                else{


                    if(valueOfState==true){
                        mBillingDetailsViewModel.getProceedToPayForShippingAddress(pujaDatetime,shippingbkgId,mFragmentBillingdetailsBinding.edtTxtFirstName.getText().toString(),mFragmentBillingdetailsBinding.edtTxtLastName.getText().toString(),
                                mFragmentBillingdetailsBinding.edtTxtAddress.getText().toString(),shippingAddress2,shippingAddress3,mFragmentBillingdetailsBinding.edtMobileNo.getText().toString(),
                                mFragmentBillingdetailsBinding.edtTxtCity.getText().toString(),mFragmentBillingdetailsBinding.edtTxtState.getText().toString(),mFragmentBillingdetailsBinding.edtTxtPincode.getText().toString(),Double.parseDouble(shippingOrderAmount),shippingOrderId,shippingFirstName,shippingLastName,shippingAlternateMobile,
                                shippingemail,shippingAddress, shippingPincode,shippingAdditionalInfo
                        ).observe(getActivity(), BillingDetailsFragment.this::handleProceedToPay);
                    }

                    else{

                        mBillingDetailsViewModel.getProceedToPayForBillingAddress(pujaDatetime,bkgId,mFragmentBillingdetailsBinding.edtTxtFirstName.getText().toString(),
                            mFragmentBillingdetailsBinding.edtTxtLastName.getText().toString(),mFragmentBillingdetailsBinding.edtTxtAddress.getText().toString(),
                            mFragmentBillingdetailsBinding.edtTxtAddress2.getText().toString(),mFragmentBillingdetailsBinding.edtTxtAddress3.getText().toString(),
                            mFragmentBillingdetailsBinding.edtMobileNo.getText().toString(),mFragmentBillingdetailsBinding.edtTxtCity.getText().toString(),
                            mFragmentBillingdetailsBinding.edtTxtState.getText().toString(),mFragmentBillingdetailsBinding.edtTxtPincode.getText().toString(),Double.parseDouble(orderAmount),
                            orderId).observe(getActivity(), BillingDetailsFragment.this::handleProceedToPay);
                      }

                }


            }
        });




    }

    @Override
    public void onResume() {
        super.onResume();
        try {

            Log.e("SOUMMMMMEN", "Frag");
            ((MainActivity) getActivity()).returnPaymentDetails();
            Bundle bundle = ((MainActivity) getActivity()).returnPaymentDetails();
            if (bundle != null)
                paymentorderID = bundle.getString("orderId");
            paymentMode = bundle.getString("paymentMode");
            Log.e("PAYEEMODEEE",paymentMode);
            transactionTime = bundle.getString("txTime");
            Log.e("TRANSTIME",transactionTime);
            referenceId = bundle.getString("referenceId");
            Log.e("REFERENCETIME",referenceId);
            txMsg = bundle.getString("txMsg");
            Log.e("TRANSACTIONMSG",txMsg);
            orderAmount = bundle.getString("orderAmount");
            Log.e("ORDERAMOUNT",orderAmount);
            txStatus = bundle.getString("txStatus");
            Log.e("STATUS",txStatus);
            if(paymentorderID.length()>0){
                mBillingDetailsViewModel.getUpdateInvoice(Integer.parseInt(paymentorderID)).observe(getActivity(),BillingDetailsFragment.this::handleUpdateInvoice);
            }

            Log.e("OrderIddd", paymentorderID);
            for (String key : bundle.keySet()) {
                if (bundle.getString(key) != null) {
                    Log.d(TAG, key + " : " + bundle.getString(key));

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
                        mBillingDetailsViewModel.getCashFreeToken("INR",String.valueOf(orderId),orderAmount).observe(getActivity(), BillingDetailsFragment.this::handleCashFreeToken);



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
                        cfPaymentService.doPayment(BillingDetailsFragment.this.getActivity(), getInputParams(), cashFreeToken, "TEST", "#784BD2", "#FFFFFF", false);
                         //cfPaymentService.doPayment(BillingDetailsFragment.this.getActivity(), getInputParams(), cashFreeToken, "PROD", "#784BD2", "#FFFFFF", false);



                    }
                    DisplayDialog.getInstance().dismissAlertDialog();
                    break;
                default:
                    DisplayDialog.getInstance().dismissAlertDialog();

                    break;
            }
        }
    }
    private void handleUpdateInvoice(Resource<UpdatedInvoicesModel> resource) {
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
                    DisplayDialog.getInstance().dismissAlertDialog();
                    if ( resource.data.returnStatus.equals("SUCCESS")) {
                        CustBkg custBkg=resource.data.custInvoiceAsEmail.getCustBkg();
                        CustInvoice  custInvoice= resource.data.custInvoiceAsEmail.getCustInvoice();
                        List<PujaSamagriForDelivery> pujaSamagriForDelivery=resource.data.custInvoiceAsEmail.getPujaSamagriForDeliveryList();
                        List<PujaPrcdr> pujaPrcdrList=resource.data.custInvoiceAsEmail.getPujaPrcdrList();
                        List<PujasamagriHH> pujasamagriHH=resource.data.custInvoiceAsEmail.getPujasamagriHHList();
                        mBillingDetailsViewModel.getSendEmailInvoice(custBkg,custInvoice,pujaSamagriForDelivery,pujaPrcdrList,pujasamagriHH).observe(getActivity(),BillingDetailsFragment.this::handlegetSendEmailInvoice);

                    }

                    break;
                default:
                    DisplayDialog.getInstance().dismissAlertDialog();

                    break;
            }
        }
    }





    private void handlegetSendEmailInvoice(Resource<SendEmailForCustInvoiceModel> resource) {
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
                    DisplayDialog.getInstance().dismissAlertDialog();
                    if ( resource.data.returnStatus.equals("SUCCESS")) {

                        BillingDetailsFragmentDirections.ActionBillingDetailsFragmentToTransactionStatusFragment action =
                                    BillingDetailsFragmentDirections.actionBillingDetailsFragmentToTransactionStatusFragment();
                            action.setPaymentorderID(paymentorderID);
                            action.setOrderAmount(orderAmount);
                            action.setReferenceid(referenceId);
                            action.setTransactionstatus(txMsg);
                            action.setPaymentmode(paymentMode);
                            action.setMessage(txStatus);
                            action.setTransactiontime(transactionTime);
                            Navigation.findNavController(mView).navigate(action);


                    }

                    break;
                default:
                    DisplayDialog.getInstance().dismissAlertDialog();

                    break;
            }
        }
    }
    private Map<String, String> getInputParams() {
        String appId = "6159303c6dd0fdc88e24a424f39516";//TEST
         //String appId = "10732304e9cb87da1696501a98323701";//PRODUCTION
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
