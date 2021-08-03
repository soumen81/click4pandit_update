package com.autumntechcreation.click4panditcustomer.ui.ordersummary;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterResponse;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import javax.inject.Inject;

public class OrderSummaryViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickConfirmOrder = new SingleLiveEvent<>();
    OrderSummaryRepository mOrderSummaryRepository;
    public LiveData<Resource<OrderSummeryModel>> mOrderSummeryModelResponse;
    @Inject
    public OrderSummaryViewModel(OrderSummaryRepository orderSummaryRepository) {
        this.mOrderSummaryRepository=orderSummaryRepository;
    }


    public LiveData<Resource<OrderSummeryModel>> getNewOrderResult(int languageId, String pujaLanguageName, double amount, double cgstsgst,
                                                                   double totalAmount, String pkgDesc,int pujaPackageTypeId, int pujaPackageId,int noOfPandit,
                                                                   String subCategoryName,int subCategoryId,String locationName,int locationId,
                                                                   String dateTime) {
        mOrderSummeryModelResponse = new MutableLiveData<>();
        mOrderSummeryModelResponse = mOrderSummaryRepository.getOrderGenerate(languageId, pujaLanguageName, amount, cgstsgst, totalAmount,
                pkgDesc,pujaPackageTypeId, pujaPackageId,noOfPandit,subCategoryName,subCategoryId,locationName,locationId,dateTime);
        return mOrderSummeryModelResponse;

    }



    public void onClickConfirmOrder(View view) {
        Log.e("Click",view.getId()+"");
        mclickConfirmOrder.call();

    }
    public SingleLiveEvent<Void> getOnClickConfirmOrder() {
        return mclickConfirmOrder;
    }



}
