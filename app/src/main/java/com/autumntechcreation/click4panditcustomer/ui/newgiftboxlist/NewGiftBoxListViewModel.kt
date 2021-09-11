package com.autumntechcreation.click4panditcustomer.ui.newgiftboxlist

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.adapter.NewPujaGiftBoxListAdapter
import com.autumntechcreation.click4panditcustomer.adapter.NewPujaItemBoxListAdapter
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist.NewPujaBoxItemListRepository
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.AddWishListItemModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitAddtoCartOrBuyNowModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent
import com.google.gson.Gson
import java.util.ArrayList
import javax.inject.Inject

class NewGiftBoxListViewModel @Inject constructor(private val mNewGiftBoxListRepository: NewGiftBoxListRepository) : ViewModel() {
    var newPujaGiftBoxListAdapter: NewPujaGiftBoxListAdapter? = null
        internal set
    var newPujaGiftBoxList: MutableLiveData<ArrayList<NewPujaItemKitListModel>>? =
        MutableLiveData<ArrayList<NewPujaItemKitListModel>>();
    var pujaGiftBoxListLiveData: LiveData<Resource<List<NewPujaItemKitListModel>>>? = null
    private val mSelectedBuyNowPujaItemGiftBoxes = SingleLiveEvent<Int>()
    private val mSelectedAddtoCartPujaItemGiftBoxes = SingleLiveEvent<Int>()
    private val mSelectedWishListGiftBox = SingleLiveEvent<Int>()
    var addWishListGiftBoxModelLiveData: LiveData<Resource<AddWishListItemModel>>? = null
    var newGiftBoxAddtoCartOrBuyNowModelLiveData: LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>? = null
    fun init() {
        newPujaGiftBoxListAdapter = NewPujaGiftBoxListAdapter(R.layout.singlerow_newgiftboxlist, this)
    }


    fun getNewGiftBoxAddtoCartOrBuyNow(prodMasterId: Int, productPrice: Int): LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>> {
        newGiftBoxAddtoCartOrBuyNowModelLiveData = MutableLiveData()
        newGiftBoxAddtoCartOrBuyNowModelLiveData = mNewGiftBoxListRepository.getAddtoCartBuyNowForGiftBox(prodMasterId, productPrice)
        return newGiftBoxAddtoCartOrBuyNowModelLiveData as LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>

    }



    fun getAddForWishListGiftBoxItem(prodMasterId:Int,prodCustWishLisQty:Int,prodCustscWishListRate:Double): LiveData<Resource<AddWishListItemModel>> {
        addWishListGiftBoxModelLiveData = MutableLiveData()
        addWishListGiftBoxModelLiveData = mNewGiftBoxListRepository.getAddItemForWishGiftBoxListItem(prodMasterId, prodCustWishLisQty,prodCustscWishListRate)
        return addWishListGiftBoxModelLiveData as LiveData<Resource<AddWishListItemModel>>

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
    fun onClickBuyNowGiftBoxItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedBuyNowPujaItemGiftBoxes.setValue(pos)
    }

    fun getSelectedPujaItemGiftBoxListItem(): SingleLiveEvent<Int> {
        return mSelectedBuyNowPujaItemGiftBoxes
    }

    fun onClickAddtoCartGiftBoxItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedAddtoCartPujaItemGiftBoxes.setValue(pos)
    }

    fun getSelectedAddtoCartGiftBoxListItem(): SingleLiveEvent<Int> {
        return mSelectedAddtoCartPujaItemGiftBoxes
    }

    fun onClickWishListGiftBoxList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedWishListGiftBox.setValue(pos)
    }

    fun getSelectedGiftBoxWishListItem(): SingleLiveEvent<Int> {
        return mSelectedWishListGiftBox
    }
}