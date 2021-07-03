package com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist;

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
import com.autumntechcreation.click4panditcustomer.adapter.PujaItemKitListingAdapter;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileRepository;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitListingModel;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

public class PujaBoxItemListViewModel extends ViewModel {
    PujaBoxItemListRepository mPujaBoxItemListRepository;
    PujaBoxItemListingAdapter pujaBoxItemListingAdapter;
    public MutableLiveData<List<PujaBoxItemListingModel>> mPujaBoxItemListingModel;
    private SingleLiveEvent<Integer> mSelectedPujaBoxItemKit = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> mSelectedWishList = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> mSelectedPujaBoxAddtoCart = new SingleLiveEvent<>();
    @Inject
    public PujaBoxItemListViewModel(PujaBoxItemListRepository pujaBoxItemListRepository) {
        this.mPujaBoxItemListRepository=pujaBoxItemListRepository;
    }
    public void init(){
        pujaBoxItemListingAdapter=new PujaBoxItemListingAdapter(R.layout.singlerow_pujaboxitemlist,this);
        this.pujaBoxItemListingAdapter.notifyDataSetChanged();
    }
    public PujaBoxItemListingAdapter getAdapter() {
        return pujaBoxItemListingAdapter;
    }
    public void setPujaBoxListingAdapter(List<PujaBoxItemListingModel> list) {

        mPujaBoxItemListingModel = new MutableLiveData<>();
        mPujaBoxItemListingModel.setValue(list);
        this.pujaBoxItemListingAdapter.setPujaBoxKitListingPage(list);
        this.pujaBoxItemListingAdapter.notifyDataSetChanged();
    }
    public String getPujaBoxItemKitName(int position) {
        List<PujaBoxItemListingModel> list = mPujaBoxItemListingModel.getValue();
        return "" + list.get(position).getPujaBoxItemKitName();
    }public String getPujaBoxItemKitPrice(int position) {
        List<PujaBoxItemListingModel> list = mPujaBoxItemListingModel.getValue();
        return "" + list.get(position).getPujaBoxItemKitPrice();
    }
    @BindingAdapter("pujaBoxListAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
    @BindingAdapter("pujabox_item_img")
    public static void pujaboxitemimg(ImageView img, int position){
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



    public void onClickPujaBoxBuyNow(View view, int pos) {
        Log.e("ClickPOSITION", view.getId() + "POSITION:" + Integer.toString(pos));

        mSelectedPujaBoxItemKit.setValue(pos);
    }

    public SingleLiveEvent<Integer> getSelectedPujaBoxBuyNowListItem() {
        return mSelectedPujaBoxItemKit;
    }


    public void onClickPujaBoxAddtoCart(View view, int pos) {
        Log.e("ClickPOSITION", view.getId() + "POSITION:" + Integer.toString(pos));

        mSelectedPujaBoxAddtoCart.setValue(pos);
    }

    public SingleLiveEvent<Integer> getSelectedPujaBoxAddtoCart() {
        return mSelectedPujaBoxAddtoCart;
    }
}
