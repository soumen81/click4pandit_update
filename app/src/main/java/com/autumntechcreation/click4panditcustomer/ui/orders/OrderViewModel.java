package com.autumntechcreation.click4panditcustomer.ui.orders;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.adapter.OrderListingAdapter;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageListModel;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;
import com.autumntechcreation.click4panditcustomer.util.Static;

import java.util.List;

import javax.inject.Inject;

public class OrderViewModel extends ViewModel {
    OrderListingAdapter mOrderListingAdapter;
    private MutableLiveData<List<OrderListModel>> mOrderListModel;
    private SingleLiveEvent<Integer> mSelectedOrderListItem=new SingleLiveEvent<>();
    public LiveData<Resource<List<OrderListModel>>> mOrderListResponse;
    OrderRepository mOrderRepository;
    @Inject
    public OrderViewModel(OrderRepository orderRepository) {
        this.mOrderRepository=orderRepository;
    }
    public void init(){
        mOrderListingAdapter=new OrderListingAdapter(R.layout.singlerow_orderlist,this);
        this.mOrderListingAdapter.notifyDataSetChanged();
    }


    public LiveData<Resource<List<OrderListModel>>> getOrderList(String orderDateCriteria) {
        mOrderListResponse = new MutableLiveData<>();
        mOrderListResponse = mOrderRepository.getOrderList(orderDateCriteria);
        return mOrderListResponse;
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



    public String getCustOrderNo(int position){
        List<OrderListModel> list = mOrderListModel.getValue();
        return "" + list.get(position).getCustOrdNo();
    }public String getPujaSubCategoryDesc(int position){
        List<OrderListModel> list = mOrderListModel.getValue();
        return "" + list.get(position).getPujaSubCtgryDscr();
    }public String getCustOrderDate(int position){
        List<OrderListModel> list = mOrderListModel.getValue();
        return "" + Static.convertToDate(list.get(position).getCustOrdDt());
    }public String getBkgPkgTotalAmount(int position){
        List<OrderListModel> list = mOrderListModel.getValue();
        return "" + list.get(position).getCustBkgPkgTotalAmt();
    }public String getBkgStatusDesc(int position){
        List<OrderListModel> list = mOrderListModel.getValue();
        if(list.get(position).getBkgStsDscr().equals("ACCEPT")){
            return "PAID";
        }else {
            return "" + list.get(position).getBkgStsDscr();
        }
    }
}
