package com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.autumntechcreation.click4panditcustomer.BaseFragment
import com.autumntechcreation.click4panditcustomer.MainActivity
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.databinding.FragmentNewpujaboxitemdetailsBinding
import com.autumntechcreation.click4panditcustomer.databinding.FragmentNewpujabrassitemdetailsBinding
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.network.Status
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemdetails.NewPujaBrassItemDetailsFragmentArgs
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemdetails.NewPujaBrassItemDetailsViewModel
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemdetails.NewPujaBrassItemdetailsFactory
import com.autumntechcreation.click4panditcustomer.ui.newpujasamagrilistdetails.NewPujaSamgriDetailsModel
import com.bumptech.glide.Glide
import com.google.gson.Gson
import dagger.android.support.AndroidSupportInjection
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class NewPujaBoxItemDetailsFragment: BaseFragment() {
    private lateinit var mView: View
    private lateinit var mNewPujaBoxItemDetailsViewModel: NewPujaBoxItemDetailsViewModel
    private lateinit var mFragmentNewpujaboxitemdetailsBinding: FragmentNewpujaboxitemdetailsBinding
    @Inject
    lateinit var mNewPujaBoxItemDetailsFactory: NewPujaBoxItemDetailsFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mFragmentNewpujaboxitemdetailsBinding =
            DataBindingUtil.inflate(inflater, defineLayoutResource(), container, false)
        mFragmentNewpujaboxitemdetailsBinding.lifecycleOwner = this
        mView = mFragmentNewpujaboxitemdetailsBinding.root
        // val bundle = arguments

        return mView

    }
    override fun defineLayoutResource(): Int {
        return R.layout.fragment_newpujaboxitemdetails
    }

    override fun initializeComponent(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)

        mView = view;
        mNewPujaBoxItemDetailsViewModel =
            ViewModelProviders.of(activity as FragmentActivity, mNewPujaBoxItemDetailsFactory)
                .get(NewPujaBoxItemDetailsViewModel::class.java)
        (activity as MainActivity?)!!.setToolbar(true, true, false, true)
        mFragmentNewpujaboxitemdetailsBinding.viewModel = mNewPujaBoxItemDetailsViewModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: NewPujaBoxItemDetailsFragmentArgs by navArgs()
        val prodMasterId = args.prodMasterId
        Log.e("VALUE",prodMasterId.toString())

        mNewPujaBoxItemDetailsViewModel.getpujaSamagriDetails(prodMasterId).observe(activity as FragmentActivity,
            {
                if (isDeviceOnline()) {
                    handleNewPujaSamagriDetailsResponse(it)
                }
            })
    }
    private fun handleNewPujaSamagriDetailsResponse(resource: Resource<NewPujaSamgriDetailsModel>?) {
        if (resource != null) {

            when (resource.status) {
                Status.ERROR -> {
                    Log.e("NewPujaSamagriResponse", "ERROR")
                    Log.e("NewPujaSamagriResponse", resource.message)
                    Log.e("NewPujaSamagriResponse", resource.status.toString() + "")
                    Log.e("NewPujaSamagriResponse", resource.data.toString() + "")
                    //  if (resource.message != null && resource.data == null) {
                    if (resource.message != null && resource.data == null) {
                        val jsonObject: JSONObject
                        try {
                            jsonObject = JSONObject(resource.message)




                        } catch (e: JSONException) {
                            e.printStackTrace()

                        }

                    }
                }
                Status.LOADING -> {
                    Log.e("NewPujaSamagriResponse", "LOADING")
                    Log.e("NewPujaSamagriResponse", resource.data.toString() + "")

                }
                Status.SUCCESS -> {
                    if (resource.data != null) {
                        Log.e("NewPujaSamagriResponse", resource.data.toString())
                        val gson = Gson()
                        val json = gson.toJson(resource.data)
                        Log.e("NewPujaSamagriResponse_SUCCESS", json.toString())
                        var productMasterName:String= resource.data.prodMasterName!!
                        var prodPrice:Double= resource.data.prodPrice!!
                        var productImg:String= resource.data.prodImgModel!!.prodImgDataURL!!
                        mFragmentNewpujaboxitemdetailsBinding.tvPujaBoxName.setText(productMasterName)
                        mFragmentNewpujaboxitemdetailsBinding.tvPujaBoxNamePrice.setText(""+prodPrice)

                        Glide.with(this).load(productImg).centerCrop().into(mFragmentNewpujaboxitemdetailsBinding.imgvwPujaBoxItem)
                    } else {
                    }

                }
                else -> {
                }
            }
        }
    }
}