package com.autumntechcreation.click4panditcustomer.ui.choosepackage;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentChoosepackageBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeFragment;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeViewModel;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;

import javax.inject.Inject;

public class ChoosePackageFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentChoosepackageBinding mFragmentChoosepackageBinding;
    ChoosePackageViewModel mChoosePackageViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentChoosepackageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_choosepackage, container, false);
        mFragmentChoosepackageBinding.setLifecycleOwner(this);

        return mFragmentChoosepackageBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mChoosePackageViewModel = ViewModelProviders.of(ChoosePackageFragment.this, viewModelFactory).get(ChoosePackageViewModel.class);
        mFragmentChoosepackageBinding.setChoosePackageViewModel(mChoosePackageViewModel);


        mChoosePackageViewModel.getonClickStandardPackage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                showCustomChoosePackageDialog();
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
