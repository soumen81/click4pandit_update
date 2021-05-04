package com.autumntechcreation.click4panditcustomer.ui.ordersummary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentOrdersummeryBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaFragment;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaFragmentDirections;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingPujaViewModel;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterResponse;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.navigation.Navigation.findNavController;

public class OrderSummaryFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    OrderSummaryViewModel mOrderSummaryViewModel;
    FragmentOrdersummeryBinding mFragmentOrdersummeryBinding;
    private View mView;
    NavController navController;
    String pujaName,pujaAmount,pujaDesc,procedure,pujaSamagri,yajaman,locationName,languageName,
            pujaDate,pujaTime,packageTypeIdDesc,subcategoryName,isAllSamagries;
    int subCategoryId,pujPackageId,locationId,languageId,noOfPandit;
    double dd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentOrdersummeryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_ordersummery, container, false);
        mFragmentOrdersummeryBinding.setLifecycleOwner(this);

        pujaName= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaName();
        Log.e("PujaName",""+pujaName);
        pujaAmount= OrderSummaryFragmentArgs.fromBundle(getArguments()).getAmount();
        Log.e("pujaAmount",""+pujaAmount);
        pujaDesc= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPackageDesc();
        Log.e("pujaDesc",""+pujaDesc);
        procedure= OrderSummaryFragmentArgs.fromBundle(getArguments()).getProcedure();
        Log.e("Procedure",""+procedure);
        pujaSamagri= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaSamagries();
        Log.e("pujaSamagri",""+pujaSamagri);
        yajaman= OrderSummaryFragmentArgs.fromBundle(getArguments()).getYajaman();
        Log.e("Yajaman",""+yajaman);
        locationName= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaLocation();
        Log.e("LocationName",""+locationName);
        languageName= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaLanguage();
        Log.e("LanguageName",""+languageName);
        pujaDate= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaDate();
        Log.e("PujaDate",""+pujaDate);
        pujaTime= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaTime();
        Log.e("pujaTime",""+pujaTime);
        packageTypeIdDesc= OrderSummaryFragmentArgs.fromBundle(getArguments()).getPackageTypeIdDesc();
        Log.e("packageTypeIdDesc",""+packageTypeIdDesc);
        subcategoryName= OrderSummaryFragmentArgs.fromBundle(getArguments()).getSubCategoryName();
        Log.e("subcategoryName",""+subcategoryName);
        subCategoryId= OrderSummaryFragmentArgs.fromBundle(getArguments()).getSubCategoryId();
        Log.e("SubCategoryId",""+subCategoryId);
        pujPackageId=OrderSummaryFragmentArgs.fromBundle(getArguments()).getPujaPackageId();
        Log.e("pujPackageId",""+pujPackageId);
         locationId=BookingPujaFragmentArgs.fromBundle(getArguments()).getLocationId();
        Log.e("locationId",""+locationId);
        languageId=BookingPujaFragmentArgs.fromBundle(getArguments()).getLanguageId();
        Log.e("languageId",""+languageId);
        isAllSamagries=BookingPujaFragmentArgs.fromBundle(getArguments()).getIsAllSamagries();
        Log.e("isAllSamagries",""+isAllSamagries);
        noOfPandit=BookingPujaFragmentArgs.fromBundle(getArguments()).getNoOfPandit();
        Log.e("noOfPandit",""+noOfPandit);
        return mFragmentOrdersummeryBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        navController=findNavController(mView);
        ((MainActivity) getActivity()).setToolbar(false,true,false,true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mOrderSummaryViewModel = ViewModelProviders.of(OrderSummaryFragment.this, viewModelFactory).get(OrderSummaryViewModel.class);
        mFragmentOrdersummeryBinding.setOrderSummeryViewModel(mOrderSummaryViewModel);




        mFragmentOrdersummeryBinding.tvPujaNameValue.setText(pujaName);
        mFragmentOrdersummeryBinding.tvPackageValue.setText(packageTypeIdDesc);
        mFragmentOrdersummeryBinding.tvPanditDetails.setText(pujaDesc);
        mFragmentOrdersummeryBinding.tvPriestPreferenceValue.setText(languageName);
        mFragmentOrdersummeryBinding.tvAllPujaSamagriesValue.setText(pujaSamagri);
        mFragmentOrdersummeryBinding.tvDakshinaValue.setText(procedure);
        mFragmentOrdersummeryBinding.tvAllPujaSamagriesValue.setText(pujaSamagri);
        mFragmentOrdersummeryBinding.tvHavanValue.setText(yajaman );
        mFragmentOrdersummeryBinding.tvDateTimeValue.setText(pujaDate+" " + "at"+" "+ pujaTime );
        mFragmentOrdersummeryBinding.tvSubTotalValue.setText(pujaAmount );

        double cgst = Double.parseDouble(pujaAmount)*9/100;
        System.out.println("int i = " + cgst);
            double cgstvalue=Static.roundAvoid(cgst,2);
            mFragmentOrdersummeryBinding.tvCgstValue.setText(Double.toString(cgstvalue));
            double sgst = Double.parseDouble(pujaAmount)*9/100;
            System.out.println("int i = " + sgst);
        double sgstvalue=Static.roundAvoid(sgst,2);
        mFragmentOrdersummeryBinding.tvSgstValue.setText(Double.toString(sgstvalue));
        double cgstsgst=cgstvalue+sgstvalue;
         dd=cgstvalue+sgstvalue+Double.parseDouble(pujaAmount);
        mFragmentOrdersummeryBinding.tvTotalValue.setText(Double.toString(dd));
        String str=Static.convertNewDate(pujaDate)+"T"+pujaTime;



        mOrderSummaryViewModel.getOnClickConfirmOrder().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                // findNavController(mView).navigate(OrderSummaryFragmentDirections.actionOrderSummaryFragmentToBillingDetailsFragment());
                mOrderSummaryViewModel.getNewOrderResult(languageId,languageName,Double.parseDouble(pujaAmount),cgstsgst,dd,pujaDesc,
                        pujPackageId,noOfPandit,subcategoryName,subCategoryId,locationName,locationId,str).observe(getActivity(),OrderSummaryFragment.this::handleNewOrderGenerate);
            }
        });




    }

    private void handleNewOrderGenerate(Resource<OrderSummeryModel> resource) {
        if (resource != null) {

            switch (resource.status) {
                case ERROR:
                    DisplayDialog.getInstance().dismissAlertDialog();
                    if (resource.message != null &&  resource.data==null) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(resource.message);

                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(jsonObject.getString("error"))
                                    .setContentText(jsonObject.getString("error_description"))
                                    .show();

                        } catch (JSONException e) {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Error")
                                    .setContentText("Unhandle Error")
                                    .show();
                        }
                    } else if (!Static.isNetworkAvailable(getActivity()) && resource.data==null) {

                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(this.getString(R.string.nointernet))
                                .setContentText(this.getString(R.string.nointernetdetails))
                                .show();

                    }

                    break;
                case LOADING:
                    Log.e("handleRegisterResponse", "LOADING");
                    DisplayDialog.getInstance().showAlertDialog(getActivity(), getActivity().getString(R.string.please_wait));


                    break;
                case SUCCESS:
                    Log.e("handleRegisterResponse", "SUCCESS");
                    // Log.e("handleLoginResponse",resource.message);
                    Log.e("handleRegisterResponse", resource.status + "");
                    Log.e("handleRegisterResponse", resource.data + "");
                    Gson gson = new Gson();
                    String json = gson.toJson(resource.data);
                    Log.e("handleRegisterResponse", json + "");
                    if ( resource.data.returnStatus.equals("SUCCESS")) {
                        int  orderId=resource.data.orderId;
                        int bkgId=resource.data.getBkgId();
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText(this.getString(R.string.success))
                                .setContentText(this.getString(R.string.ordergenerate))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                       OrderSummaryFragmentDirections.ActionOrderSummaryFragmentToBillingDetailsFragment action=
                                               OrderSummaryFragmentDirections.actionOrderSummaryFragmentToBillingDetailsFragment();
                                       action.setOrderId(orderId);
                                       action.setBkgId(bkgId);
                                       action.setOrderAmount(String.valueOf(dd));
                                        Navigation.findNavController(mView).navigate(action);
                                    }
                                })
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                    }
                                })

                                .show();



                    }
                    DisplayDialog.getInstance().dismissAlertDialog();
                    break;
                default:
                    DisplayDialog.getInstance().dismissAlertDialog();

                    break;
            }
        }
    }
}
