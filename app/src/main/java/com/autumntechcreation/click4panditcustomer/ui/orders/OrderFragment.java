package com.autumntechcreation.click4panditcustomer.ui.orders;

import android.os.Bundle;
import android.os.Handler;
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
import com.autumntechcreation.click4panditcustomer.util.AppConstants;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.gson.Gson;

import org.json.JSONException;
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
    String[] filterDays = { "Last 7 Days", "This Month", "Last Month"};
    ArrayAdapter<String> mSpinFilterAdapter;
    int mSkip = 1,mTop = 5;
    boolean isLoading = false, itHasMoreDataToLoad = true,isFilter=false;
    String searchText = "";
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
        ((MainActivity) getActivity()).setToolbar(true,true,false,true);
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
                    itHasMoreDataToLoad = true;
                    isLoading = false;
                    mSkip = 1;
                    searchText="Last-7-Days";
                    mOrderViewModel.getOrderList(searchText,mSkip).observe(getActivity(), OrderFragment.this::handleOrderList);


                }else if(position==1){
                    itHasMoreDataToLoad = true;
                    isLoading = false;
                    mSkip = 1;
                    searchText="This Month";
                    mOrderViewModel.getOrderList(searchText,mSkip).observe(getActivity(), OrderFragment.this::handleOrderList);


                }else if(position==2){
                    itHasMoreDataToLoad = true;
                    isLoading = false;
                    mSkip = 1;
                    searchText="Last Month";
                   mOrderViewModel.getOrderList(searchText,mSkip).observe(getActivity(), OrderFragment.this::handleOrderList);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //bind Vertical RecyclerView
       /* RecyclerView orderListRecyclerView=mFragmentOrderBinding.rvOrderList;
        orderListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));
        orderListRecyclerView.setHasFixedSize(true);*/



        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentOrderBinding.rvOrderList.setLayoutManager(llm);


        mOrderViewModel= ViewModelProviders.of(OrderFragment.this, viewModelFactory).get(OrderViewModel.class);
        mFragmentOrderBinding.setOrderViewModel(mOrderViewModel);
        mOrderViewModel.init();
        mOrderViewModel.getOrderList("Last-7-Days",mSkip).observe(getActivity(), OrderFragment.this::handleOrderList);



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




        //Pagination Work Here.....
        mFragmentOrderBinding.rvOrderList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int firstVisibleItem, visibleItemCount, totalItemCount;
                //visibleItemCount:No of item visible in the screen...
                visibleItemCount = llm.getChildCount();
                Log.e("SOUMEN_VISIBLEITEMCOUNT", Integer.toString(visibleItemCount));
                //totalItemCount:Total no. of item in the list...
                totalItemCount = llm.getItemCount();
                Log.e("SOUMEN_TOTALITEMCOUNT", Integer.toString(totalItemCount));
                //firstVisibleItem:No. of item remove from top...
                firstVisibleItem = llm.findFirstVisibleItemPosition();
                Log.e("SOUMEN_FIRSTVISIBLEITEM", Integer.toString(firstVisibleItem));

                if ((firstVisibleItem + visibleItemCount) > visibleItemCount) {
                    mFragmentOrderBinding.txtCount.animate().alpha(1.0f);
                    mFragmentOrderBinding.txtCount.setText("History : " + Integer.toString(firstVisibleItem + visibleItemCount) + "/" + Integer.toString(totalItemCount));
                    showListVisibleCount();
                }
                Log.e("Suman_isLoading", isLoading + "");
                Log.e("Suman_itHasMoreDataToLoad", itHasMoreDataToLoad + "");

                if (!isLoading && itHasMoreDataToLoad) {
                    if ((visibleItemCount + firstVisibleItem) >=
                            totalItemCount && firstVisibleItem >= 0) {
                        isLoading = true;
                        mSkip = mSkip + 1;
                        mOrderViewModel.getOrderList(searchText,mSkip).observe(getActivity(), OrderFragment.this::handleOrderList);

                    }
                }
            }
        });

    }








    private void handleOrderList(Resource<List<OrderListModel>> resource) {
        if (resource != null) {
            JSONObject jsonObject = null;
            switch (resource.status) {
                case ERROR:
                    Log.e("handleHistoryListError", "ERROR");
                    Log.e("handleHistoryListMessage", resource.message);
                    Log.e("handleHistoryListStatus", resource.status + "");
                    Log.e("handleHistoryListData", resource.data + "");

                    mFragmentOrderBinding.shimmerViewContainer.setVisibility(View.GONE);
                    String errorStr = "", errorTitle = "";
                    if (resource.message != null) {
                        if (!Static.isNetworkAvailable(getActivity())) {
                            if (mSkip == 1) {
                                mFragmentOrderBinding.shimmerViewContainer.setVisibility(View.GONE);
                                mFragmentOrderBinding.llNoData.setVisibility(View.GONE);
                                mFragmentOrderBinding.rvOrderList.setVisibility(View.GONE);
                                mFragmentOrderBinding.llNoInternet.setVisibility(View.VISIBLE);
                            } else {
                                mFragmentOrderBinding.progressBar.setVisibility(View.GONE);
                            }

                        } else if ((resource.message.toUpperCase()).contains(AppConstants.CONECTION_FAILED_TIMEOUT_MESSAGE.toUpperCase())
                                || (resource.message.toUpperCase()).contains(AppConstants.TIMEOUT_MESSAGE.toUpperCase())) {
                            errorStr = getActivity().getString(R.string.nointernetdetails);
                            errorTitle = getActivity().getString(R.string.network_error);

                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(errorTitle)
                                    .setContentText(errorStr)
                                    .show();

                        } else {
                            errorTitle = "Oops..";
                            try {
                                jsonObject = new JSONObject(resource.message);
                                jsonObject.getString("error_description");
                            } catch (Exception e) {
                                try {
                                    errorStr = jsonObject.getJSONArray("errors").getJSONObject(0).getString("code");
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            mFragmentOrderBinding.shimmerViewContainer.setVisibility(View.GONE);
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(errorTitle)
                                    .setContentText(errorStr)
                                    .show();
                        }
                    } else if (!Static.isNetworkAvailable(getActivity())) {
                        if (mSkip == 1) {
                            mFragmentOrderBinding.shimmerViewContainer.setVisibility(View.GONE);
                            mFragmentOrderBinding.llNoData.setVisibility(View.GONE);
                            mFragmentOrderBinding.llNoInternet.setVisibility(View.VISIBLE);
                            mFragmentOrderBinding.rvOrderList.setVisibility(View.GONE);
                        } else {
                            mFragmentOrderBinding.progressBar.setVisibility(View.GONE);
                        }


                    } else {
                        if (mSkip == 1) {
                            mFragmentOrderBinding.shimmerViewContainer.setVisibility(View.GONE);
                            mFragmentOrderBinding.llNoData.setVisibility(View.VISIBLE);
                            mFragmentOrderBinding.llNoInternet.setVisibility(View.GONE);
                            mFragmentOrderBinding.rvOrderList.setVisibility(View.GONE);

                        } else {
                            mFragmentOrderBinding.progressBar.setVisibility(View.GONE);
                        }

                    }
                    break;
                case LOADING:

                    DisplayDialog.getInstance().showAlertDialog(getActivity(), OrderFragment.this.getString(R.string.please_wait));
                    Log.e("handleHistoryListLoading", "LOADING");
                    if (mSkip == 1) {
                        mFragmentOrderBinding.shimmerViewContainer.setVisibility(View.VISIBLE);
                        mFragmentOrderBinding.llNoData.setVisibility(View.GONE);
                        mFragmentOrderBinding.llNoInternet.setVisibility(View.GONE);
                        mFragmentOrderBinding.rvOrderList.setVisibility(View.GONE);

                    } else {
                        mFragmentOrderBinding.progressBar.setVisibility(View.VISIBLE);
                    }
                    break;
                case SUCCESS:

                    Log.e("handleOrderListStatus", "SUCCESS");
                    Log.e("handleOrderListStatus", resource.data + "");
                    Log.e("handleOrderListStatus", resource.message + "");
                    Log.e("handleOrderListStatus", resource.status + "");

                    DisplayDialog.getInstance().dismissAlertDialog();
                    if (resource.data != null) {
                        Log.e("handlePendingApprovalListCount", resource.data.size() + "");

                        if (resource.data.size() == 0) {
                            if (mSkip == 1) {
                                mFragmentOrderBinding.shimmerViewContainer.setVisibility(View.GONE);
                                mFragmentOrderBinding.llNoData.setVisibility(View.VISIBLE);
                                mFragmentOrderBinding.llNoInternet.setVisibility(View.GONE);
                                mFragmentOrderBinding.rvOrderList.setVisibility(View.GONE);
                            } else {
                                mFragmentOrderBinding.progressBar.setVisibility(View.GONE);
                            }
                        } else {
                            Gson gson = new Gson();
                            List<OrderListModel> list = resource.data;

                            mOrderViewModel.setOrderList(list,mSkip);
                            isFilter=false;

                            if (resource.data.size() < mTop) {

                                itHasMoreDataToLoad = false;
                                Log.e("Suman_itHasMoreDataToLoad_1", itHasMoreDataToLoad + "");
                            }
                            if (mSkip == 1) {
                                mFragmentOrderBinding.shimmerViewContainer.setVisibility(View.GONE);
                                mFragmentOrderBinding.llNoData.setVisibility(View.GONE);
                                mFragmentOrderBinding.llNoInternet.setVisibility(View.GONE);
                                mFragmentOrderBinding.rvOrderList.setVisibility(View.VISIBLE);
                            } else {
                                mFragmentOrderBinding.progressBar.setVisibility(View.GONE);
                            }
                            //Gson gson = new Gson();
                            Log.e("handlePendingApprovalHistoryListData", gson.toJson(resource.data));
                            isLoading = false;

                        }
                    }


                    // mModuleDetailsViewModel.setUserWiseWidgetList(resource.data);




                    break;
                default:

                    DisplayDialog.getInstance().dismissAlertDialog();
                    if (mSkip == 1)
                        mFragmentOrderBinding.shimmerViewContainer.setVisibility(View.GONE);
                    else {
                        mFragmentOrderBinding.progressBar.setVisibility(View.GONE);
                    }

                    break;
            }
        }
    }
    private void showListVisibleCount() {

        Handler h = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                mFragmentOrderBinding.txtCount.animate().alpha(0.0f).setDuration(300);
            }
        };
        h.postDelayed(r, AppConstants.LIST_VISIBLE_COUNT_VIEW);
    }
}
