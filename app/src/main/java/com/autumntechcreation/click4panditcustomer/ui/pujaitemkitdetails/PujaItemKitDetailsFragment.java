package com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails;

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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentPujaitemkitdetailsBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileFragment;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujabrassitemdetails.PujaBrassItemDetailsFragmentDirections;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class PujaItemKitDetailsFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    PujaItemKitDetailsViewModel mPujaItemKitDetailsViewModel;
    FragmentPujaitemkitdetailsBinding mFragmentPujaitemkitdetailsBinding;
    private View mView;
    NavController navController;
    String productName;
    private List<PujaItemProductDescModel> alPujaItemProductDescModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentPujaitemkitdetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pujaitemkitdetails, container, false);
        mFragmentPujaitemkitdetailsBinding.setLifecycleOwner(this);

        productName= PujaItemKitDetailsFragmentArgs.fromBundle(getArguments()).getProductName();
        Log.e("productName",""+productName);

        return mFragmentPujaitemkitdetailsBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        navController=findNavController(mView);
        ((MainActivity) getActivity()).setToolbar(true,true,false,true);


    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPujaItemKitDetailsViewModel = ViewModelProviders.of(PujaItemKitDetailsFragment.this, viewModelFactory).get(PujaItemKitDetailsViewModel.class);
        mFragmentPujaitemkitdetailsBinding.setPujaItemKitDetailsViewModel(mPujaItemKitDetailsViewModel);


        RecyclerView chartDetailsRecyclerviewView=mFragmentPujaitemkitdetailsBinding.rvProductDescListForItemkit;
        chartDetailsRecyclerviewView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        chartDetailsRecyclerviewView.setHasFixedSize(true);
        mPujaItemKitDetailsViewModel.init();
        setProductDescDetails();


        mFragmentPujaitemkitdetailsBinding.tvPujaItemWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PujaItemKitDetailsFragmentDirections.ActionPujaItemKitDetailsFragmentToWishListFragment action=
                        PujaItemKitDetailsFragmentDirections.actionPujaItemKitDetailsFragmentToWishListFragment();
                action.setProductwishlistName("Ghati");
                Navigation.findNavController(mView).navigate(action);
            }
        });
    }

    private void setProductDescDetails(){

        alPujaItemProductDescModel=new ArrayList<>();
        PujaItemProductDescModel pujaItemProductDescModel1=new PujaItemProductDescModel();
        pujaItemProductDescModel1.setPujaItemProductName("Abeer");
        pujaItemProductDescModel1.setPujaItemProductWeight("25");
        pujaItemProductDescModel1.setPujaItemProductUnit("Grams");
        alPujaItemProductDescModel.add(pujaItemProductDescModel1);

        PujaItemProductDescModel pujaItemProductDescModel2=new PujaItemProductDescModel();
        pujaItemProductDescModel2.setPujaItemProductName("Gulal");
        pujaItemProductDescModel2.setPujaItemProductWeight("20");
        pujaItemProductDescModel2.setPujaItemProductUnit("Grams");
        alPujaItemProductDescModel.add(pujaItemProductDescModel2);

        PujaItemProductDescModel pujaItemProductDescModel3=new PujaItemProductDescModel();
        pujaItemProductDescModel3.setPujaItemProductName("Agarbati");
        pujaItemProductDescModel3.setPujaItemProductWeight("10");
        pujaItemProductDescModel3.setPujaItemProductUnit("Piece");
        alPujaItemProductDescModel.add(pujaItemProductDescModel3);
        mPujaItemKitDetailsViewModel.setPujaProductDescListingAdapter(alPujaItemProductDescModel);
    }
}
