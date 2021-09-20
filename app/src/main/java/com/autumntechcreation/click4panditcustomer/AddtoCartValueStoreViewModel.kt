package com.autumntechcreation.click4panditcustomer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.ui.home.CartItemCountModel
import javax.inject.Inject

class AddtoCartValueStoreViewModel @Inject constructor(): ViewModel() {
    private val mutableSelectedItem = MutableLiveData<CartItemCountModel>()
    val selectedItem: LiveData<CartItemCountModel> get() = mutableSelectedItem
    fun selectItem(item: CartItemCountModel) {
        mutableSelectedItem.value = item
    }
}