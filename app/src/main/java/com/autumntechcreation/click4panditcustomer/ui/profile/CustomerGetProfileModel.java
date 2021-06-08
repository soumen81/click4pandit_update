package com.autumntechcreation.click4panditcustomer.ui.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerGetProfileModel {

    @SerializedName("custMasterProfileImageModel")
    @Expose
    public CustMasterProfileImageModel custMasterProfileImageModel;
    @SerializedName("custMasterProfileDataModel")
    @Expose
    public CustMasterProfileDataModel custMasterProfileDataModel;

    public CustMasterProfileImageModel getCustMasterProfileImageModel() {
        return custMasterProfileImageModel;
    }

    public void setCustMasterProfileImageModel(CustMasterProfileImageModel custMasterProfileImageModel) {
        this.custMasterProfileImageModel = custMasterProfileImageModel;
    }

    public CustMasterProfileDataModel getCustMasterProfileDataModel() {
        return custMasterProfileDataModel;
    }

    public void setCustMasterProfileDataModel(CustMasterProfileDataModel custMasterProfileDataModel) {
        this.custMasterProfileDataModel = custMasterProfileDataModel;
    }









  }
