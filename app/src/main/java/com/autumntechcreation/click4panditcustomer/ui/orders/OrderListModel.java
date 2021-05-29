package com.autumntechcreation.click4panditcustomer.ui.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderListModel {

    @SerializedName("totalCount")
    @Expose
    public Integer totalCount;
    @SerializedName("rowNo")
    @Expose
    public Integer rowNo;
    @SerializedName("custOrdId")
    @Expose
    public Integer custOrdId;
    @SerializedName("custOrdNo")
    @Expose
    public String custOrdNo;
    @SerializedName("custOrdDt")
    @Expose
    public String custOrdDt;
    @SerializedName("custOrdAmt")
    @Expose
    public Double custOrdAmt;
    @SerializedName("custOrdAmoutCurrId")
    @Expose
    public Integer custOrdAmoutCurrId;
    @SerializedName("custOrdAmtCurrCode")
    @Expose
    public String custOrdAmtCurrCode;
    @SerializedName("custOrdDscr")
    @Expose
    public String custOrdDscr;
    @SerializedName("bkgDt")
    @Expose
    public String bkgDt;
    @SerializedName("bkgStsId")
    @Expose
    public Integer bkgStsId;
    @SerializedName("bkgStsDscr")
    @Expose
    public String bkgStsDscr;
    @SerializedName("langMasterId")
    @Expose
    public Integer langMasterId;
    @SerializedName("langName")
    @Expose
    public Object langName;
    @SerializedName("sublcltyId")
    @Expose
    public Integer sublcltyId;
    @SerializedName("sublcltyName")
    @Expose
    public String sublcltyName;
    @SerializedName("pujaPkgId")
    @Expose
    public Integer pujaPkgId;
    @SerializedName("pujaPkgTypName")
    @Expose
    public String pujaPkgTypName;
    @SerializedName("pujaSubCtgryId")
    @Expose
    public Integer pujaSubCtgryId;
    @SerializedName("pujaSubCtgryDscr")
    @Expose
    public String pujaSubCtgryDscr;
    @SerializedName("pujaPkgDescription")
    @Expose
    public String pujaPkgDescription;
    @SerializedName("pujaPkgAllSamagriFlg")
    @Expose
    public String pujaPkgAllSamagriFlg;
    @SerializedName("pujaPkgNoOfPandit")
    @Expose
    public Integer pujaPkgNoOfPandit;
    @SerializedName("custBkgPkgAmt")
    @Expose
    public Double custBkgPkgAmt;
    @SerializedName("custBkgPkgTaxAmt")
    @Expose
    public Double custBkgPkgTaxAmt;
    @SerializedName("custBkgPkgTotalAmt")
    @Expose
    public Double custBkgPkgTotalAmt;
    @SerializedName("custBkgPkgTotalAmtCurrId")
    @Expose
    public Integer custBkgPkgTotalAmtCurrId;
    @SerializedName("custBkgPkgTotalAmtCurrCode")
    @Expose
    public String custBkgPkgTotalAmtCurrCode;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }

    public Integer getCustOrdId() {
        return custOrdId;
    }

    public void setCustOrdId(Integer custOrdId) {
        this.custOrdId = custOrdId;
    }

    public String getCustOrdNo() {
        return custOrdNo;
    }

    public void setCustOrdNo(String custOrdNo) {
        this.custOrdNo = custOrdNo;
    }

    public String getCustOrdDt() {
        return custOrdDt;
    }

    public void setCustOrdDt(String custOrdDt) {
        this.custOrdDt = custOrdDt;
    }

    public Double getCustOrdAmt() {
        return custOrdAmt;
    }

    public void setCustOrdAmt(Double custOrdAmt) {
        this.custOrdAmt = custOrdAmt;
    }

    public Integer getCustOrdAmoutCurrId() {
        return custOrdAmoutCurrId;
    }

    public void setCustOrdAmoutCurrId(Integer custOrdAmoutCurrId) {
        this.custOrdAmoutCurrId = custOrdAmoutCurrId;
    }

    public String getCustOrdAmtCurrCode() {
        return custOrdAmtCurrCode;
    }

    public void setCustOrdAmtCurrCode(String custOrdAmtCurrCode) {
        this.custOrdAmtCurrCode = custOrdAmtCurrCode;
    }

    public String getCustOrdDscr() {
        return custOrdDscr;
    }

    public void setCustOrdDscr(String custOrdDscr) {
        this.custOrdDscr = custOrdDscr;
    }

    public String getBkgDt() {
        return bkgDt;
    }

    public void setBkgDt(String bkgDt) {
        this.bkgDt = bkgDt;
    }

    public Integer getBkgStsId() {
        return bkgStsId;
    }

    public void setBkgStsId(Integer bkgStsId) {
        this.bkgStsId = bkgStsId;
    }

    public String getBkgStsDscr() {
        return bkgStsDscr;
    }

    public void setBkgStsDscr(String bkgStsDscr) {
        this.bkgStsDscr = bkgStsDscr;
    }

    public Integer getLangMasterId() {
        return langMasterId;
    }

    public void setLangMasterId(Integer langMasterId) {
        this.langMasterId = langMasterId;
    }

    public Object getLangName() {
        return langName;
    }

    public void setLangName(Object langName) {
        this.langName = langName;
    }

    public Integer getSublcltyId() {
        return sublcltyId;
    }

    public void setSublcltyId(Integer sublcltyId) {
        this.sublcltyId = sublcltyId;
    }

    public String getSublcltyName() {
        return sublcltyName;
    }

    public void setSublcltyName(String sublcltyName) {
        this.sublcltyName = sublcltyName;
    }

    public Integer getPujaPkgId() {
        return pujaPkgId;
    }

    public void setPujaPkgId(Integer pujaPkgId) {
        this.pujaPkgId = pujaPkgId;
    }

    public String getPujaPkgTypName() {
        return pujaPkgTypName;
    }

    public void setPujaPkgTypName(String pujaPkgTypName) {
        this.pujaPkgTypName = pujaPkgTypName;
    }

    public Integer getPujaSubCtgryId() {
        return pujaSubCtgryId;
    }

    public void setPujaSubCtgryId(Integer pujaSubCtgryId) {
        this.pujaSubCtgryId = pujaSubCtgryId;
    }

    public String getPujaSubCtgryDscr() {
        return pujaSubCtgryDscr;
    }

    public void setPujaSubCtgryDscr(String pujaSubCtgryDscr) {
        this.pujaSubCtgryDscr = pujaSubCtgryDscr;
    }

    public String getPujaPkgDescription() {
        return pujaPkgDescription;
    }

    public void setPujaPkgDescription(String pujaPkgDescription) {
        this.pujaPkgDescription = pujaPkgDescription;
    }

    public String getPujaPkgAllSamagriFlg() {
        return pujaPkgAllSamagriFlg;
    }

    public void setPujaPkgAllSamagriFlg(String pujaPkgAllSamagriFlg) {
        this.pujaPkgAllSamagriFlg = pujaPkgAllSamagriFlg;
    }

    public Integer getPujaPkgNoOfPandit() {
        return pujaPkgNoOfPandit;
    }

    public void setPujaPkgNoOfPandit(Integer pujaPkgNoOfPandit) {
        this.pujaPkgNoOfPandit = pujaPkgNoOfPandit;
    }

    public Double getCustBkgPkgAmt() {
        return custBkgPkgAmt;
    }

    public void setCustBkgPkgAmt(Double custBkgPkgAmt) {
        this.custBkgPkgAmt = custBkgPkgAmt;
    }

    public Double getCustBkgPkgTaxAmt() {
        return custBkgPkgTaxAmt;
    }

    public void setCustBkgPkgTaxAmt(Double custBkgPkgTaxAmt) {
        this.custBkgPkgTaxAmt = custBkgPkgTaxAmt;
    }

    public Double getCustBkgPkgTotalAmt() {
        return custBkgPkgTotalAmt;
    }

    public void setCustBkgPkgTotalAmt(Double custBkgPkgTotalAmt) {
        this.custBkgPkgTotalAmt = custBkgPkgTotalAmt;
    }

    public Integer getCustBkgPkgTotalAmtCurrId() {
        return custBkgPkgTotalAmtCurrId;
    }

    public void setCustBkgPkgTotalAmtCurrId(Integer custBkgPkgTotalAmtCurrId) {
        this.custBkgPkgTotalAmtCurrId = custBkgPkgTotalAmtCurrId;
    }

    public String getCustBkgPkgTotalAmtCurrCode() {
        return custBkgPkgTotalAmtCurrCode;
    }

    public void setCustBkgPkgTotalAmtCurrCode(String custBkgPkgTotalAmtCurrCode) {
        this.custBkgPkgTotalAmtCurrCode = custBkgPkgTotalAmtCurrCode;
    }


}
