package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.autumntechcreation.click4panditcustomer.BaseFragment
import com.autumntechcreation.click4panditcustomer.MainActivity
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.databinding.FragmentShopbillingaddressBinding
import com.autumntechcreation.click4panditcustomer.databinding.FragmentShopshippingaddressBinding
import com.autumntechcreation.click4panditcustomer.ui.shopbillingaddress.ShopBillingAddressFactory
import com.autumntechcreation.click4panditcustomer.ui.shopbillingaddress.ShopBillingAddressViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ShopShippingAddressFragment: BaseFragment() {
    private lateinit var mView: View
    private lateinit var mShopShippingAddressViewModel: ShopShippingAddressViewModel
    private lateinit var mFragmentShopshippingaddressBinding: FragmentShopshippingaddressBinding
    @Inject
    lateinit var mShopShippingAddressFactory: ShopShippingAddressFactory
    override fun defineLayoutResource(): Int {
        return R.layout.fragment_shopshippingaddress
    }

    override fun initializeComponent(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        mView = view;
        mShopShippingAddressViewModel =
            ViewModelProviders.of(activity as FragmentActivity, mShopShippingAddressFactory)
                .get(ShopShippingAddressViewModel::class.java)
        (activity as MainActivity?)!!.setToolbar(true, true, false, true)

        mFragmentShopshippingaddressBinding.viewModel = mShopShippingAddressViewModel
    }
}