package com.autumntechcreation.click4panditcustomer.ui.choosepackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChoosePackageListModel {
    @SerializedName("pujaPkgId")
    @Expose
    public Integer pujaPkgId;
    @SerializedName("pujaPkgTypeId")
    @Expose
    public Integer pujaPkgTypeId;
    @SerializedName("pujaPkgTypeIdDscr")
    @Expose
    public String pujaPkgTypeIdDscr;
    @SerializedName("pujaSubCtrgryId")
    @Expose
    public Integer pujaSubCtrgryId;
    @SerializedName("pujaSubCtrgryDscr")
    @Expose
    public String pujaSubCtrgryDscr;
    @SerializedName("noOfPandit")
    @Expose
    public Integer noOfPandit;
    @SerializedName("pujaPkgAmount")
    @Expose
    public Double pujaPkgAmount;
    @SerializedName("isAllSamagriInclude")
    @Expose
    public String isAllSamagriInclude;
    @SerializedName("pujaPkgDscr")
    @Expose
    public String pujaPkgDscr;
    @SerializedName("pujaPkgNote")
    @Expose
    public String pujaPkgNote;
    @SerializedName("pujaPrcdrList")
    @Expose
    public List<PujaPrcdr> pujaPrcdrList = null;
    @SerializedName("pujaInclnList")
    @Expose
    public Object pujaInclnList;
    @SerializedName("pujaSamagriDesdlvrList")
    @Expose
    public List<PujaSamagriDesdlvr> pujaSamagriDesdlvrList = null;
    @SerializedName("pujasamagriHHList")
    @Expose
    public List<PujasamagriHH> pujasamagriHHList = null;

    public Integer getPujaPkgId() {
        return pujaPkgId;
    }

    public void setPujaPkgId(Integer pujaPkgId) {
        this.pujaPkgId = pujaPkgId;
    }

    public Integer getPujaPkgTypeId() {
        return pujaPkgTypeId;
    }

    public void setPujaPkgTypeId(Integer pujaPkgTypeId) {
        this.pujaPkgTypeId = pujaPkgTypeId;
    }

    public String getPujaPkgTypeIdDscr() {
        return pujaPkgTypeIdDscr;
    }

    public void setPujaPkgTypeIdDscr(String pujaPkgTypeIdDscr) {
        this.pujaPkgTypeIdDscr = pujaPkgTypeIdDscr;
    }

    public Integer getPujaSubCtrgryId() {
        return pujaSubCtrgryId;
    }

    public void setPujaSubCtrgryId(Integer pujaSubCtrgryId) {
        this.pujaSubCtrgryId = pujaSubCtrgryId;
    }

    public String getPujaSubCtrgryDscr() {
        return pujaSubCtrgryDscr;
    }

    public void setPujaSubCtrgryDscr(String pujaSubCtrgryDscr) {
        this.pujaSubCtrgryDscr = pujaSubCtrgryDscr;
    }

    public Integer getNoOfPandit() {
        return noOfPandit;
    }

    public void setNoOfPandit(Integer noOfPandit) {
        this.noOfPandit = noOfPandit;
    }

    public Double getPujaPkgAmount() {
        return pujaPkgAmount;
    }

    public void setPujaPkgAmount(Double pujaPkgAmount) {
        this.pujaPkgAmount = pujaPkgAmount;
    }

    public String getIsAllSamagriInclude() {
        return isAllSamagriInclude;
    }

    public void setIsAllSamagriInclude(String isAllSamagriInclude) {
        this.isAllSamagriInclude = isAllSamagriInclude;
    }

    public String getPujaPkgDscr() {
        return pujaPkgDscr;
    }

    public void setPujaPkgDscr(String pujaPkgDscr) {
        this.pujaPkgDscr = pujaPkgDscr;
    }

    public String getPujaPkgNote() {
        return pujaPkgNote;
    }

    public void setPujaPkgNote(String pujaPkgNote) {
        this.pujaPkgNote = pujaPkgNote;
    }

    public List<PujaPrcdr> getPujaPrcdrList() {
        return pujaPrcdrList;
    }

    public void setPujaPrcdrList(List<PujaPrcdr> pujaPrcdrList) {
        this.pujaPrcdrList = pujaPrcdrList;
    }

    public Object getPujaInclnList() {
        return pujaInclnList;
    }

    public void setPujaInclnList(Object pujaInclnList) {
        this.pujaInclnList = pujaInclnList;
    }

    public List<PujaSamagriDesdlvr> getPujaSamagriDesdlvrList() {
        return pujaSamagriDesdlvrList;
    }

    public void setPujaSamagriDesdlvrList(List<PujaSamagriDesdlvr> pujaSamagriDesdlvrList) {
        this.pujaSamagriDesdlvrList = pujaSamagriDesdlvrList;
    }

    public List<PujasamagriHH> getPujasamagriHHList() {
        return pujasamagriHHList;
    }

    public void setPujasamagriHHList(List<PujasamagriHH> pujasamagriHHList) {
        this.pujasamagriHHList = pujasamagriHHList;
    }

}
