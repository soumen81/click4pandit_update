package com.autumntechcreation.click4panditcustomer.ui.address;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.adapter.AddressBookListadapter;
import com.autumntechcreation.click4panditcustomer.adapter.OrderListingAdapter;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageRepository;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderListModel;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class AddressViewModel extends ViewModel {
    AddressBookListadapter mAddressBookListadapter;
    private MutableLiveData<List<AddressListModel>> mAddressListModel;
    public LiveData<Resource<List<AddressListModel>>> mAddressListResponse;
    AddressRepository mAddressRepository;
    private SingleLiveEvent<Integer> mSelectedUpdateListItem=new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> mSelectedRemoveListItem=new SingleLiveEvent<>();
    @Inject
    public AddressViewModel(AddressRepository addressRepository) {
    this.mAddressRepository=addressRepository;
    }
    public void init(){
        mAddressBookListadapter=new AddressBookListadapter(R.layout.singlerow_addressbook,this);
        this.mAddressBookListadapter.notifyDataSetChanged();
    }

    public LiveData<Resource<List<AddressListModel>>> getAddressViewList() {
        mAddressListResponse = new MutableLiveData<>();
        mAddressListResponse = mAddressRepository.getAddressList();
        return mAddressListResponse;
    }
    
    public AddressBookListadapter getAddressListAdapter(){
        return mAddressBookListadapter;
    }


    public void setAddressList(List<AddressListModel>listAddressListModel){
        mAddressListModel=new MutableLiveData<>();
        mAddressListModel.setValue(listAddressListModel);
        this.mAddressBookListadapter.setAddressListing(listAddressListModel);
        this.mAddressBookListadapter.notifyDataSetChanged();
        Collections.reverse(listAddressListModel);
    }



    public String getCustName(int position){
        List<AddressListModel> list = mAddressListModel.getValue();
        return "Name:" +" "+list.get(position).getFirstName()+" "+list.get(position).getLastName();
    }  public String getCustAddress(int position){
        List<AddressListModel> list = mAddressListModel.getValue();
        return "Address1:"+" "+ list.get(position).getAddr1();
    }public String getCustAddress2(int position){
        List<AddressListModel> list = mAddressListModel.getValue();
        if (list.get(position).getAddr2() == null) {
            return "";
        }else {
            return "Address2:" + " " + list.get(position).getAddr2();
        }
    }public String getCustAddress3(int position){
        List<AddressListModel> list = mAddressListModel.getValue();
        if (list.get(position).getAddr3() == null) {
            return "";
        }else {
            return "Address3:" + " " + list.get(position).getAddr3();
        }
    }public String getCity(int position){
        List<AddressListModel> list = mAddressListModel.getValue();
        {
            return "City:" + " " + "Kolkata";
        }
    }public String getPinCode(int position){
        List<AddressListModel> list = mAddressListModel.getValue();
        if (list.get(position).getPostal() == null) {
            return "";
        }else {
            return "PinCode:" + " " + list.get(position).getPostal();
        }
    }

    public void onClickUpdateList(View view, int pos){
        Log.e("ClickPosition",view.getId()+"POSITION:"+Integer.toString(pos));
        mSelectedUpdateListItem.setValue(pos);
    }
    public SingleLiveEvent<Integer> getUpdateListItemsClick(){
        return mSelectedUpdateListItem;
    }


    public void onClickRemoveList(View view, int pos){
        Log.e("ClickPosition",view.getId()+"POSITION:"+Integer.toString(pos));
        mSelectedRemoveListItem.setValue(pos);
    }
    public SingleLiveEvent<Integer> getRemoveListItemsClick(){
        return mSelectedRemoveListItem;
    }

}
