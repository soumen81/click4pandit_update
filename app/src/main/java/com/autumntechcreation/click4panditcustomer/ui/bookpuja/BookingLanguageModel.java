package com.autumntechcreation.click4panditcustomer.ui.bookpuja;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingLanguageModel {
    @SerializedName("langMasterId")
    @Expose
    public Integer langMasterId;
    @SerializedName("orglStamp")
    @Expose
    public Object orglStamp;
    @SerializedName("orglUser")
    @Expose
    public Object orglUser;
    @SerializedName("updtStamp")
    @Expose
    public Object updtStamp;
    @SerializedName("updtUser")
    @Expose
    public Object updtUser;
    @SerializedName("delFlg")
    @Expose
    public Object delFlg;
    @SerializedName("actFlg")
    @Expose
    public Object actFlg;
    @SerializedName("langMasterName")
    @Expose
    public String langMasterName;
    @SerializedName("langMasterDscr")
    @Expose
    public Object langMasterDscr;
    @SerializedName("seqNo")
    @Expose
    public Object seqNo;
    @SerializedName("dspOrd")
    @Expose
    public Object dspOrd;

    public Integer getLangMasterId() {
        return langMasterId;
    }

    public void setLangMasterId(Integer langMasterId) {
        this.langMasterId = langMasterId;
    }

    public Object getOrglStamp() {
        return orglStamp;
    }

    public void setOrglStamp(Object orglStamp) {
        this.orglStamp = orglStamp;
    }

    public Object getOrglUser() {
        return orglUser;
    }

    public void setOrglUser(Object orglUser) {
        this.orglUser = orglUser;
    }

    public Object getUpdtStamp() {
        return updtStamp;
    }

    public void setUpdtStamp(Object updtStamp) {
        this.updtStamp = updtStamp;
    }

    public Object getUpdtUser() {
        return updtUser;
    }

    public void setUpdtUser(Object updtUser) {
        this.updtUser = updtUser;
    }

    public Object getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Object delFlg) {
        this.delFlg = delFlg;
    }

    public Object getActFlg() {
        return actFlg;
    }

    public void setActFlg(Object actFlg) {
        this.actFlg = actFlg;
    }

    public String getLangMasterName() {
        return langMasterName;
    }

    public void setLangMasterName(String langMasterName) {
        this.langMasterName = langMasterName;
    }

    public Object getLangMasterDscr() {
        return langMasterDscr;
    }

    public void setLangMasterDscr(Object langMasterDscr) {
        this.langMasterDscr = langMasterDscr;
    }

    public Object getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Object seqNo) {
        this.seqNo = seqNo;
    }

    public Object getDspOrd() {
        return dspOrd;
    }

    public void setDspOrd(Object dspOrd) {
        this.dspOrd = dspOrd;
    }
}


