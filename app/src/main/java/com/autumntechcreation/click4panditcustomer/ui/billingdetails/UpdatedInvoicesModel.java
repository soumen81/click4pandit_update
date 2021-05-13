package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatedInvoicesModel {
    @SerializedName("returnStatus")
    @Expose
    public String returnStatus;
    @SerializedName("returnErrMsg")
    @Expose
    public Object returnErrMsg;
    @SerializedName("custInvoiceAsEmail")
    @Expose
    public CustInvoiceAsEmail custInvoiceAsEmail;

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

    public CustInvoiceAsEmail getCustInvoiceAsEmail() {
        return custInvoiceAsEmail;
    }

    public void setCustInvoiceAsEmail(CustInvoiceAsEmail custInvoiceAsEmail) {
        this.custInvoiceAsEmail = custInvoiceAsEmail;
    }
}
