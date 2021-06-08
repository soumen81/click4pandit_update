package com.autumntechcreation.click4panditcustomer.ui.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustMasterProfileDataModel {
     @SerializedName("logonId")
    @Expose
    public String logonId;
    @SerializedName("custMasterId")
    @Expose
    public Integer custMasterId;
    @SerializedName("firstName")
    @Expose
    public String firstName;
    @SerializedName("lastName")
    @Expose
    public String lastName;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("alternateMobile")
    @Expose
    public Object alternateMobile;
    @SerializedName("emailId")
    @Expose
    public String emailId;
    @SerializedName("phoneNo")
    @Expose
    public Object phoneNo;
    @SerializedName("faxNo")
    @Expose
    public Object faxNo;

    @SerializedName("imgAction")
    @Expose
    public String imgAction;

    public String getImgAction() {
        return imgAction;
    }

    public void setImgAction(String imgAction) {
        this.imgAction = imgAction;
    }

    public String getLogonId() {
        return logonId;
    }

    public void setLogonId(String logonId) {
        this.logonId = logonId;
    }

    public Integer getCustMasterId() {
        return custMasterId;
    }

    public void setCustMasterId(Integer custMasterId) {
        this.custMasterId = custMasterId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getAlternateMobile() {
        return alternateMobile;
    }

    public void setAlternateMobile(Object alternateMobile) {
        this.alternateMobile = alternateMobile;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Object getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Object phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Object getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(Object faxNo) {
        this.faxNo = faxNo;
    }


}
