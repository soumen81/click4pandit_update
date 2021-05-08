package com.autumntechcreation.click4panditcustomer.ui.cashfree;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivityCashfreeBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.differentpujalocation.DifferentPujaLocationFragmentArgs;
import com.cashfree.pg.CFPaymentService;
import com.cashfree.pg.ui.gpay.GooglePayStatusListener;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static androidx.navigation.Navigation.findNavController;
import static com.cashfree.pg.CFPaymentService.PARAM_APP_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_BANK_CODE;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_CVV;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_HOLDER;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_MM;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_NUMBER;
import static com.cashfree.pg.CFPaymentService.PARAM_CARD_YYYY;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_CURRENCY;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_NOTE;
import static com.cashfree.pg.CFPaymentService.PARAM_PAYMENT_OPTION;
import static com.cashfree.pg.CFPaymentService.PARAM_UPI_VPA;
import static com.cashfree.pg.CFPaymentService.PARAM_WALLET_CODE;

public class CashFreeFragment extends Fragment implements Injectable {


    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivityCashfreeBinding mActivityCashfreeBinding;
    private View mView;
    NavController navController;
    CashFreeViewModel mCashFreeViewModel;
    enum SeamlessMode {
        CARD, WALLET, NET_BANKING, UPI_COLLECT, PAY_PAL
    }

    SeamlessMode currentMode = SeamlessMode.CARD;

    private static final String TAG = "CashFreeFragment";
    String cashToken;
    String appId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivityCashfreeBinding = DataBindingUtil.inflate(inflater, R.layout.activity_cashfree, container, false);
        mActivityCashfreeBinding.setLifecycleOwner(this);

       /* cashToken = CashFreeFragmentArgs.fromBundle(getArguments()).getCashFreeToken();
        Log.e("CASHTOKEN", cashToken);*/
        return mActivityCashfreeBinding.getRoot();
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

        mCashFreeViewModel = ViewModelProviders.of(CashFreeFragment.this, viewModelFactory).get(CashFreeViewModel.class);
        mActivityCashfreeBinding.setCashFreeViewModel(mCashFreeViewModel);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Same request code for all payment APIs.
//        Log.d(TAG, "ReqCode : " + CFPaymentService.REQ_CODE);
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

    public void onClick(View view) {




        String stage = "TEST";

        //Show the UI for doGPayPayment and phonePePayment only after checking if the apps are ready for payment
        if (view.getId() == R.id.phonePe_exists) {
            Toast.makeText(
                    getActivity(), CFPaymentService.getCFPaymentServiceInstance().doesPhonePeExist(getActivity(), stage)+"",
                    Toast.LENGTH_SHORT).show();
            return;
        } else if (view.getId() == R.id.gpay_ready) {
            CFPaymentService.getCFPaymentServiceInstance().isGPayReadyForPayment(getActivity(), new GooglePayStatusListener() {
                @Override
                public void isReady() {
                    Toast.makeText(getActivity(), "Ready", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void isNotReady() {
                    Toast.makeText(getActivity(), "Not Ready", Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }




        //String token = "CV9JCN4MzUIJiOicGbhJCLiQ1VKJiOiAXe0Jye.xB0nIlJWY3MWMmBTMiVDM2IiOiQHbhN3XiwSO0gjM3ETOxYTM6ICc4VmIsIiUOlkI6ISej5WZyJXdDJXZkJ3biwCMxojI05Wdv1WQyVGZy9mIsISM1ADMfJXZkJ3biojIklkclRmcvJye.hstKpUsrEjHV-gmoLRHXuEPaMcvcJZFS6ypMzS-7u9IIXpiLqjP5pqiQX_AfFxQVEe";
        String token = cashToken;
        Log.e("TOKENCASH",token);


        CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
        cfPaymentService.setOrientation(0);
        switch (view.getId()) {



            case R.id.web: {
                cfPaymentService.doPayment(getActivity(), getInputParams(), token, stage, "#784BD2", "#FFFFFF", false);
//                 cfPaymentService.doPayment(MainActivity.this, params, token, stage);
                break;
            }


            case R.id.upi: {
//                                cfPaymentService.selectUpiClient("com.google.android.apps.nbu.paisa.user");
                cfPaymentService.upiPayment(getActivity(), getInputParams(), token, stage);
                break;
            }
            case R.id.amazon: {
                cfPaymentService.doAmazonPayment(getActivity(), getInputParams(), token, stage);
                break;
            }
            case R.id.gpay: {
                cfPaymentService.gPayPayment(getActivity(), getInputParams(), token, stage);
                break;
            }
            case R.id.phonePe: {
                cfPaymentService.phonePePayment(getActivity(), getInputParams(), token, stage);
                break;
            }
            case R.id.web_seamless: {
                cfPaymentService.doPayment(getActivity(), getSeamlessCheckoutParams(), token, stage);
                break;
            }
        }
    }

    private Map<String, String> getInputParams() {




         appId = "6159303c6dd0fdc88e24a424f39516";
        String orderId = "order_0051";
        String orderAmount = "10";
        String orderNote = "Puja";
        String customerName = "John Doe";
        String customerPhone = "9900012345";
        String customerEmail = "test@gmail.com";

        Map<String, String> params = new HashMap<>();

        params.put(PARAM_APP_ID, appId);
        params.put(PARAM_ORDER_ID, orderId);
        params.put(PARAM_ORDER_AMOUNT, orderAmount);
        params.put(PARAM_ORDER_NOTE, orderNote);
        params.put(PARAM_CUSTOMER_NAME, customerName);
        params.put(PARAM_CUSTOMER_PHONE, customerPhone);
        params.put(PARAM_CUSTOMER_EMAIL, customerEmail);
        params.put(PARAM_ORDER_CURRENCY, "INR");
        return params;
    }

    private Map<String, String> getSeamlessCheckoutParams() {
        Map<String, String> params = getInputParams();
        switch (currentMode) {
            case CARD:
                params.put(PARAM_PAYMENT_OPTION, "card");
                params.put(PARAM_CARD_NUMBER, "VALID_CARD_NUMBER");
                params.put(PARAM_CARD_YYYY, "YYYY");
                params.put(PARAM_CARD_MM, "MM");
                params.put(PARAM_CARD_HOLDER, "CARD_HOLDER_NAME");
                params.put(PARAM_CARD_CVV, "CVV");
                break;
            case WALLET:
                params.put(PARAM_PAYMENT_OPTION, "wallet");
                params.put(PARAM_WALLET_CODE, "4007"); // Put one of the wallet codes mentioned here https://dev.cashfree.com/payment-gateway/payments/wallets
                break;
            case NET_BANKING:
                params.put(PARAM_PAYMENT_OPTION, "nb");
                params.put(PARAM_BANK_CODE, "3333"); // Put one of the bank codes mentioned here https://dev.cashfree.com/payment-gateway/payments/netbanking
                break;
            case UPI_COLLECT:
                params.put(PARAM_PAYMENT_OPTION, "upi");
                params.put(PARAM_UPI_VPA, "VALID_VPA");
                break;
            case PAY_PAL:
                params.put(PARAM_PAYMENT_OPTION, "paypal");
                break;
        }
        return params;
    }
    }
