package com.autumntechcreation.click4panditcustomer.ui.shopcategory

import android.view.View
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent
import javax.inject.Inject

class ShopCategoryViewModel @Inject constructor(private val mShopCategoryRepository: ShopCategoryRepository) : ViewModel() {
    val mSelectPujaItemKit = SingleLiveEvent<Int>()




}