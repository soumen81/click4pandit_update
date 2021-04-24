package com.autumntechcreation.click4panditcustomer.ui.choosepackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PujaSamagriDesdlvr {
    @SerializedName("pujaPkgId")
    @Expose
    private Integer pujaPkgId;
    @SerializedName("pujaSamagriDelvryDtlId")
    @Expose
    private Integer pujaSamagriDelvryDtlId;
    @SerializedName("pujaSamagriDelvryMasterId")
    @Expose
    private Object pujaSamagriDelvryMasterId;
    @SerializedName("pujaSamagriDelvryDscr")
    @Expose
    private String pujaSamagriDelvryDscr;
    @SerializedName("pujaDelvrySamagriDspOrd")
    @Expose
    private Integer pujaDelvrySamagriDspOrd;

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
