package com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit

import android.content.Context
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
import com.autumntechcreation.click4panditcustomer.databinding.FragmentNewpujaitemkitlistBinding
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.network.Status
import dagger.android.support.AndroidSupportInjection
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject


class NewPujaItemKitList : BaseFragment() {
    private lateinit var mView: View
    private lateinit var mNewPujaItemKitListViewModel: NewPujaItemKitListViewModel
    private lateinit var mFragmentNewpujaitemkitlistBinding: FragmentNewpujaitemkitlistBinding
    var pujaitemKitListid: Int? = null
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: NewPujaItemKitListArgs by navArgs()
        val pujaItemKitListId = args.pujaItemKitListId
        Log.e("VALUE",pujaItemKitListId.toString())
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