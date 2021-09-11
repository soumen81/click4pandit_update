package com.autumntechcreation.click4panditcustomer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListViewModel
import com.autumntechcreation.click4panditcustomer.BR
import com.autumntechcreation.click4panditcustomer.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.singlerow_newpujaitemkitlist.view.*

class NewPujaItemKitListAdapter (private val layoutId: Int, internal var mNewPujaItemKitListViewModel: NewPujaItemKitListViewModel):
    RecyclerView.Adapter<NewPujaItemKitListAdapter.MyViewHolder>(){
    internal var mNewPujaItemKitListModel: List<NewPujaItemKitListModel>? = null

    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NewPujaItemKitListAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, viewGroup, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(myViewHolder: NewPujaItemKitListAdapter.MyViewHolder, i: Int) {
        myViewHolder.bind(mNewPujaItemKitListViewModel, i)
    }

    override fun getItemCount(): Int {
        return if (mNewPujaItemKitListModel == null) 0 else mNewPujaItemKitListModel!!.size
    }

    inner class MyViewHolder internal constructor(internal val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageThumb = binding.root.imgPujaItemKit
        //val imageWishThumb = binding.root.imgWishItemKit

        internal fun bind(viewModel: NewPujaItemKitListViewModel, position: Int?) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
            val url= position?.let { mNewPujaItemKitListModel!!.get(it).prodImgDataURL }

            Glide.with(imageThumb)
                .load(url)
                .into(imageThumb)
           /* val urlWish= position?.let { mNewPujaItemKitListModel!!.get(it).isSelect }

            if(urlWish!!.equals(false)){
                imageWishThumb.setBackgroundResource(R.drawable.wish)
            }else if(urlWish!!.equals(true)){
                imageWishThumb.setBackgroundResource(R.drawable.wish_black)
            }*/
        }
    }
    fun setNewPujaItemKitAdapterList(listNewPujaItemKitListModel:List<NewPujaItemKitListModel>) {
        this.mNewPujaItemKitListModel = listNewPujaItemKitListModel
    }
    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

}