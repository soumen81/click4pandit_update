package com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.adapter.NewPujaItemBoxListAdapter
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.bumptech.glide.Glide
import com.google.gson.Gson
import java.util.*
import javax.inject.Inject

class NewPujaBoxItemListViewModel @Inject constructor(private val mNewPujaBoxItemListRepository: NewPujaBoxItemListRepository) : ViewModel() {
    var newPujaItemBoxListAdapter: NewPujaItemBoxListAdapter? = null
        internal set
    var newPujaItemBoxList: MutableLiveData<ArrayList<NewPujaItemKitListModel>>? =
        MutableLiveData<ArrayList<NewPujaItemKitListModel>>();
    var pujaItemBoxListLiveData: LiveData<Resource<List<NewPujaItemKitListModel>>>? = null
    fun init() {
        newPujaItemBoxListAdapter = NewPujaItemBoxListAdapter(R.layout.singlerow_newpujaitemboxlist, this)
    }


    fun getPujaItemBoxList(): LiveData<Resource<List<NewPujaItemKitListModel>>> {
        pujaItemBoxListLiveData = MutableLiveData()
        pujaItemBoxListLiveData = mNewPujaBoxItemListRepository.getPujaItemBoxList()
        return pujaItemBoxListLiveData as LiveData<Resource<List<NewPujaItemKitListModel>>>

    }

    fun getAdapter(): NewPujaItemBoxListAdapter? {
        return newPujaItemBoxListAdapter
    }

    fun setPujaItemBoxAdapter( list: List<NewPujaItemKitListModel>) {
        val gson = Gson()
        val json = gson.toJson(list)
        var modifyList=ArrayList<NewPujaItemKitListModel>();
        for (item in list) {
            if(item.prodCtgryId==1001){
                modifyList.add(item)
            }
            newPujaItemBoxList!!.value=modifyList;
        }

        this.newPujaItemBoxListAdapter!!.setNewPujaItemBoxAdapterList(newPujaItemBoxList!!.value!!)
        this.newPujaItemBoxListAdapter!!.notifyDataSetChanged()
    }



    fun getPujaItemBoxName(position: Int): String {
        val list = newPujaItemBoxList!!.getValue()
        return list!!.get(position).prodMasterName!!
    }fun getPujaItemBoxQuantity(position: Int): String {
        val list = newPujaItemBoxList!!.getValue()
        return list!!.get(position).prodUnit?.toString()!!+" "+list!!.get(position).prodWtDscr
    }fun getPujaItemBoxPrice(position: Int): String {
        val list = newPujaItemBoxList!!.getValue()
        return list!!.get(position).prodPrice.toString()
    }



}