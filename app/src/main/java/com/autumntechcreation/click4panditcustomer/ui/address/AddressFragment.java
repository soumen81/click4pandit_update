package com.autumntechcreation.click4panditcustomer.ui.address;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentAddressbookBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.MyReceiver;
import com.autumntechcreation.click4panditcustomer.network.Resource;

import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.navigation.Navigation.findNavController;

public class AddressFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentAddressbookBinding mFragmentAddressbookBinding;
    AddressViewModel addressViewModel;
    private View mView;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mFragmentAddressbookBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_addressbook, container, false);
        mFragmentAddressbookBinding.setLifecycleOwner(this);

        return mFragmentAddressbookBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        navController = findNavController(mView);
        ((MainActivity) getActivity()).setToolbar(true, true, false, true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView addressListRecyclerView=mFragmentAddressbookBinding.rvAddressBookList;
        addressListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        addressListRecyclerView.setHasFixedSize(true);

        addressViewModel = ViewModelProviders.of(AddressFragment.this, viewModelFactory).get(AddressViewModel.class);
        mFragmentAddressbookBinding.setAddressViewModel(addressViewModel);
        addressViewModel.init();

        addressViewModel.getAddressViewList().observe(getActivity(), AddressFragment.this::handleAddressList);

        mFragmentAddressbookBinding.tvAddNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String add="ADD";
            AddressFragmentDirections.ActionAddressFragmentToAddUpdateRemoveFragment action=
                    AddressFragmentDirections.actionAddressFragmentToAddUpdateRemoveFragment();
            action.setAddAction(add);
                Navigation.findNavController(mView).navigate(action);

            }
        });



        addressViewModel.getUpdateListItemsClick().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {
                String update="UPDATE";
                String firstName=addressViewModel.mAddressListResponse.getValue().data.get(position).getFirstName();
                String lastName=addressViewModel.mAddressListResponse.getValue().data.get(position).getLastName();
                Object address1=addressViewModel.mAddressListResponse.getValue().data.get(position).getAddr1();
                Object address2=addressViewModel.mAddressListResponse.getValue().data.get(position).getAddr2();
                Object address3=addressViewModel.mAddressListResponse.getValue().data.get(position).getAddr3();
                Object city=addressViewModel.mAddressListResponse.getValue().data.get(position).getCityDescr();
                Object state=addressViewModel.mAddressListResponse.getValue().data.get(position).getStDescr();
                Object postal=addressViewModel.mAddressListResponse.getValue().data.get(position).getPostal();
                int  shippingAddressId=addressViewModel.mAddressListResponse.getValue().data.get(position).getShippingAddrId();
                String  updtStamp=addressViewModel.mAddressListResponse.getValue().data.get(position).getUpdtStamp();
                String  orglStamp=addressViewModel.mAddressListResponse.getValue().data.get(position).getOrglStamp();
                int  custMasterId=addressViewModel.mAddressListResponse.getValue().data.get(position).custMasterId;
                AddressFragmentDirections.ActionAddressFragmentToAddUpdateRemoveFragment action=
                        AddressFragmentDirections.actionAddressFragmentToAddUpdateRemoveFragment();
                action.setUpdateAction(update);
                action.setFirstName(firstName);
                action.setLastName(lastName);
                action.setAddress1(String.valueOf(address1));
                action.setAddress2(String.valueOf(address2));
                action.setAddress3(String.valueOf(address3));
                action.setCity(String.valueOf(city));
                action.setState(String.valueOf(state));
                action.setPincode(String.valueOf(postal));
                action.setShippingAddressId(shippingAddressId);
                action.setUpdtStamp(updtStamp);
                action.setOrglStamp(orglStamp);
                action.setCustMasterId(custMasterId);
                Navigation.findNavController(mView).navigate(action);
            }
        }); addressViewModel.getRemoveListItemsClick().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {
                String delete="DELETE";
                String firstName=addressViewModel.mAddressListResponse.getValue().data.get(position).getFirstName();
                String lastName=addressViewModel.mAddressListResponse.getValue().data.get(position).getLastName();
                Object address1=addressViewModel.mAddressListResponse.getValue().data.get(position).getAddr1();
                Object address2=addressViewModel.mAddressListResponse.getValue().data.get(position).getAddr2();
                Object address3=addressViewModel.mAddressListResponse.getValue().data.get(position).getAddr3();
                Object city=addressViewModel.mAddressListResponse.getValue().data.get(position).getCityDescr();
                Object state=addressViewModel.mAddressListResponse.getValue().data.get(position).getStDescr();
                Object postal=addressViewModel.mAddressListResponse.getValue().data.get(position).getPostal();
                int  shippingAddressId=addressViewModel.mAddressListResponse.getValue().data.get(position).getShippingAddrId();
                String  updtStamp=addressViewModel.mAddressListResponse.getValue().data.get(position).getUpdtStamp();
                String  orglStamp=addressViewModel.mAddressListResponse.getValue().data.get(position).getOrglStamp();
                int  custMasterId=addressViewModel.mAddressListResponse.getValue().data.get(position).custMasterId;
                AddressFragmentDirections.ActionAddressFragmentToAddUpdateRemoveFragment action=
                        AddressFragmentDirections.actionAddressFragmentToAddUpdateRemoveFragment();
                action.setRemoveAction(delete);
                action.setFirstName(firstName);
                action.setLastName(lastName);
                action.setAddress1(String.valueOf(address1));
                action.setAddress2(String.valueOf(address2));
                action.setAddress3(String.valueOf(address3));
                action.setCity(String.valueOf(city));
                action.setState(String.valueOf(state));
                action.setPincode(String.valueOf(postal));
                action.setShippingAddressId(shippingAddressId);
                action.setUpdtStamp(updtStamp);
                action.setOrglStamp(orglStamp);
                action.setCustMasterId(custMasterId);
                Navigation.findNavController(mView).navigate(action);

            }
        });
    }

    private void handleAddressList(Resource<List<AddressListModel>> resource) {
        if (resource != null) {
            JSONObject jsonObject = null;
            switch (resource.status) {
                case ERROR:
                    DisplayDialog.getInstance().dismissAlertDialog();
                     if (!Static.isNetworkAvailable(getActivity())) {

                }
                    break;
                case LOADING:

                    DisplayDialog.getInstance().showAlertDialog(getActivity(), AddressFragment.this.getString(R.string.please_wait));

                    break;
                case SUCCESS:

                    Log.e("handleAddressListStatus", "SUCCESS");
                    Log.e("handleAddressListStatus", resource.data + "");
                    Log.e("handleAddressListStatus", resource.message + "");
                    Log.e("handleAddressListStatus", resource.status + "");


                    if (resource.data != null) {

                        Log.e("handleAddressListStatus_count", resource.data.size() + "");
                        if (resource.data.size() == 0) {
                            DisplayDialog.getInstance().dismissAlertDialog();
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Error")
                                    .setContentText("Data Not found")
                                    .show();
                        } else {
                            Gson gson = new Gson();
                            List<AddressListModel> list = resource.data;

                            addressViewModel.setAddressList(list);
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
