package com.autumntechcreation.click4panditcustomer.ui.shopbillingaddress

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import cn.pedant.SweetAlert.SweetAlertDialog
import com.autumntechcreation.click4panditcustomer.BaseFragment
import com.autumntechcreation.click4panditcustomer.MainActivity
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.databinding.FragmentShopbillingaddressBinding
import com.google.gson.Gson
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ShopBillingAddressFragment: BaseFragment() {
    private lateinit var mView: View
    private lateinit var mShopBillingAddressViewModel: ShopBillingAddressViewModel
    private lateinit var mFragmentShopbillingaddressBinding: FragmentShopbillingaddressBinding
    @Inject
    lateinit var mShopBillingAddressFactory: ShopBillingAddressFactory
    var custOrdTypId:Int = 0
    var prodOrderId:Int = 0
    var shopShippingAddress1:String = ""
    var shopShippingPincode:String = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mFragmentShopbillingaddressBinding =
            DataBindingUtil.inflate(inflater, defineLayoutResource(), container, false)
        mFragmentShopbillingaddressBinding.lifecycleOwner = this
        mView = mFragmentShopbillingaddressBinding.root
        // val bundle = arguments

        return mView

    }

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
        mFragmentShopbillingaddressBinding.edtTxtShopBillingState.setText("West Bengal")
        mFragmentShopbillingaddressBinding.edtTxtShopBillingState.isEnabled=false
        mFragmentShopbillingaddressBinding.edtTxtShopBillingCity.setText("Kolkata")
        mFragmentShopbillingaddressBinding.edtTxtShopBillingCity.isEnabled=false

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: ShopBillingAddressFragmentArgs by navArgs()

        val bundle = arguments
        val gson = Gson()
        Log.e("OBJECT",gson.toJson(args))
        val custOrdTypIdd=args
        custOrdTypId=args.custOrdTypId
        prodOrderId=args.productOrderId
        shopShippingAddress1=args.shopShippingAddress1
        shopShippingPincode=args.shopShippingPincode

        mFragmentShopbillingaddressBinding.tvShopBillingSubmit.setOnClickListener {

            if (mFragmentShopbillingaddressBinding.edtTxtShopBillingFirstName.getText().toString().trim().equals("")) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_firstname))
                    .show()
            } else if (mFragmentShopbillingaddressBinding.edtTxtShopBillingLastName.getText().toString().trim().equals("")) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_lastname))
                    .show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(
                    mFragmentShopbillingaddressBinding.edtTxtShopBillingEmail.getText().toString()
                ).matches() ||
                mFragmentShopbillingaddressBinding.edtTxtShopBillingEmail.getText().toString().trim().equals("")
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_validemail))
                    .show()
            } else if (!Patterns.PHONE.matcher(
                    mFragmentShopbillingaddressBinding.edtShopBillingMobileNo.getText().toString()
                ).matches() ||
                mFragmentShopbillingaddressBinding.edtShopBillingMobileNo.getText().toString().trim()
                    .equals("") || mFragmentShopbillingaddressBinding.edtShopBillingMobileNo.getText().toString()
                    .trim()!!.length < 10
            ) {
                //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();
                SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_valid_phone_number))
                    .show()
            }




           /* else if (!Patterns.PHONE.matcher(
                    mFragmentShopbillingaddressBinding.edtShopBillingAlternateMobileNo.getText().toString()
                ).matches() ||
                mFragmentShopbillingaddressBinding.edtShopBillingAlternateMobileNo.getText().toString().trim()
                    .equals("") || mFragmentShopbillingaddressBinding.edtShopBillingAlternateMobileNo.getText().toString()
                    .trim()!!.length < 10
            ) {
                //  Toast.makeText(SettingPageActivity.this,R.string.please_enter_valid_phone_number,Toast.LENGTH_SHORT).show();
                SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_valid_phone_number))
                    .show()
            }*/
            else if (mFragmentShopbillingaddressBinding.edtTxtShopBillingAddress.getText().toString().trim()
                    .equals("")
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_address))
                    .show()
            }  else if (mFragmentShopbillingaddressBinding.edtTxtShopBillingState.getText().toString().trim()
                    .equals("")
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_state))
                    .show()
            } else if (mFragmentShopbillingaddressBinding.edtTxtShopBillingCity.getText().toString().trim()
                    .equals("")
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_city))
                    .show()
            } else if (mFragmentShopbillingaddressBinding.edtTxtShopBillingPincode.getText().toString().trim()
                    .equals("") || mFragmentShopbillingaddressBinding.edtTxtShopBillingPincode.getText().toString()
                    .length < 6
            ) {
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(resources.getString(R.string.validation_error))
                    .setContentText(resources.getString(R.string.please_enter_pincode))
                    .show()
            }else{
                if(mFragmentShopbillingaddressBinding.edtTxtShopBillingAddress.length()>0 && mFragmentShopbillingaddressBinding.edtTxtShopBillingPincode.length()==6) {
                    val action =
                        ShopBillingAddressFragmentDirections.actionShopBillingAddressFragmentToShopShippingAddressFragment()
                    action.shopBillingFName =
                        mFragmentShopbillingaddressBinding.edtTxtShopBillingFirstName.text.toString()
                    action.shopBillingLName =
                        mFragmentShopbillingaddressBinding.edtTxtShopBillingLastName.text.toString()
                    action.shopBillingEmail =
                        mFragmentShopbillingaddressBinding.edtTxtShopBillingEmail.text.toString()
                    action.shopBillingMobile =
                        mFragmentShopbillingaddressBinding.edtShopBillingMobileNo.text.toString()
                    action.shopBillingAddress1 =
                        mFragmentShopbillingaddressBinding.edtTxtShopBillingAddress.text.toString()
                    action.shopBillingPincode =
                        mFragmentShopbillingaddressBinding.edtTxtShopBillingPincode.text.toString()
                    action.productOrderId=prodOrderId
                    Navigation.findNavController(mView).navigate(action)
                }else{
                    Toast.makeText(activity,"Address and Pincode should not be blank", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}