package com.autumntechcreation.click4panditcustomer.ui.billingdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustInvoice {
    @SerializedName("custInvId")
    @Expose
    private Integer custInvId;
    @SerializedName("updtStamp")
    @Expose
    private String updtStamp;
    @SerializedName("updtUser")
    @Expose
    private String updtUser;
    @SerializedName("orglStamp")
    @Expose
    private String orglStamp;
    @SerializedName("orglUser")
    @Expose
    private String orglUser;
    @SerializedName("delFlg")
    @Expose
    private String delFlg;
    @SerializedName("custOrdId")
    @Expose
    private Integer custOrdId;



    @SerializedName("custOrdNo")
    @Expose
    private String custOrdNo;
    @SerializedName("prodSellBy")
    @Expose
    private String prodSellBy;
    @SerializedName("billingFirstName")
    @Expose
    private String billingFirstName;
    @SerializedName("billingLastName")
    @Expose
    private String billingLastName;
    @SerializedName("billingAddr1")
    @Expose
    private String billingAddr1;
    @SerializedName("billingAddr2")
    @Expose
    private String billingAddr2;
    @SerializedName("billingAddr3")
    @Expose
    private String billingAddr3;
    @SerializedName("billingMob1")
    @Expose
    private String billingMob1;
    @SerializedName("billingMob2")
    @Expose
    private String billingMob2;
    @SerializedName("billingEmailAddr")
    @Expose
    private String billingEmailAddr;
    @SerializedName("billingCtyId")
    @Expose
    private Integer billingCtyId;
    @SerializedName("billingCtyName")
    @Expose
    private String billingCtyName;
    @SerializedName("billingStId")
    @Expose
    private Integer billingStId;
    @SerializedName("billingStName")
    @Expose
    private String billingStName;
    @SerializedName("billingCntryId")
    @Expose
    private Integer billingCntryId;
    @SerializedName("billingCntryName")
    @Expose
    private String billingCntryName;
    @SerializedName("billingPostal")
    @Expose
    private String billingPostal;
    @SerializedName("shippingFirstName")
    @Expose
    private String shippingFirstName;
    @SerializedName("shippingLastName")
    @Expose
    private String shippingLastName;
    @SerializedName("shippingAddr1")
    @Expose
    private String shippingAddr1;
    @SerializedName("shippingAddr2")
    @Expose
    private String shippingAddr2;
    @SerializedName("shippingAddr3")
    @Expose
    private String shippingAddr3;
    @SerializedName("shippingMob1")
    @Expose
    private String shippingMob1;
    @SerializedName("shippingMob2")
    @Expose
    private String shippingMob2;
    @SerializedName("shippingEmailAddr")
    @Expose
    private String shippingEmailAddr;
    @SerializedName("shippingCtyId")
    @Expose
    private Integer shippingCtyId;
    @SerializedName("shippingCtyName")
    @Expose
    private String shippingCtyName;
    @SerializedName("shippingStId")
    @Expose
    private Integer shippingStId;
    @SerializedName("shippingStName")
    @Expose
    private String shippingStName;
    @SerializedName("shippingCntryId")
    @Expose
    private Integer shippingCntryId;
    @SerializedName("shippingCntryName")
    @Expose
    private String shippingCntryName;
    @SerializedName("shippingPostal")
    @Expose
    private String shippingPostal;
    @SerializedName("compPanNo")
    @Expose
    private String compPanNo;
    @SerializedName("compGstNo")
    @Expose
    private String compGstNo;
    @SerializedName("invDt")
    @Expose
    private String invDt;
    @SerializedName("slNo")
    @Expose
    private String slNo;
    @SerializedName("itmDscr")
    @Expose
    private String itmDscr;
    @SerializedName("itmUnPrc")
    @Expose
    private Object itmUnPrc;
    @SerializedName("itmQty")
    @Expose
    private Integer itmQty;
    @SerializedName("netAmt")
    @Expose
    private Object netAmt;
    @SerializedName("taxTypId")
    @Expose
    private Integer taxTypId;
    @SerializedName("taxAmt")
    @Expose
    private Object taxAmt;
    @SerializedName("total")
    @Expose
    private Object total;
    @SerializedName("curId")
    @Expose
    private Integer curId;
    @SerializedName("invStsId")
    @Expose
    private Integer invStsId;

    public Integer getCustInvId() {
        return custInvId;
    }

    public void setCustInvId(Integer custInvId) {
        this.custInvId = custInvId;
    }

    public String getUpdtStamp() {
        return updtStamp;
    }

    public void setUpdtStamp(String updtStamp) {
        this.updtStamp = updtStamp;
    }

    public String getUpdtUser() {
        return updtUser;
    }

    public void setUpdtUser(String updtUser) {
        this.updtUser = updtUser;
    }

    public String getOrglStamp() {
        return orglStamp;
    }

    public void setOrglStamp(String orglStamp) {
        this.orglStamp = orglStamp;
    }

    public String getOrglUser() {
        return orglUser;
    }

    public void setOrglUser(String orglUser) {
        this.orglUser = orglUser;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
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

    public String getProdSellBy() {
        return prodSellBy;
    }

    public void setProdSellBy(String prodSellBy) {
        this.prodSellBy = prodSellBy;
    }

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    public String getBillingAddr1() {
        return billingAddr1;
    }

    public void setBillingAddr1(String billingAddr1) {
        this.billingAddr1 = billingAddr1;
    }

    public String getBillingAddr2() {
        return billingAddr2;
    }

    public void setBillingAddr2(String billingAddr2) {
        this.billingAddr2 = billingAddr2;
    }

    public String getBillingAddr3() {
        return billingAddr3;
    }

    public void setBillingAddr3(String billingAddr3) {
        this.billingAddr3 = billingAddr3;
    }

    public String getBillingMob1() {
        return billingMob1;
    }

    public void setBillingMob1(String billingMob1) {
        this.billingMob1 = billingMob1;
    }

    public String getBillingMob2() {
        return billingMob2;
    }

    public void setBillingMob2(String billingMob2) {
        this.billingMob2 = billingMob2;
    }

    public String getBillingEmailAddr() {
        return billingEmailAddr;
    }

    public void setBillingEmailAddr(String billingEmailAddr) {
        this.billingEmailAddr = billingEmailAddr;
    }

    public Integer getBillingCtyId() {
        return billingCtyId;
    }

    public void setBillingCtyId(Integer billingCtyId) {
        this.billingCtyId = billingCtyId;
    }

    public String getBillingCtyName() {
        return billingCtyName;
    }

    public void setBillingCtyName(String billingCtyName) {
        this.billingCtyName = billingCtyName;
    }

    public Integer getBillingStId() {
        return billingStId;
    }

    public void setBillingStId(Integer billingStId) {
        this.billingStId = billingStId;
    }

    public String getBillingStName() {
        return billingStName;
    }

    public void setBillingStName(String billingStName) {
        this.billingStName = billingStName;
    }

    public Integer getBillingCntryId() {
        return billingCntryId;
    }

    public void setBillingCntryId(Integer billingCntryId) {
        this.billingCntryId = billingCntryId;
    }

    public String getBillingCntryName() {
        return billingCntryName;
    }

    public void setBillingCntryName(String billingCntryName) {
        this.billingCntryName = billingCntryName;
    }

    public String getBillingPostal() {
        return billingPostal;
    }

    public void setBillingPostal(String billingPostal) {
        this.billingPostal = billingPostal;
    }

    public String getShippingFirstName() {
        return shippingFirstName;
    }

    public void setShippingFirstName(String shippingFirstName) {
        this.shippingFirstName = shippingFirstName;
    }

    public String getShippingLastName() {
        return shippingLastName;
    }

    public void setShippingLastName(String shippingLastName) {
        this.shippingLastName = shippingLastName;
    }

    public String getShippingAddr1() {
        return shippingAddr1;
    }

    public void setShippingAddr1(String shippingAddr1) {
        this.shippingAddr1 = shippingAddr1;
    }

    public String getShippingAddr2() {
        return shippingAddr2;
    }

    public void setShippingAddr2(String shippingAddr2) {
        this.shippingAddr2 = shippingAddr2;
    }

    public String getShippingAddr3() {
        return shippingAddr3;
    }

    public void setShippingAddr3(String shippingAddr3) {
        this.shippingAddr3 = shippingAddr3;
    }

    public String getShippingMob1() {
        return shippingMob1;
    }

    public void setShippingMob1(String shippingMob1) {
        this.shippingMob1 = shippingMob1;
    }

    public String getShippingMob2() {
        return shippingMob2;
    }

    public void setShippingMob2(String shippingMob2) {
        this.shippingMob2 = shippingMob2;
    }

    public String getShippingEmailAddr() {
        return shippingEmailAddr;
    }

    public void setShippingEmailAddr(String shippingEmailAddr) {
        this.shippingEmailAddr = shippingEmailAddr;
    }

    public Integer getShippingCtyId() {
        return shippingCtyId;
    }

    public void setShippingCtyId(Integer shippingCtyId) {
        this.shippingCtyId = shippingCtyId;
    }

    public String getShippingCtyName() {
        return shippingCtyName;
    }

    public void setShippingCtyName(String shippingCtyName) {
        this.shippingCtyName = shippingCtyName;
    }

    public Integer getShippingStId() {
        return shippingStId;
    }

    public void setShippingStId(Integer shippingStId) {
        this.shippingStId = shippingStId;
    }

    public String getShippingStName() {
        return shippingStName;
    }

    public void setShippingStName(String shippingStName) {
        this.shippingStName = shippingStName;
    }

    public Integer getShippingCntryId() {
        return shippingCntryId;
    }

    public void setShippingCntryId(Integer shippingCntryId) {
        this.shippingCntryId = shippingCntryId;
    }

    public String getShippingCntryName() {
        return shippingCntryName;
    }

    public void setShippingCntryName(String shippingCntryName) {
        this.shippingCntryName = shippingCntryName;
    }

    public String getShippingPostal() {
        return shippingPostal;
    }

    public void setShippingPostal(String shippingPostal) {
        this.shippingPostal = shippingPostal;
    }

    public String getCompPanNo() {
        return compPanNo;
    }

    public void setCompPanNo(String compPanNo) {
        this.compPanNo = compPanNo;
    }

    public String getCompGstNo() {
        return compGstNo;
    }

    public void setCompGstNo(String compGstNo) {
        this.compGstNo = compGstNo;
    }

    public String getInvDt() {
        return invDt;
    }

    public void setInvDt(String invDt) {
        this.invDt = invDt;
    }

    public String getSlNo() {
        return slNo;
    }

    public void setSlNo(String slNo) {
        this.slNo = slNo;
    }

    public String getItmDscr() {
        return itmDscr;
    }

    public void setItmDscr(String itmDscr) {
        this.itmDscr = itmDscr;
    }

    public Object getItmUnPrc() {
        return itmUnPrc;
    }

    public void setItmUnPrc(Object itmUnPrc) {
        this.itmUnPrc = itmUnPrc;
    }

    public Integer getItmQty() {
        return itmQty;
    }

    public void setItmQty(Integer itmQty) {
        this.itmQty = itmQty;
    }

    public Object getNetAmt() {
        return netAmt;
    }

    public void setNetAmt(Object netAmt) {
        this.netAmt = netAmt;
    }

    public Integer getTaxTypId() {
        return taxTypId;
    }

    public void setTaxTypId(Integer taxTypId) {
        this.taxTypId = taxTypId;
    }

    public Object getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(Object taxAmt) {
        this.taxAmt = taxAmt;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public Integer getCurId() {
        return curId;
    }

    public void setCurId(Integer curId) {
        this.curId = curId;
    }

    public Integer getInvStsId() {
        return invStsId;
    }

    public void setInvStsId(Integer invStsId) {
        this.invStsId = invStsId;
    }

}
