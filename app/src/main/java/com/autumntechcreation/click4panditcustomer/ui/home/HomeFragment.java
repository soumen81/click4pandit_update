package com.autumntechcreation.click4panditcustomer.ui.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentHomeBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragmentDirections;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.navigation.Navigation.findNavController;


public class HomeFragment  extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
     FragmentHomeBinding mFragmentHomeBinding;
    HomeViewModel mHomeViewModel;
    private View mView;
    NavController navController;
    String pujaName="";
    int  pujaSubCategoryId;
    int  pujaCategoryId;
    private int[]mImager={R.drawable.pandit1,R.drawable.pandit2,R.drawable.pandit3,R.drawable.pandit4,R.drawable.pandit5};
    private String[]mImagetitle=new String[]{"Pandit1,Pandit2,Pandit3,Pandit4,Pandit5"};

    ArrayAdapter<String> mSpinPujaTypesAdapter;
    List<String> mListTypesPuja = new ArrayList<>();
    ArrayList<PujaTypesModel> pujaTypesModellist = new ArrayList<PujaTypesModel>();



    ArrayAdapter<String> mSpinPujaCategoryAdapter;
    List<String> mListCategoryPuja = new ArrayList<>();
    ArrayList<PujaCategoryModel> pujaCategoryModellist = new ArrayList<PujaCategoryModel>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mFragmentHomeBinding.setLifecycleOwner(this);



        return mFragmentHomeBinding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;


        navController=findNavController(mView);
        ((MainActivity) getActivity()).setToolbar(true,false,false,true);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mHomeViewModel = ViewModelProviders.of(HomeFragment.this, viewModelFactory).get(HomeViewModel.class);
        mFragmentHomeBinding.setHomeViewModel(mHomeViewModel);


        DisplayDialog.getInstance().showAlertDialog(getActivity(),"Please wait");
        mHomeViewModel.getPujaTypesList().observe(getActivity(),HomeFragment.this::handlePujaTypesList);


        mSpinPujaTypesAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, mListTypesPuja);
        // Specify the layout to use when the list of choices appears
        mSpinPujaTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mFragmentHomeBinding.tvSpinTypeOfPuja.setAdapter(mSpinPujaTypesAdapter);


        mFragmentHomeBinding.tvSpinTypeOfPuja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
try {

    pujaCategoryId = mHomeViewModel.mPujaTypesList.getValue().data.get(position).pujaCtgryId;
    mHomeViewModel.getPujaCategoryList(pujaCategoryId).observe(getActivity(), HomeFragment.this::handlePujCategoryList);

}catch(Exception e){
    e.printStackTrace();
}




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinPujaCategoryAdapter=new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, mListCategoryPuja);
        // Specify the layout to use when the list of choices appears
        mSpinPujaCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mFragmentHomeBinding.tvSpinTypeCategories.setAdapter(mSpinPujaCategoryAdapter);

        mFragmentHomeBinding.tvSpinTypeCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                  pujaSubCategoryId = mHomeViewModel.mPujaCategoryList.getValue().data.get(position).getPujaSubCtgryId();
                    Log.e("SUBCATE",""+pujaSubCategoryId);
                  pujaName = mHomeViewModel.mPujaCategoryList.getValue().data.get(position).getPujaSubCtgryDscr();
                Log.e("PUJANAME",""+pujaName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        mFragmentHomeBinding.carousal.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImager[position]);
            }
        });
        mFragmentHomeBinding.carousal.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(),mImagetitle[position],Toast.LENGTH_SHORT).show();
            }
        });
        mFragmentHomeBinding.carousal.setPageCount(mImager.length);
        mFragmentHomeBinding.tvViewPackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentHomeBinding.tvSpinTypeOfPuja.getSelectedItem().toString().trim().equalsIgnoreCase("Types of Puja")) {
                    Toast.makeText(getActivity(), "Please select the Types of Puja", Toast.LENGTH_SHORT).show();
                }else if(mFragmentHomeBinding.tvSpinTypeCategories.getSelectedItem().toString().trim().equalsIgnoreCase("Categories")){
                    Toast.makeText(getActivity(), "Please select the Categories", Toast.LENGTH_SHORT).show();
                }else {

                    Log.e("SUBCATE", "" + pujaSubCategoryId);           //  findNavController(mView).navigate(HomeFragmentDirections.actionHomeFragmentFragmentToChoosePackageFragment());

                    HomeFragmentDirections.ActionHomeFragmentFragmentToChoosePackageFragment action =
                            HomeFragmentDirections.actionHomeFragmentFragmentToChoosePackageFragment();
                    action.setSubCategoryId(pujaSubCategoryId);
                    action.setPujaName(pujaName);
                    Navigation.findNavController(mView).navigate(action);
                }



            }
        });

    }

    private void handlePujaTypesList(Resource<List<PujaTypesModel>> resource) {
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



                    break;
                case SUCCESS:

                    Log.e("handleGetLeaveStatus", "SUCCESS");
                    Log.e("handleGetLeaveStatus", resource.data + "");
                    Log.e("handleGetLeaveStatus", resource.message + "");
                    Log.e("handleGetLeaveStatus", resource.status + "");


                    if (resource.data != null) {

                        Log.e("handleGetLeaveStatus_count", resource.data.size() + "");
                        if (resource.data.size() == 0) {
                            DisplayDialog.getInstance().dismissAlertDialog();
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Error")
                                    .setContentText("Something went wrong")
                                    .show();
                        } else {

                            //  ArrayList<EnterpriseModel> list1 = new ArrayList<EnterpriseModel>();
                            pujaTypesModellist.clear();
                            mListTypesPuja.clear();
                            mListTypesPuja.add(0,"Types of Puja");
                            for (int i = 0; i < resource.data.size(); i++) {
                                mListTypesPuja.add(resource.data.get(i).getPujaCtgryDscr());

                                pujaTypesModellist.add(resource.data.get(i));

                            }

                            mSpinPujaTypesAdapter.notifyDataSetChanged();
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


    private void handlePujCategoryList(Resource<List<PujaCategoryModel>> resource) {
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



                    break;
                case SUCCESS:

                    Log.e("handleGetLeaveStatus", "SUCCESS");
                    Log.e("handleGetLeaveStatus", resource.data + "");
                    Log.e("handleGetLeaveStatus", resource.message + "");
                    Log.e("handleGetLeaveStatus", resource.status + "");


                    if (resource.data != null) {

                        Log.e("handleGetLeaveStatus_count", resource.data.size() + "");
                        if (resource.data.size() == 0) {
                            DisplayDialog.getInstance().dismissAlertDialog();
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Error")
                                    .setContentText("Something went wrong")
                                    .show();
                        } else {

                            //  ArrayList<EnterpriseModel> list1 = new ArrayList<EnterpriseModel>();
                            pujaCategoryModellist.clear();
                            mListCategoryPuja.clear();
                            mListCategoryPuja.add(0,"Categories");
                            for (int i = 0; i < resource.data.size(); i++) {
                                mListCategoryPuja.add(resource.data.get(i).getPujaSubCtgryDscr());
                                pujaCategoryModellist.add(resource.data.get(i));


                            }
                            Log.e("LISSSST", mListCategoryPuja.size()+"");

                            mSpinPujaCategoryAdapter.notifyDataSetChanged();
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
