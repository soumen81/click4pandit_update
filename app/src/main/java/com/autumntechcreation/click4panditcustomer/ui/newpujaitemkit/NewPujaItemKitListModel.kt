package com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class NewPujaItemKitListModel() : Parcelable {
    @SerializedName("totalCount")
    @Expose
     var totalCount: Int? = null

    @SerializedName("rowNo")
    @Expose
     var rowNo: Int? = null

    @SerializedName("prodMasterId")
    @Expose
     var prodMasterId: Int? = null

    @SerializedName("prodCtgryId")
    @Expose
     var prodCtgryId: Int? = null

    @SerializedName("prodCtgryDscr")
    @Expose
     var prodCtgryDscr: String? = null

    @SerializedName("prodTypId")
    @Expose
     var prodTypId: Int? = null

    @SerializedName("prodTypDscr")
    @Expose
     var prodTypDscr: String? = null

    @SerializedName("prodMasterName")
    @Expose
     var prodMasterName: String? = null

    @SerializedName("prodMtrlTypId")
    @Expose
     var prodMtrlTypId: Int? = null

    @SerializedName("prodMtrlTypDscr")
    @Expose
     var prodMtrlTypDscr: String? = null

    @SerializedName("prodWt")
    @Expose
     var prodWt: Double? = null

    @SerializedName("prodWtUmTypId")
    @Expose
     var prodWtUmTypId: Int? = null

    @SerializedName("prodWtDscr")
    @Expose
     var prodWtDscr: String? = null

    @SerializedName("prodUnit")
    @Expose
     var prodUnit: Int? = null

    @SerializedName("prodUnitTypDscr")
    @Expose
     var prodUnitTypDscr: String? = null

    @SerializedName("prodPkgUnit")
    @Expose
     var prodPkgUnit: Int? = null

    @SerializedName("prodPkgTypDscr")
    @Expose
     var prodPkgTypDscr: String? = null

    @SerializedName("curCode")
    @Expose
     var curCode: String? = null

    @SerializedName("prodPrice")
    @Expose
     var prodPrice: Double? = null

    @SerializedName("delFlg")
    @Expose
     var delFlg: String? = null

    @SerializedName("cloudImgId")
    @Expose
     var cloudImgId: String? = null

    @SerializedName("prodImgDataURL")
    @Expose
     var prodImgDataURL: String? = null

    constructor(parcel: Parcel) : this() {
        totalCount = parcel.readValue(Int::class.java.classLoader) as? Int
        rowNo = parcel.readValue(Int::class.java.classLoader) as? Int
        prodMasterId = parcel.readValue(Int::class.java.classLoader) as? Int
        prodCtgryId = parcel.readValue(Int::class.java.classLoader) as? Int
        prodCtgryDscr = parcel.readString()
        prodTypId = parcel.readValue(Int::class.java.classLoader) as? Int
        prodTypDscr = parcel.readString()
        prodMasterName = parcel.readString()
        prodMtrlTypId = parcel.readValue(Int::class.java.classLoader) as? Int
        prodMtrlTypDscr = parcel.readString()
        prodWt = parcel.readValue(Double::class.java.classLoader) as? Double
        prodWtUmTypId = parcel.readValue(Int::class.java.classLoader) as? Int
        prodWtDscr = parcel.readString()
        prodUnit = parcel.readValue(Int::class.java.classLoader) as? Int
        prodUnitTypDscr = parcel.readString()
        prodPkgUnit = parcel.readValue(Int::class.java.classLoader) as? Int
        prodPkgTypDscr = parcel.readString()
        curCode = parcel.readString()
        prodPrice = parcel.readValue(Double::class.java.classLoader) as? Double
        delFlg = parcel.readString()
        cloudImgId = parcel.readString()
        prodImgDataURL = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(totalCount)
        parcel.writeValue(rowNo)
        parcel.writeValue(prodMasterId)
        parcel.writeValue(prodCtgryId)
        parcel.writeString(prodCtgryDscr)
        parcel.writeValue(prodTypId)
        parcel.writeString(prodTypDscr)
        parcel.writeString(prodMasterName)
        parcel.writeValue(prodMtrlTypId)
        parcel.writeString(prodMtrlTypDscr)
        parcel.writeValue(prodWt)
        parcel.writeValue(prodWtUmTypId)
        parcel.writeString(prodWtDscr)
        parcel.writeValue(prodUnit)
        parcel.writeString(prodUnitTypDscr)
        parcel.writeValue(prodPkgUnit)
        parcel.writeString(prodPkgTypDscr)
        parcel.writeString(curCode)
        parcel.writeValue(prodPrice)
        parcel.writeString(delFlg)
        parcel.writeString(cloudImgId)
        parcel.writeString(prodImgDataURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewPujaItemKitListModel> {
        override fun createFromParcel(parcel: Parcel): NewPujaItemKitListModel {
            return NewPujaItemKitListModel(parcel)
        }

        override fun newArray(size: Int): Array<NewPujaItemKitListModel?> {
            return arrayOfNulls(size)
        }
    }


}