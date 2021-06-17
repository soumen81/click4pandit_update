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
    MutableLiveData<List<BookingLocationModel>>bookingLocationModelMutableLiveData=new MutableLiveData<>();
    @Inject
    public BookingPujaViewModel(BookingPujaRepository bookingPujaRepository) {
        this.mBookingPujaRepository=bookingPujaRepository;
    }


    public LiveData<Resource<List<BookingLocationModel>>> getBookingLocationList(String cityName) {
        mBookingLocationList=new MutableLiveData<>();
        mBookingLocationList=mBookingPujaRepository.getLocationList(cityName);
        return mBookingLocationList;
    }public LiveData<Resource<List<BookingLanguageModel>>> getBookingLanguageList() {
        mBookingLanguageList=new MutableLiveData<>();
        mBookingLanguageList=mBookingPujaRepository.getLanguageList();
        return mBookingLanguageList;
    }
    public void updateLocationList() {
        bookingLocationModelMutableLiveData.postValue(mBookingLocationList.getValue().data);
    }
    public MutableLiveData<List<BookingLocationModel>> getLocationList() {
        return bookingLocationModelMutableLiveData;
    }

    public void onClickBookingPackage(View view) {
        Log.e("Click",view.getId()+"");
        mclickBookingPackage.call();

    }
    public SingleLiveEvent<Void> getOnClickBookPackage() {
        return mclickBookingPackage;
    }



    public int getLocationId(String locationName){
        int locationId=0;
        List<BookingLocationModel>listBookingLocation=mBookingLocationList.getValue().data;
        for(int i=0;i<listBookingLocation.size();i++){
            if(locationName.equals(listBookingLocation.get(i).subLcltyName)){
                locationId=listBookingLocation.get(i).getSubLcltyId();

            }
        }
        return locationId;

    }

}
