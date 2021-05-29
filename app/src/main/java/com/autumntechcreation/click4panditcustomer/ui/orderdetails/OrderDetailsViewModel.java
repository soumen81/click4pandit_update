package com.autumntechcreation.click4panditcustomer.ui.orderdetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.adapter.OrderListingAdapter;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderListModel;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

public class OrderDetailsViewModel extends ViewModel {
    OrderDetailsRepository mOrderDetailsRepository;

    @Inject
    public OrderDetailsViewModel(OrderDetailsRepository orderDetailsRepository) {
        this.mOrderDetailsRepository=orderDetailsRepository;
    }


}
