package com.autumntechcreation.click4panditcustomer.ui.settings;

import android.content.Intent;
import android.os.Bundle;
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
import com.autumntechcreation.click4panditcustomer.databinding.FragmentSettingsBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordAcitivity;
import com.autumntechcreation.click4panditcustomer.ui.contactus.ContactUsActivity;
import com.autumntechcreation.click4panditcustomer.ui.privacypolicy.PrivacyPolicyActivity;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileFragment;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileViewModel;
import com.autumntechcreation.click4panditcustomer.ui.refundpolicy.RefundPolicyActivity;
import com.autumntechcreation.click4panditcustomer.ui.termscondition.TermsConditionActivity;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class SettingsFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    SettingsViewModel mSettingsViewModel;
    FragmentSettingsBinding mFragmentSettingsBinding;
    private View mView;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentSettingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        mFragmentSettingsBinding.setLifecycleOwner(this);

        return mFragmentSettingsBinding.getRoot();
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


        mFragmentSettingsBinding.tvChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), ChangePasswordAcitivity.class);
                startActivity(in);
            }
        });
        mFragmentSettingsBinding.tvContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), ContactUsActivity.class);
                startActivity(in);
            }
        });
        mFragmentSettingsBinding.tvPrivacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), PrivacyPolicyActivity.class);
                startActivity(in);
            }
        });
        mFragmentSettingsBinding.tvRefundPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), RefundPolicyActivity.class);
                startActivity(in);
            }
        }); mFragmentSettingsBinding.tvTermsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), TermsConditionActivity.class);
                startActivity(in);
            }
        });
    }



}
