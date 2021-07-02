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
import com.autumntechcreation.click4panditcustomer.ui.wishlist.WishListItemModel;
import com.autumntechcreation.click4panditcustomer.ui.wishlist.WishListViewModel;

import java.util.ArrayList;
import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.MyViewHolder> {
    private int layoutId;
    private WishListViewModel mWishListViewModel;
    private List<WishListItemModel> mWishListItemModel=new ArrayList<>();
    public WishListAdapter(int layoutId, WishListViewModel wishListViewModel){
        this.layoutId=layoutId;
        this.mWishListViewModel=wishListViewModel;


    }
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }
    @NonNull
    @Override
    public WishListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, viewGroup, false);

        return new WishListAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.MyViewHolder holder, int i) {
        holder.bind(mWishListViewModel,i);
    }

    @Override
    public int getItemCount() {
        return mWishListItemModel == null ? 0 : mWishListItemModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(WishListViewModel viewModel, Integer position) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }
    public void setWishListingPage(List<WishListItemModel> wishListItemModelList) {
        this.mWishListItemModel = wishListItemModelList;
    }
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

}
