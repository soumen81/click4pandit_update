package com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.adapter.NewPujaItemKitListAdapter
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageViewModel
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent
import com.google.gson.Gson
import java.util.*
import javax.inject.Inject


class NewPujaItemKitListViewModel @Inject constructor(private val mNewPujaItemKitListRepository: NewPujaItemKitListRepository) : ViewModel() {
    var newPujaItemKitListAdapter: NewPujaItemKitListAdapter? = null
        internal set
    var newPujaItemKitList: MutableLiveData<ArrayList<NewPujaItemKitListModel>>? =
        MutableLiveData<ArrayList<NewPujaItemKitListModel>>();
    var pujaItemKitListLiveData: LiveData<Resource<List<NewPujaItemKitListModel>>>? = null
    private val mSelectedBuyNowPujaItemSamagri = SingleLiveEvent<Int>()
    private val mSelectedAddtoCartItem = SingleLiveEvent<Int>()
    private val mSelectedWishListItem = SingleLiveEvent<Int>()
    private val mSelectedRedirectedSamagriListItem = SingleLiveEvent<Int>()
    var addWishListModelLiveData: LiveData<Resource<AddWishListItemModel>>? = null
    var newPujaItemKitAddtoCartOrBuyNowModelLiveData: LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>? = null
    fun init() {

        newPujaItemKitListAdapter = NewPujaItemKitListAdapter(R.layout.singlerow_newpujaitemkitlist, this)
    }


    fun getPujaItemKitList(): LiveData<Resource<List<NewPujaItemKitListModel>>> {
        pujaItemKitListLiveData = MutableLiveData()
        pujaItemKitListLiveData = mNewPujaItemKitListRepository.getPujaItemKitList()
        return pujaItemKitListLiveData as LiveData<Resource<List<NewPujaItemKitListModel>>>

    }



    fun getNewPujaItemKitAddtoCartOrBuyNow(prodMasterId: Int, productPrice: Int): LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>> {
        newPujaItemKitAddtoCartOrBuyNowModelLiveData = MutableLiveData()
        newPujaItemKitAddtoCartOrBuyNowModelLiveData = mNewPujaItemKitListRepository.getAddtoCartBuyNowForPujaSamagri(prodMasterId, productPrice)
        return newPujaItemKitAddtoCartOrBuyNowModelLiveData as LiveData<Resource<NewPujaItemKitAddtoCartOrBuyNowModel>>

    }

    fun getAddForWishListItem(prodMasterId:Int,prodCustWishLisQty:Int,prodCustscWishListRate:Double): LiveData<Resource<AddWishListItemModel>> {
        addWishListModelLiveData = MutableLiveData()
        addWishListModelLiveData = mNewPujaItemKitListRepository.getAddItemForWishListItem(prodMasterId, prodCustWishLisQty,prodCustscWishListRate)
        return addWishListModelLiveData as LiveData<Resource<AddWishListItemModel>>

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


    fun onClickBuyNowPujaSamagriItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedBuyNowPujaItemSamagri.setValue(pos)
    }

    fun getSelectedPujaItemSamagriListItem(): SingleLiveEvent<Int> {
        return mSelectedBuyNowPujaItemSamagri
    }
 fun onClickAddtoCartPujaSamagriItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
     mSelectedAddtoCartItem.setValue(pos)
    }

    fun getSelectedaddtoCartListItem(): SingleLiveEvent<Int> {
        return mSelectedAddtoCartItem
    }

    fun onClickWishListPujaSamagriItemList(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))


        mSelectedWishListItem.setValue(pos)
       /* val newPujaItemKitListModel =
            newPujaItemKitList!!.value!![pos]
        if (newPujaItemKitListModel.isSelect) {
            view.setBackgroundResource(R.drawable.wish)
        } else {
            view.setBackgroundResource(R.drawable.wish_black)
        }*/
    }

    fun getSelectedmSelectedWishListItem(): SingleLiveEvent<Int> {
        return mSelectedWishListItem
    }

    fun onClickSamagriItemListRedirect(view: View,pos: Int){
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedRedirectedSamagriListItem.setValue(pos)
    }
    fun getSelectedRedirectedSamagriListItem(): SingleLiveEvent<Int> {
        return mSelectedRedirectedSamagriListItem
    }
   /* @BindingAdapter("load_back")
    fun loadBack(view: ImageView, position: Int) {
        Log.e("ImageId", position.toString() + "")
        var newPujaItemKitList: MutableLiveData<ArrayList<NewPujaItemKitListModel>>? =
            MutableLiveData<ArrayList<NewPujaItemKitListModel>>();
        val newPujaItemKitListModel =
            newPujaItemKitList!!.value!![position]
        if (newPujaItemKitListModel.isSelect) {
            view.setBackgroundResource(R.drawable.wish)
        } else {
            view.setBackgroundResource(R.drawable.wish_black)
        }
    }*/


}