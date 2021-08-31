package com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.adapter.NewPujaItemKitListAdapter
import com.autumntechcreation.click4panditcustomer.ui.shopcategory.ShopCategoryRepository
import java.util.ArrayList
import javax.inject.Inject

class NewPujaItemKitListViewModel @Inject constructor(private val mNewPujaItemKitListRepository: NewPujaItemKitListRepository) : ViewModel() {
    var newPujaItemKitListAdapter: NewPujaItemKitListAdapter? = null
        internal set
    var newPujaItemKitList: MutableLiveData<ArrayList<NewPujaItemKitListModel>>? =
        MutableLiveData<ArrayList<NewPujaItemKitListModel>>();
    fun init() {
        newPujaItemKitListAdapter = NewPujaItemKitListAdapter(R.layout.singlerow_newpujaitemkitlist, this)
    }

    fun getAdapter(): NewPujaItemKitListAdapter? {
        return newPujaItemKitListAdapter
    }

}