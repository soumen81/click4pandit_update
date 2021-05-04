package com.autumntechcreation.click4panditcustomer.ui.forgetpassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TriggerMailModel {
    @SerializedName("returnStatus")
    @Expose
    public String returnStatus;
    @SerializedName("returnErrMsg")
    @Expose
    public Object returnErrMsg;
    @SerializedName("firstName")
    @Expose
    public Object firstName;
    @SerializedName("lastName")
    @Expose
    public Object lastName;
    @SerializedName("mobile")
    @Expose
    public Object mobile;

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

    public Object getFirstName() {
        return firstName;
    }

    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

}
