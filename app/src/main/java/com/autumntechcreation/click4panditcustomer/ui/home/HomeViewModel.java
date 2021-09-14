package com.autumntechcreation.click4panditcustomer.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordModel;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {
    public LiveData<Resource<List<PujaTypesModel>>> mPujaTypesList;
    public LiveData<Resource<List<PujaCategoryModel>>> mPujaCategoryList;
    private LiveData<Resource<CartItemCountModel>> mCartItemCountModelResponse;
    HomeRepository mHomeRepository;
    @Inject
    public HomeViewModel(HomeRepository homeRepository) {
        this.mHomeRepository=homeRepository;
    }


    public LiveData<Resource<List<PujaTypesModel>>> getPujaTypesList() {
        mPujaTypesList=new MutableLiveData<>();
        mPujaTypesList=mHomeRepository.getPujaTypesList();
        return mPujaTypesList;
    } public LiveData<Resource<List<PujaCategoryModel>>> getPujaCategoryList(int categoryId) {
        mPujaCategoryList=new MutableLiveData<>();
        mPujaCategoryList=mHomeRepository.getPujaCategoriesList(categoryId);
        return mPujaCategoryList;
    }
    public LiveData<Resource<CartItemCountModel>> getCartCountItem() {
        mCartItemCountModelResponse=new MutableLiveData<>();
        mCartItemCountModelResponse=mHomeRepository.getCartCountItem();
        return mCartItemCountModelResponse;
    }

    public String storeCartCount() {
       return mHomeRepository.getcartCount();

    }public String getUpdateCartCount() {
       return mHomeRepository.getupdateCartCount();

    }
}
