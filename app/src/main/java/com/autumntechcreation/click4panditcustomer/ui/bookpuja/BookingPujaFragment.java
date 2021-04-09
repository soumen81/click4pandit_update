package com.autumntechcreation.click4panditcustomer.ui.bookpuja;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentBookingpujaBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;


import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class BookingPujaFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentBookingpujaBinding mFragmentBookingpujaBinding;
    BookingPujaViewModel mBookingPujaViewModel;
    private int[]mImager={R.drawable.pandit1,R.drawable.pandit2,R.drawable.pandit3,R.drawable.pandit4,R.drawable.dog};
    private String[]mImagetitle=new String[]{"Pandit1,Pandit2,Pandit3,Pandit4,Pandit5"};
    private View mView;
    NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBookingpujaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookingpuja, container, false);
        mFragmentBookingpujaBinding.setLifecycleOwner(this);

        return mFragmentBookingpujaBinding.getRoot();
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

        mBookingPujaViewModel = ViewModelProviders.of(BookingPujaFragment.this, viewModelFactory).get(BookingPujaViewModel.class);
        mFragmentBookingpujaBinding.setBookingPujaViewModel(mBookingPujaViewModel);



        mFragmentBookingpujaBinding.carousal.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImager[position]);
            }
        });

        mFragmentBookingpujaBinding.carousal.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(),mImagetitle[position],Toast.LENGTH_SHORT).show();
            }
        });
        mFragmentBookingpujaBinding.carousal.setPageCount(mImager.length);
        mBookingPujaViewModel.getOnClickBookPackage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                findNavController(mView).navigate(BookingPujaFragmentDirections.actionBookingPujaFragmentToOrderSummaryFragment());
            }
        });

    }


}
