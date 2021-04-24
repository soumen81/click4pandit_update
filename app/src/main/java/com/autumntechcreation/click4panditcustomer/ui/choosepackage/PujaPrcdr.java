package com.autumntechcreation.click4panditcustomer.ui.choosepackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PujaPrcdr {
    @SerializedName("pujaPkgId")
    @Expose
    public Integer pujaPkgId;
    @SerializedName("pujaPkgPrcdrDtlId")
    @Expose
    public Integer pujaPkgPrcdrDtlId;
    @SerializedName("pujaPrcdrMasterId")
    @Expose
    public Integer pujaPrcdrMasterId;
    @SerializedName("pujaPrcdrDscr")
    @Expose
    public String pujaPrcdrDscr;
    @SerializedName("pkgDspOrd")
    @Expose
    public Object pkgDspOrd;
    @SerializedName("prcdrDspOrd")
    @Expose
    public Integer prcdrDspOrd;

    public Integer getPujaPkgId() {
        return pujaPkgId;
    }

    public void setPujaPkgId(Integer pujaPkgId) {
        this.pujaPkgId = pujaPkgId;
    }

    public Integer getPujaPkgPrcdrDtlId() {
        return pujaPkgPrcdrDtlId;
    }

    public void setPujaPkgPrcdrDtlId(Integer pujaPkgPrcdrDtlId) {
        this.pujaPkgPrcdrDtlId = pujaPkgPrcdrDtlId;
    }

    public Integer getPujaPrcdrMasterId() {
        return pujaPrcdrMasterId;
    }

    public void setPujaPrcdrMasterId(Integer pujaPrcdrMasterId) {
        this.pujaPrcdrMasterId = pujaPrcdrMasterId;
    }

    public String getPujaPrcdrDscr() {
        return pujaPrcdrDscr;
    }

    public void setPujaPrcdrDscr(String pujaPrcdrDscr) {
        this.pujaPrcdrDscr = pujaPrcdrDscr;
    }

    public Object getPkgDspOrd() {
        return pkgDspOrd;
    }

    public void setPkgDspOrd(Object pkgDspOrd) {
        this.pkgDspOrd = pkgDspOrd;
    }

    public Integer getPrcdrDspOrd() {
        return prcdrDspOrd;
    }

    public void setPrcdrDspOrd(Integer prcdrDspOrd) {
        this.prcdrDspOrd = prcdrDspOrd;
    }

}
