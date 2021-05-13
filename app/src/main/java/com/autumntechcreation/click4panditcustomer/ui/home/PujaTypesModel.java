package com.autumntechcreation.click4panditcustomer.ui.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PujaTypesModel {
    @SerializedName("pujaCtgryId")
    @Expose
    public Integer pujaCtgryId;
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
    @SerializedName("pujaCtgryDscr")
    @Expose
    public String pujaCtgryDscr;






    public Integer getPujaCtgryId() {
        return pujaCtgryId;
    }

    public void setPujaCtgryId(Integer pujaCtgryId) {
        this.pujaCtgryId = pujaCtgryId;
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

    public String getPujaCtgryDscr() {
        return pujaCtgryDscr;
    }

    public void setPujaCtgryDscr(String pujaCtgryDscr) {
        this.pujaCtgryDscr = pujaCtgryDscr;
    }

}


