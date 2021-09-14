package com.autumntechcreation.click4panditcustomer.ui.shopbillingaddress

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.autumntechcreation.click4panditcustomer.BaseFragment
import com.autumntechcreation.click4panditcustomer.MainActivity
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.databinding.FragmentShopbillingaddressBinding
import com.autumntechcreation.click4panditcustomer.databinding.FragmentShopcategoryBinding
import com.autumntechcreation.click4panditcustomer.ui.shopcategory.ShopCategoryFactory
import com.autumntechcreation.click4panditcustomer.ui.shopcategory.ShopCategoryViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ShopBillingAddressFragment: BaseFragment() {
    private lateinit var mView: View
    private lateinit var mShopBillingAddressViewModel: ShopBillingAddressViewModel
    private lateinit var mFragmentShopbillingaddressBinding: FragmentShopbillingaddressBinding
    @Inject
    lateinit var mShopBillingAddressFactory: ShopBillingAddressFactory

    override fun defineLayoutResource(): Int {
        return R.layout.fragment_shopbillingaddress
    }

    override fun initializeComponent(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        mView = view;
        mShopBillingAddressViewModel =
            ViewModelProviders.of(activity as FragmentActivity, mShopBillingAddressFactory)
                .get(ShopBillingAddressViewModel::class.java)
        (activity as MainActivity?)!!.setToolbar(true, true, false, true)

        mFragmentShopbillingaddressBinding.viewModel = mShopBillingAddressViewModel
    }
}