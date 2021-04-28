package com.autumntechcreation.click4panditcustomer.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.BR;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageListModel;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageViewModel;

import java.util.List;

public class ChoosePackageListAdapter extends RecyclerView.Adapter<ChoosePackageListAdapter.MyViewHolder>{
    private int layoutId;
    private List<ChoosePackageListModel> mChoosePackageListModel;
    private ChoosePackageViewModel mChoosePackageViewModel;


    public ChoosePackageListAdapter(int layoutId, ChoosePackageViewModel choosePackageViewModel){
        this.layoutId=layoutId;
        this.mChoosePackageViewModel=choosePackageViewModel;

    }
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, viewGroup, false);

        return new MyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(mChoosePackageViewModel, i);


    }

    @Override
    public int getItemCount() {
        return mChoosePackageListModel == null ? 0 : mChoosePackageListModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(ChoosePackageViewModel viewModel, Integer position) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();

        }
    }


    public void setChoosePackageList(List<ChoosePackageListModel> listChoosePackageListModel) {
        this.mChoosePackageListModel = listChoosePackageListModel;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

}
