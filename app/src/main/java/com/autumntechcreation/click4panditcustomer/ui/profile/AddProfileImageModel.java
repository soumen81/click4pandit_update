package com.autumntechcreation.click4panditcustomer.ui.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddProfileImageModel {
    @SerializedName("returnStatus")
    @Expose
    public String returnStatus;
    @SerializedName("returnErrMsg")
    @Expose
    public Object returnErrMsg;
    @SerializedName("custMasterProfImg")
    @Expose
    public CustMasterProfImg custMasterProfImg;

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Object getReturnErrMsg() {
        return returnErrMsg;
    }

    public void setReturnErrMsg(Object returnErrMsg) {
        this.returnErrMsg = returnErrMsg;
    }

    public CustMasterProfImg getCustMasterProfImg() {
        return custMasterProfImg;
    }

    public void setCustMasterProfImg(CustMasterProfImg custMasterProfImg) {
        this.custMasterProfImg = custMasterProfImg;
    }


}
