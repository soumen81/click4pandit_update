package com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemlist

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.adapter.NewPujaBrassItemListAdapter
import com.autumntechcreation.click4panditcustomer.adapter.NewPujaItemBoxListAdapter
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.AddWishListItemModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitAddtoCartOrBuyNowModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent
import com.google.gson.Gson
import java.util.ArrayList
import javax.inject.Inject

class NewPujaBrassItemListViewModel @Inject constructor(private val mNewPujaBrassItemListRepository: NewPujaBrassItemListRepository) : ViewModel() {
    var newPujaBrassItemListAdapter: NewPujaBrassItemListAdapter? = null
        internal set
    var newPujaItemBrassList: MutableLiveData<ArrayList<NewPujaItemKitListModel>>? =
        MutableLiveData<ArrayList<NewPujaItemKitListModel>>();
    var pujaItemBrassListLiveData: LiveData<Resource<List<NewPujaItemKitListModel>>>? = null
    var newPujaItemBrassAddtoCartOrBuyNowModelLiveData: LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>? = null
    private val mSelectedBuyNowPujaItemBrass = SingleLiveEvent<Int>()
    private val mSelectedAddtoCartPujaBrassItem = SingleLiveEvent<Int>()
    private val mSelectedWishListPujaBrassItem = SingleLiveEvent<Int>()
    private val mSelectedRedirectedFromPujaBrassItem = SingleLiveEvent<Int>()
    var addWishListPujaBrassModelLiveData: LiveData<Resource<AddWishListItemModel>>? = null
    fun init() {
        newPujaBrassItemListAdapter = NewPujaBrassItemListAdapter(R.layout.singlerow_newpujabrassitemlist, this)
    }

    fun getNewPujaItemBrassAddtoCartOrBuyNow(prodMasterId: Int, productPrice: Int): LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>> {
        newPujaItemBrassAddtoCartOrBuyNowModelLiveData = MutableLiveData()
        newPujaItemBrassAddtoCartOrBuyNowModelLiveData = mNewPujaBrassItemListRepository.getAddtoCartBuyNowForPujaBrass(prodMasterId, productPrice)
        return newPujaItemBrassAddtoCartOrBuyNowModelLiveData as LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>

    }
    fun getAddForWishListPujaBrassItem(prodMasterId:Int,prodCustWishLisQty:Int,prodCustscWishListRate:Double): LiveData<Resource<AddWishListItemModel>> {
        addWishListPujaBrassModelLiveData = MutableLiveData()
        addWishListPujaBrassModelLiveData = mNewPujaBrassItemListRepository.getAddItemForWishPujaBrassListItem(prodMasterId, prodCustWishLisQty,prodCustscWishListRate)
        return addWishListPujaBrassModelLiveData as LiveData<Resource<AddWishListItemModel>>

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

    fun onClickBuyNowPujaBrassItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedBuyNowPujaItemBrass.setValue(pos)
    }

    fun getSelectedPujaItemBrassListItem(): SingleLiveEvent<Int> {
        return mSelectedBuyNowPujaItemBrass
    }

    fun onClickAddtoCartPujaBrassItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedAddtoCartPujaBrassItem.setValue(pos)
    }

    fun getSelectedAddtoCartPujaItemBrassListItem(): SingleLiveEvent<Int> {
        return mSelectedAddtoCartPujaBrassItem
    }

    fun onClickWishListPujaBrassItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedWishListPujaBrassItem.setValue(pos)
    }

    fun getSelectedpujaBoxWishListItem(): SingleLiveEvent<Int> {
        return mSelectedWishListPujaBrassItem
    }

    fun onClickRedirectFromPujaBrassItemList(view: View,pos: Int){
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedRedirectedFromPujaBrassItem.setValue(pos)
    }
    fun getSelecteRedirectFromPujaBrassItem(): SingleLiveEvent<Int> {
        return mSelectedRedirectedFromPujaBrassItem
    }
}