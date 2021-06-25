package com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentPujaboxitemlistBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitFragment;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitListingModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class PujaBoxItemListFragment extends Fragment implements Injectable {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentPujaboxitemlistBinding mFragmentPujaboxitemlistBinding;
    PujaBoxItemListViewModel mPujaBoxItemListViewModel;
    private View mView;
    NavController navController;
    String pujaBoxId;
    private List<PujaBoxItemListingModel> alPujaBoxItemKitListingModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentPujaboxitemlistBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pujaboxitemlist, container, false);
        mFragmentPujaboxitemlistBinding.setLifecycleOwner(this);

        pujaBoxId= PujaBoxItemListFragmentArgs.fromBundle(getArguments()).getPujaBoxId();
        Log.e("pujaBoxId",""+pujaBoxId);
        return mFragmentPujaboxitemlistBinding.getRoot();
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

        mPujaBoxItemListViewModel = ViewModelProviders.of(PujaBoxItemListFragment.this, viewModelFactory).get(PujaBoxItemListViewModel.class);
        mFragmentPujaboxitemlistBinding.setPujaBoxItemListViewModel(mPujaBoxItemListViewModel);
        mPujaBoxItemListViewModel.init();
        setData();
    }

    private void setData(){
        alPujaBoxItemKitListingModel=new ArrayList<>();

        PujaBoxItemListingModel pujaBoxItemListingModel1=new PujaBoxItemListingModel();
        pujaBoxItemListingModel1.setPujaBoxItemKitName("Ghanti");
        pujaBoxItemListingModel1.setPujaBoxItemKitPrice("Price:1500");
        pujaBoxItemListingModel1.setIconPujaBoxImage(ContextCompat.getDrawable(getActivity(), R.drawable.ghanti));
        alPujaBoxItemKitListingModel.add(pujaBoxItemListingModel1);

        PujaBoxItemListingModel pujaBoxItemListingModel2=new PujaBoxItemListingModel();
        pujaBoxItemListingModel2.setPujaBoxItemKitName("Ghati");
        pujaBoxItemListingModel2.setPujaBoxItemKitPrice("Price:1200");
        pujaBoxItemListingModel2.setIconPujaBoxImage(ContextCompat.getDrawable(getActivity(), R.drawable.ghati));
        alPujaBoxItemKitListingModel.add(pujaBoxItemListingModel2);

        PujaBoxItemListingModel pujaBoxItemListingModel3=new PujaBoxItemListingModel();
        pujaBoxItemListingModel3.setPujaBoxItemKitName("Thali");
        pujaBoxItemListingModel3.setPujaBoxItemKitPrice("Price:2500");
        pujaBoxItemListingModel3.setIconPujaBoxImage(ContextCompat.getDrawable(getActivity(), R.drawable.thali));
        alPujaBoxItemKitListingModel.add(pujaBoxItemListingModel3);

        PujaBoxItemListingModel pujaBoxItemListingModel4=new PujaBoxItemListingModel();
        pujaBoxItemListingModel4.setPujaBoxItemKitName("Brass");
        pujaBoxItemListingModel4.setPujaBoxItemKitPrice("Price:1900");
        pujaBoxItemListingModel4.setIconPujaBoxImage(ContextCompat.getDrawable(getActivity(), R.drawable.brass));
        alPujaBoxItemKitListingModel.add(pujaBoxItemListingModel4);

        PujaBoxItemListingModel pujaBoxItemListingModel5=new PujaBoxItemListingModel();
        pujaBoxItemListingModel5.setPujaBoxItemKitName("Astramongola");
        pujaBoxItemListingModel5.setPujaBoxItemKitPrice("Price:3500");
        pujaBoxItemListingModel5.setIconPujaBoxImage(ContextCompat.getDrawable(getActivity(), R.drawable.astromongola));
        alPujaBoxItemKitListingModel.add(pujaBoxItemListingModel5);

        mPujaBoxItemListViewModel.setPujaBoxListingAdapter(alPujaBoxItemKitListingModel);
    }
}
