package com.autumntechcreation.click4panditcustomer.ui.shopshippingaddress

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import cn.pedant.SweetAlert.SweetAlertDialog
import com.autumntechcreation.click4panditcustomer.BaseFragment
import com.autumntechcreation.click4panditcustomer.MainActivity
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.databinding.FragmentShopshippingaddressBinding
import com.autumntechcreation.click4panditcustomer.ui.shopbillingaddress.ShopBillingAddressFragmentArgs
import com.google.gson.Gson
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ShopShippingAddressFragment: BaseFragment() {
    private lateinit var mView: View
    private lateinit var mShopShippingAddressViewModel: ShopShippingAddressViewModel
    private lateinit var mFragmentShopshippingaddressBinding: FragmentShopshippingaddressBinding
    var custOrdTypId: Int = 0
    var productOrderId: Int = 0

    @Inject
    lateinit var mShopShippingAddressFactory: ShopShippingAddressFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mFragmentShopshippingaddressBinding =
            DataBindingUtil.inflate(inflater, defineLayoutResource(), container, false)
        mFragmentShopshippingaddressBinding.lifecycleOwner = this
        mView = mFragmentShopshippingaddressBinding.root
        // val bundle = arguments

        return mView

    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: ShopBillingAddressFragmentArgs by navArgs()

        val bundle = arguments
        val gson = Gson()
        Log.e("OBJECT", gson.toJson(args))
        val custOrdTypIdd = args
        custOrdTypId = args.custOrdTypId
        productOrderId = args.productOrderId

        mFragmentShopshippingaddressBinding.tvShopShippingDiffLoc.setOnClickListener {
            val action = ShopShippingAddressFragmentDirections.actionShopShippingAddressFragmentToShopBillingAddressFragment()
            action.custOrdTypId=1003
            Navigation.findNavController(mView).navigate(action)
        }

        mFragmentShopshippingaddressBinding.tvShopShippingSubmit.setOnClickListener {
            if (mFragmentShopshippingaddressBinding.edtTxtShopShippingFirstName.getText().toString().trim()
                    .equals("")
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_firstname))
                    .show()
            } else if (mFragmentShopshippingaddressBinding.edtTxtShopShippingLastName.getText().toString().trim()
                    .equals("")
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_lastname))
                    .show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(
                    mFragmentShopshippingaddressBinding.edtTxtShopShippingEmail.getText().toString()
                ).matches() ||
                mFragmentShopshippingaddressBinding.edtTxtShopShippingEmail.getText().toString().trim().equals("")
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_validemail))
                    .show()
            } else if (!Patterns.PHONE.matcher(
                    mFragmentShopshippingaddressBinding.edtShopBillingMobileNo.getText().toString()
                ).matches() ||
                mFragmentShopshippingaddressBinding.edtShopBillingMobileNo.getText().toString().trim()
                    .equals("") || mFragmentShopshippingaddressBinding.edtShopBillingMobileNo.getText().toString()
                    .trim().length < 10
            ) {
                //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();
                SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_valid_phone_number))
                    .show()
            } else if (mFragmentShopshippingaddressBinding.edtShopShippingAlternateMobileNo.getText().toString()
                    .trim().length > 0 &&
                !Patterns.PHONE.matcher(
                    mFragmentShopshippingaddressBinding.edtShopShippingAlternateMobileNo.getText().toString()
                ).matches()
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_valid_phone_number))
                    .show()
            } else if (mFragmentShopshippingaddressBinding.edtShopBillingMobileNo.getText().toString().equals(
                    mFragmentShopshippingaddressBinding.edtShopShippingAlternateMobileNo.getText().toString()
                )
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.mobilenodoesnotmatch))
                    .show()
            } else if (mFragmentShopshippingaddressBinding.edtTxtShopShippingAddress.getText().toString().trim()
                    .equals("")
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_address))
                    .show()
            } else if (mFragmentShopshippingaddressBinding.edtTxtShopShippingState.getText().toString().trim()
                    .equals("")
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_state))
                    .show()
            } else if (mFragmentShopshippingaddressBinding.edtTxtShopShippingCity.getText().toString().trim()
                    .equals("")
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_city))
                    .show()
            } else if (mFragmentShopshippingaddressBinding.edtTxtShopShippingPincode.getText().toString().trim()
                    .equals("") || mFragmentShopshippingaddressBinding.edtTxtShopShippingPincode.getText().toString()
                    .length < 6
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_pincode))
                    .show()
            }else{

            }
        }
    }
}