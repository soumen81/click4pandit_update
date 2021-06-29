package com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.adapter.PujaItemProductDescAdapter;
import com.autumntechcreation.click4panditcustomer.ui.profile.ProfileRepository;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitListingModel;

import java.util.List;

import javax.inject.Inject;

public class PujaItemKitDetailsViewModel extends ViewModel {
    PujaItemKitDetailsRepository mPujaItemKitDetailsRepository;
    PujaItemProductDescAdapter mPujaItemProductDescAdapter;
    public MutableLiveData<List<PujaItemProductDescModel>> mPujaItemProductDescModel;

    @Inject
    public PujaItemKitDetailsViewModel(PujaItemKitDetailsRepository pujaItemKitDetailsRepository) {
        this.mPujaItemKitDetailsRepository=pujaItemKitDetailsRepository;
    }
public void init(){
    mPujaItemProductDescAdapter=new PujaItemProductDescAdapter(R.layout.singlerow_pujaitemkitproductdesc,this);
}
    public void setPujaProductDescListingAdapter(List<PujaItemProductDescModel> list) {

        mPujaItemProductDescModel = new MutableLiveData<>();
        mPujaItemProductDescModel.setValue(list);
        this.mPujaItemProductDescAdapter.setPujaItemProductDescListingPage(list);
        this.mPujaItemProductDescAdapter.notifyDataSetChanged();
    }



public PujaItemProductDescAdapter getProductDescForPujaItemKit(){
        return mPujaItemProductDescAdapter;
}
    public String getProductName(int position) {
        List<PujaItemProductDescModel> list = mPujaItemProductDescModel.getValue();
        return "" + list.get(position).getPujaItemProductName();
    }public String getProductWeight(int position) {
        List<PujaItemProductDescModel> list = mPujaItemProductDescModel.getValue();
        return "" + list.get(position).getPujaItemProductWeight();
    }
    public String getProductUnit(int position) {
        List<PujaItemProductDescModel> list = mPujaItemProductDescModel.getValue();
        return "" + list.get(position).getPujaItemProductUnit();
    }
}
