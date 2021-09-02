package com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.adapter.NewPujaBrassItemListAdapter
import com.autumntechcreation.click4panditcustomer.adapter.NewPujaItemBoxListAdapter
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.google.gson.Gson
import java.util.ArrayList
import javax.inject.Inject

class NewPujaBrassItemListViewModel @Inject constructor(private val mNewPujaBrassItemListRepository: NewPujaBrassItemListRepository) : ViewModel() {
    var newPujaBrassItemListAdapter: NewPujaBrassItemListAdapter? = null
        internal set
    var newPujaItemBrassList: MutableLiveData<ArrayList<NewPujaItemKitListModel>>? =
        MutableLiveData<ArrayList<NewPujaItemKitListModel>>();
    var pujaItemBrassListLiveData: LiveData<Resource<List<NewPujaItemKitListModel>>>? = null
    fun init() {
        newPujaBrassItemListAdapter = NewPujaBrassItemListAdapter(R.layout.singlerow_newpujabrassitemlist, this)
    }

    fun getPujaItemBrassList(): LiveData<Resource<List<NewPujaItemKitListModel>>> {
        pujaItemBrassListLiveData = MutableLiveData()
        pujaItemBrassListLiveData = mNewPujaBrassItemListRepository.getPujaItemBrassList()
        return pujaItemBrassListLiveData as LiveData<Resource<List<NewPujaItemKitListModel>>>

    }

    fun getAdapter(): NewPujaBrassItemListAdapter? {
        return newPujaBrassItemListAdapter
    }

    fun setPujaItemBrassAdapter( list: List<NewPujaItemKitListModel>) {

        val gson = Gson()
        val json = gson.toJson(list)
        var modifyList=ArrayList<NewPujaItemKitListModel>();
        for (item in list) {
            if(item.prodCtgryId==1003){
                modifyList.add(item)
            }
            newPujaItemBrassList!!.value=modifyList;
        }
        this.newPujaBrassItemListAdapter!!.setNewPujaItemBrassAdapterList(newPujaItemBrassList!!.value!!)
        this.newPujaBrassItemListAdapter!!.notifyDataSetChanged()
    }



    fun getPujaItemBrassName(position: Int): String {
        val list = newPujaItemBrassList!!.getValue()
        return list!!.get(position).prodMasterName!!
    }fun getPujaItemBrassQuantity(position: Int): String {
        val list = newPujaItemBrassList!!.getValue()
        return list!!.get(position).prodUnit?.toString()!!+" "+list!!.get(position).prodWtDscr
    }fun getPujaItemBrassPrice(position: Int): String {
        val list = newPujaItemBrassList!!.getValue()
        return list!!.get(position).prodPrice.toString()
    }
}