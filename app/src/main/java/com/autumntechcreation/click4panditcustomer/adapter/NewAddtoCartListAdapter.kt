package com.autumntechcreation.click4panditcustomer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.autumntechcreation.click4panditcustomer.BR
import com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist.NewAddtoCartListModel
import com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist.NewAddtoCartListViewModel
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemlist.NewPujaBrassItemListViewModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.singlerow_newaddtocartlist.view.*
import kotlinx.android.synthetic.main.singlerow_newpujabrassitemlist.view.*

class NewAddtoCartListAdapter (private val layoutId: Int, internal var mNewAddtoCartListViewModel: NewAddtoCartListViewModel):
    RecyclerView.Adapter<NewAddtoCartListAdapter.MyViewHolder>(){
    internal var mNewAddtoCartListModel: List<NewAddtoCartListModel>? = null

    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NewAddtoCartListAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, viewGroup, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(myViewHolder: NewAddtoCartListAdapter.MyViewHolder, i: Int) {
        myViewHolder.bind(mNewAddtoCartListViewModel, i)
    }

    override fun getItemCount(): Int {
        return if (mNewAddtoCartListModel == null) 0 else mNewAddtoCartListModel!!.size
    }

    inner class MyViewHolder internal constructor(internal val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageThumb = binding.root.imgVwNewAddtoCart
        internal fun bind(viewModel: NewAddtoCartListViewModel, position: Int?) {
            // viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
            val url= position?.let { mNewAddtoCartListModel!!.get(it).prodImgDataURL }
            Glide.with(imageThumb)
                .load(url)
                .into(imageThumb)

        }
    }
    fun setNewPujaAddtoCartListAdapterList(listNewPujaAddtoCartListModel:List<NewAddtoCartListModel>) {
        this.mNewAddtoCartListModel = listNewPujaAddtoCartListModel
    }
    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }


}