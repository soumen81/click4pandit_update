package com.autumntechcreation.click4panditcustomer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.BR;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemsdetails.PujaBoxItemDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemsdetails.PujaBoxItemProductDescModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemKitDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemProductDescModel;

import java.util.ArrayList;
import java.util.List;

public class PujaBoxProductDescAdapter extends RecyclerView.Adapter<PujaBoxProductDescAdapter.MyViewHolder> {
    PujaBoxItemDetailsViewModel mPujaBoxItemDetailsViewModel;
    private int layoutId;

    private List<PujaBoxItemProductDescModel> mPujaBoxItemProductDescModel = new ArrayList<>();

    public PujaBoxProductDescAdapter(int layoutId, PujaBoxItemDetailsViewModel pujaBoxItemDetailsViewModel) {
        this.layoutId = layoutId;
        this.mPujaBoxItemDetailsViewModel = pujaBoxItemDetailsViewModel;


    }
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }
    @NonNull
    @Override
    public PujaBoxProductDescAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, viewGroup, false);

        return new PujaBoxProductDescAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PujaBoxProductDescAdapter.MyViewHolder holder, int i) {
        holder.bind(mPujaBoxItemDetailsViewModel, i);
    }

    @Override
    public int getItemCount() {
        return mPujaBoxItemProductDescModel == null ? 0 : mPujaBoxItemProductDescModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(PujaBoxItemDetailsViewModel viewModel, Integer position) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }

    public void setPujaBoxProductDescListingPage(List<PujaBoxItemProductDescModel> pujaBoxItemProductDescModelList) {
        this.mPujaBoxItemProductDescModel = pujaBoxItemProductDescModelList;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }
}



