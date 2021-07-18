package com.autumntechcreation.click4panditcustomer.ui.pujaitemkit;

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
import com.autumntechcreation.click4panditcustomer.databinding.FragmentPujaitemkitBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileFragment;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class PujaItemKitFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentPujaitemkitBinding mFragmentPujaitemkitBinding;
    private View mView;
    NavController navController;
    PujaItemKitViewModel mPujaItemKitViewModel;
    String shopId;
    private List<PujaItemKitListingModel> alPujaItemKitListingModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentPujaitemkitBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pujaitemkit, container, false);
        mFragmentPujaitemkitBinding.setLifecycleOwner(this);

        shopId= PujaItemKitFragmentArgs.fromBundle(getArguments()).getShopId();
        Log.e("shopId",""+shopId);
        return mFragmentPujaitemkitBinding.getRoot();
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

        mPujaItemKitViewModel = ViewModelProviders.of(PujaItemKitFragment.this, viewModelFactory).get(PujaItemKitViewModel.class);
        mFragmentPujaitemkitBinding.setPujaItemKitViewModel(mPujaItemKitViewModel);
        mPujaItemKitViewModel.init();
        setData();

        mPujaItemKitViewModel.getSelectedBuyNowListItem().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                PujaItemKitFragmentDirections.ActionPujaItemKitFragmentToPujaItemKitDetailsFragment action=
                        PujaItemKitFragmentDirections.actionPujaItemKitFragmentToPujaItemKitDetailsFragment();
                action.setProductName("Brass");
                Navigation.findNavController(mView).navigate(action);
            }
        });
        mPujaItemKitViewModel.getSelectedPujaItemKitAddtoCart().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                PujaItemKitFragmentDirections.ActionPujaItemKitFragmentToAddtoCartFragment action=
                        PujaItemKitFragmentDirections.actionPujaItemKitFragmentToAddtoCartFragment();
                action.setProductAddtoCartName("Ghati");
                Navigation.findNavController(mView).navigate(action);
            }
        });
    }

    private void setData(){
        alPujaItemKitListingModel=new ArrayList<>();

        PujaItemKitListingModel pujaItemKitListingModel1=new PujaItemKitListingModel();
        pujaItemKitListingModel1.setPujaItemKitName("Ghanti");
        pujaItemKitListingModel1.setPujaItemKitPrice("Price:1500");
        pujaItemKitListingModel1.setIconImage(ContextCompat.getDrawable(getActivity(), R.drawable.ghanti));
        alPujaItemKitListingModel.add(pujaItemKitListingModel1);

        PujaItemKitListingModel pujaItemKitListingModel2=new PujaItemKitListingModel();
        pujaItemKitListingModel2.setPujaItemKitName("Ghati");
        pujaItemKitListingModel2.setPujaItemKitPrice("Price:1200");
        pujaItemKitListingModel2.setIconImage(ContextCompat.getDrawable(getActivity(), R.drawable.ghati));
        alPujaItemKitListingModel.add(pujaItemKitListingModel2);

        PujaItemKitListingModel pujaItemKitListingModel3=new PujaItemKitListingModel();
        pujaItemKitListingModel3.setPujaItemKitName("Thali");
        pujaItemKitListingModel3.setPujaItemKitPrice("Price:2500");
        pujaItemKitListingModel3.setIconImage(ContextCompat.getDrawable(getActivity(), R.drawable.thali));
        alPujaItemKitListingModel.add(pujaItemKitListingModel3);

        PujaItemKitListingModel pujaItemKitListingModel4=new PujaItemKitListingModel();
        pujaItemKitListingModel4.setPujaItemKitName("Brass");
        pujaItemKitListingModel4.setPujaItemKitPrice("Price:1900");
        pujaItemKitListingModel4.setIconImage(ContextCompat.getDrawable(getActivity(), R.drawable.brass));
        alPujaItemKitListingModel.add(pujaItemKitListingModel4);

        PujaItemKitListingModel pujaItemKitListingModel5=new PujaItemKitListingModel();
        pujaItemKitListingModel5.setPujaItemKitName("Astramongola");
        pujaItemKitListingModel5.setPujaItemKitPrice("Price:3500");
        pujaItemKitListingModel5.setIconImage(ContextCompat.getDrawable(getActivity(), R.drawable.astromongola));
        alPujaItemKitListingModel.add(pujaItemKitListingModel5);

        mPujaItemKitViewModel.setPujaItemKitListingAdapter(alPujaItemKitListingModel);
    }

}
