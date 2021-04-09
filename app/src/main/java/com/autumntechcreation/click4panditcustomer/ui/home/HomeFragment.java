package com.autumntechcreation.click4panditcustomer.ui.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentHomeBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;


public class HomeFragment  extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
     FragmentHomeBinding mFragmentHomeBinding;
    HomeViewModel mHomeViewModel;
    private View mView;
    NavController navController;
    private int[]mImager={R.drawable.pandit1,R.drawable.pandit2,R.drawable.pandit3,R.drawable.pandit4,R.drawable.pandit5};
    private String[]mImagetitle=new String[]{"Pandit1,Pandit2,Pandit3,Pandit4,Pandit5"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mFragmentHomeBinding.setLifecycleOwner(this);



        return mFragmentHomeBinding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;

        navController=findNavController(mView);
        ((MainActivity) getActivity()).setToolbar(true,false,false,true);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mHomeViewModel = ViewModelProviders.of(HomeFragment.this, viewModelFactory).get(HomeViewModel.class);
        mFragmentHomeBinding.setHomeViewModel(mHomeViewModel);


        mFragmentHomeBinding.carousal.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImager[position]);
            }
        });
        mFragmentHomeBinding.carousal.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(),mImagetitle[position],Toast.LENGTH_SHORT).show();
            }
        });
        mFragmentHomeBinding.carousal.setPageCount(mImager.length);
        mFragmentHomeBinding.tvViewPackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Navigation.findNavController(mView).navigate(HomeFr)
                findNavController(mView).navigate(HomeFragmentDirections.actionHomeFragmentFragmentToChoosePackageFragment());
            }
        });

    }
}
