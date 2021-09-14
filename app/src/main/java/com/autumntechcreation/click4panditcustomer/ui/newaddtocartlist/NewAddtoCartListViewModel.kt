package com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.adapter.NewAddtoCartListAdapter
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitAddtoCartOrBuyNowModel
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent
import kotlinx.android.synthetic.main.singlerow_newaddtocartlist.view.*
import java.util.*
import javax.inject.Inject

class NewAddtoCartListViewModel @Inject constructor(private val mNewAddtoCartListRepository: NewAddtoCartListRepository) : ViewModel() {
    var newAddtoCartListAdapter: NewAddtoCartListAdapter? = null
        internal set
    var newAddtoCartList: MutableLiveData<ArrayList<NewAddtoCartListModel>>? =
        MutableLiveData<ArrayList<NewAddtoCartListModel>>();
    var newAddtoCartLiveData: LiveData<Resource<List<NewAddtoCartListModel>>>? = null
    private val mSelectedDeleteForItem = SingleLiveEvent<Int>()
    private val mSelectedMinusForItem = SingleLiveEvent<Int>()
    private val mSelectedUpdateForItem = SingleLiveEvent<Int>()
    private val mSelectedAddForItem = SingleLiveEvent<Int>()
    var newPujaItemKitAddtoCartOrBuyNowModelLiveData: LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>? = null
    var updateCartItemCountModelLiveData: LiveData<Resource<UpdateCartItemCountModel>>? = null
    var minteger = 1
    fun init() {
        newAddtoCartListAdapter = NewAddtoCartListAdapter(R.layout.singlerow_newaddtocartlist, this)
    }


    fun getNewPujaItemKitAddtoCartOrBuyNow(prodMasterId: Int, productPrice: Int): LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>> {
        newPujaItemKitAddtoCartOrBuyNowModelLiveData = MutableLiveData()
        newPujaItemKitAddtoCartOrBuyNowModelLiveData = mNewAddtoCartListRepository.getAddtoCartBuyNowForPujaSamagri(prodMasterId, productPrice)
        return newPujaItemKitAddtoCartOrBuyNowModelLiveData as LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>

    }  fun getRemoveForPujaItem(productCustScId: Int,prodMasterId:Int,productPrice:Double,prodCustScDate:String,updateCartQuantity:Int): LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>> {
        newPujaItemKitAddtoCartOrBuyNowModelLiveData = MutableLiveData()
        newPujaItemKitAddtoCartOrBuyNowModelLiveData = mNewAddtoCartListRepository.getRemoveForPujaItem(productCustScId, prodMasterId,productPrice,prodCustScDate,updateCartQuantity)
        return newPujaItemKitAddtoCartOrBuyNowModelLiveData as LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>

    }

    fun getUpdateAddToCart(productCustScId:Int,prodMasterId: Int,productPrice:Double,productCustScDate:String,prodCustscQty:Int): LiveData<Resource<UpdateCartItemCountModel>> {
        updateCartItemCountModelLiveData = MutableLiveData()
        updateCartItemCountModelLiveData = mNewAddtoCartListRepository.getUpdateAddToCart(productCustScId, prodMasterId,productPrice,productCustScDate,prodCustscQty)
        return updateCartItemCountModelLiveData as LiveData<Resource<UpdateCartItemCountModel>>

    }

    fun getAddtoCartItemList(): LiveData<Resource<List<NewAddtoCartListModel>>> {
        newAddtoCartLiveData = MutableLiveData()
        newAddtoCartLiveData = mNewAddtoCartListRepository.getNewAddtoCartList()
        return newAddtoCartLiveData as LiveData<Resource<List<NewAddtoCartListModel>>>

    }

    fun setAddtoCartListAdapter(list: ArrayList<NewAddtoCartListModel>) {
        newAddtoCartList = MutableLiveData()
        newAddtoCartList!!.value = list
        this.newAddtoCartListAdapter!!.setNewPujaAddtoCartListAdapterList(list)
        this.newAddtoCartListAdapter!!.notifyDataSetChanged()
    }

    fun getAdapter(): NewAddtoCartListAdapter? {
        return newAddtoCartListAdapter
    }
    fun getAddtoCartProductName(position: Int): String {
        val list = newAddtoCartList!!.getValue()
        if(list!=null) {
            return list!!.get(position).prodMasterName!!
        }else{
           return ""
        }
    }fun getAddtoCartProductPrice(position: Int): String {
        val list = newAddtoCartList!!.getValue()
        return list!!.get(position).prodCustScRate?.toString()!!
    }
  fun getProductCustScQuantity(position: Int):String{
      val list=newAddtoCartList!!.getValue()
      return list!!.get(position).prodCustScQty.toString()
  }

    fun storeCartCount(): String? {
        return mNewAddtoCartListRepository.getcartCount()
    }

    fun onClickDeleteForItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedDeleteForItem.setValue(pos)
    }

    fun getSelectedDeleteForListItem(): SingleLiveEvent<Int> {
        return mSelectedDeleteForItem
    }

    fun onClickMinusForItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedMinusForItem.setValue(pos)
    }

    fun getSelectedMinusForListItem(): SingleLiveEvent<Int> {
        return mSelectedMinusForItem
    }


    fun onClickAddForItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
       /* minteger += 1
       view.tvQuantityPlus.setText(""+minteger)*/
        mSelectedAddForItem.setValue(pos)
    }

    fun getSelectedAddForListItem(): SingleLiveEvent<Int> {
        return mSelectedAddForItem
    }


    fun storeUpdateCartCount(updateCartCount: String?): String? {
        mNewAddtoCartListRepository.storeUpdateCartCount(updateCartCount)
        return updateCartCount
    }

}