package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PujasamagriHH {
    @SerializedName("pujaPkgId")
    @Expose
    private Integer pujaPkgId;
    @SerializedName("pujaSamagriHHDtlId")
    @Expose
    private Integer pujaSamagriHHDtlId;
    @SerializedName("pujaSamagriHHMasterId")
    @Expose
    private Object pujaSamagriHHMasterId;
    @SerializedName("pujaSamagriHHDscr")
    @Expose
    private String pujaSamagriHHDscr;
    @SerializedName("pujaHHSamagriDspOrd")
    @Expose
    private Integer pujaHHSamagriDspOrd;

    public Integer getPujaPkgId() {
        return pujaPkgId;
    }

    public void setPujaPkgId(Integer pujaPkgId) {
        this.pujaPkgId = pujaPkgId;
    }

    public Integer getPujaSamagriHHDtlId() {
        return pujaSamagriHHDtlId;
    }

    public void setPujaSamagriHHDtlId(Integer pujaSamagriHHDtlId) {
        this.pujaSamagriHHDtlId = pujaSamagriHHDtlId;
    }

    public Object getPujaSamagriHHMasterId() {
        return pujaSamagriHHMasterId;
    }

    public void setPujaSamagriHHMasterId(Object pujaSamagriHHMasterId) {
        this.pujaSamagriHHMasterId = pujaSamagriHHMasterId;
    }

    public String getPujaSamagriHHDscr() {
        return pujaSamagriHHDscr;
    }

    public void setPujaSamagriHHDscr(String pujaSamagriHHDscr) {
        this.pujaSamagriHHDscr = pujaSamagriHHDscr;
    }

    public Integer getPujaHHSamagriDspOrd() {
        return pujaHHSamagriDspOrd;
    }

    public void setPujaHHSamagriDspOrd(Integer pujaHHSamagriDspOrd) {
        this.pujaHHSamagriDspOrd = pujaHHSamagriDspOrd;
    }
}
