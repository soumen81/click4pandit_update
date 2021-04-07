package com.autumntechcreation.click4panditcustomer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.autumntechcreation.click4panditcustomer.BR;
import androidx.recyclerview.widget.RecyclerView;


import com.autumntechcreation.click4panditcustomer.ui.orders.OrderListModel;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrderListingAdapter extends RecyclerView.Adapter<OrderListingAdapter.MyViewHolder>{
    private int layoutId;
    private OrderViewModel mOrderViewModel;
    private List<OrderListModel> mOrderListModel=new ArrayList<>();


    public OrderListingAdapter(int layoutId,OrderViewModel orderViewModel){
        this.layoutId=layoutId;
        this.mOrderViewModel=orderViewModel;

    }
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @NonNull
    @Override
    public OrderListingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, viewGroup, false);

        return new OrderListingAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListingAdapter.MyViewHolder holder, int i) {
        holder.bind(mOrderViewModel,i);
    }

    @Override
    public int getItemCount() {
        return mOrderListModel == null ? 0 : mOrderListModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(OrderViewModel orderViewModel, Integer position) {

            binding.setVariable(BR.viewModel, orderViewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }


    public void setOrderListing(List<OrderListModel> orderListModelList) {
        this.mOrderListModel = orderListModelList;
    }
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }
}
