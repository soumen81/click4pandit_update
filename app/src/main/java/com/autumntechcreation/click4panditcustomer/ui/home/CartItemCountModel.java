package com.autumntechcreation.click4panditcustomer.ui.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartItemCountModel {
    @SerializedName("returnStatus")
    @Expose
    private String returnStatus;
    @SerializedName("returnErrMsg")
    @Expose
    private Object returnErrMsg;
    @SerializedName("returnCartValue")
    @Expose
    private ReturnCartValue returnCartValue;

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

    public ReturnCartValue getReturnCartValue() {
        return returnCartValue;
    }

    public void setReturnCartValue(ReturnCartValue returnCartValue) {
        this.returnCartValue = returnCartValue;
    }

}
