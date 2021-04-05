package com.autumntechcreation.click4panditcustomer.ui.bookpuja;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import javax.inject.Inject;

public class BookingPujaViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickBookingPackage = new SingleLiveEvent<>();
    @Inject
    public BookingPujaViewModel() {
    }

    public void onClickBookingPackage(View view) {
        Log.e("Click",view.getId()+"");
        mclickBookingPackage.call();

    }
    public SingleLiveEvent<Void> getOnClickBookPackage() {
        return mclickBookingPackage;
    }

}
