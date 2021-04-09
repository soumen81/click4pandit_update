package com.autumntechcreation.click4panditcustomer.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentOrderBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryFragmentDirections;
import com.autumntechcreation.click4panditcustomer.ui.settings.SettingsViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static androidx.navigation.Navigation.findNavController;

public class OrderFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    OrderViewModel mOrderViewModel;
    FragmentOrderBinding mFragmentOrderBinding;
    private View mView;
    NavController navController;
    private List<OrderListModel> listOrderListModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentOrderBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        mFragmentOrderBinding.setLifecycleOwner(this);

        return mFragmentOrderBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        navController=findNavController(mView);
        ((MainActivity) getActivity()).setToolbar(false,true,false,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //bind Vertical RecyclerView
        RecyclerView orderListRecyclerView=mFragmentOrderBinding.rvOrderList;
        orderListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        orderListRecyclerView.setHasFixedSize(true);

        mOrderViewModel= ViewModelProviders.of(OrderFragment.this, viewModelFactory).get(OrderViewModel.class);
        mFragmentOrderBinding.setOrderViewModel(mOrderViewModel);
        mOrderViewModel.init();

        setOrderList();

        mOrderViewModel.getOrderListItemsClick().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                findNavController(mView).navigate(OrderFragmentDirections.actionOrderFragmentToOrderDetailsFragment());
            }
        });
    }
    private void setOrderList(){
        listOrderListModel=new ArrayList<>();

        OrderListModel orderListModel=new OrderListModel();
        orderListModel.setOrderNo("#776644");
        orderListModel.setPujaName("Ganesh Puja");
        orderListModel.setDateTime("16th, May 21");
        orderListModel.setPaymentStatus("Paid");
        orderListModel.setAmount("TOTAL \u20B9 5,000.00");
        listOrderListModel.add(orderListModel);

        OrderListModel orderListModel2=new OrderListModel();
        orderListModel2.setOrderNo("#776644");
        orderListModel2.setPujaName("Kali Puja");
        orderListModel2.setDateTime("16th, June 21");
        orderListModel2.setPaymentStatus("Paid");
        orderListModel2.setAmount("TOTAL \u20B9 3,000.00");
        listOrderListModel.add(orderListModel2);

        OrderListModel orderListModel3=new OrderListModel();
        orderListModel3.setOrderNo("#776644");
        orderListModel3.setPujaName("Durga Puja");
        orderListModel3.setDateTime("16th, July 21");
        orderListModel3.setPaymentStatus("Pending");
        orderListModel3.setAmount("TOTAL \u20B9 7,000.00");
        listOrderListModel.add(orderListModel3);

        OrderListModel orderListModel4=new OrderListModel();
        orderListModel4.setOrderNo("#776644");
        orderListModel4.setPujaName("Ganesh Puja");
        orderListModel4.setDateTime("16th, July 21");
        orderListModel4.setPaymentStatus("Pending");
        orderListModel4.setAmount("TOTAL \u20B9 4,000.00");
        listOrderListModel.add(orderListModel4);

        mOrderViewModel.setOrderList(listOrderListModel);
    }
}
