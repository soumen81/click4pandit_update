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
import com.autumntechcreation.click4panditcustomer.ui.pujabrassitemlist.PujaBrassItemListingModel;
import com.autumntechcreation.click4panditcustomer.ui.pujabrassitemlist.PujaBrassItemListingViewModel;

import java.util.ArrayList;
import java.util.List;

public class PujaBrassItemListAdapter extends RecyclerView.Adapter<PujaBrassItemListAdapter.MyViewHolder> {
    private int layoutId;
    private PujaBrassItemListingViewModel mPujaBrassItemListingViewModel;
    private List<PujaBrassItemListingModel> mPujaBrassItemListingModel=new ArrayList<>();

    public PujaBrassItemListAdapter(int layoutId, PujaBrassItemListingViewModel pujaBrassItemListingViewModel){
        this.layoutId=layoutId;
        this.mPujaBrassItemListingViewModel=pujaBrassItemListingViewModel;


    }
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }
    @NonNull
    @Override
    public PujaBrassItemListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, viewGroup, false);

        return new PujaBrassItemListAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PujaBrassItemListAdapter.MyViewHolder holder, int i) {
        holder.bind(mPujaBrassItemListingViewModel,i);
    }

    @Override
    public int getItemCount() {
        return mPujaBrassItemListingModel == null ? 0 : mPujaBrassItemListingModel.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(PujaBrassItemListingViewModel viewModel, Integer position) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }
    public void setPujaBrassListingPage(List<PujaBrassItemListingModel> pujaBrassItemListingModelList) {
        this.mPujaBrassItemListingModel = pujaBrassItemListingModelList;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }
}
