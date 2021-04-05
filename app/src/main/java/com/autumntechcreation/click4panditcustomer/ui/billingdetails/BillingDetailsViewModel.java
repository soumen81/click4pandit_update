package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import javax.inject.Inject;

public class BillingDetailsViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickonClickBillingDiffLocation = new SingleLiveEvent<>();
    @Inject
    public BillingDetailsViewModel() {
    }

    public void onClickBillingDiffLocation(View view) {
        Log.e("Click",view.getId()+"");
        mclickonClickBillingDiffLocation.call();

    }
    public SingleLiveEvent<Void> getOnClickBillingDiffLocation() {
        return mclickonClickBillingDiffLocation;
    }

}
