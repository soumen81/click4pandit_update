package com.autumntechcreation.click4panditcustomer.ui.pujabrassitemdetails;

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
import com.autumntechcreation.click4panditcustomer.databinding.FragmentPujabrassitemdetailsBinding;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentPujabrassitemlistBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemsdetails.PujaBoxItemDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemsdetails.PujaBoxItemDetailsFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemsdetails.PujaBoxItemDetailsFragmentDirections;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemsdetails.PujaBoxItemDetailsViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemProductDescModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class PujaBrassItemDetailsFragment  extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentPujabrassitemdetailsBinding mFragmentPujabrassitemdetailsBinding;
    PujaBrassItemDetailsViewModel mPujaBrassItemDetailsViewModel;
    private View mView;
    NavController navController;
    String brassId;
    private List<PujaBrassProductDescModel> alPujaBrassProductDescModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentPujabrassitemdetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pujabrassitemdetails, container, false);
        mFragmentPujabrassitemdetailsBinding.setLifecycleOwner(this);

        brassId= PujaBrassItemDetailsFragmentArgs.fromBundle(getArguments()).getPujaBrassId();
        Log.e("BrassId",""+brassId);

        return mFragmentPujabrassitemdetailsBinding.getRoot();
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

        mPujaBrassItemDetailsViewModel = ViewModelProviders.of(PujaBrassItemDetailsFragment.this, viewModelFactory).get(PujaBrassItemDetailsViewModel.class);
        mFragmentPujabrassitemdetailsBinding.setPujaBrassItemDetailsViewModel(mPujaBrassItemDetailsViewModel);
        RecyclerView chartDetailsRecyclerviewView=mFragmentPujabrassitemdetailsBinding.rvProductDescListForPujaBrass;
        chartDetailsRecyclerviewView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        chartDetailsRecyclerviewView.setHasFixedSize(true);
        mPujaBrassItemDetailsViewModel.init();
        setProductBrassDescDetails();

        mFragmentPujabrassitemdetailsBinding.tvPujaBrassWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PujaBrassItemDetailsFragmentDirections.ActionPujaBrassItemDetailsFragmentToWishListFragment action=
                        PujaBrassItemDetailsFragmentDirections.actionPujaBrassItemDetailsFragmentToWishListFragment();
                action.setProductwishlistName("Ghati");
                Navigation.findNavController(mView).navigate(action);
            }
        });
    }


    private void setProductBrassDescDetails(){

        alPujaBrassProductDescModel=new ArrayList<>();
        PujaBrassProductDescModel pujaBrassProductDescModel1=new PujaBrassProductDescModel();
        pujaBrassProductDescModel1.setPujaBrassItemProductName("Haldi");
        pujaBrassProductDescModel1.setPujaBrassItemProductWeight("25");
        pujaBrassProductDescModel1.setPujaBrassItemProductUnit("Grams");
        alPujaBrassProductDescModel.add(pujaBrassProductDescModel1);

        PujaBrassProductDescModel pujaBrassProductDescModel2=new PujaBrassProductDescModel();
        pujaBrassProductDescModel2.setPujaBrassItemProductName("Elaichi");
        pujaBrassProductDescModel2.setPujaBrassItemProductWeight("10");
        pujaBrassProductDescModel2.setPujaBrassItemProductUnit("Grams");
        alPujaBrassProductDescModel.add(pujaBrassProductDescModel2);

        PujaBrassProductDescModel pujaBrassProductDescModel3=new PujaBrassProductDescModel();
        pujaBrassProductDescModel3.setPujaBrassItemProductName("Ganga Jal");
        pujaBrassProductDescModel3.setPujaBrassItemProductWeight("100");
        pujaBrassProductDescModel3.setPujaBrassItemProductUnit("Mili Litres");
        alPujaBrassProductDescModel.add(pujaBrassProductDescModel3);
        mPujaBrassItemDetailsViewModel.setPujaProductDescListingAdapter(alPujaBrassProductDescModel);
    }

}
