package com.autumntechcreation.click4panditcustomer.ui.pujaitemkit;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.adapter.PujaItemKitListingAdapter;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileRepository;
import com.autumntechcreation.click4panditcustomer.util.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

public class PujaItemKitViewModel extends ViewModel {
    PujaItemKitRepository mPujaItemKitRepository;
    PujaItemKitListingAdapter mPujaItemKitListingAdapter;
    public MutableLiveData<List<PujaItemKitListingModel>> mPujaItemKitListingModel;
    private SingleLiveEvent<Integer> mSelectedPujaItemKit = new SingleLiveEvent<>();
    @Inject
    public PujaItemKitViewModel(PujaItemKitRepository pujaItemKitRepository) {
        this.mPujaItemKitRepository=pujaItemKitRepository;
    }
    public void init(){
        mPujaItemKitListingAdapter=new PujaItemKitListingAdapter(R.layout.singlerow_pujaitemkitlist,this);
        this.mPujaItemKitListingAdapter.notifyDataSetChanged();
    }
    public PujaItemKitListingAdapter getAdapter() {
        return mPujaItemKitListingAdapter;
    }



    public void setPujaItemKitListingAdapter(List<PujaItemKitListingModel> list) {

        mPujaItemKitListingModel = new MutableLiveData<>();
        mPujaItemKitListingModel.setValue(list);
        this.mPujaItemKitListingAdapter.setPujaItemKitListingPage(list);
        this.mPujaItemKitListingAdapter.notifyDataSetChanged();
    }

    public String getPujaItemKitName(int position) {
        List<PujaItemKitListingModel> list = mPujaItemKitListingModel.getValue();
        return "" + list.get(position).getPujaItemKitName();
    }public String getPujaItemKitPrice(int position) {
        List<PujaItemKitListingModel> list = mPujaItemKitListingModel.getValue();
        return "" + list.get(position).getPujaItemKitPrice();
    }

    @BindingAdapter("pujaItemKitAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
    @BindingAdapter("puja_item_kit_img")
    public static void pujaitemkitimg(ImageView img, int position){
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

    public void onClickBuyNow(View view, int pos) {
        Log.e("ClickPOSITION", view.getId() + "POSITION:" + Integer.toString(pos));

        mSelectedPujaItemKit.setValue(pos);
    }

    public SingleLiveEvent<Integer> getSelectedBuyNowListItem() {
        return mSelectedPujaItemKit;
    }
}
