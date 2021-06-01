package com.autumntechcreation.click4panditcustomer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.BR;
import com.autumntechcreation.click4panditcustomer.ui.address.AddressListModel;
import com.autumntechcreation.click4panditcustomer.ui.address.AddressViewModel;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderListModel;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddressBookListadapter extends RecyclerView.Adapter<AddressBookListadapter.MyViewHolder>{
    private int layoutId;
    private AddressViewModel mAddressViewModel;
    private List<AddressListModel> mAddressListModel=new ArrayList<>();

    public AddressBookListadapter(int layoutId,AddressViewModel addressViewModel){
        this.layoutId=layoutId;
        this.mAddressViewModel=addressViewModel;

    }
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }
    @NonNull
    @Override
    public AddressBookListadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, viewGroup, false);

        return new AddressBookListadapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressBookListadapter.MyViewHolder holder, int i) {
        holder.bind(mAddressViewModel,i);
    }

    @Override
    public int getItemCount() {
        return mAddressListModel == null ? 0 : mAddressListModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(AddressViewModel addressViewModel, Integer position) {

            binding.setVariable(BR.viewModel, addressViewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }

    public void setAddressListing(List<AddressListModel> addressListModel) {
        this.mAddressListModel = addressListModel;
    }
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }
}
