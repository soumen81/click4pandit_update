package com.autumntechcreation.click4panditcustomer.ui.shopcategory

import android.graphics.drawable.Drawable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ShopCategoryModel {
    @SerializedName("prodCtgryId")
    @Expose
     var prodCtgryId: Int? = null

    @SerializedName("prodCtgryDscr")
    @Expose
     var prodCtgryDscr: String? = null

    @SerializedName("isSelected")
    @Expose
     var isSelected: Boolean? = null



    @SerializedName("iconPujaBoxImage")
    @Expose
    var iconPujaBoxImage: Drawable? = null

}