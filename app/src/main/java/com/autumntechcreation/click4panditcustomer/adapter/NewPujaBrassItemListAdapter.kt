package com.autumntechcreation.click4panditcustomer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.autumntechcreation.click4panditcustomer.BR
import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist.NewPujaBoxItemListViewModel
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemlist.NewPujaBrassItemListViewModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.singlerow_newpujabrassitemlist.view.*
import kotlinx.android.synthetic.main.singlerow_pujaboxitemlist.view.*

class NewPujaBrassItemListAdapter (private val layoutId: Int, internal var mNewPujaBrassItemListViewModel: NewPujaBrassItemListViewModel):
    RecyclerView.Adapter<NewPujaBrassItemListAdapter.MyViewHolder>(){
    internal var mNewPujaItemKitListModel: List<NewPujaItemKitListModel>? = null

    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NewPujaBrassItemListAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, viewGroup, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(myViewHolder: NewPujaBrassItemListAdapter.MyViewHolder, i: Int) {
        myViewHolder.bind(mNewPujaBrassItemListViewModel, i)
    }

    override fun getItemCount(): Int {
        return if (mNewPujaItemKitListModel == null) 0 else mNewPujaItemKitListModel!!.size
    }

    inner class MyViewHolder internal constructor(internal val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageThumb = binding.root.imgPujaBrassItem
        internal fun bind(viewModel: NewPujaBrassItemListViewModel, position: Int?) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
            val url= position?.let { mNewPujaItemKitListModel!!.get(it).prodImgDataURL }
            Glide.with(imageThumb)
                .load(url)
                .into(imageThumb)

        }
    }
    fun setNewPujaItemBrassAdapterList(listNewPujaItemKitListModel:List<NewPujaItemKitListModel>) {
        this.mNewPujaItemKitListModel = listNewPujaItemKitListModel
    }
    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

}