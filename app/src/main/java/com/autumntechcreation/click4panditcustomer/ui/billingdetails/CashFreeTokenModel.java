package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CashFreeTokenModel {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("cftoken")
    @Expose
    public String cftoken;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCftoken() {
        return cftoken;
    }

    public void setCftoken(String cftoken) {
        this.cftoken = cftoken;
    }
}
