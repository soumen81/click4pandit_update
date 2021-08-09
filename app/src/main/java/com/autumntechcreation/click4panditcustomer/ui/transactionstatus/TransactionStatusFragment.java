package com.autumntechcreation.click4panditcustomer.ui.transactionstatus;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentTransactionstatusBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.BillingDetailsViewModel;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class TransactionStatusFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentTransactionstatusBinding mFragmentTransactionstatusBinding;
    TransactionStatusViewModel mTransactionStatusViewModel;
    private View mView;
    NavController navController;
    String getPaymentorderID,getOrderAmount,getReferenceid,getTransactionstatus,getPaymentmode,getMessage,getTransactiontime;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentTransactionstatusBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_transactionstatus, container, false);
        mFragmentTransactionstatusBinding.setLifecycleOwner(this);

        if(TransactionStatusFragmentArgs.fromBundle(getArguments()).getPaymentorderID().length()>0) {
            getPaymentorderID = BillingDetailsFragmentArgs.fromBundle(getArguments()).getPaymentorderID();
            Log.e("ORDERID", getPaymentorderID);
        } if(TransactionStatusFragmentArgs.fromBundle(getArguments()).getOrderAmount().length()>0) {
            getOrderAmount = BillingDetailsFragmentArgs.fromBundle(getArguments()).getOrderAmount();
            Log.e("ORDERAMOUNT", getOrderAmount);
        }if(TransactionStatusFragmentArgs.fromBundle(getArguments()).getReferenceid().length()>0) {
            getReferenceid = BillingDetailsFragmentArgs.fromBundle(getArguments()).getReferenceid();
            Log.e("REFERENCEID", getReferenceid);
        }if(TransactionStatusFragmentArgs.fromBundle(getArguments()).getTransactionstatus().length()>0) {
            getTransactionstatus = BillingDetailsFragmentArgs.fromBundle(getArguments()).getTransactionstatus();
            Log.e("TRANSACTIOSTATUS", getTransactionstatus);
        }if(TransactionStatusFragmentArgs.fromBundle(getArguments()).getPaymentmode().length()>0) {
            getPaymentmode = BillingDetailsFragmentArgs.fromBundle(getArguments()).getPaymentmode();
            Log.e("PAYMENTMODE", getPaymentmode);
        }if(TransactionStatusFragmentArgs.fromBundle(getArguments()).getMessage().length()>0) {
            getMessage = BillingDetailsFragmentArgs.fromBundle(getArguments()).getMessage();
            Log.e("MESSAGE", getMessage);
        }if(TransactionStatusFragmentArgs.fromBundle(getArguments()).getTransactiontime().length()>0) {
            getTransactiontime = BillingDetailsFragmentArgs.fromBundle(getArguments()).getTransactiontime();
            Log.e("TRANSACTIONTIME", getTransactiontime);
        }

        return mFragmentTransactionstatusBinding.getRoot();

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

        mTransactionStatusViewModel = ViewModelProviders.of(TransactionStatusFragment.this, viewModelFactory).get(TransactionStatusViewModel.class);
        mFragmentTransactionstatusBinding.setViewModel(mTransactionStatusViewModel);

        mFragmentTransactionstatusBinding.orderIdValue.setText(getPaymentorderID);
        mFragmentTransactionstatusBinding.tvOrderAmountValue.setText(getOrderAmount);
        mFragmentTransactionstatusBinding.tvReferenceIdValue.setText(getReferenceid);
        mFragmentTransactionstatusBinding.tvTransactionStatusValue.setText(getTransactionstatus);
        mFragmentTransactionstatusBinding.tvPaymentModeValue.setText(getPaymentmode);
        mFragmentTransactionstatusBinding.tvMessageValue.setText(getMessage);
        mFragmentTransactionstatusBinding.tvtransactimeValue.setText(getTransactiontime);

        mFragmentTransactionstatusBinding.tvBacktoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransactionStatusFragmentDirections.ActionTransactionStatusFragmentToHomeFragmentFragment action=
                        TransactionStatusFragmentDirections.actionTransactionStatusFragmentToHomeFragmentFragment();
                Navigation.findNavController(mView).navigate(action);
              // getActivity().finish();
            }
        });

    }
    }
