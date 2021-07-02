package com.autumntechcreation.click4panditcustomer.ui.wishlist;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.adapter.AddtoCartListAdapter;
import com.autumntechcreation.click4panditcustomer.adapter.WishListAdapter;
import com.autumntechcreation.click4panditcustomer.ui.addtocart.AddtoCartItemModel;
import com.autumntechcreation.click4panditcustomer.ui.addtocart.AddtoCartRepository;

import java.util.List;

import javax.inject.Inject;

public class WishListViewModel extends ViewModel {
    WishListRepository mWishListRepository;
    WishListAdapter mWishListAdapter;
    public MutableLiveData<List<WishListItemModel>> mWishListItemModel;
    @Inject
    public WishListViewModel(WishListRepository wishListRepository) {
        this.mWishListRepository=wishListRepository;
    }
    public void init(){
        mWishListAdapter=new WishListAdapter(R.layout.singlerow_wishlist,this);
        this.mWishListAdapter.notifyDataSetChanged();
    }
    public WishListAdapter getWishList() {
        return mWishListAdapter;
    }

    public void setWishListingAdapter(List<WishListItemModel> list) {

        mWishListItemModel = new MutableLiveData<>();
        mWishListItemModel.setValue(list);
        this.mWishListAdapter.setWishListingPage(list);
        this.mWishListAdapter.notifyDataSetChanged();
    }


    public String getWiishProductName(int position) {
        List<WishListItemModel> list = mWishListItemModel.getValue();
        return "" + list.get(position).getProductWishName();
    }public String getWishProductPrice(int position) {
        List<WishListItemModel> list = mWishListItemModel.getValue();
        return "" + list.get(position).getProductWishPrice();
    }
    @BindingAdapter("wishlist_item_img")
    public static void wishlistitemimg(ImageView img, int position){
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
}
