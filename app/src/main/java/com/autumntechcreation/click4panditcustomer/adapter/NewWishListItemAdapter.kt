package com.autumntechcreation.click4panditcustomer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.autumntechcreation.click4panditcustomer.BR
import com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist.NewAddtoCartListModel
import com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist.NewAddtoCartListViewModel
import com.autumntechcreation.click4panditcustomer.ui.newwishlist.NewWishListItemModel
import com.autumntechcreation.click4panditcustomer.ui.newwishlist.NewWishListViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.singlerow_newaddtocartlist.view.*
import kotlinx.android.synthetic.main.singlerow_newwishlistitem.view.*

class NewWishListItemAdapter  (private val layoutId: Int, internal var mNewWishListViewModel: NewWishListViewModel):
    RecyclerView.Adapter<NewWishListItemAdapter.MyViewHolder>(){
        internal var mNewWishListItemModel: List<NewWishListItemModel>? = null

        private fun getLayoutIdForPosition(position: Int): Int {
            return layoutId
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NewWishListItemAdapter.MyViewHolder {
            val layoutInflater = LayoutInflater.from(viewGroup.context)
            val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, viewGroup, false)

            return MyViewHolder(binding)
        }

        override fun onBindViewHolder(myViewHolder: NewWishListItemAdapter.MyViewHolder, i: Int) {
            myViewHolder.bind(mNewWishListViewModel, i)
        }

        override fun getItemCount(): Int {
            return if (mNewWishListItemModel == null) 0 else mNewWishListItemModel!!.size
        }

        inner class MyViewHolder internal constructor(internal val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
            val imageThumb = binding.root.imgVwForWishList
            internal fun bind(viewModel: NewWishListViewModel, position: Int?) {
                // viewModel.fetchDogBreedImagesAt(position);
                binding.setVariable(BR.viewModel, viewModel)
                binding.setVariable(BR.position, position)
                binding.executePendingBindings()
                val url= position?.let { mNewWishListItemModel!!.get(it).prodImgDataURL }
                Glide.with(imageThumb)
                    .load(url)
                    .into(imageThumb)

            }
        }
        fun setNewWishListAdapterList(listNewWishListItemModel:List<NewWishListItemModel>) {
            this.mNewWishListItemModel = listNewWishListItemModel
        }
        override fun getItemViewType(position: Int): Int {
            return getLayoutIdForPosition(position)
        }

    }