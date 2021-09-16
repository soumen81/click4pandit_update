package com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit

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
import com.autumntechcreation.click4panditcustomer.databinding.FragmentNewpujaitemkitlistBinding
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.network.Status
import com.google.gson.Gson
import dagger.android.support.AndroidSupportInjection
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import kotlin.math.roundToInt


class NewPujaItemKitList : BaseFragment() {
    private lateinit var mView: View
    private lateinit var mNewPujaItemKitListViewModel: NewPujaItemKitListViewModel
    private lateinit var mFragmentNewpujaitemkitlistBinding: FragmentNewpujaitemkitlistBinding
    var pujaitemKitListid: Int? = null
    var cartCount: Int = 0

    @Inject
    lateinit var mNewPujaItemKitListFactory: NewPujaItemKitListFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mFragmentNewpujaitemkitlistBinding =
            DataBindingUtil.inflate(inflater, defineLayoutResource(), container, false)
        mFragmentNewpujaitemkitlistBinding.lifecycleOwner = this
        mView = mFragmentNewpujaitemkitlistBinding.root
       // val bundle = arguments

        return mView

    }
    override fun defineLayoutResource(): Int {
        return R.layout.fragment_newpujaitemkitlist
    }

    override fun initializeComponent(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)

        mView = view;
        mNewPujaItemKitListViewModel =
            ViewModelProviders.of(activity as FragmentActivity, mNewPujaItemKitListFactory)
                .get(NewPujaItemKitListViewModel::class.java)
        (activity as MainActivity?)!!.setToolbar(true, true, false, true)

        mFragmentNewpujaitemkitlistBinding.viewModel = mNewPujaItemKitListViewModel

       /* val llm = LinearLayoutManager(activity)
        llm.orientation = RecyclerView.VERTICAL
        mFragmentNewpujaitemkitlistBinding.rvNewPujaItemKitList.layoutManager = llm*/
        mFragmentNewpujaitemkitlistBinding.rvNewPujaItemKitList.layoutManager = GridLayoutManager(Click4PanditApp.getInstance(), 2)
        mNewPujaItemKitListViewModel.init()


        mNewPujaItemKitListViewModel.getPujaItemKitList().observe(this, Observer {
            handlePujaItemKitList(it)

        })

        mNewPujaItemKitListViewModel.getSelectedPujaItemSamagriListItem().observe(this, Observer {
            var mNewPujaItemKitListModel: NewPujaItemKitListModel = mNewPujaItemKitListViewModel.newPujaItemKitList!!.value!!.get(it)
            var prodMasterId:Int= mNewPujaItemKitListViewModel.newPujaItemKitList!!.value!!.get(it).prodMasterId!!
            var prodPrice:Double= mNewPujaItemKitListViewModel.newPujaItemKitList!!.value!!.get(it).prodPrice!!
            val action = NewPujaItemKitListDirections.actionNewPujaItemKitListToNewAddtoCartListFragment()
            action.setMyArg(mNewPujaItemKitListModel)
            action.prodMasterId=prodMasterId
            action.prodPrice= prodPrice.roundToInt()
            Navigation.findNavController(mView).navigate(action)
        })


        mNewPujaItemKitListViewModel.getSelectedaddtoCartListItem().observe(this, Observer {
            mNewPujaItemKitListViewModel.getNewPujaItemKitAddtoCartOrBuyNow(mNewPujaItemKitListViewModel.newPujaItemKitList!!.value!!.get(it).prodMasterId!!,
                mNewPujaItemKitListViewModel.newPujaItemKitList!!.value!!.get(it).prodPrice!!.toInt()
            )
                .observe(activity as FragmentActivity, Observer {
                    if (isDeviceOnline()) {
                        handlePujaItemKitAddtoCartOrBuyNowResponse(it)
                    }
                })
        })

        mNewPujaItemKitListViewModel.getSelectedRedirectedSamagriListItem().observe(this, Observer {
            var prodMasterIdd:Int= mNewPujaItemKitListViewModel.newPujaItemKitList!!.value!!.get(it).prodMasterId!!
            val action=NewPujaItemKitListDirections.actionNewPujaItemKitListToNewPujaSamagriListDetailsFragment()
            action.setProdMasterId(prodMasterIdd)
            Navigation.findNavController(mView).navigate(action)
        })

        mNewPujaItemKitListViewModel.getSelectedmSelectedWishListItem().observe(this, Observer {

            var newPujaItemKitList: java.util.ArrayList<NewPujaItemKitListModel>? =
                mNewPujaItemKitListViewModel.newPujaItemKitList!!.value
            //For select background color change

            Log.e("SIZE", newPujaItemKitList!!.size.toString() + "")
           // mNewPujaItemKitListViewModel.pujaitemkitImg(it)
           /* for (i in newPujaItemKitList!!.indices) {
                val newPujaitemKitListItem= newPujaItemKitList[i]
                if (i == it) {
                    newPujaitemKitListItem.isSelect==true

                } else {
                    newPujaitemKitListItem.isSelect==false

                }
                newPujaItemKitList[i] = newPujaitemKitListItem
            }
            val gson = Gson()
            Log.e("CLICK", gson.toJson(newPujaItemKitList))*/
          //  mNewPujaItemKitListViewModel.setPujaItemKitAdapter(newPujaItemKitList)

            mNewPujaItemKitListViewModel.getAddForWishListItem(mNewPujaItemKitListViewModel.newPujaItemKitList!!.value!!.get(it).prodMasterId!!,
                cartCount, mNewPujaItemKitListViewModel.newPujaItemKitList!!.value!!.get(it).prodPrice!!
            ).observe(activity as FragmentActivity,
                Observer {
                    if (isDeviceOnline()) {
                        handleAddWishListItemResponse(it)
                    }
                })
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: NewPujaItemKitListArgs by navArgs()
        val prodCategoryId = args.pujaItemKitListId
        Log.e("VALUE",prodCategoryId.toString())
    }


    private fun handleAddWishListItemResponse(resource: Resource<AddWishListItemModel>?) {
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

                          Toast.makeText(activity!!,"Item Added SucessFully in the WishList",Toast.LENGTH_SHORT).show()

                        }


                    } else {
                    }

                }
                else -> {
                }
            }
        }
    }









    private fun handlePujaItemKitAddtoCartOrBuyNowResponse(resource: Resource<NewPujaItemKitAddtoCartOrBuyNowModel>?) {
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



















    private fun handlePujaItemKitList(resource: Resource<List<NewPujaItemKitListModel>>?) {
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








                        mNewPujaItemKitListViewModel.setPujaItemKitAdapter(list)


                        DisplayDialog.getInstance().dismissAlertDialog()
                    } else {

                    }
                }

            }
        }
    }

}