package com.autumntechcreation.click4panditcustomer.ui.shopcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.autumntechcreation.click4panditcustomer.BaseFragment
import com.autumntechcreation.click4panditcustomer.MainActivity
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.databinding.FragmentShopcategoryBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ShopCategoryFragment : BaseFragment() {
    private lateinit var mView: View
    private lateinit var mShopCategoryViewModel: ShopCategoryViewModel
    private lateinit var mFragmentShopcategoryBinding: FragmentShopcategoryBinding
    @Inject
    lateinit var mShopCategoryFactory: ShopCategoryFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
    }


}