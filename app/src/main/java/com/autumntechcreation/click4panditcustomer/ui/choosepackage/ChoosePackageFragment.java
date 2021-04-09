package com.autumntechcreation.click4panditcustomer.ui.choosepackage;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.autumntechcreation.click4panditcustomer.databinding.FragmentChoosepackageBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeFragment;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeViewModel;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class ChoosePackageFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentChoosepackageBinding mFragmentChoosepackageBinding;
    ChoosePackageViewModel mChoosePackageViewModel;
    private View mView;
    NavController navController;
    private int[]mImager={R.drawable.pandit1,R.drawable.pandit2,R.drawable.pandit3,R.drawable.pandit4,R.drawable.pandit5};
    private String[]mImagetitle=new String[]{"Pandit1,Pandit2,Pandit3,Pandit4,Pandit5"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentChoosepackageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_choosepackage, container, false);
        mFragmentChoosepackageBinding.setLifecycleOwner(this);

        return mFragmentChoosepackageBinding.getRoot();

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

        mChoosePackageViewModel = ViewModelProviders.of(ChoosePackageFragment.this, viewModelFactory).get(ChoosePackageViewModel.class);
        mFragmentChoosepackageBinding.setChoosePackageViewModel(mChoosePackageViewModel);


        mFragmentChoosepackageBinding.carousal.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImager[position]);
            }
        });

        mFragmentChoosepackageBinding.carousal.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(),mImagetitle[position],Toast.LENGTH_SHORT).show();
            }
        });
        mFragmentChoosepackageBinding.carousal.setPageCount(mImager.length);
        mChoosePackageViewModel.getonClickStandardPackage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                showCustomChoosePackageDialog();
            }
        });
         mChoosePackageViewModel.getonClickViewPackage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                findNavController(mView).navigate(ChoosePackageFragmentDirections.actionChoosePackageFragmentToBookingPujaFragment());
            }
        });


    }


    private void showCustomChoosePackageDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = getActivity().findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_choosepackage, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();

       // EditText fragment_change_password_etOldPassword = dialogView.findViewById(R.id.fragment_change_password_etOldPassword);

        alertDialog.show();
    }
}
