package com.autumntechcreation.click4panditcustomer.ui.pujabrassitemlist;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.adapter.PujaBoxItemListingAdapter;
import com.autumntechcreation.click4panditcustomer.adapter.PujaBrassItemListAdapter;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListRepository;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListingModel;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

public class PujaBrassItemListingViewModel extends ViewModel {
    PujaBrassItemListingRepository mPujaBrassItemListingRepository;
    PujaBrassItemListAdapter mPujaBrassItemListAdapter;
    public MutableLiveData<List<PujaBrassItemListingModel>> mPujaBrassItemListingModel;
    private SingleLiveEvent<Integer> mSelectedPujaBrassItem = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> mSelectedPujaBrassAddtoCart = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> mSelectedPujaBrassWishlist = new SingleLiveEvent<>();
    @Inject
    public PujaBrassItemListingViewModel(PujaBrassItemListingRepository pujaBrassItemListingRepository) {
        this.mPujaBrassItemListingRepository=pujaBrassItemListingRepository;
    }
    public void init(){
        mPujaBrassItemListAdapter=new PujaBrassItemListAdapter(R.layout.singlerow_pujabrassitemlist,this);
        this.mPujaBrassItemListAdapter.notifyDataSetChanged();
    }
    public PujaBrassItemListAdapter getAdapter() {
        return mPujaBrassItemListAdapter;
    }

    public void setPujaBrassListingAdapter(List<PujaBrassItemListingModel> list) {

        mPujaBrassItemListingModel = new MutableLiveData<>();
        mPujaBrassItemListingModel.setValue(list);
        this.mPujaBrassItemListAdapter.setPujaBrassListingPage(list);
        this.mPujaBrassItemListAdapter.notifyDataSetChanged();
    }
    public String getPujaBrassName(int position) {
        List<PujaBrassItemListingModel> list = mPujaBrassItemListingModel.getValue();
        return "" + list.get(position).getPujaBrassItemKitName();
    }public String getPujaBrassItemPrice(int position) {
        List<PujaBrassItemListingModel> list = mPujaBrassItemListingModel.getValue();
        return "" + list.get(position).getPujaBrassItemKitPrice();
    }
    @BindingAdapter("pujaBrassListAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
    @BindingAdapter("pujabrass_item_img")
    public static void pujabrassitemimg(ImageView img, int position){
        if(position==0){
            img.setBackgroundResource(R.drawable.ghanti);
        }else if(position==1){
            img.setBackgroundResource(R.drawable.ghati);
        }else if(position==2){
            img.setBackgroundResource(R.drawable.thali);
        }else if(position==3){
            img.setBackgroundResource(R.drawable.brass);
        }else if(position==4){
            img.setBackgroundResource(R.drawable.astromongola);
        }
    }

    public void onClickPujaBrassBuyNow(View view, int pos) {
        Log.e("ClickPOSITION", view.getId() + "POSITION:" + Integer.toString(pos));

        mSelectedPujaBrassItem.setValue(pos);
    }

    public SingleLiveEvent<Integer> getSelectedPujaBrassBuyNowListItem() {
        return mSelectedPujaBrassItem;
    }
    public void onClickPujaBrassAddtoCart(View view, int pos) {
        Log.e("ClickPOSITION", view.getId() + "POSITION:" + Integer.toString(pos));

        mSelectedPujaBrassAddtoCart.setValue(pos);
    }

    public SingleLiveEvent<Integer> getSelectedPujaBrassAddtoCart() {
        return mSelectedPujaBrassAddtoCart;
    }
    public void onClickPujaBrassKitWishList(View view, int pos) {
        Log.e("ClickPOSITION", view.getId() + "POSITION:" + Integer.toString(pos));

        mSelectedPujaBrassWishlist.setValue(pos);
    }

    public SingleLiveEvent<Integer> getSelectedPujaBrassWishList() {
        return mSelectedPujaBrassWishlist;
    }
}
