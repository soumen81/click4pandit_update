package com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist

import android.util.Log
import android.view.View
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
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.AddWishListItemModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitAddtoCartOrBuyNowModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent
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
    private val mSelectedBuyNowPujaItemBox = SingleLiveEvent<Int>()
    private val mSelectedAddtoCartPujaItemBox = SingleLiveEvent<Int>()
    private val mSelectedWishListPujaBoxItem = SingleLiveEvent<Int>()
    private val mSelectedRedirectedPujaBoxItemDetails = SingleLiveEvent<Int>()
    var newPujaItemBoxAddtoCartOrBuyNowModelLiveData: LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>? = null
    var addWishListPujaBoxModelLiveData: LiveData<Resource<AddWishListItemModel>>? = null
    fun init() {
        newPujaItemBoxListAdapter = NewPujaItemBoxListAdapter(R.layout.singlerow_newpujaitemboxlist, this)
    }

    fun getNewPujaItemBoxAddtoCartOrBuyNow(prodMasterId: Int, productPrice: Int): LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>> {
        newPujaItemBoxAddtoCartOrBuyNowModelLiveData = MutableLiveData()
        newPujaItemBoxAddtoCartOrBuyNowModelLiveData = mNewPujaBoxItemListRepository.getAddtoCartBuyNowForPujaBox(prodMasterId, productPrice)
        return newPujaItemBoxAddtoCartOrBuyNowModelLiveData as LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>

    }
    fun getAddForWishListItem(prodMasterId:Int,prodCustWishLisQty:Int,prodCustscWishListRate:Double): LiveData<Resource<AddWishListItemModel>> {
        addWishListPujaBoxModelLiveData = MutableLiveData()
        addWishListPujaBoxModelLiveData = mNewPujaBoxItemListRepository.getAddItemForWishPujaBoxListItem(prodMasterId, prodCustWishLisQty,prodCustscWishListRate)
        return addWishListPujaBoxModelLiveData as LiveData<Resource<AddWishListItemModel>>

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

    fun onClickBuyNowPujaBoxItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedBuyNowPujaItemBox.setValue(pos)
    }

    fun getSelectedPujaItemBoxListItem(): SingleLiveEvent<Int> {
        return mSelectedBuyNowPujaItemBox
    }
    fun onClickAddtoCartPujaBoxItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedAddtoCartPujaItemBox.setValue(pos)
    }

    fun getSelectedAddtoCartPujaItemBoxListItem(): SingleLiveEvent<Int> {
        return mSelectedAddtoCartPujaItemBox
    }

    fun onClickWishListPujaBoxItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedWishListPujaBoxItem.setValue(pos)
    }

    fun getSelectedpujaBoxWishListItem(): SingleLiveEvent<Int> {
        return mSelectedWishListPujaBoxItem
    }fun onClickRedirecNewPujaBoxItemDetails(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedRedirectedPujaBoxItemDetails.setValue(pos)
    }

    fun getRedirecNewPujaBoxItemDetails(): SingleLiveEvent<Int> {
        return mSelectedRedirectedPujaBoxItemDetails
    }


}