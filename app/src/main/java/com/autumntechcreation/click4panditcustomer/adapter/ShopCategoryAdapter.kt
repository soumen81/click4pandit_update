package com.autumntechcreation.click4panditcustomer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.autumntechcreation.click4panditcustomer.BR
import com.autumntechcreation.click4panditcustomer.R

import com.autumntechcreation.click4panditcustomer.ui.shopcategory.ShopCategoryModel
import com.autumntechcreation.click4panditcustomer.ui.shopcategory.ShopCategoryViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.singlerow_pujaboxitemlist.view.*
import kotlinx.android.synthetic.main.singlerow_shopcategory.view.*


class ShopCategoryAdapter (private val layoutId: Int, internal var mShopCategoryViewModel: ShopCategoryViewModel):
    RecyclerView.Adapter<ShopCategoryAdapter.MyViewHolder>(){
    internal var mShopCategoryListModel: List<ShopCategoryModel>? = null

    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ShopCategoryAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, viewGroup, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(myViewHolder: ShopCategoryAdapter.MyViewHolder, i: Int) {
        myViewHolder.bind(mShopCategoryViewModel, i)
    }

    override fun getItemCount(): Int {
        return if (mShopCategoryListModel == null) 0 else mShopCategoryListModel!!.size
    }

    inner class MyViewHolder internal constructor(internal val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageThumb = binding.root.imgPujaItemKit
        internal fun bind(viewModel: ShopCategoryViewModel, position: Int?) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
            if (position == 0) {
                imageThumb.setBackgroundResource(R.drawable.ghanti)
            }  else if (position == 1) {
                imageThumb.setBackgroundResource(R.drawable.ghati)
            } else if (position == 2) {
                imageThumb.setBackgroundResource(R.drawable.thali)
            } else if (position == 3) {
                imageThumb.setBackgroundResource(R.drawable.brass)
            }
        }
    }
    fun setShopCategoryList(listShopCategoryModel:List<ShopCategoryModel>) {
        this.mShopCategoryListModel = listShopCategoryModel
    }
    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }
}