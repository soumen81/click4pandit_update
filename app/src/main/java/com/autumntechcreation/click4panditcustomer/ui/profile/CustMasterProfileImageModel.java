package com.autumntechcreation.click4panditcustomer.ui.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public   class CustMasterProfileImageModel {
    @SerializedName("returnStatus")
    @Expose
    public String returnStatus;
    @SerializedName("custMasterProfImgId")
    @Expose
    public int custMasterProfImgId;
    @SerializedName("custMasterId")
    @Expose
    public Object custMasterId;
    @SerializedName("logonId")
    @Expose
    public String logonId;
    @SerializedName("updtStamp")
    @Expose
    public Object updtStamp;
    @SerializedName("updtUser")
    @Expose
    public Object updtUser;
    @SerializedName("orglStamp")
    @Expose
    public Object orglStamp;
    @SerializedName("orglUser")
    @Expose
    public Object orglUser;
    @SerializedName("delFlg")
    @Expose
    public String delFlg;
    @SerializedName("cloudImgId")
    @Expose
    public String cloudImgId;
    @SerializedName("orglFileName")
    @Expose
    public String orglFileName;
    @SerializedName("cloudFileName")
    @Expose
    public String cloudFileName;
    @SerializedName("mimeTyp")
    @Expose
    public String mimeTyp;
    @SerializedName("imgAction")
    @Expose
    public String imgAction;
    @SerializedName("fileData")
    @Expose
    public String fileData;



    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public int getCustMasterProfImgId() {
        return custMasterProfImgId;
    }

    public void setCustMasterProfImgId(int custMasterProfImgId) {
        this.custMasterProfImgId = custMasterProfImgId;
    }

    public Object getCustMasterId() {
        return custMasterId;
    }

    public void setCustMasterId(Object custMasterId) {
        this.custMasterId = custMasterId;
    }

    public String getLogonId() {
        return logonId;
    }

    public void setLogonId(String logonId) {
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

    public String getImgAction() {
        return imgAction;
    }

    public void setImgAction(String imgAction) {
        this.imgAction = imgAction;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

}

