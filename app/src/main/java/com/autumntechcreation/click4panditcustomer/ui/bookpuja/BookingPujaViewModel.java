package com.autumntechcreation.click4panditcustomer.ui.bookpuja;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.home.PujaCategoryModel;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

public class BookingPujaViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickBookingPackage = new SingleLiveEvent<>();
    BookingPujaRepository mBookingPujaRepository;
    public LiveData<Resource<List<BookingLocationModel>>> mBookingLocationList;
    public LiveData<Resource<List<BookingLanguageModel>>> mBookingLanguageList;
    @Inject
    public BookingPujaViewModel(BookingPujaRepository bookingPujaRepository) {
        this.mBookingPujaRepository=bookingPujaRepository;
    }


    public LiveData<Resource<List<BookingLocationModel>>> getBookingLocationList() {
        mBookingLocationList=new MutableLiveData<>();
        mBookingLocationList=mBookingPujaRepository.getLocationList();
        return mBookingLocationList;
    }public LiveData<Resource<List<BookingLanguageModel>>> getBookingLanguageList() {
        mBookingLanguageList=new MutableLiveData<>();
        mBookingLanguageList=mBookingPujaRepository.getLanguageList();
        return mBookingLanguageList;
    }

    public void onClickBookingPackage(View view) {
        Log.e("Click",view.getId()+"");
        mclickBookingPackage.call();

    }
    public SingleLiveEvent<Void> getOnClickBookPackage() {
        return mclickBookingPackage;
    }

}
