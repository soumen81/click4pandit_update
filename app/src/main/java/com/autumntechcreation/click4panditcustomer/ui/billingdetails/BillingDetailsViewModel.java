package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummeryModel;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import javax.inject.Inject;

public class BillingDetailsViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickonClickBillingDiffLocation = new SingleLiveEvent<>();
    BillingDetailsRepository mBillingDetailsRepository;
    public LiveData<Resource<ProceedtoPayModel>> mProceedtoPayModelResponse;
    @Inject
    public BillingDetailsViewModel(BillingDetailsRepository billingDetailsRepository) {
        this.mBillingDetailsRepository=billingDetailsRepository;
    }


    public LiveData<Resource<ProceedtoPayModel>> getProceedPayResult(String pujaDateTime, int custBkgId, String firstName, String lastName,
                                                                   String address, String mobileNo, String city,String state,String pincode,
                                                                   double orderAmount,int orderId) {
        mProceedtoPayModelResponse = new MutableLiveData<>();
        mProceedtoPayModelResponse = mBillingDetailsRepository.getProceedtoPay(pujaDateTime, custBkgId, firstName, lastName, address,
                mobileNo, city,state,pincode,orderAmount,orderId);
        return mProceedtoPayModelResponse;

    }


    public void onClickBillingDiffLocation(View view) {
        Log.e("Click",view.getId()+"");
        mclickonClickBillingDiffLocation.call();

    }
    public SingleLiveEvent<Void> getOnClickBillingDiffLocation() {
        return mclickonClickBillingDiffLocation;
    }

}
