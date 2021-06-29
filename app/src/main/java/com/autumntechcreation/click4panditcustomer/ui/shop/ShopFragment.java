package com.autumntechcreation.click4panditcustomer.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentShopBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class ShopFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentShopBinding mFragmentShopBinding;
    ShopViewModel mShopViewModel;
    private View mView;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentShopBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false);
        mFragmentShopBinding.setLifecycleOwner(this);

        return mFragmentShopBinding.getRoot();
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

        mFragmentShopBinding.tvPujaItemKitShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ShopFragmentDirections.ActionShopFragmentToPujaItemKitFragment action=
                       ShopFragmentDirections.actionShopFragmentToPujaItemKitFragment();
               action.setShopId("1");
                Navigation.findNavController(mView).navigate(action);
            }
        });

        mFragmentShopBinding.tvPujaBoxShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopFragmentDirections.ActionShopFragmentToPujaBoxItemListFragment action=
                        ShopFragmentDirections.actionShopFragmentToPujaBoxItemListFragment();
                action.setPujaBoxId("2");
                Navigation.findNavController(mView).navigate(action);
            }
        });

        mFragmentShopBinding.tvPujaBrassItemShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopFragmentDirections.ActionShopFragmentToPujaBrassItemListingFragment action=
                    ShopFragmentDirections.actionShopFragmentToPujaBrassItemListingFragment();
                action.setPujaBrassId("3");
                Navigation.findNavController(mView).navigate(action);
            }
        });


    }
    }
