package com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.autumntechcreation.click4panditcustomer.BaseFragment
import com.autumntechcreation.click4panditcustomer.MainActivity
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.databinding.FragmentNewaddtocartlistBinding
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.network.Status
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitAddtoCartOrBuyNowModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import com.google.gson.Gson
import dagger.android.support.AndroidSupportInjection
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class NewAddtoCartListFragment : BaseFragment(){
    private lateinit var mView: View
    private lateinit var mNewAddtoCartListViewModel: NewAddtoCartListViewModel
    private lateinit var mFragmentNewaddtocartlistBinding: FragmentNewaddtocartlistBinding
    @Inject
    lateinit var mNewAddtoCartListFactory: NewAddtoCartListFactory
    var args: NewPujaItemKitListModel? = null;
    var prodMasterId:Int = 0
    var prodPrice:Int = 0
    var cartCount: Int=0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mFragmentNewaddtocartlistBinding =
            DataBindingUtil.inflate(inflater, defineLayoutResource(), container, false)
        mFragmentNewaddtocartlistBinding.lifecycleOwner = this
        mView = mFragmentNewaddtocartlistBinding.root
        // val bundle = arguments

        return mView

    }
    override fun defineLayoutResource(): Int {
        return R.layout.fragment_newaddtocartlist
    }

    override fun initializeComponent(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)

        mView = view;
        mNewAddtoCartListViewModel =
            ViewModelProviders.of(activity as FragmentActivity, mNewAddtoCartListFactory)
                .get(NewAddtoCartListViewModel::class.java)
        (activity as MainActivity?)!!.setToolbar(true, true, false, true)

        mFragmentNewaddtocartlistBinding.viewModel = mNewAddtoCartListViewModel

        val llm = LinearLayoutManager(activity)
        llm.orientation = RecyclerView.VERTICAL
        mFragmentNewaddtocartlistBinding.rvNewPujaAddtoCartList.layoutManager = llm

       /* mFragmentNewaddtocartlistBinding.rvNewPujaAddtoCartList.layoutManager = GridLayoutManager(
            Click4PanditApp.getInstance(), 2)*/
        mNewAddtoCartListViewModel.init()







    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: NewAddtoCartListFragmentArgs by navArgs()
       /* val pujaSamagriCategoryId = args.pujaSamagriCategoryId
        Log.e("VALUE",pujaSamagriCategoryId.toString())*/


       // args = UpdateGateOutwardFragmentArgs.fromBundle(arguments).myArg

        //Toast.makeText(activity, args!!.vehicleNo.toString(),Toast.LENGTH_SHORT).show()

        val bundle = arguments
        val gson = Gson()
        Log.e("OBJECT",gson.toJson(args))
        val pujaSamagri=args
             prodMasterId=args.prodMasterId
             prodPrice=args.prodPrice
        mNewAddtoCartListViewModel.getNewPujaItemKitAddtoCartOrBuyNow(prodMasterId, prodPrice)
            .observe(activity as FragmentActivity, Observer {
                if (isDeviceOnline()) {
                    handlePujaItemKitAddtoCartOrBuyNowResponse(it)
                }
            })

        mNewAddtoCartListViewModel.getSelectedDeleteForListItem().observe(this, Observer {
            mNewAddtoCartListViewModel.newAddtoCartList!!.value!!.get(it).prodCustScId!!
            mNewAddtoCartListViewModel.newAddtoCartList!!.value!!.get(it).prodCustScDt!!
            mNewAddtoCartListViewModel.newAddtoCartList!!.value!!.get(it).prodCustScRate!!

        mNewAddtoCartListViewModel.getRemoveForPujaItem(mNewAddtoCartListViewModel.newAddtoCartList!!.value!!.get(it).prodCustScId!!,prodMasterId,
            mNewAddtoCartListViewModel.newAddtoCartList!!.value!!.get(it).prodCustScRate!!, mNewAddtoCartListViewModel.newAddtoCartList!!.value!!.get(it).prodCustScDt!!,cartCount).observe(activity as FragmentActivity,
            Observer {
                if (isDeviceOnline()) {
                    handlePujaItemKitAddtoCartOrBuyNowResponse(it)
                }
            })
        })

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
                            mNewAddtoCartListViewModel.getAddtoCartItemList().observe(activity!!, Observer {

                                handleAddToCartItemList(it)

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






    private fun handleAddToCartItemList(resource: Resource<List<NewAddtoCartListModel>>?) {
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
                        var list = ArrayList<NewAddtoCartListModel>()
                        list = resource.data as ArrayList<NewAddtoCartListModel>
                        list.size;
                        Log.e("handlePendingDocumentResponse", list.size.toString());

                        mNewAddtoCartListViewModel.setAddtoCartListAdapter(list)
                        DisplayDialog.getInstance().dismissAlertDialog()
                    } else {

                    }
                }

            }
        }
    }
}