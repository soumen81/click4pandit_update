package com.autumntechcreation.click4panditcustomer.ui.wishlist;

import android.graphics.drawable.Drawable;

public class WishListItemModel {
    private String productWishName;
    private String productWishQuantity;
    private String productWishPrice;
    private String subTotalWish;
    private Drawable iconWishImage;

    public String getProductWishName() {
        return productWishName;
    }

    public void setProductWishName(String productWishName) {
        this.productWishName = productWishName;
    }

    public String getProductWishQuantity() {
        return productWishQuantity;
    }

    public void setProductWishQuantity(String productWishQuantity) {
        this.productWishQuantity = productWishQuantity;
    }

    public String getProductWishPrice() {
        return productWishPrice;
    }

    public void setProductWishPrice(String productWishPrice) {
        this.productWishPrice = productWishPrice;
    }

    public String getSubTotalWish() {
        return subTotalWish;
    }

    public void setSubTotalWish(String subTotalWish) {
        this.subTotalWish = subTotalWish;
    }

    public Drawable getIconWishImage() {
        return iconWishImage;
    }

    public void setIconWishImage(Drawable iconWishImage) {
        this.iconWishImage = iconWishImage;
    }
}
