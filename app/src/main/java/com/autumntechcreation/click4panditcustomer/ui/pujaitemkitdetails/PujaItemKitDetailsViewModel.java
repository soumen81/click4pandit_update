package com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails;

import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileRepository;

import javax.inject.Inject;

public class PujaItemKitDetailsViewModel extends ViewModel {
    PujaItemKitDetailsRepository mPujaItemKitDetailsRepository;
    @Inject
    public PujaItemKitDetailsViewModel(PujaItemKitDetailsRepository pujaItemKitDetailsRepository) {
        this.mPujaItemKitDetailsRepository=pujaItemKitDetailsRepository;
    }
}
