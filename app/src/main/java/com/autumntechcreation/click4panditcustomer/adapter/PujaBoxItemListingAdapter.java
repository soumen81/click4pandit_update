package com.autumntechcreation.click4panditcustomer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.BR;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListingModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitListingModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitViewModel;

import java.util.ArrayList;
import java.util.List;

public class PujaBoxItemListingAdapter extends RecyclerView.Adapter<PujaBoxItemListingAdapter.MyViewHolder> {
    private int layoutId;
    private PujaBoxItemListViewModel mPujaBoxItemListViewModel;
    private List<PujaBoxItemListingModel> mPujaBoxItemListingModel=new ArrayList<>();
    public PujaBoxItemListingAdapter(int layoutId, PujaBoxItemListViewModel pujaBoxItemListViewModel){
        this.layoutId=layoutId;
        this.mPujaBoxItemListViewModel=pujaBoxItemListViewModel;


    }
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }
    @NonNull
    @Override
    public PujaBoxItemListingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, viewGroup, false);

        return new PujaBoxItemListingAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PujaBoxItemListingAdapter.MyViewHolder holder, int i) {
        holder.bind(mPujaBoxItemListViewModel,i);
    }

    @Override
    public int getItemCount() {
        return mPujaBoxItemListingModel == null ? 0 : mPujaBoxItemListingModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(PujaBoxItemListViewModel viewModel, Integer position) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }
    public void setPujaBoxKitListingPage(List<PujaBoxItemListingModel> pujaBoxItemListingModelList) {
        this.mPujaBoxItemListingModel = pujaBoxItemListingModelList;
    }
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }
}
