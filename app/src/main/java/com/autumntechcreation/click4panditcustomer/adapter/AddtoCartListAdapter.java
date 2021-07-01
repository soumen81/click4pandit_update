package com.autumntechcreation.click4panditcustomer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.BR;
import com.autumntechcreation.click4panditcustomer.ui.addtocart.AddtoCartItemModel;
import com.autumntechcreation.click4panditcustomer.ui.addtocart.AddtoCartViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListingModel;

import java.util.ArrayList;
import java.util.List;

public class AddtoCartListAdapter extends RecyclerView.Adapter<AddtoCartListAdapter.MyViewHolder> {
    private int layoutId;
    private AddtoCartViewModel mAddtoCartViewModel;
    private List<AddtoCartItemModel> mAddtoCartItemModel=new ArrayList<>();
    public AddtoCartListAdapter(int layoutId, AddtoCartViewModel addtoCartViewModel){
        this.layoutId=layoutId;
        this.mAddtoCartViewModel=addtoCartViewModel;


    }
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }
    @NonNull
    @Override
    public AddtoCartListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, viewGroup, false);

        return new AddtoCartListAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddtoCartListAdapter.MyViewHolder holder, int i) {
        holder.bind(mAddtoCartViewModel,i);
    }

    @Override
    public int getItemCount() {
        return mAddtoCartItemModel == null ? 0 : mAddtoCartItemModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(AddtoCartViewModel viewModel, Integer position) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }
    public void setAddtoCartListingPage(List<AddtoCartItemModel> addtoCartItemModelList) {
        this.mAddtoCartItemModel = addtoCartItemModelList;
    }
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }
}


