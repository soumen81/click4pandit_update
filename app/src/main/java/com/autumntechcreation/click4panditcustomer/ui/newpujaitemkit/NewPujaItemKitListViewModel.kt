package com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.adapter.NewPujaItemKitListAdapter
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.bumptech.glide.Glide
import com.google.gson.Gson
import java.util.*
import javax.inject.Inject


/*@BindingAdapter("pujaitemkit_img")
fun pujaitemkitImg(view: ImageView, position: Int) {

    var newPujaItemKitListt: MutableLiveData<ArrayList<NewPujaItemKitListModel>>? =
        MutableLiveData<ArrayList<NewPujaItemKitListModel>>();
    val list = newPujaItemKitListt!!.getValue()

    Glide.with(view.context)
        .load(list!!.get(position).prodImgDataURL)
        .into(view)

}*/




class NewPujaItemKitListViewModel @Inject constructor(private val mNewPujaItemKitListRepository: NewPujaItemKitListRepository) : ViewModel() {
    var newPujaItemKitListAdapter: NewPujaItemKitListAdapter? = null
        internal set
    var newPujaItemKitList: MutableLiveData<ArrayList<NewPujaItemKitListModel>>? =
        MutableLiveData<ArrayList<NewPujaItemKitListModel>>();
    var pujaItemKitListLiveData: LiveData<Resource<List<NewPujaItemKitListModel>>>? = null

    fun init() {

        newPujaItemKitListAdapter = NewPujaItemKitListAdapter(R.layout.singlerow_newpujaitemkitlist, this)
    }


    fun getPujaItemKitList(): LiveData<Resource<List<NewPujaItemKitListModel>>> {
        pujaItemKitListLiveData = MutableLiveData()
        pujaItemKitListLiveData = mNewPujaItemKitListRepository.getPujaItemKitList()
        return pujaItemKitListLiveData as LiveData<Resource<List<NewPujaItemKitListModel>>>

    }


    fun getAdapter(): NewPujaItemKitListAdapter? {
        return newPujaItemKitListAdapter
    }


    fun setPujaItemKitAdapter( list: ArrayList<NewPujaItemKitListModel>) {
        val gson = Gson()
        val json = gson.toJson(list)
        var modifyList=ArrayList<NewPujaItemKitListModel>();
        for (item in list) {
            if(item.prodCtgryId==1002){
                modifyList.add(item)
            }
            newPujaItemKitList!!.value=modifyList;
        }


        this.newPujaItemKitListAdapter!!.setNewPujaItemKitAdapterList(newPujaItemKitList!!.value!!)
        this.newPujaItemKitListAdapter!!.notifyDataSetChanged()
    }

    fun getPujaItemKitName(position: Int): String {
        val list = newPujaItemKitList!!.getValue()
        return list!!.get(position).prodMasterName!!
    }fun getPujaItemKitQuantity(position: Int): String {
        val list = newPujaItemKitList!!.getValue()
        return list!!.get(position).prodUnit?.toString()!!+" "+list!!.get(position).prodUnitTypDscr
    }fun getPujaItemKitPrice(position: Int): String {
        val list = newPujaItemKitList!!.getValue()
        return list!!.get(position).prodPrice.toString()
    }

/*    @BindingAdapter("pujaItemKitListAdapter")
    fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }*/
  /*  @BindingAdapter("pujaitemkit_img")
    fun pujaitemkitimg(img: ImageView, position: Int) {
        val list = newPujaItemKitList!!.getValue()
        Glide.with(img.context)
            .load(list!!.get(position).prodImgDataURL)
            .into(img)

    }*/



}