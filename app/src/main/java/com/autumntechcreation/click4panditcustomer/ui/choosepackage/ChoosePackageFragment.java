package com.autumntechcreation.click4panditcustomer.ui.choosepackage;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.DialogChoosepackageDetailsBinding;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentChoosepackageBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeFragment;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeFragmentDirections;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeViewModel;
import com.autumntechcreation.click4panditcustomer.ui.home.PujaCategoryModel;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterActivity;
import com.google.gson.Gson;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.navigation.Navigation.findNavController;

public class ChoosePackageFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentChoosepackageBinding mFragmentChoosepackageBinding;
    ChoosePackageViewModel mChoosePackageViewModel;
    DialogChoosepackageDetailsBinding mDialogChoosepackageDetailsBinding;
    private View mView;
    NavController navController;
    private int[]mImager={R.drawable.pandit1,R.drawable.pandit2,R.drawable.pandit3,R.drawable.pandit4,R.drawable.pandit5};
    private String[]mImagetitle=new String[]{"Pandit1,Pandit2,Pandit3,Pandit4,Pandit5"};
    int subCategoryId;
    AlertDialog mDialogForChoosePackage = null;
    String pujaName;
    String pujaAmount,pujaDesc,packageTypeIdDesc,isAllSamagries;
    int pujaPackageId,noOfPandit;
    String procedures="",pujaSamagries="",Yajaman="",subCategoryName="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentChoosepackageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_choosepackage, container, false);
        mFragmentChoosepackageBinding.setLifecycleOwner(this);
        subCategoryId=ChoosePackageFragmentArgs.fromBundle(getArguments()).getSubCategoryId();
        Log.e("SubCategoryId",""+subCategoryId);
        subCategoryName=ChoosePackageFragmentArgs.fromBundle(getArguments()).getSubCategoryName();
        Log.e("subCategoryName",""+subCategoryName);


        pujaName=ChoosePackageFragmentArgs.fromBundle(getArguments()).getPujaName();
        Log.e("PUJANAME",""+pujaName);



        return mFragmentChoosepackageBinding.getRoot();

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

        mChoosePackageViewModel = ViewModelProviders.of(ChoosePackageFragment.this, viewModelFactory).get(ChoosePackageViewModel.class);
        mFragmentChoosepackageBinding.setChoosePackageViewModel(mChoosePackageViewModel);


        mDialogForChoosePackage = new AlertDialog.Builder(this.getActivity()).create();
        mDialogChoosepackageDetailsBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_choosepackage_details, null, true);
        mDialogChoosepackageDetailsBinding.setViewModel(mChoosePackageViewModel);
        mDialogForChoosePackage.setView(mDialogChoosepackageDetailsBinding.getRoot());
        mDialogChoosepackageDetailsBinding.tvChoosePackageConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogForChoosePackage.dismiss();
            }
        });





        mFragmentChoosepackageBinding.carousal.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImager[position]);
            }
        });

        mFragmentChoosepackageBinding.carousal.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(),mImagetitle[position],Toast.LENGTH_SHORT).show();
            }
        });
        mFragmentChoosepackageBinding.carousal.setPageCount(mImager.length);
        mChoosePackageViewModel.getonClickStandardPackage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {
                showCustomChoosePackageDialog();
            }
        });



        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentChoosepackageBinding.rvChoosePackageList.setLayoutManager(llm);
        mChoosePackageViewModel.init();

        mChoosePackageViewModel.getPujaPackageList(subCategoryId).observe(getActivity(),ChoosePackageFragment.this::handlePujPackageList);


        mChoosePackageViewModel.getSelectedChoosePackageListItem().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {


                ChoosePackageListModel choosePackageListModel=mChoosePackageViewModel.mChoosePackageList.getValue().data.get(position);

                for(int i=0;i<choosePackageListModel.getPujaPrcdrList().size();i++){
                    if(i==0){
                        procedures =choosePackageListModel.getPujaPrcdrList().get(i).getPujaPrcdrDscr();
                    }else {
                        procedures = procedures + "," + choosePackageListModel.getPujaPrcdrList().get(i).getPujaPrcdrDscr();
                    }
                }

                for(int i=0;i<choosePackageListModel.getPujaSamagriDesdlvrList().size();i++){
                    if(i==0){
                        pujaSamagries =choosePackageListModel.getPujaSamagriDesdlvrList().get(i).getPujaSamagriDelvryDscr();
                    }else {
                        pujaSamagries = pujaSamagries + "," + choosePackageListModel.getPujaSamagriDesdlvrList().get(i).getPujaSamagriDelvryDscr();
                    }
                }

                for(int i=0;i<choosePackageListModel.getPujasamagriHHList().size();i++){
                    if(i==0){
                        Yajaman =choosePackageListModel.getPujasamagriHHList().get(i).getPujaSamagriHHDscr();
                    }else {
                        Yajaman = Yajaman + "," + choosePackageListModel.getPujasamagriHHList().get(i).getPujaSamagriHHDscr();
                    }
                }
                mDialogForChoosePackage.show();
                isAllSamagries=mChoosePackageViewModel.mChoosePackageList.getValue().data.get(position).getIsAllSamagriInclude();
                noOfPandit=mChoosePackageViewModel.mChoosePackageList.getValue().data.get(position).getNoOfPandit();
                 packageTypeIdDesc=mChoosePackageViewModel.mChoosePackageList.getValue().data.get(position).getPujaPkgTypeIdDscr();
                 pujaAmount= mChoosePackageViewModel.mChoosePackageList.getValue().data.get(position).getPujaPkgAmount().toString();
                 pujaDesc= mChoosePackageViewModel.mChoosePackageList.getValue().data.get(position).getPujaPkgDscr();
                    pujaPackageId=mChoosePackageViewModel.mChoosePackageList.getValue().data.get(position).getPujaPkgTypeId();
                mDialogChoosepackageDetailsBinding.tvStandardPackage.setText(mChoosePackageViewModel.mChoosePackageList.getValue().data.get(position).getPujaPkgTypeIdDscr());
                mDialogChoosepackageDetailsBinding.tvStandardAmount.setText(mChoosePackageViewModel.mChoosePackageList.getValue().data.get(position).getPujaPkgAmount().toString());
                mDialogChoosepackageDetailsBinding.tvPujaDesc.setText(mChoosePackageViewModel.mChoosePackageList.getValue().data.get(position).getPujaPkgDscr());
                mDialogChoosepackageDetailsBinding.tvPujaProcedureList.setText(procedures);
                mDialogChoosepackageDetailsBinding.tvPujaSamagriList.setText(pujaSamagries);
                mDialogChoosepackageDetailsBinding.tvYajamanNameList.setText(Yajaman);

                //For select background color change
                List<ChoosePackageListModel> listChoosePackage=mChoosePackageViewModel.mChoosePackageList.getValue().data;
                for(int i=0;i<listChoosePackage.size();i++){

                    if(i==position) {
                        listChoosePackage.get(position).isSelect=true;

                    }else{
                        listChoosePackage.get(position).isSelect=false;
                    }
                }
                mChoosePackageViewModel.setChoosePackageListAdapter(listChoosePackage);


            }
        });



        mChoosePackageViewModel.getonClickViewPackage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void _) {

                ChoosePackageFragmentDirections.ActionChoosePackageFragmentToBookingPujaFragment action=
                        ChoosePackageFragmentDirections.actionChoosePackageFragmentToBookingPujaFragment();
                action.setPujaName(pujaName);
                action.setAmount(pujaAmount);
                action.setPackageDesc(pujaDesc);
                action.setProcedure(procedures);
                action.setPujaSamagries(pujaSamagries);
                action.setYajaman(Yajaman);
                action.setPackageTypeIdDesc(packageTypeIdDesc);
                action.setSubCategoryName(subCategoryName);
                action.setSubCategoryId(subCategoryId);
                action.setPujaPackageId(pujaPackageId);
                action.setIsAllSamagries(isAllSamagries);
                action.setNoOfPandit(noOfPandit);
                Navigation.findNavController(mView).navigate(action);

            }
        });
    }


    private void showCustomChoosePackageDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = getActivity().findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_choosepackage, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();

       // EditText fragment_change_password_etOldPassword = dialogView.findViewById(R.id.fragment_change_password_etOldPassword);

        alertDialog.show();
    }

    private void handlePujPackageList(Resource<List<ChoosePackageListModel>> resource) {
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
                            List<ChoosePackageListModel> list = resource.data;
                            mChoosePackageViewModel.setChoosePackageListAdapter(list);
                            List<PujaPrcdr> listPujaPrcdr;
                            listPujaPrcdr = new ArrayList<>();

                            String pujapcdrList="";
                            for(int i=0;i<list.size();i++){
                                ChoosePackageListModel choosePackageListModel=list.get(i);
                                Log.e("listChoosePackageListModel----" + i, gson.toJson(choosePackageListModel));

                                for (int j = 0; j < list.get(i).getPujaPrcdrList().size(); j++) {
                                    PujaPrcdr pujaPrcdr=list.get(i).getPujaPrcdrList().get(j);
                                    pujapcdrList=list.get(i).getPujaPrcdrList().get(j).getPujaPrcdrDscr();
                                    pujaPrcdr.setPujaPrcdrDscr(pujapcdrList);
                                    listPujaPrcdr.add(pujaPrcdr);

                                }


                               // mDialogChoosepackageDetailsBinding.tvPujaNameList.setText(str);

                            }

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
