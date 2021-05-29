package com.autumntechcreation.click4panditcustomer.ui.orders;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentOrderBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragment;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageListModel;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.PujaPrcdr;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummaryFragmentDirections;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.autumntechcreation.click4panditcustomer.ui.settings.SettingsViewModel;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.navigation.Navigation.findNavController;

public class OrderFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    OrderViewModel mOrderViewModel;
    FragmentOrderBinding mFragmentOrderBinding;
    private View mView;
    NavController navController;
    private List<OrderListModel> listOrderListModel;
    String[] filterDays = { "Choose Days","Last 7 Days", "This Month", "Last Month"};
    ArrayAdapter<String> mSpinFilterAdapter;
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



        mSpinFilterAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, filterDays);
        // Specify the layout to use when the list of choices appears
        mSpinFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mFragmentOrderBinding.spinFilter.setAdapter(mSpinFilterAdapter);


        mFragmentOrderBinding.spinFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){


                }else if(position==1){

                    mOrderViewModel.getOrderList("Last-7-Days").observe(getActivity(), OrderFragment.this::handleOrderList);


                }else if(position==2){
                    mOrderViewModel.getOrderList("This Month").observe(getActivity(), OrderFragment.this::handleOrderList);


                }else if(position==3){
                    mOrderViewModel.getOrderList("Last Month").observe(getActivity(), OrderFragment.this::handleOrderList);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //bind Vertical RecyclerView
        RecyclerView orderListRecyclerView=mFragmentOrderBinding.rvOrderList;
        orderListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        orderListRecyclerView.setHasFixedSize(true);

        mOrderViewModel= ViewModelProviders.of(OrderFragment.this, viewModelFactory).get(OrderViewModel.class);
        mFragmentOrderBinding.setOrderViewModel(mOrderViewModel);
        mOrderViewModel.init();
        mOrderViewModel.getOrderList("Last-7-Days").observe(getActivity(), OrderFragment.this::handleOrderList);



        mOrderViewModel.getOrderListItemsClick().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {
                String pujaPackageTypeName=mOrderViewModel.mOrderListResponse.getValue().data.get(position).getPujaPkgTypName();
                String custOrderDesc=mOrderViewModel.mOrderListResponse.getValue().data.get(position).getCustOrdDscr();
                String pujaPackageDesc=mOrderViewModel.mOrderListResponse.getValue().data.get(position).getPujaPkgDescription();
                String custOrderDate=mOrderViewModel.mOrderListResponse.getValue().data.get(position).getCustOrdDt();
                String custOrderNo=mOrderViewModel.mOrderListResponse.getValue().data.get(position).getCustOrdNo();
                String custPujaSubCategoryDesc=mOrderViewModel.mOrderListResponse.getValue().data.get(position).getPujaSubCtgryDscr();
                Double custBkgPkgAmount=mOrderViewModel.mOrderListResponse.getValue().data.get(position).getCustBkgPkgAmt();
                Double custBkgPkgTaxAmount=mOrderViewModel.mOrderListResponse.getValue().data.get(position).getCustBkgPkgTaxAmt();
                Double custBkgPkgTotalAmount=mOrderViewModel.mOrderListResponse.getValue().data.get(position).getCustBkgPkgTotalAmt();
                String statusBill=mOrderViewModel.mOrderListResponse.getValue().data.get(position).getBkgStsDscr();

                OrderFragmentDirections.ActionOrderFragmentToOrderDetailsFragment action=OrderFragmentDirections.actionOrderFragmentToOrderDetailsFragment();
                action.setPackageTypeName(pujaPackageTypeName);
                action.setOrderDesc(custOrderDesc);
                action.setPujaPackageDesc(pujaPackageDesc);
                action.setCustOrderDate(custOrderDate);
                action.setCustOrderNo(custOrderNo);
                action.setCustPujaSubCategoryDesc(custPujaSubCategoryDesc);
                action.setCustBkgPkgAmount(custBkgPkgAmount.toString());
                action.setCustBkgPkgTaxAmount(custBkgPkgTaxAmount.toString());
                action.setCustBkgPkgTotalAmount(custBkgPkgTotalAmount.toString());
                action.setBkgstsDesc(statusBill);
                Navigation.findNavController(mView).navigate(action);


               // findNavController(mView).navigate(OrderFragmentDirections.actionOrderFragmentToOrderDetailsFragment());
            }
        });
    }



    private void handleOrderList(Resource<List<OrderListModel>> resource) {
        if (resource != null) {
            JSONObject jsonObject = null;
            switch (resource.status) {
                case ERROR:
                    DisplayDialog.getInstance().dismissAlertDialog();
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Something went wrong")
                            .show();
                    break;
                case LOADING:

                    DisplayDialog.getInstance().showAlertDialog(getActivity(), OrderFragment.this.getString(R.string.please_wait));

                    break;
                case SUCCESS:

                    Log.e("handleChoosePackageListStatus", "SUCCESS");
                    Log.e("handleChoosePackageListStatus", resource.data + "");
                    Log.e("handleChoosePackageListStatus", resource.message + "");
                    Log.e("handleChoosePackageListStatus", resource.status + "");


                    if (resource.data != null) {

                        Log.e("handleChoosePackageListStatus_count", resource.data.size() + "");
                        if (resource.data.size() == 0) {
                            DisplayDialog.getInstance().dismissAlertDialog();
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Error")
                                    .setContentText("Something went wrong")
                                    .show();
                        } else {
                            Gson gson = new Gson();
                            List<OrderListModel> list = resource.data;

                            mOrderViewModel.setOrderList(list);
                            DisplayDialog.getInstance().dismissAlertDialog();

                        }
                    }

                    // mModuleDetailsViewModel.setUserWiseWidgetList(resource.data);




                    break;
                default:

                    DisplayDialog.getInstance().dismissAlertDialog();

                    break;
            }
        }
    }

}
