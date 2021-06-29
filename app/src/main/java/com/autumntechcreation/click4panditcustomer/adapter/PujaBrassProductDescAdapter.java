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
import com.autumntechcreation.click4panditcustomer.ui.pujabrassitemdetails.PujaBrassItemDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujabrassitemdetails.PujaBrassProductDescModel;

import java.util.ArrayList;
import java.util.List;

public class PujaBrassProductDescAdapter extends RecyclerView.Adapter<PujaBrassProductDescAdapter.MyViewHolder> {
    private int layoutId;
    PujaBrassItemDetailsViewModel mPujaBrassItemDetailsViewModel;
    private List<PujaBrassProductDescModel> mPujaBrassProductDescModel= new ArrayList<>();


    public PujaBrassProductDescAdapter(int layoutId, PujaBrassItemDetailsViewModel pujaBrassItemDetailsViewModel) {
        this.layoutId = layoutId;
        this.mPujaBrassItemDetailsViewModel = pujaBrassItemDetailsViewModel;


    }
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }
    @NonNull
    @Override
    public PujaBrassProductDescAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, viewGroup, false);

        return new PujaBrassProductDescAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PujaBrassProductDescAdapter.MyViewHolder holder, int i) {
        holder.bind(mPujaBrassItemDetailsViewModel, i);
    }

    @Override
    public int getItemCount() {
        return mPujaBrassProductDescModel == null ? 0 : mPujaBrassProductDescModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(PujaBrassItemDetailsViewModel viewModel, Integer position) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }

    public void setPujaBrassProductDescListingPage(List<PujaBrassProductDescModel> pujaBrassProductDescModelList) {
        this.mPujaBrassProductDescModel = pujaBrassProductDescModelList;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

}
