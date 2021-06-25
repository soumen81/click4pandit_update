package com.autumntechcreation.click4panditcustomer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.BR;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitListingModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitViewModel;

import java.util.ArrayList;
import java.util.List;

public class PujaItemKitListingAdapter extends RecyclerView.Adapter<PujaItemKitListingAdapter.MyViewHolder> {
    private int layoutId;
    private PujaItemKitViewModel mPujaItemKitViewModel;
    private List<PujaItemKitListingModel> mPujaItemKitListingModel=new ArrayList<>();

    public PujaItemKitListingAdapter(int layoutId, PujaItemKitViewModel pujaItemKitViewModel){
        this.layoutId=layoutId;
        this.mPujaItemKitViewModel=pujaItemKitViewModel;


    }
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }
    @NonNull
    @Override
    public PujaItemKitListingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, viewGroup, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PujaItemKitListingAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(mPujaItemKitViewModel,i);
    }

    @Override
    public int getItemCount() {
        return mPujaItemKitListingModel == null ? 0 : mPujaItemKitListingModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(PujaItemKitViewModel viewModel, Integer position) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }
    public void setPujaItemKitListingPage(List<PujaItemKitListingModel> pujaItemKitListingModel) {
        this.mPujaItemKitListingModel = pujaItemKitListingModel;
    }
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }
}
