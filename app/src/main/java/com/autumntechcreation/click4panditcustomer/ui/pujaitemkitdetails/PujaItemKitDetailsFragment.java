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

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentPujaitemkitdetailsBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileFragment;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileViewModel;

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
        ((MainActivity) getActivity()).setToolbar(false,true,false,true);


    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPujaItemKitDetailsViewModel = ViewModelProviders.of(PujaItemKitDetailsFragment.this, viewModelFactory).get(PujaItemKitDetailsViewModel.class);
        mFragmentPujaitemkitdetailsBinding.setPujaItemKitDetailsViewModel(mPujaItemKitDetailsViewModel);
    }
}
