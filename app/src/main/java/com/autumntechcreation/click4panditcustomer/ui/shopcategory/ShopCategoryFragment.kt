package com.autumntechcreation.click4panditcustomer.ui.shopcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.autumntechcreation.click4panditcustomer.BaseFragment
import com.autumntechcreation.click4panditcustomer.MainActivity
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.databinding.FragmentNewpujaitemkitlistBinding
import com.autumntechcreation.click4panditcustomer.databinding.FragmentShopcategoryBinding
import com.autumntechcreation.click4panditcustomer.ui.shopcategory.ShopCategoryFragmentDirections.actionShopCategoryFragmentToNewPujaItemKitList
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ShopCategoryFragment : BaseFragment() {
    private lateinit var mView: View
    private lateinit var mShopCategoryViewModel: ShopCategoryViewModel
    private lateinit var mFragmentShopcategoryBinding: FragmentShopcategoryBinding

    @Inject
    lateinit var mShopCategoryFactory: ShopCategoryFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentShopcategoryBinding =
            DataBindingUtil.inflate(inflater, defineLayoutResource(), container, false)
        mFragmentShopcategoryBinding.lifecycleOwner = this
        mView = mFragmentShopcategoryBinding.root
        return mFragmentShopcategoryBinding.root
    }

    override fun defineLayoutResource(): Int {
        return R.layout.fragment_shopcategory
    }

    override fun initializeComponent(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        mView = view;
        mShopCategoryViewModel =
            ViewModelProviders.of(activity as FragmentActivity, mShopCategoryFactory)
                .get(ShopCategoryViewModel::class.java)
        (activity as MainActivity?)!!.setToolbar(true, true, false, true)
       /* MainActivity.instance.setToolbar(
            true,
            false,
            getString(R.string.brokerprofile),
            false,
            false
        )*/
        mFragmentShopcategoryBinding.viewModel = mShopCategoryViewModel

        mFragmentShopcategoryBinding.tvPujaItemKitShopNow.setOnClickListener(View.OnClickListener {
            /*val action=ShopCategoryFragmentDirections.actionShopCategoryFragmentToNewPujaItemKitList()
            action.pujaItemKitListId=1
            Navigation.findNavController(mView).navigate(action)*/



            val action1 = ShopCategoryFragmentDirections.actionShopCategoryFragmentToNewPujaItemKitList()
            action1.pujaItemKitListId=1
            Navigation.findNavController(mView).navigate(action1)
        })

    }


}