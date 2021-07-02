package com.autumntechcreation.click4panditcustomer.ui.wishlist;

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
import com.autumntechcreation.click4panditcustomer.databinding.FragmentWishlistBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.addtocart.AddtoCartFragment;
import com.autumntechcreation.click4panditcustomer.ui.addtocart.AddtoCartFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.addtocart.AddtoCartItemModel;
import com.autumntechcreation.click4panditcustomer.ui.addtocart.AddtoCartViewModel;
import com.autumntechcreation.click4panditcustomer.ui.pujaboxitemlist.PujaBoxItemListFragmentArgs;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class WishListFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    WishListViewModel mWishListViewModel;
    FragmentWishlistBinding mFragmentWishlistBinding;
    private View mView;
    NavController navController;
    String wishListName="";
    private List<WishListItemModel> alWishListItemModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentWishlistBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_wishlist, container, false);
        mFragmentWishlistBinding.setLifecycleOwner(this);
        wishListName= WishListFragmentArgs.fromBundle(getArguments()).getProductwishlistName();
        Log.e("wishListName",""+wishListName);
        return mFragmentWishlistBinding.getRoot();
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

        mWishListViewModel = ViewModelProviders.of(WishListFragment.this, viewModelFactory).get(WishListViewModel.class);
        mFragmentWishlistBinding.setWishlistViewModel(mWishListViewModel);
        RecyclerView wishListRecyclerviewView=mFragmentWishlistBinding.rvWishlist;
        wishListRecyclerviewView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        wishListRecyclerviewView.setHasFixedSize(true);

        mWishListViewModel.init();
        setData();

    }

    private void setData(){
        alWishListItemModel=new ArrayList<>();

        WishListItemModel wishListItemModel1=new WishListItemModel();
        wishListItemModel1.setProductWishName("Ghanti");
        wishListItemModel1.setProductWishPrice("Price:1500");
        wishListItemModel1.setIconWishImage(ContextCompat.getDrawable(getActivity(), R.drawable.ghanti));
        alWishListItemModel.add(wishListItemModel1);

        WishListItemModel wishListItemModel2=new WishListItemModel();
        wishListItemModel2.setProductWishName("Thali");
        wishListItemModel2.setProductWishPrice("Price:1900");
        wishListItemModel2.setIconWishImage(ContextCompat.getDrawable(getActivity(), R.drawable.thali));
        alWishListItemModel.add(wishListItemModel2);

        WishListItemModel wishListItemModel3=new WishListItemModel();
        wishListItemModel3.setProductWishName("Ghati");
        wishListItemModel3.setProductWishPrice("Price:1900");
        wishListItemModel3.setIconWishImage(ContextCompat.getDrawable(getActivity(), R.drawable.ghati));
        alWishListItemModel.add(wishListItemModel3);

        WishListItemModel wishListItemModel4=new WishListItemModel();
        wishListItemModel4.setProductWishName("Brass");
        wishListItemModel4.setProductWishPrice("Price:4900");
        wishListItemModel4.setIconWishImage(ContextCompat.getDrawable(getActivity(), R.drawable.brass));
        alWishListItemModel.add(wishListItemModel4);

        WishListItemModel wishListItemModel5=new WishListItemModel();
        wishListItemModel5.setProductWishName("Pital");
        wishListItemModel5.setProductWishPrice("Price:3900");
        wishListItemModel5.setIconWishImage(ContextCompat.getDrawable(getActivity(), R.drawable.brass));
        alWishListItemModel.add(wishListItemModel5);

        mWishListViewModel.setWishListingAdapter(alWishListItemModel);

}


}
