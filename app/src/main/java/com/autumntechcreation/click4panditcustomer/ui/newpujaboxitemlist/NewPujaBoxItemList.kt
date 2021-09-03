package com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class NewPujaBoxItemList : BaseFragment(){

    private lateinit var mView: View
    private lateinit var mNewPujaBoxItemListViewModel: NewPujaBoxItemListViewModel
    private lateinit var mFragmentNewpujaboxitemlistBinding: FragmentNewpujaboxitemlistBinding
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
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: NewPujaBoxItemListArgs by navArgs()
        val prodCategoryId = args.pujaItemBoxListId
        Log.e("VALUE",prodCategoryId.toString())
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