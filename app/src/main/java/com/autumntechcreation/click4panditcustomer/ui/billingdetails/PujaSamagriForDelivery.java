package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PujaSamagriForDelivery {
    @SerializedName("pujaPkgId")
    @Expose
    public Integer pujaPkgId;
    @SerializedName("pujaSamagriDelvryDtlId")
    @Expose
    public Integer pujaSamagriDelvryDtlId;
    @SerializedName("pujaSamagriDelvryMasterId")
    @Expose
    public Object pujaSamagriDelvryMasterId;
    @SerializedName("pujaSamagriDelvryDscr")
    @Expose
    public String pujaSamagriDelvryDscr;
    @SerializedName("pujaDelvrySamagriDspOrd")
    @Expose
    public Integer pujaDelvrySamagriDspOrd;

    public Integer getPujaPkgId() {
        return pujaPkgId;
    }

    public void setPujaPkgId(Integer pujaPkgId) {
        this.pujaPkgId = pujaPkgId;
    }

    public Integer getPujaSamagriDelvryDtlId() {
        return pujaSamagriDelvryDtlId;
    }

    public void setPujaSamagriDelvryDtlId(Integer pujaSamagriDelvryDtlId) {
        this.pujaSamagriDelvryDtlId = pujaSamagriDelvryDtlId;
    }

    public Object getPujaSamagriDelvryMasterId() {
        return pujaSamagriDelvryMasterId;
    }

    public void setPujaSamagriDelvryMasterId(Object pujaSamagriDelvryMasterId) {
        this.pujaSamagriDelvryMasterId = pujaSamagriDelvryMasterId;
    }

    public String getPujaSamagriDelvryDscr() {
        return pujaSamagriDelvryDscr;
    }

    public void setPujaSamagriDelvryDscr(String pujaSamagriDelvryDscr) {
        this.pujaSamagriDelvryDscr = pujaSamagriDelvryDscr;
    }

    public Integer getPujaDelvrySamagriDspOrd() {
        return pujaDelvrySamagriDspOrd;
    }

    public void setPujaDelvrySamagriDspOrd(Integer pujaDelvrySamagriDspOrd) {
        this.pujaDelvrySamagriDspOrd = pujaDelvrySamagriDspOrd;
    }

}
