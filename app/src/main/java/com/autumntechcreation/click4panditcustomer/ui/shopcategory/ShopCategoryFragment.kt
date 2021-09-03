package com.autumntechcreation.click4panditcustomer.ui.shopcategory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.autumntechcreation.click4panditcustomer.BaseFragment
import com.autumntechcreation.click4panditcustomer.MainActivity
import com.autumntechcreation.click4panditcustomer.R
import com.autumntechcreation.click4panditcustomer.databinding.FragmentShopcategoryBinding
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog
import com.autumntechcreation.click4panditcustomer.network.Resource
import com.autumntechcreation.click4panditcustomer.network.Status
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ShopCategoryFragment : BaseFragment() {
    private lateinit var mView: View
    private lateinit var mShopCategoryViewModel: ShopCategoryViewModel
    private lateinit var mFragmentShopcategoryBinding: FragmentShopcategoryBinding
    private var alShopCategoryModel: List<ShopCategoryModel>? = null
    @Inject
    lateinit var mShopCategoryFactory: ShopCategoryFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentShopcategoryBinding =
            DataBindingUtil.inflate(inflater, defineLayoutResource(), container, false)
        mFragmentShopcategoryBinding.lifecycleOwner = this
        mView = mFragmentShopcategoryBinding.root
        return mFragmentShopcategoryBinding.root
    }

    override fun defineLayoutResource(): Int {
        return R.layout.fragment_shopcategory
    }

    override fun initializeComponent(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        mView = view;
        mShopCategoryViewModel =
            ViewModelProviders.of(activity as FragmentActivity, mShopCategoryFactory)
                .get(ShopCategoryViewModel::class.java)
        (activity as MainActivity?)!!.setToolbar(true, true, false, true)

        mFragmentShopcategoryBinding.viewModel = mShopCategoryViewModel


        /*  mFragmentChoosepackageBinding.carousal.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImager[position]);
            }
        });

        mFragmentChoosepackageBinding.carousal.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(),mImagetitle[position],Toast.LENGTH_SHORT).show();
            }
        });
        mFragmentChoosepackageBinding.carousal.setPageCount(mImager.length);
        mChoosePackageViewModel.getonClickStandardPackage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                showCustomChoosePackageDialog();
            }
        });
*/
        val llm = LinearLayoutManager(activity)
        llm.orientation = LinearLayoutManager.VERTICAL
        mFragmentShopcategoryBinding.rvShopCategoryList.setLayoutManager(llm)
        mShopCategoryViewModel.init()
       // setData()
        mShopCategoryViewModel.getShopCategoryItemList().observe(this, Observer {
            handleShopCategoryItemList(it)

        })
       /* mFragmentShopcategoryBinding.tvPujaItemKitShopNow.setOnClickListener(View.OnClickListener {

            val action1 = ShopCategoryFragmentDirections.actionShopCategoryFragmentToNewPujaItemKitList()
            action1.pujaItemKitListId=1
            Navigation.findNavController(mView).navigate(action1)
        })
        mFragmentShopcategoryBinding.tvPujaBoxShopNow.setOnClickListener(View.OnClickListener {

            val action2 = ShopCategoryFragmentDirections.actionShopCategoryFragmentToNewPujaBoxItemList()
            action2.pujaItemBoxListId=2
            Navigation.findNavController(mView).navigate(action2)
        })
        mFragmentShopcategoryBinding.tvPujaBrassItemShopNow.setOnClickListener(View.OnClickListener {

            val action3 = ShopCategoryFragmentDirections.actionShopCategoryFragmentToNewPujaBrassItemListFragment()
            action3.pujaItemBrassListId=3
            Navigation.findNavController(mView).navigate(action3)
        })
*/



        mShopCategoryViewModel.getSelectedShopCategoryListItem().observe(this, Observer {
            var shopCategoryModel: ShopCategoryModel = mShopCategoryViewModel.shopCategoryModelList!!.value!!.get(it)
            if(shopCategoryModel.prodCtgryId==1001){
                val action2 = ShopCategoryFragmentDirections.actionShopCategoryFragmentToNewPujaBoxItemList()
                action2.pujaItemBoxListId=1001
                Navigation.findNavController(mView).navigate(action2)
            }else if(shopCategoryModel.prodCtgryId==1002){
                val action1 = ShopCategoryFragmentDirections.actionShopCategoryFragmentToNewPujaItemKitList()
                action1.pujaItemKitListId=1002
                Navigation.findNavController(mView).navigate(action1)
            }else if(shopCategoryModel.prodCtgryId==1003){
                val action3 = ShopCategoryFragmentDirections.actionShopCategoryFragmentToNewPujaBrassItemListFragment()
                action3.pujaItemBrassListId=1003
                Navigation.findNavController(mView).navigate(action3)
            }else if(shopCategoryModel.prodCtgryId==1004){
                val action4=ShopCategoryFragmentDirections.actionShopCategoryFragmentToNewGiftBoxListFragment()
                action4.pujaGiftBoxListId=1004
                Navigation.findNavController(mView).navigate(action4)
            }
        })


    }



    private fun handleShopCategoryItemList(resource: Resource<List<ShopCategoryModel>>?) {
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
                        var list = ArrayList<ShopCategoryModel>()
                        list = resource.data as ArrayList<ShopCategoryModel>
                        list.size;
                        Log.e("handlePendingDocumentResponse", list.size.toString());
                        mShopCategoryViewModel.setShopCategoryListAdapter(list)

                        DisplayDialog.getInstance().dismissAlertDialog()
                    } else {

                    }
                }

            }
        }
    }

}