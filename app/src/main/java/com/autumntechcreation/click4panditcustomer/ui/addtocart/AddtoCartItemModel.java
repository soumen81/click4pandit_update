package com.autumntechcreation.click4panditcustomer.ui.addtocart;

import android.graphics.drawable.Drawable;

public class AddtoCartItemModel {
    private String productName;
    private String productQuantity;
    private String productPrice;
    private String subTotal;
    private Drawable iconAddtoCartImage;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public Drawable getIconAddtoCartImage() {
        return iconAddtoCartImage;
    }

    public void setIconAddtoCartImage(Drawable iconAddtoCartImage) {
        this.iconAddtoCartImage = iconAddtoCartImage;
    }
}
