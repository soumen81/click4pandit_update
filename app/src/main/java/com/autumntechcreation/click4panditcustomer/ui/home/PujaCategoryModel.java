package com.autumntechcreation.click4panditcustomer.ui.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PujaCategoryModel {
    @SerializedName("pujaSubCtgryId")
    @Expose
    private Integer pujaSubCtgryId;
    @SerializedName("orglStamp")
    @Expose
    private Object orglStamp;
    @SerializedName("orglUser")
    @Expose
    private Object orglUser;
    @SerializedName("updtStamp")
    @Expose
    private Object updtStamp;
    @SerializedName("updtUser")
    @Expose
    private Object updtUser;
    @SerializedName("delFlg")
    @Expose
    private Object delFlg;
    @SerializedName("pujaCtgryId")
    @Expose
    private Object pujaCtgryId;
    @SerializedName("pujaSubCtgryDscr")
    @Expose
    private String pujaSubCtgryDscr;

    public Integer getPujaSubCtgryId() {
        return pujaSubCtgryId;
    }

    public void setPujaSubCtgryId(Integer pujaSubCtgryId) {
        this.pujaSubCtgryId = pujaSubCtgryId;
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

    public Object getPujaCtgryId() {
        return pujaCtgryId;
    }

    public void setPujaCtgryId(Object pujaCtgryId) {
        this.pujaCtgryId = pujaCtgryId;
    }

    public String getPujaSubCtgryDscr() {
        return pujaSubCtgryDscr;
    }

    public void setPujaSubCtgryDscr(String pujaSubCtgryDscr) {
        this.pujaSubCtgryDscr = pujaSubCtgryDscr;
    }


}
