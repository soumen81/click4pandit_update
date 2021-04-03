package com.autumntechcreation.click4panditcustomer.ui.choosepackage;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import javax.inject.Inject;

public class ChoosePackageViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickStandardPackage = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> mclickViewPackage = new SingleLiveEvent<>();
    @Inject
    public ChoosePackageViewModel() {
    }




    public void onClickStandardPackage(View view) {
        Log.e("Click",view.getId()+"");
        mclickStandardPackage.call();

    }
    public SingleLiveEvent<Void> getonClickStandardPackage() {
        return mclickStandardPackage;
    }



    public void onClickViewPackage(View view) {
        Log.e("Click",view.getId()+"");
        mclickViewPackage.call();

    }
    public SingleLiveEvent<Void> getonClickViewPackage() {
        return mclickViewPackage;
    }
}
