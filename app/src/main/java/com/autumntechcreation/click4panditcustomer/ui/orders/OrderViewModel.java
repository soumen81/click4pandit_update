package com.autumntechcreation.click4panditcustomer.ui.orders;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.adapter.OrderListingAdapter;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

public class OrderViewModel extends ViewModel {
    OrderListingAdapter mOrderListingAdapter;
    private MutableLiveData<List<OrderListModel>> mOrderListModel;
    private SingleLiveEvent<Integer> mSelectedOrderListItem=new SingleLiveEvent<>();

    @Inject
    public OrderViewModel() {
    }
    public void init(){
        mOrderListingAdapter=new OrderListingAdapter(R.layout.singlerow_orderlist,this);
        this.mOrderListingAdapter.notifyDataSetChanged();
    }

    public OrderListingAdapter getOrderListAdapter(){
        return mOrderListingAdapter;
    }



    public void setOrderList(List<OrderListModel>listOrderListModel){
        mOrderListModel=new MutableLiveData<>();
        mOrderListModel.setValue(listOrderListModel);
        this.mOrderListingAdapter.setOrderListing(listOrderListModel);
        this.mOrderListingAdapter.notifyDataSetChanged();
    }

    public void onClickOrderList(View view, int pos){
        Log.e("ClickPosition",view.getId()+"POSITION:"+Integer.toString(pos));
        mSelectedOrderListItem.setValue(pos);
    }
    public SingleLiveEvent<Integer> getOrderListItemsClick(){
        return mSelectedOrderListItem;
    }
}
