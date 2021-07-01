package com.autumntechcreation.click4panditcustomer.ui.pujabrassitemlist;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentPujabrassitemlistBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListFragment;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListingModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class PujaBrassItemListingFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentPujabrassitemlistBinding mFragmentPujabrassitemlistBinding;
    private View mView;
    NavController navController;
    PujaBrassItemListingViewModel mPujaBrassItemListingViewModel;
    private List<PujaBrassItemListingModel> alPujaBrassItemListingModel;
    String pujaBrassId="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentPujabrassitemlistBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pujabrassitemlist, container, false);
        mFragmentPujabrassitemlistBinding.setLifecycleOwner(this);
        pujaBrassId= PujaBrassItemListingFragmentArgs.fromBundle(getArguments()).getPujaBrassId();
        Log.e("pujaBrassId",""+pujaBrassId);

        return mFragmentPujabrassitemlistBinding.getRoot();
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

        mPujaBrassItemListingViewModel = ViewModelProviders.of(PujaBrassItemListingFragment.this, viewModelFactory).get(PujaBrassItemListingViewModel.class);
        mFragmentPujabrassitemlistBinding.setPujaBrassItemListViewModel(mPujaBrassItemListingViewModel);
        mPujaBrassItemListingViewModel.init();
        setData();

        mPujaBrassItemListingViewModel.getSelectedPujaBrassBuyNowListItem().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                PujaBrassItemListingFragmentDirections.ActionPujaBrassItemListingFragmentToPujaBrassItemDetailsFragment action=
                        PujaBrassItemListingFragmentDirections.actionPujaBrassItemListingFragmentToPujaBrassItemDetailsFragment();
                action.setPujaBrassId("3");
                Navigation.findNavController(mView).navigate(action);
            }
        });

        mPujaBrassItemListingViewModel.getSelectedPujaBrassAddtoCart().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                PujaBrassItemListingFragmentDirections.ActionPujaBrassItemListingFragmentToAddtoCartFragment action=
                        PujaBrassItemListingFragmentDirections.actionPujaBrassItemListingFragmentToAddtoCartFragment();
                action.setProductAddtoCartName("Ghati");
                Navigation.findNavController(mView).navigate(action);
            }
        });

    }
    private void setData(){
        alPujaBrassItemListingModel=new ArrayList<>();

        PujaBrassItemListingModel pujaBrassItemListingModel1=new PujaBrassItemListingModel();
        pujaBrassItemListingModel1.setPujaBrassItemKitName("Ghanti");
        pujaBrassItemListingModel1.setPujaBrassItemKitPrice("Price:1500");
        pujaBrassItemListingModel1.setIconPujaBrassImage(ContextCompat.getDrawable(getActivity(), R.drawable.ghanti));
        alPujaBrassItemListingModel.add(pujaBrassItemListingModel1);

        PujaBrassItemListingModel pujaBrassItemListingModel2=new PujaBrassItemListingModel();
        pujaBrassItemListingModel2.setPujaBrassItemKitName("Ghati");
        pujaBrassItemListingModel2.setPujaBrassItemKitPrice("Price:1200");
        pujaBrassItemListingModel2.setIconPujaBrassImage(ContextCompat.getDrawable(getActivity(), R.drawable.ghati));
        alPujaBrassItemListingModel.add(pujaBrassItemListingModel2);

        PujaBrassItemListingModel pujaBrassItemListingModel3=new PujaBrassItemListingModel();
        pujaBrassItemListingModel3.setPujaBrassItemKitName("Thali");
        pujaBrassItemListingModel3.setPujaBrassItemKitPrice("Price:2500");
        pujaBrassItemListingModel3.setIconPujaBrassImage(ContextCompat.getDrawable(getActivity(), R.drawable.thali));
        alPujaBrassItemListingModel.add(pujaBrassItemListingModel3);

        PujaBrassItemListingModel pujaBrassItemListingModel4=new PujaBrassItemListingModel();
        pujaBrassItemListingModel4.setPujaBrassItemKitName("Brass");
        pujaBrassItemListingModel4.setPujaBrassItemKitPrice("Price:1900");
        pujaBrassItemListingModel4.setIconPujaBrassImage(ContextCompat.getDrawable(getActivity(), R.drawable.brass));
        alPujaBrassItemListingModel.add(pujaBrassItemListingModel4);

        PujaBrassItemListingModel pujaBrassItemListingModel5=new PujaBrassItemListingModel();
        pujaBrassItemListingModel5.setPujaBrassItemKitName("Astramongola");
        pujaBrassItemListingModel5.setPujaBrassItemKitPrice("Price:3500");
        pujaBrassItemListingModel5.setIconPujaBrassImage(ContextCompat.getDrawable(getActivity(), R.drawable.astromongola));
        alPujaBrassItemListingModel.add(pujaBrassItemListingModel5);

        mPujaBrassItemListingViewModel.setPujaBrassListingAdapter(alPujaBrassItemListingModel);
    }
}
