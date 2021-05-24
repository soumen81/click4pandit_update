package com.autumntechcreation.click4panditcustomer.ui.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerGetProfileModel {


    @SerializedName("custMasterProfileDataModel")
    @Expose
    public CustMasterProfileDataModel custMasterProfileDataModel;


    public CustMasterProfileDataModel getCustMasterProfileDataModel() {
        return custMasterProfileDataModel;
    }

    public void setCustMasterProfileDataModel(CustMasterProfileDataModel custMasterProfileDataModel) {
        this.custMasterProfileDataModel = custMasterProfileDataModel;
    }



  }
