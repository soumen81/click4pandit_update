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
    private List<PujaSamagriForDelivery> pujaSamagriForDeliveryList = null;
    @SerializedName("pujaPrcdrList")
    @Expose
    private List<PujaPrcdr> pujaPrcdrList = null;
    @SerializedName("pujasamagriHHList")
    @Expose
    private List<PujasamagriHH> pujasamagriHHList = null;

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

    public List<PujaSamagriForDelivery> getPujaSamagriForDeliveryList() {
        return pujaSamagriForDeliveryList;
    }

    public void setPujaSamagriForDeliveryList(List<PujaSamagriForDelivery> pujaSamagriForDeliveryList) {
        this.pujaSamagriForDeliveryList = pujaSamagriForDeliveryList;
    }

    public List<PujaPrcdr> getPujaPrcdrList() {
        return pujaPrcdrList;
    }

    public void setPujaPrcdrList(List<PujaPrcdr> pujaPrcdrList) {
        this.pujaPrcdrList = pujaPrcdrList;
    }

    public List<PujasamagriHH> getPujasamagriHHList() {
        return pujasamagriHHList;
    }

    public void setPujasamagriHHList(List<PujasamagriHH> pujasamagriHHList) {
        this.pujasamagriHHList = pujasamagriHHList;
    }

}
