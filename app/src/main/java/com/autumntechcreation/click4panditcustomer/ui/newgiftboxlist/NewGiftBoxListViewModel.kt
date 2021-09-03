package com.autumntechcreation.click4panditcustomer.ui.newgiftboxlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.adapter.NewPujaGiftBoxListAdapter
import com.autumntechcreation.click4panditcustomer.adapter.NewPujaItemBoxListAdapter
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist.NewPujaBoxItemListRepository
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.google.gson.Gson
import java.util.ArrayList
import javax.inject.Inject

class NewGiftBoxListViewModel @Inject constructor(private val mNewGiftBoxListRepository: NewGiftBoxListRepository) : ViewModel() {
    var newPujaGiftBoxListAdapter: NewPujaGiftBoxListAdapter? = null
        internal set
    var newPujaGiftBoxList: MutableLiveData<ArrayList<NewPujaItemKitListModel>>? =
        MutableLiveData<ArrayList<NewPujaItemKitListModel>>();
    var pujaGiftBoxListLiveData: LiveData<Resource<List<NewPujaItemKitListModel>>>? = null
    fun init() {
        newPujaGiftBoxListAdapter = NewPujaGiftBoxListAdapter(R.layout.singlerow_newgiftboxlist, this)
    }

    fun getPujaGiftBoxList(): LiveData<Resource<List<NewPujaItemKitListModel>>> {
        pujaGiftBoxListLiveData = MutableLiveData()
        pujaGiftBoxListLiveData = mNewGiftBoxListRepository.getPujaGiftBoxList()
        return pujaGiftBoxListLiveData as LiveData<Resource<List<NewPujaItemKitListModel>>>

    }

    fun getAdapter(): NewPujaGiftBoxListAdapter? {
        return newPujaGiftBoxListAdapter
    }

    fun setPujaGiftBoxAdapter( list: List<NewPujaItemKitListModel>) {
        val gson = Gson()
        val json = gson.toJson(list)
        var modifyList=ArrayList<NewPujaItemKitListModel>();
        for (item in list) {
            if(item.prodCtgryId==1004){
                modifyList.add(item)
            }
            newPujaGiftBoxList!!.value=modifyList;
        }

        this.newPujaGiftBoxListAdapter!!.setNewPujaGiftBoxAdapterList(newPujaGiftBoxList!!.value!!)
        this.newPujaGiftBoxListAdapter!!.notifyDataSetChanged()
    }



    fun getPujaGiftBoxName(position: Int): String {
        val list = newPujaGiftBoxList!!.getValue()
        return list!!.get(position).prodMasterName!!
    }fun getPujaGiftBoxQuantity(position: Int): String {
        val list = newPujaGiftBoxList!!.getValue()
        return list!!.get(position).prodUnit?.toString()!!+" "+list!!.get(position).prodWtDscr
    }fun getPujaGiftBoxPrice(position: Int): String {
        val list = newPujaGiftBoxList!!.getValue()
        return list!!.get(position).prodPrice.toString()
    }

}