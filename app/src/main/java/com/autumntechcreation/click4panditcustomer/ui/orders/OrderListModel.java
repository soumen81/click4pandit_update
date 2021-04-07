package com.autumntechcreation.click4panditcustomer.ui.orders;

public class OrderListModel {

    private String orderNo;
    private String pujaName;
    private String dateTime;
    private String paymentStatus;
    private String amount;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPujaName() {
        return pujaName;
    }

    public void setPujaName(String pujaName) {
        this.pujaName = pujaName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
