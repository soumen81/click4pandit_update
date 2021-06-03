package com.autumntechcreation.click4panditcustomer.ui.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustMasterProfImg {
    @SerializedName("custMasterProfImgId")
    @Expose
    private Integer custMasterProfImgId;
    @SerializedName("custMasterId")
    @Expose
    private Integer custMasterId;
    @SerializedName("logonId")
    @Expose
    private Object logonId;
    @SerializedName("updtStamp")
    @Expose
    private Object updtStamp;
    @SerializedName("updtUser")
    @Expose
    private Object updtUser;
    @SerializedName("orglStamp")
    @Expose
    private Object orglStamp;
    @SerializedName("orglUser")
    @Expose
    private Object orglUser;
    @SerializedName("delFlg")
    @Expose
    private String delFlg;
    @SerializedName("cloudImgId")
    @Expose
    private String cloudImgId;
    @SerializedName("orglFileName")
    @Expose
    private String orglFileName;
    @SerializedName("cloudFileName")
    @Expose
    private String cloudFileName;
    @SerializedName("mimeTyp")
    @Expose
    private String mimeTyp;
    @SerializedName("imgAction")
    @Expose
    private Object imgAction;
    @SerializedName("fileData")
    @Expose
    private Object fileData;

    public Integer getCustMasterProfImgId() {
        return custMasterProfImgId;
    }

    public void setCustMasterProfImgId(Integer custMasterProfImgId) {
        this.custMasterProfImgId = custMasterProfImgId;
    }

    public Integer getCustMasterId() {
        return custMasterId;
    }

    public void setCustMasterId(Integer custMasterId) {
        this.custMasterId = custMasterId;
    }

    public Object getLogonId() {
        return logonId;
    }

    public void setLogonId(Object logonId) {
        this.logonId = logonId;
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

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public String getCloudImgId() {
        return cloudImgId;
    }

    public void setCloudImgId(String cloudImgId) {
        this.cloudImgId = cloudImgId;
    }

    public String getOrglFileName() {
        return orglFileName;
    }

    public void setOrglFileName(String orglFileName) {
        this.orglFileName = orglFileName;
    }

    public String getCloudFileName() {
        return cloudFileName;
    }

    public void setCloudFileName(String cloudFileName) {
        this.cloudFileName = cloudFileName;
    }

    public String getMimeTyp() {
        return mimeTyp;
    }

    public void setMimeTyp(String mimeTyp) {
        this.mimeTyp = mimeTyp;
    }

    public Object getImgAction() {
        return imgAction;
    }

    public void setImgAction(Object imgAction) {
        this.imgAction = imgAction;
    }

    public Object getFileData() {
        return fileData;
    }

    public void setFileData(Object fileData) {
        this.fileData = fileData;
    }

}
