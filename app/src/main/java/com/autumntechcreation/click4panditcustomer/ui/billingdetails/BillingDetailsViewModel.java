package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummeryModel;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;
import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;

public class BillingDetailsViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickonClickBillingDiffLocation = new SingleLiveEvent<>();
    BillingDetailsRepository mBillingDetailsRepository;
    public LiveData<Resource<ProceedtoPayModel>> mProceedtoPayModelResponse;
    public LiveData<Resource<CashFreeTokenModel>> mCashFreeTokenResponse;
    public LiveData<Resource<UpdatedInvoicesModel>> mUpdateInvoiceResponse;
    public LiveData<Resource<SendEmailForCustInvoiceModel>> mSendEmailInvoiceResponse;
    @Inject
    public BillingDetailsViewModel(BillingDetailsRepository billingDetailsRepository) {
        this.mBillingDetailsRepository=billingDetailsRepository;
    }


    public LiveData<Resource<ProceedtoPayModel>> getProceedToPayForBillingAddress(String pujaDateTime, int custBkgId, String firstName, String lastName,
                                                                   String address,String address2,String address3, String mobileNo, String city,String state,String pincode,
                                                                   double orderAmount,int orderId) {
        mProceedtoPayModelResponse = new MutableLiveData<>();
        mProceedtoPayModelResponse = mBillingDetailsRepository.proceedtoPayForBillingAddress(pujaDateTime, custBkgId, firstName, lastName, address,address2,address3,
                mobileNo, city,state,pincode,orderAmount,orderId);
        return mProceedtoPayModelResponse;

    }
    public LiveData<Resource<ProceedtoPayModel>> getProceedToPayForShippingAddress(String pujaDateTime, int custBkgId, String firstName, String lastName,
                                                                                   String address,String shippingAddress2,String shippingAddress3, String mobileNo, String city,String state,String pincode,
                                                                                   double orderAmount,int orderId, String shippingFirstName,String shippingLastName,
                                                                                   String shippingMobileNo,String shippingEmailId,String shippingAddress,
                                                                                   String shippingPostalCode,String additionalInfo) {
        mProceedtoPayModelResponse = new MutableLiveData<>();
        mProceedtoPayModelResponse = mBillingDetailsRepository.proceedtoPayForShippingAddress(pujaDateTime, custBkgId, firstName, lastName, address,
                shippingAddress2,shippingAddress3,mobileNo, city,state,pincode,orderAmount,orderId,shippingFirstName,shippingLastName,shippingMobileNo,shippingEmailId,shippingAddress,
                shippingPostalCode,additionalInfo);
        return mProceedtoPayModelResponse;

    } public LiveData<Resource<CashFreeTokenModel>> getCashFreeToken(String orderCurrency, String orderId,String orderAmount) {
        mCashFreeTokenResponse = new MutableLiveData<>();
        mCashFreeTokenResponse = mBillingDetailsRepository.getCashFreeToken(orderCurrency,orderId,orderAmount);
        return mCashFreeTokenResponse;

    }public LiveData<Resource<UpdatedInvoicesModel>> getUpdateInvoice(int orderId) {
        mUpdateInvoiceResponse = new MutableLiveData<>();
        mUpdateInvoiceResponse = mBillingDetailsRepository.updateinvoice(orderId);
        return mUpdateInvoiceResponse;

    }public LiveData<Resource<SendEmailForCustInvoiceModel>> getSendEmailInvoice(CustBkg custBkg, CustInvoice custInvoice,
                                                                                 List<PujaSamagriForDelivery> listPujaSamagriForDelivery,
                                                                                 List<PujaPrcdr> listPujaPrcdcr,
                                                                                 List<PujasamagriHH> listpujasamagriHHList) {
        mSendEmailInvoiceResponse = new MutableLiveData<>();
        mSendEmailInvoiceResponse = mBillingDetailsRepository.sendCustomerInvoice(custBkg,custInvoice,listPujaSamagriForDelivery,listPujaPrcdcr,listpujasamagriHHList);
        return mSendEmailInvoiceResponse;

    }


    public void onClickBillingDiffLocation(View view) {
        Log.e("Click",view.getId()+"");
        mclickonClickBillingDiffLocation.call();

    }
    public SingleLiveEvent<Void> getOnClickBillingDiffLocation() {
        return mclickonClickBillingDiffLocation;
    }




    public String getEmail() {
       return  mBillingDetailsRepository.getEmail();
    } public String getFirstName() {
       return  mBillingDetailsRepository.getFirstName();
    } public String getLastName() {
       return  mBillingDetailsRepository.getLastName();
    } public String getMobile() {
       return  mBillingDetailsRepository.getMobile();
    }
}
