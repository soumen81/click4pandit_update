package com.autumntechcreation.click4panditcustomer.ui.pujaboxitemsdetails;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentPujaboxitemdetailsBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemKitDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemKitDetailsFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemKitDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemProductDescModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class PujaBoxItemDetailsFragment extends Fragment implements Injectable {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentPujaboxitemdetailsBinding mFragmentPujaboxitemdetailsBinding;
    PujaBoxItemDetailsViewModel pujaBoxItemDetailsViewModel;
    private View mView;
    NavController navController;
    String boxId;
    private List<PujaBoxItemProductDescModel> alPujaBoxItemProductDescModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentPujaboxitemdetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pujaboxitemdetails, container, false);
        mFragmentPujaboxitemdetailsBinding.setLifecycleOwner(this);

        boxId= PujaBoxItemDetailsFragmentArgs.fromBundle(getArguments()).getPujaBoxId();
        Log.e("boxId",""+boxId);

        return mFragmentPujaboxitemdetailsBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        navController=findNavController(mView);
        ((MainActivity) getActivity()).setToolbar(false,true,false,true);


    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pujaBoxItemDetailsViewModel = ViewModelProviders.of(PujaBoxItemDetailsFragment.this, viewModelFactory).get(PujaBoxItemDetailsViewModel.class);
        mFragmentPujaboxitemdetailsBinding.setPujaBoxItemDetailsViewModel(pujaBoxItemDetailsViewModel);

        RecyclerView chartDetailsRecyclerviewView=mFragmentPujaboxitemdetailsBinding.rvProductDescListForPujaBox;
        chartDetailsRecyclerviewView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        chartDetailsRecyclerviewView.setHasFixedSize(true);
        pujaBoxItemDetailsViewModel.init();
        setProductBoxDescDetails();
    }
    private void setProductBoxDescDetails(){

        alPujaBoxItemProductDescModel=new ArrayList<>();
        PujaBoxItemProductDescModel pujaBoxItemProductDescModel1=new PujaBoxItemProductDescModel();
        pujaBoxItemProductDescModel1.setPujaBoxItemProductName("Madhu");
        pujaBoxItemProductDescModel1.setPujaBoxItemProductWeight("1");
        pujaBoxItemProductDescModel1.setPujaBoxItemProductUnit("Unit");
        alPujaBoxItemProductDescModel.add(pujaBoxItemProductDescModel1);

        PujaBoxItemProductDescModel pujaBoxItemProductDescModel2=new PujaBoxItemProductDescModel();
        pujaBoxItemProductDescModel2.setPujaBoxItemProductName("Supari");
        pujaBoxItemProductDescModel2.setPujaBoxItemProductWeight("20");
        pujaBoxItemProductDescModel2.setPujaBoxItemProductUnit("Pieces");
        alPujaBoxItemProductDescModel.add(pujaBoxItemProductDescModel2);

        PujaBoxItemProductDescModel pujaBoxItemProductDescModel3=new PujaBoxItemProductDescModel();
        pujaBoxItemProductDescModel3.setPujaBoxItemProductName("Kalas");
        pujaBoxItemProductDescModel3.setPujaBoxItemProductWeight("1");
        pujaBoxItemProductDescModel3.setPujaBoxItemProductUnit("Unit");
        alPujaBoxItemProductDescModel.add(pujaBoxItemProductDescModel3);
        pujaBoxItemDetailsViewModel.setPujaBoxProductListingAdapter(alPujaBoxItemProductDescModel);
    }
}
