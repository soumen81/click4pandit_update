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
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemKitDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemProductDescModel;

import java.util.ArrayList;
import java.util.List;

public class PujaItemProductDescAdapter extends RecyclerView.Adapter<PujaItemProductDescAdapter.MyViewHolder> {
    PujaItemKitDetailsViewModel mPujaItemKitDetailsViewModel;
    private int layoutId;
    private List<PujaItemProductDescModel> mPujaItemProductDescModel = new ArrayList<>();

    public PujaItemProductDescAdapter(int layoutId, PujaItemKitDetailsViewModel pujaItemKitDetailsViewModel) {
        this.layoutId = layoutId;
        this.mPujaItemKitDetailsViewModel = pujaItemKitDetailsViewModel;


    }
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }
    @NonNull
    @Override
    public PujaItemProductDescAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, viewGroup, false);

        return new PujaItemProductDescAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PujaItemProductDescAdapter.MyViewHolder holder, int i) {
        holder.bind(mPujaItemKitDetailsViewModel, i);
    }

    @Override
    public int getItemCount() {
        return mPujaItemProductDescModel == null ? 0 : mPujaItemProductDescModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(PujaItemKitDetailsViewModel viewModel, Integer position) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }

    public void setPujaItemProductDescListingPage(List<PujaItemProductDescModel> pujaItemProductDescModelList) {
        this.mPujaItemProductDescModel = pujaItemProductDescModelList;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }
}

