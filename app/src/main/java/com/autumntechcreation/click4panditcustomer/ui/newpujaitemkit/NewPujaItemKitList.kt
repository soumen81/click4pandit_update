package com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.compose.navArgument
import androidx.navigation.fragment.navArgs

import com.autumntechcreation.click4panditcustomer.BaseFragment
import com.autumntechcreation.click4panditcustomer.MainActivity
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.databinding.FragmentNewpujaitemkitlistBinding
import dagger.android.support.AndroidSupportInjection
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

       // pujaitemKitListid= NewPujaItemKitListArgs.fromBundle(arguments).pujaItemKitListId

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: NewPujaItemKitListArgs by navArgs()
        val pujaItemKitListId = args.pujaItemKitListId
        Log.e("VALUE",pujaItemKitListId.toString())
    }
}