package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustInvoiceAsEmail {
    @SerializedName("custBkg")
    @Expose
    private CustBkg custBkg;
    @SerializedName("custInvoice")
    @Expose
    private CustInvoice custInvoice;
    @SerializedName("pujaSamagriForDeliveryList")
    @Expose
    private List<Object> pujaSamagriForDeliveryList = null;
    @SerializedName("pujaPrcdrList")
    @Expose
    private List<Object> pujaPrcdrList = null;
    @SerializedName("pujasamagriHHList")
    @Expose
    private List<Object> pujasamagriHHList = null;

    public CustBkg getCustBkg() {
        return custBkg;
    }

    public void setCustBkg(CustBkg custBkg) {
        this.custBkg = custBkg;
    }

    public CustInvoice getCustInvoice() {
        return custInvoice;
    }

    public void setCustInvoice(CustInvoice custInvoice) {
        this.custInvoice = custInvoice;
    }

    public List<Object> getPujaSamagriForDeliveryList() {
        return pujaSamagriForDeliveryList;
    }

    public void setPujaSamagriForDeliveryList(List<Object> pujaSamagriForDeliveryList) {
        this.pujaSamagriForDeliveryList = pujaSamagriForDeliveryList;
    }

    public List<Object> getPujaPrcdrList() {
        return pujaPrcdrList;
    }

    public void setPujaPrcdrList(List<Object> pujaPrcdrList) {
        this.pujaPrcdrList = pujaPrcdrList;
    }

    public List<Object> getPujasamagriHHList() {
        return pujasamagriHHList;
    }

    public void setPujasamagriHHList(List<Object> pujasamagriHHList) {
        this.pujasamagriHHList = pujasamagriHHList;
    }


}
