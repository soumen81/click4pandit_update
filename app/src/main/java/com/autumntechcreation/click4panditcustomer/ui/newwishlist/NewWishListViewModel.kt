package com.autumntechcreation.click4panditcustomer.ui.newwishlist

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.adapter.NewAddtoCartListAdapter
import com.autumntechcreation.click4panditcustomer.adapter.NewWishListItemAdapter
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist.NewAddtoCartListModel
import com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist.NewAddtoCartListRepository
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitAddtoCartOrBuyNowModel
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent
import java.util.ArrayList
import javax.inject.Inject

class NewWishListViewModel @Inject constructor(private val mNewWishListRepository: NewWishListRepository) : ViewModel() {
    var mNewWishListItemAdapter: NewWishListItemAdapter? = null
        internal set
    var newWishList: MutableLiveData<ArrayList<NewWishListItemModel>>? =
        MutableLiveData<ArrayList<NewWishListItemModel>>();
    var newWishListLiveData: LiveData<Resource<List<NewWishListItemModel>>>? = null
    private val mSelectedDeleteForWishListItem = SingleLiveEvent<Int>()
    var deleteWishListModelLiveData: LiveData<Resource<DeleteWishListModel>>? = null
    fun init() {
        mNewWishListItemAdapter = NewWishListItemAdapter(R.layout.singlerow_newwishlistitem, this)
    }
    fun getWishItemList(): LiveData<Resource<List<NewWishListItemModel>>> {
        newWishListLiveData = MutableLiveData()
        newWishListLiveData = mNewWishListRepository.getWishListItem()
        return newWishListLiveData as LiveData<Resource<List<NewWishListItemModel>>>

    }

    fun getRemoveForWishListItem(productCustWishListId:Int,prodMasterId:Int,prodCustWishLisQty:Int,prodCustscWishListRate:Double): LiveData<Resource<DeleteWishListModel>> {
        deleteWishListModelLiveData = MutableLiveData()
        deleteWishListModelLiveData = mNewWishListRepository.getRemoveForWishListItem(productCustWishListId, prodMasterId,prodCustWishLisQty,prodCustscWishListRate)
        return deleteWishListModelLiveData as LiveData<Resource<DeleteWishListModel>>

    }





    fun setWishListItemAdapter(list: ArrayList<NewWishListItemModel>) {
        newWishList = MutableLiveData()
        newWishList!!.value = list
        this.mNewWishListItemAdapter!!.setNewWishListAdapterList(list)
        this.mNewWishListItemAdapter!!.notifyDataSetChanged()
    }

    fun getAdapter(): NewWishListItemAdapter? {
        return mNewWishListItemAdapter
    }
    fun getWiishProductName(position: Int): String {
        val list = newWishList!!.getValue()
        return list!!.get(position).prodMasterName!!
    }fun getWishProductPrice(position: Int): String {
        val list = newWishList!!.getValue()
        return list!!.get(position).prodCustWshlstRate?.toString()!!
    }

    fun onClickDeleteForWishListItem(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedDeleteForWishListItem.setValue(pos)
    }

    fun getSelectedDeleteForWishListItem(): SingleLiveEvent<Int> {
        return mSelectedDeleteForWishListItem
    }


}