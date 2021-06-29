package com.autumntechcreation.click4panditcustomer.ui.pujaboxitemsdetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.adapter.PujaBoxProductDescAdapter;
import com.autumntechcreation.click4panditcustomer.adapter.PujaItemProductDescAdapter;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemKitDetailsRepository;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemProductDescModel;

import java.util.List;

import javax.inject.Inject;

public class PujaBoxItemDetailsViewModel extends ViewModel {
    PujaBoxItemDetailsRepository mPujaBoxItemDetailsRepository;
    PujaBoxProductDescAdapter mPujaBoxProductDescAdapter;
    public MutableLiveData<List<PujaBoxItemProductDescModel>> mPujaBoxItemProductDescModel;
    @Inject
    public PujaBoxItemDetailsViewModel(PujaBoxItemDetailsRepository pujaBoxItemDetailsRepository) {
        this.mPujaBoxItemDetailsRepository=pujaBoxItemDetailsRepository;
    }
    public void init(){
        mPujaBoxProductDescAdapter=new PujaBoxProductDescAdapter(R.layout.singlerow_pujaboxproductdesc,this);
    }
    public void setPujaBoxProductListingAdapter(List<PujaBoxItemProductDescModel> list) {

        mPujaBoxItemProductDescModel = new MutableLiveData<>();
        mPujaBoxItemProductDescModel.setValue(list);
        this.mPujaBoxProductDescAdapter.setPujaBoxProductDescListingPage(list);
        this.mPujaBoxProductDescAdapter.notifyDataSetChanged();
    }



    public PujaBoxProductDescAdapter getProductDescForPujaBoxItem(){
        return mPujaBoxProductDescAdapter;
    }
    public String getProductBoxName(int position) {
        List<PujaBoxItemProductDescModel> list = mPujaBoxItemProductDescModel.getValue();
        return "" + list.get(position).getPujaBoxItemProductName();
    }public String getProductBoxWeight(int position) {
        List<PujaBoxItemProductDescModel> list = mPujaBoxItemProductDescModel.getValue();
        return "" + list.get(position).getPujaBoxItemProductWeight();
    }
    public String getProductBoxUnit(int position) {
        List<PujaBoxItemProductDescModel> list = mPujaBoxItemProductDescModel.getValue();
        return "" + list.get(position).getPujaBoxItemProductUnit();
    }

}
