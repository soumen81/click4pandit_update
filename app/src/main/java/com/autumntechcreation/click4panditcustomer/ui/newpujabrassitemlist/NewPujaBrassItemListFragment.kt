package com.autumntechcreation.click4panditcustomer.ui.newpujabrassitemlist

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
import com.autumntechcreation.click4panditcustomer.databinding.FragmentNewpujabrassitemlistBinding
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.network.Status
import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist.NewPujaBoxItemListArgs
import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist.NewPujaBoxItemListFactory
import com.autumntechcreation.click4panditcustomer.ui.newpujaboxitemlist.NewPujaBoxItemListViewModel
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NewPujaBrassItemListFragment : BaseFragment(){
    private lateinit var mView: View
    private lateinit var mNewPujaBrassItemListViewModel: NewPujaBrassItemListViewModel
    private lateinit var mFragmentNewpujabrassitemlistBinding: FragmentNewpujabrassitemlistBinding
    @Inject
    lateinit var mNewPujaBrassItemListFactory: NewPujaBrassItemListFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentNewpujabrassitemlistBinding =
            DataBindingUtil.inflate(inflater, defineLayoutResource(), container, false)
        mFragmentNewpujabrassitemlistBinding.lifecycleOwner = this
        mView = mFragmentNewpujabrassitemlistBinding.root
        // val bundle = arguments

        return mView
    }
    override fun defineLayoutResource(): Int {
        return R.layout.fragment_newpujabrassitemlist
    }

    override fun initializeComponent(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)

        mView = view;
        mNewPujaBrassItemListViewModel =
            ViewModelProviders.of(activity as FragmentActivity, mNewPujaBrassItemListFactory)
                .get(NewPujaBrassItemListViewModel::class.java)
        (activity as MainActivity?)!!.setToolbar(true, true, false, true)

        mFragmentNewpujabrassitemlistBinding.viewModel = mNewPujaBrassItemListViewModel

        mFragmentNewpujabrassitemlistBinding.rvNewPujaBrassItemList.layoutManager = GridLayoutManager(
            Click4PanditApp.getInstance(), 2)
        mNewPujaBrassItemListViewModel.init()

        mNewPujaBrassItemListViewModel.getPujaItemBrassList().observe(this, Observer {
            handlePujaItemBrassList(it)

        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: NewPujaBrassItemListFragmentArgs by navArgs()
        val pujaItemBrassListId = args.pujaItemBrassListId
        Log.e("VALUE",pujaItemBrassListId.toString())
    }
    private fun handlePujaItemBrassList(resource: Resource<List<NewPujaItemKitListModel>>?) {
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

                        mNewPujaBrassItemListViewModel.setPujaItemBrassAdapter(list)
                        DisplayDialog.getInstance().dismissAlertDialog()
                    } else {

                    }
                }

            }
        }
    }
}