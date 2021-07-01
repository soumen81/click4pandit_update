package com.autumntechcreation.click4panditcustomer.ui.addtocart;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentAddtocartBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListingModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkit.PujaItemKitFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemKitDetailsFragment;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemKitDetailsFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.pujaitemkitdetails.PujaItemKitDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class AddtoCartFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    AddtoCartViewModel mAddtoCartViewModel;
    FragmentAddtocartBinding mFragmentAddtocartBinding;
    private View mView;
    NavController navController;
    private List<AddtoCartItemModel> alAddtoCartItemModel;
    String cartProductname="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentAddtocartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_addtocart, container, false);
        mFragmentAddtocartBinding.setLifecycleOwner(this);
        cartProductname= AddtoCartFragmentArgs.fromBundle(getArguments()).getProductAddtoCartName();
        Log.e("cartProductname",""+cartProductname);

        return mFragmentAddtocartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        navController = findNavController(mView);
        ((MainActivity) getActivity()).setToolbar(false, true, false, true);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAddtoCartViewModel = ViewModelProviders.of(AddtoCartFragment.this, viewModelFactory).get(AddtoCartViewModel.class);
        mFragmentAddtocartBinding.setAddtocartViewModel(mAddtoCartViewModel);
        RecyclerView chartDetailsRecyclerviewView=mFragmentAddtocartBinding.rvAddtoCartItem;
        chartDetailsRecyclerviewView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        chartDetailsRecyclerviewView.setHasFixedSize(true);


        mAddtoCartViewModel.init();
        setData();

    }
    private void setData(){
        alAddtoCartItemModel=new ArrayList<>();

        AddtoCartItemModel addtoCartItemModel1=new AddtoCartItemModel();
        addtoCartItemModel1.setProductName("Ghanti");
        addtoCartItemModel1.setProductPrice("Price:1500");
        addtoCartItemModel1.setIconAddtoCartImage(ContextCompat.getDrawable(getActivity(), R.drawable.ghanti));
        alAddtoCartItemModel.add(addtoCartItemModel1);

        AddtoCartItemModel addtoCartItemModel2=new AddtoCartItemModel();
        addtoCartItemModel2.setProductName("Thali");
        addtoCartItemModel2.setProductPrice("Price:1900");
        addtoCartItemModel2.setIconAddtoCartImage(ContextCompat.getDrawable(getActivity(), R.drawable.thali));
        alAddtoCartItemModel.add(addtoCartItemModel2);

        AddtoCartItemModel addtoCartItemModel3=new AddtoCartItemModel();
        addtoCartItemModel3.setProductName("Ghati");
        addtoCartItemModel3.setProductPrice("Price:1900");
        addtoCartItemModel3.setIconAddtoCartImage(ContextCompat.getDrawable(getActivity(), R.drawable.ghati));
        alAddtoCartItemModel.add(addtoCartItemModel3);

        AddtoCartItemModel addtoCartItemModel4=new AddtoCartItemModel();
        addtoCartItemModel4.setProductName("Brass");
        addtoCartItemModel4.setProductPrice("Price:4900");
        addtoCartItemModel4.setIconAddtoCartImage(ContextCompat.getDrawable(getActivity(), R.drawable.brass));
        alAddtoCartItemModel.add(addtoCartItemModel4);

        AddtoCartItemModel addtoCartItemModel5=new AddtoCartItemModel();
        addtoCartItemModel5.setProductName("Pital");
        addtoCartItemModel5.setProductPrice("Price:3900");
        addtoCartItemModel5.setIconAddtoCartImage(ContextCompat.getDrawable(getActivity(), R.drawable.brass));
        alAddtoCartItemModel.add(addtoCartItemModel4);

        mAddtoCartViewModel.setAddtoCartListingAdapter(alAddtoCartItemModel);
    }
}
