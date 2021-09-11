package com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.autumntechcreation.click4panditcustomer.BaseFragment
import com.autumntechcreation.click4panditcustomer.Click4PanditApp
import com.autumntechcreation.click4panditcustomer.MainActivity
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.databinding.FragmentNewpujaboxitemlistBinding
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.network.Status
import com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemlist.NewPujaBrassItemListFragmentDirections
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.AddWishListItemModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitAddtoCartOrBuyNowModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.google.gson.Gson
import dagger.android.support.AndroidSupportInjection
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import kotlin.math.roundToInt


class NewPujaBoxItemList : BaseFragment(){

    private lateinit var mView: View
    private lateinit var mNewPujaBoxItemListViewModel: NewPujaBoxItemListViewModel
    private lateinit var mFragmentNewpujaboxitemlistBinding: FragmentNewpujaboxitemlistBinding
    var cartCount: Int = 0
    @Inject
    lateinit var mNewPujaBoxItemListFactory: NewPujaBoxItemListFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mFragmentNewpujaboxitemlistBinding =
            DataBindingUtil.inflate(inflater, defineLayoutResource(), container, false)
        mFragmentNewpujaboxitemlistBinding.lifecycleOwner = this
        mView = mFragmentNewpujaboxitemlistBinding.root
        // val bundle = arguments

        return mView

    }
    override fun defineLayoutResource(): Int {
        return R.layout.fragment_newpujaboxitemlist
    }

    override fun initializeComponent(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)

        mView = view;
        mNewPujaBoxItemListViewModel =
            ViewModelProviders.of(activity as FragmentActivity, mNewPujaBoxItemListFactory)
                .get(NewPujaBoxItemListViewModel::class.java)
        (activity as MainActivity?)!!.setToolbar(true, true, false, true)

        mFragmentNewpujaboxitemlistBinding.viewModel = mNewPujaBoxItemListViewModel
        mFragmentNewpujaboxitemlistBinding.rvNewPujaBoxItemList.layoutManager = GridLayoutManager(
            Click4PanditApp.getInstance(), 2)
        mNewPujaBoxItemListViewModel.init()

        mNewPujaBoxItemListViewModel.getPujaItemBoxList().observe(this, Observer {
            handlePujaItemBoxList(it)

        })
        mNewPujaBoxItemListViewModel.getSelectedPujaItemBoxListItem().observe(this, Observer {
            var prodMasterId:Int= mNewPujaBoxItemListViewModel.newPujaItemBoxList!!.value!!.get(it).prodMasterId!!
            var prodPrice:Double= mNewPujaBoxItemListViewModel.newPujaItemBoxList!!.value!!.get(it).prodPrice!!
            val action=NewPujaBoxItemListDirections.actionNewPujaBoxItemListToNewAddtoCartListFragment()
            action.prodMasterId=prodMasterId
            action.prodPrice= prodPrice.roundToInt()
            Navigation.findNavController(mView).navigate(action)
        })

        mNewPujaBoxItemListViewModel.getSelectedAddtoCartPujaItemBoxListItem().observe(this,
            Observer {
                mNewPujaBoxItemListViewModel.getNewPujaItemBoxAddtoCartOrBuyNow(mNewPujaBoxItemListViewModel.newPujaItemBoxList!!.value!!.get(it).prodMasterId!!,
                    mNewPujaBoxItemListViewModel.newPujaItemBoxList!!.value!!.get(it).prodPrice!!.toInt()
                )
                    .observe(activity as FragmentActivity, Observer {
                        if (isDeviceOnline()) {
                            handlePujaItemBoxAddtoCartOrBuyNowResponse(it)
                        }
                    })
            })


        mNewPujaBoxItemListViewModel.getSelectedpujaBoxWishListItem().observe(this, Observer {
            mNewPujaBoxItemListViewModel.getAddForWishListItem(mNewPujaBoxItemListViewModel.newPujaItemBoxList!!.value!!.get(it).prodMasterId!!,
                cartCount, mNewPujaBoxItemListViewModel.newPujaItemBoxList!!.value!!.get(it).prodPrice!!
            ).observe(activity as FragmentActivity,
                Observer {
                    if (isDeviceOnline()) {
                        handleAddWishListItemPujaBoxResponse(it)
                    }
                })
        })

        mNewPujaBoxItemListViewModel.getRedirecNewPujaBoxItemDetails().observe(this, Observer {
            var prodMasterIdd:Int=mNewPujaBoxItemListViewModel.newPujaItemBoxList!!.value!!.get(it).prodMasterId!!
            val action= NewPujaBoxItemListDirections.actionNewPujaBoxItemListToNewPujaBoxItemDetailsFragment()
            action.setProdMasterId(prodMasterIdd)
            Navigation.findNavController(mView).navigate(action)
        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: NewPujaBoxItemListArgs by navArgs()
        val prodCategoryId = args.pujaItemBoxListId
        Log.e("VALUE",prodCategoryId.toString())
    }






    private fun handleAddWishListItemPujaBoxResponse(resource: Resource<AddWishListItemModel>?) {
        if (resource != null) {

            when (resource.status) {
                Status.ERROR -> {
                    Log.e("AddWishListItemModelResponse", "ERROR")
                    Log.e("AddWishListItemModelResponse", resource.message)
                    Log.e("AddWishListItemModelResponse", resource.status.toString() + "")
                    Log.e("AddWishListItemModelResponse", resource.data.toString() + "")
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
                    Log.e("AddWishListItemModelResponse", "LOADING")
                    Log.e("AddWishListItemModelResponse", resource.data.toString() + "")

                }
                Status.SUCCESS -> {
                    if (resource.data != null) {
                        Log.e("AddWishListItemModelResponse", resource.data.toString())
                        val gson = Gson()
                        val json = gson.toJson(resource.data)
                        Log.e("AddWishListItemModelResponse_SUCCESS", json.toString())
                        if (resource.data.returnStatus.equals("SUCCESS")) {

                            Toast.makeText(activity!!,"Item Added SucessFully in the WishList",
                                Toast.LENGTH_SHORT).show()

                        }


                    } else {
                    }

                }
                else -> {
                }
            }
        }
    }













    private fun handlePujaItemBoxAddtoCartOrBuyNowResponse(resource: Resource<NewPujaItemKitAddtoCartOrBuyNowModel>?) {
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
                            cartCount =resource.data.returnCartValue!!.cartItemCount
                            //  mHomeViewModel.getCartCountItem().observe(getActivity(), HomeFragment.this::handleAddtoCartItemCount);
                            val tvCartCount = activity!!.findViewById<View>(R.id.tvCartCount) as TextView
                            // val storeCartCount:String?=mNewAddtoCartListViewModel.storeCartCount()
                            // val updateCount:String?= Integer.toString(cartCount)
                            //val totalCartCount:String?=storeCartCount+updateCount
                            tvCartCount.setText(Integer.toString(cartCount))

                        }


                    } else {
                    }

                }
                else -> {
                }
            }
        }
    }

    private fun handlePujaItemBoxList(resource: Resource<List<NewPujaItemKitListModel>>?) {
        if (resource != null) {

            when (resource.status) {
                Status.ERROR -> {
                    Log.e("handlePocumentResponse", "ERROR")
                    Log.e("handlePendingDocumentResponse", resource.message)
                    Log.e("handlePendingDocumentResponse", resource.status.toString() + "")
                    Log.e("handlePendingDocumentResponse", resource.data.toString() + "")
                    DisplayDialog.getInstance().dismissAlertDialog()

                }
                Status.LOADING -> {
                    Log.e("handlePendingDocumentResponse", "LOADING")
                    Log.e("handlePendingDocumentResponse", resource.data.toString() + "")
                    DisplayDialog.getInstance()
                        .showAlertDialog(activity, activity!!.getString(R.string.please_wait))

                }
                Status.SUCCESS -> {
                    if (resource.data != null) {
                        Log.e("handlePendingDocumentResponse", resource.data.toString())
                        var list = ArrayList<NewPujaItemKitListModel>()
                        list = resource.data as ArrayList<NewPujaItemKitListModel>
                        list.size;
                        Log.e("handlePendingDocumentResponse", list.size.toString());

                        mNewPujaBoxItemListViewModel.setPujaItemBoxAdapter(list)
                        DisplayDialog.getInstance().dismissAlertDialog()
                    } else {

                    }
                }

            }
        }
    }
}