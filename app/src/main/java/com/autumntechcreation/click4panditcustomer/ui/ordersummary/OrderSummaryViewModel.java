package com.autumntechcreation.click4panditcustomer.ui.ordersummary;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import javax.inject.Inject;

public class OrderSummaryViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickConfirmOrder = new SingleLiveEvent<>();
    @Inject
    public OrderSummaryViewModel() {
    }


    public void onClickConfirmOrder(View view) {
        Log.e("Click",view.getId()+"");
        mclickConfirmOrder.call();

    }
    public SingleLiveEvent<Void> getOnClickConfirmOrder() {
        return mclickConfirmOrder;
    }

}
