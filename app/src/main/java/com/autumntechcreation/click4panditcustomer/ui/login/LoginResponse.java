package com.autumntechcreation.click4panditcustomer.ui.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("returnStatus")
    @Expose
    public String returnStatus;
    @SerializedName("returnErrMsg")
    @Expose
    public Object returnErrMsg;
    @SerializedName("firstName")
    @Expose
    public String firstName;
    @SerializedName("lastName")
    @Expose
    public String lastName;
    @SerializedName("mobile")
    @Expose
    public String mobile;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
