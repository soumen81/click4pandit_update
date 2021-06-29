package com.autumntechcreation.click4panditcustomer.ui.pujabrassitemdetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.adapter.PujaBrassProductDescAdapter;
import com.autumntechcreation.click4panditcustomer.adapter.PujaItemProductDescAdapter;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemsdetails.PujaBoxItemDetailsRepository;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemProductDescModel;

import java.util.List;

import javax.inject.Inject;

public class PujaBrassItemDetailsViewModel extends ViewModel {
    PujaBrassItemDetailsRepository mPujaBrassItemDetailsRepository;
    PujaBrassProductDescAdapter pujaBrassProductDescAdapter;
    public MutableLiveData<List<PujaBrassProductDescModel>> mPujaBrassProductDescModel;
    @Inject
    public PujaBrassItemDetailsViewModel(PujaBrassItemDetailsRepository pujaBrassItemDetailsRepository) {
        this.mPujaBrassItemDetailsRepository=pujaBrassItemDetailsRepository;
    }
    public void init(){
        pujaBrassProductDescAdapter=new PujaBrassProductDescAdapter(R.layout.singlerow_pujabrassproductdesc,this);
    }
    public void setPujaProductDescListingAdapter(List<PujaBrassProductDescModel> list) {

        mPujaBrassProductDescModel = new MutableLiveData<>();
        mPujaBrassProductDescModel.setValue(list);
        this.pujaBrassProductDescAdapter.setPujaBrassProductDescListingPage(list);
        this.pujaBrassProductDescAdapter.notifyDataSetChanged();
    }



    public PujaBrassProductDescAdapter getProductDescForPujaBrassItem(){
        return pujaBrassProductDescAdapter;
    }
    public String getProductBrassName(int position) {
        List<PujaBrassProductDescModel> list = mPujaBrassProductDescModel.getValue();
        return "" + list.get(position).getPujaBrassItemProductName();
    }public String getProductBrassWeight(int position) {
        List<PujaBrassProductDescModel> list = mPujaBrassProductDescModel.getValue();
        return "" + list.get(position).getPujaBrassItemProductWeight();
    }
    public String getProductBrassUnit(int position) {
        List<PujaBrassProductDescModel> list = mPujaBrassProductDescModel.getValue();
        return "" + list.get(position).getPujaBrassItemProductUnit();
    }
}
