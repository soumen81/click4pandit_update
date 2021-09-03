package com.autumntechcreation.click4panditcustomer.ui.shopcategory

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.adapter.ShopCategoryAdapter
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent
import javax.inject.Inject

class ShopCategoryViewModel @Inject constructor(private val mShopCategoryRepository: ShopCategoryRepository) : ViewModel() {
    var shopCategoryAdapter: ShopCategoryAdapter? = null
        internal set
    var shopCategoryModelList: MutableLiveData<ArrayList<ShopCategoryModel>>? =
        MutableLiveData<ArrayList<ShopCategoryModel>>();
    var shopCategoryModelLiveData: LiveData<Resource<List<ShopCategoryModel>>>? = null
    private val mSelectedShopCategory = SingleLiveEvent<Int>()
    fun init() {
        shopCategoryAdapter = ShopCategoryAdapter(R.layout.singlerow_shopcategory, this)
    }


    fun getShopCategoryItemList(): LiveData<Resource<List<ShopCategoryModel>>> {
        shopCategoryModelLiveData = MutableLiveData()
        shopCategoryModelLiveData = mShopCategoryRepository.getShopCategoryList()
        return shopCategoryModelLiveData as LiveData<Resource<List<ShopCategoryModel>>>

    }

    fun setShopCategoryListAdapter(list: ArrayList<ShopCategoryModel>) {
        shopCategoryModelList = MutableLiveData()
        shopCategoryModelList!!.value = list
        this.shopCategoryAdapter!!.setShopCategoryList(list)
        this.shopCategoryAdapter!!.notifyDataSetChanged()
    }
    fun getAdapter(): ShopCategoryAdapter? {
        return shopCategoryAdapter
    }
    fun getShopCategoryDesc(position: Int): String {
        val list = shopCategoryModelList!!.getValue()
        return list!!.get(position).prodCtgryDscr!!
    }


    fun onClickShopCategoryItem(view: View, pos: Int) {
        Log.e("ClickPOSITION", view.id.toString() + "POSITION:" + Integer.toString(pos))
        mSelectedShopCategory.setValue(pos)
    }

    fun getSelectedShopCategoryListItem(): SingleLiveEvent<Int> {
        return mSelectedShopCategory
    }

}