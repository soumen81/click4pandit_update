package com.autumntechcreation.click4panditcustomer.ui.addtocart;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.adapter.AddtoCartListAdapter;
import com.autumntechcreation.click4panditcustomer.adapter.PujaBoxItemListingAdapter;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListingModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemsdetails.PujaBoxItemProductDescModel;
import com.autumntechcreation.click4panditcustomer.ui.shop.ShopRepository;

import java.util.List;

import javax.inject.Inject;

public class AddtoCartViewModel  extends ViewModel {
    AddtoCartRepository mAddtoCartRepository;
    AddtoCartListAdapter mAddtoCartListAdapter;
    public MutableLiveData<List<AddtoCartItemModel>> mAddtoCartItemModelModel;
    @Inject
    public AddtoCartViewModel(AddtoCartRepository addtoCartRepository) {
        this.mAddtoCartRepository=addtoCartRepository;
    }
    public void init(){
        mAddtoCartListAdapter=new AddtoCartListAdapter(R.layout.singlerow_addtocart,this);
        this.mAddtoCartListAdapter.notifyDataSetChanged();
    }
    public AddtoCartListAdapter getAddtoCart() {
        return mAddtoCartListAdapter;
    }

    public void setAddtoCartListingAdapter(List<AddtoCartItemModel> list) {

        mAddtoCartItemModelModel = new MutableLiveData<>();
        mAddtoCartItemModelModel.setValue(list);
        this.mAddtoCartListAdapter.setAddtoCartListingPage(list);
        this.mAddtoCartListAdapter.notifyDataSetChanged();
    }
    public String getProductName(int position) {
        List<AddtoCartItemModel> list = mAddtoCartItemModelModel.getValue();
        return "" + list.get(position).getProductName();
    }public String getProductPrice(int position) {
        List<AddtoCartItemModel> list = mAddtoCartItemModelModel.getValue();
        return "" + list.get(position).getProductPrice();
    }
    @BindingAdapter("addtocart_item_img")
    public static void addtocartitemimg(ImageView img, int position){
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
