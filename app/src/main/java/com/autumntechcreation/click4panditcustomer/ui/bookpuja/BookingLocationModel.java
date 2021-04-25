package com.autumntechcreation.click4panditcustomer.ui.bookpuja;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingLocationModel {
    @SerializedName("subLcltyId")
    @Expose
    public Integer subLcltyId;
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
    @SerializedName("subLcltyName")
    @Expose
    public String subLcltyName;
    @SerializedName("subLcltyDscr")
    @Expose
    public Object subLcltyDscr;



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

    public Integer getSubLcltyId() {
        return subLcltyId;
    }

    public void setSubLcltyId(Integer subLcltyId) {
        this.subLcltyId = subLcltyId;
    }

    public String getSubLcltyName() {
        return subLcltyName;
    }

    public void setSubLcltyName(String subLcltyName) {
        this.subLcltyName = subLcltyName;
    }

    public Object getSubLcltyDscr() {
        return subLcltyDscr;
    }

    public void setSubLcltyDscr(Object subLcltyDscr) {
        this.subLcltyDscr = subLcltyDscr;
    }
}
