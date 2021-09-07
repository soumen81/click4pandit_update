package com.autumntechcreation.click4panditcustomer.ui.newwishlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.autumntechcreation.click4panditcustomer.BaseFragment
import com.autumntechcreation.click4panditcustomer.Click4PanditApp
import com.autumntechcreation.click4panditcustomer.MainActivity
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.databinding.FragmentNewpujabrassitemlistBinding
import com.autumntechcreation.click4panditcustomer.databinding.FragmentNewwishlistBinding
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.network.Status
import com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist.NewAddtoCartListModel
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemlist.NewPujaBrassItemListFactory
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemlist.NewPujaBrassItemListViewModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitAddtoCartOrBuyNowModel
import com.google.gson.Gson
import dagger.android.support.AndroidSupportInjection
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class NewWishListFragment: BaseFragment() {
    private lateinit var mView: View
    private lateinit var mNewWishListViewModel: NewWishListViewModel
    private lateinit var mFragmentNewwishlistBinding: FragmentNewwishlistBinding
    @Inject
    lateinit var mNewWishListFactory: NewWishListFactory
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentNewwishlistBinding =
            DataBindingUtil.inflate(inflater, defineLayoutResource(), container, false)
        mFragmentNewwishlistBinding.lifecycleOwner = this
        mView = mFragmentNewwishlistBinding.root
        // val bundle = arguments

        return mView
    }
    override fun defineLayoutResource(): Int {
        return R.layout.fragment_newwishlist
    }

    override fun initializeComponent(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)

        mView = view;
        mNewWishListViewModel =
            ViewModelProviders.of(activity as FragmentActivity, mNewWishListFactory)
                .get(NewWishListViewModel::class.java)
        (activity as MainActivity?)!!.setToolbar(true, true, false, true)

        mFragmentNewwishlistBinding.viewModel = mNewWishListViewModel

        val llm = LinearLayoutManager(activity)
        llm.orientation = LinearLayoutManager.VERTICAL
        mFragmentNewwishlistBinding.rvNewPujaWishList.setLayoutManager(llm)
        mNewWishListViewModel.init()

        mNewWishListViewModel.getWishItemList().observe(this, Observer {
            handleWishItemList(it)
        })

        mNewWishListViewModel.getSelectedDeleteForWishListItem().observe(this, Observer {
            mNewWishListViewModel.getRemoveForWishListItem(mNewWishListViewModel.newWishList!!.value!!.get(it).prodCustWshlstId!!,
                mNewWishListViewModel.newWishList!!.value!!.get(it).prodMasterId!!,
                mNewWishListViewModel.newWishList!!.value!!.get(it).prodCustWshlstQty!!,
                mNewWishListViewModel.newWishList!!.value!!.get(it).prodCustWshlstRate!!).observe(activity as FragmentActivity,
                Observer {
                    if (isDeviceOnline()) {
                        handleRemoveWishListItemResponse(it)
                    }
                })

        })
    }

    private fun handleRemoveWishListItemResponse(resource: Resource<DeleteWishListModel>?) {
        if (resource != null) {

            when (resource.status) {
                Status.ERROR -> {
                    Log.e("NewPujaItemKitAddtoCartOrBuyNowModelResponse", "ERROR")
                    Log.e("NewPujaItemKitAddtoCartOrBuyNowModelResponse", resource.message)
                    Log.e("NewPujaItemKitAddtoCartOrBuyNowModelResponse", resource.status.toString() + "")
                    Log.e("NewPujaItemKitAddtoCartOrBuyNowModelResponse", resource.data.toString() + "")
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
                    Log.e("NewPujaItemKitAddtoCartOrBuyNowModelResponse", "LOADING")
                    Log.e("NewPujaItemKitAddtoCartOrBuyNowModelResponse", resource.data.toString() + "")

                }
                Status.SUCCESS -> {
                    if (resource.data != null) {
                        Log.e("NewPujaItemKitAddtoCartOrBuyNowModelResponse", resource.data.toString())
                        val gson = Gson()
                        val json = gson.toJson(resource.data)
                        Log.e("NewPujaItemKitAddtoCartOrBuyNowModelResponse_SUCCESS", json.toString())
                        if (resource.data.returnStatus.equals("SUCCESS")) {

                            mNewWishListViewModel.getWishItemList().observe(this, Observer {
                                handleWishItemList(it)
                            })

                        }


                    } else {
                    }

                }
                else -> {
                }
            }
        }
    }




    private fun handleWishItemList(resource: Resource<List<NewWishListItemModel>>?) {
        if (resource != null) {

            when (resource.status) {
                Status.ERROR -> {
                    Log.e("handleWishListResponse", "ERROR")
                    Log.e("handleWishListResponse", resource.message)
                    Log.e("handleWishListResponse", resource.status.toString() + "")
                    Log.e("handleWishListResponse", resource.data.toString() + "")
                    DisplayDialog.getInstance().dismissAlertDialog()

                }
                Status.LOADING -> {
                    Log.e("handleWishListResponse", "LOADING")
                    Log.e("handleWishListResponse", resource.data.toString() + "")
                    DisplayDialog.getInstance()
                        .showAlertDialog(activity, activity!!.getString(R.string.please_wait))

                }
                Status.SUCCESS -> {
                    if (resource.data != null) {
                        Log.e("handleWishListResponse", resource.data.toString())
                        var list = ArrayList<NewWishListItemModel>()
                        list = resource.data as ArrayList<NewWishListItemModel>
                        list.size;
                        Log.e("handleWishListResponse", list.size.toString());

                        mNewWishListViewModel.setWishListItemAdapter(list)
                        DisplayDialog.getInstance().dismissAlertDialog()
                    } else {

                    }
                }

            }
        }
    }
}