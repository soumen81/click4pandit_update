package com.autumntechcreation.click4panditcustomer.ui.choosepackage;

import android.util.Log;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.adapter.ChoosePackageListAdapter;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.home.PujaCategoryModel;
import com.autumntechcreation.click4panditcustomer.ui.home.PujaTypesModel;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

public class ChoosePackageViewModel extends ViewModel {
    private SingleLiveEvent<Void> mclickStandardPackage = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> mclickViewPackage = new SingleLiveEvent<>();
    ChoosePackageRepository mChoosePackageRepository;
    public LiveData<Resource<List<ChoosePackageListModel>>> mChoosePackageList;
    ChoosePackageListAdapter mChoosePackageListAdapter;
    private static MutableLiveData<List<ChoosePackageListModel>> mChoosePackageListModel;
    private SingleLiveEvent<Integer> mSelectedChoosePackageItem = new SingleLiveEvent<>();

    @Inject
    public ChoosePackageViewModel(ChoosePackageRepository choosePackageRepository) {
        this.mChoosePackageRepository = choosePackageRepository;
    }

    public void init() {
        mChoosePackageListAdapter = new ChoosePackageListAdapter(R.layout.singlerow_choosepackagelist, this);
    }

    public LiveData<Resource<List<ChoosePackageListModel>>> getPujaPackageList(int subCategoryId) {
        mChoosePackageList = new MutableLiveData<>();
        mChoosePackageList = mChoosePackageRepository.getPujaPackageList(subCategoryId);
        return mChoosePackageList;
    }


    public void setChoosePackageListAdapter(List<ChoosePackageListModel> list) {

        mChoosePackageListModel = new MutableLiveData<>();
        mChoosePackageListModel.setValue(list);
        this.mChoosePackageListAdapter.setChoosePackageList(list);
        this.mChoosePackageListAdapter.notifyDataSetChanged();
    }

    public ChoosePackageListAdapter getChoosepackageAdapter() {
        return mChoosePackageListAdapter;
    }


    public void onClickStandardPackage(View view) {
        Log.e("Click", view.getId() + "");
        mclickStandardPackage.call();

    }

    public SingleLiveEvent<Void> getonClickStandardPackage() {
        return mclickStandardPackage;
    }


    public void onClickViewPackage(View view) {
        Log.e("Click", view.getId() + "");
        mclickViewPackage.call();

    }

    public SingleLiveEvent<Void> getonClickViewPackage() {
        return mclickViewPackage;
    }


    public String getPackageName(int position) {
        List<ChoosePackageListModel> list = mChoosePackageListModel.getValue();
        return "" + list.get(position).getPujaPkgTypeIdDscr();
    }

    public String getPackageDesc(int position) {
        List<ChoosePackageListModel> list = mChoosePackageListModel.getValue();
        return "" + list.get(position).getPujaPkgDscr();
    }

    public String getPackageAmount(int position) {
        List<ChoosePackageListModel> list = mChoosePackageListModel.getValue();
        return "" + list.get(position).getPujaPkgAmount();
    }

    public void onClickChoosePackage(View view, int pos) {
        Log.e("ClickPOSITION", view.getId() + "POSITION:" + Integer.toString(pos));

        mSelectedChoosePackageItem.setValue(pos);
    }

    public SingleLiveEvent<Integer> getSelectedChoosePackageListItem() {
        return mSelectedChoosePackageItem;
    }
    @BindingAdapter("load_back")
    public static void loadBack(ConstraintLayout view, int position) {
        Log.e("ImageId", position + "");
        ChoosePackageListModel choosePackageListModel = mChoosePackageListModel.getValue().get(position);
        if (choosePackageListModel.isSelect) {
            view.setBackgroundResource(R.drawable.round_yellow_corner);
        } else {
            view.setBackgroundResource(R.drawable.round_white_corner);
        }
    }

}



