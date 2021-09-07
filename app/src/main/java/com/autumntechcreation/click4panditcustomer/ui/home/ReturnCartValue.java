package com.autumntechcreation.click4panditcustomer.ui.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReturnCartValue {
    @SerializedName("cartItemCount")
    @Expose
    private Integer cartItemCount;
    @SerializedName("prodMasterIdArray")
    @Expose
    private List<Integer> prodMasterIdArray = null;

    public Integer getCartItemCount() {
        return cartItemCount;
    }

    public void setCartItemCount(Integer cartItemCount) {
        this.cartItemCount = cartItemCount;
    }

    public List<Integer> getProdMasterIdArray() {
        return prodMasterIdArray;
    }

    public void setProdMasterIdArray(List<Integer> prodMasterIdArray) {
        this.prodMasterIdArray = prodMasterIdArray;
    }
}
